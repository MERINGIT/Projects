const express = require('express');
const admin = require('firebase-admin');
const nodemailer = require('nodemailer');

const app = express();
const PORT = 3000; // You can change the port if needed

// Initialize Firebase Admin SDK
const serviceAccount = require('./healthsync-3712d-firebase-adminsdk-wuvhz-47bda7928d.json'); // Path to your service account key JSON file
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://healthsync-3712d-default-rtdb.firebaseio.com"
});

// Initialize Nodemailer transporter
const transporter = nodemailer.createTransport({
  host: 'smtp.gmail.com', // Specify your SMTP server
  port: 587, // Specify the port (usually 587 for TLS or 465 for SSL)
  secure: false, // true for 465, false for other ports
  auth: {
    user: 'health.sync19@gmail.com', // Your email address
    pass: 'ivtueflxuuogkxeo', // Your email password or application-specific password
  },
});

// Middleware to parse JSON bodies
app.use(express.json());
// Initialize Firestore database
const db = admin.firestore();

// Function to get the next day's date in the format "MM/DD/YYYY" with ADT timezone
function getNextDayDate() {
  const today = new Date();
  const nextDay = new Date(today);
  nextDay.setDate(today.getDate() + 1);

  // Options for formatting the date string
  const options = {
    month: '2-digit',
    day: '2-digit',
    year: 'numeric',
    timeZone: 'America/Halifax', // ADT timezone
  };

  // Format the date using toLocaleString method with options
  const formattedDate = nextDay.toLocaleString('en-US', options);
  return formattedDate;
}


// Async function to send push notification
async function sendPushNotification(pushMessage, fcmTokens) {
  try {
    await admin.messaging().sendMulticast(pushMessage);
    console.log('Push notification sent successfully to FCM Tokens:', fcmTokens);
  } catch (error) {
    console.error('Error sending push notification:', error);
  }
}

// Async function to send email notification
async function sendEmail(mailOptions) {
  try {
    await transporter.sendMail(mailOptions);
    console.log('Email notification sent successfully to:', mailOptions.to);
  } catch (error) {
    console.error('Error sending email notification:', error);
  }
}

// API endpoint to send notifications to patients with appointments on the next day
app.post('/send-notifications', async (req, res) => {
  try {
    const targetDate = getNextDayDate();
    console.log('Date of Tomorrow:', targetDate); // Print the date of tomorrow

    const db = admin.firestore();
    const appointmentsRef = db.collection('appointments');
    const querySnapshot = await appointmentsRef.where('date', '==', targetDate).get();

    if (querySnapshot.empty) {
      console.log('No appointments found for the next day.');
      return res.status(404).json({ status: 'error', message: 'No appointments found for the next day.' });
    }

    console.log('Total documents in querySnapshot:', querySnapshot.size);

    const notifications = [];

    for (const doc of querySnapshot.docs) {
      const appointmentData = doc.data();
      const patientId = appointmentData.patient_id;

      const patientsRef = db.collection('patients');
      const patientsSnapshot = await patientsRef.where('patient_id', '==', patientId).get();

      if (patientsSnapshot.empty) {
        console.log(`No patient found with ID ${patientId}`);
        continue; // Move to the next appointment if patient not found
      }
      console.log(`patient found with ID ${patientId}`);
      const patientData = patientsSnapshot.docs.map((doc) => doc.data()); // Get all patient data
      const fcmTokens = patientData.map((patient) => patient.token); // Extract FCM tokens
      
      if (!fcmTokens || fcmTokens.length === 0 || fcmTokens.some(token => token === undefined)) {
        console.log('FCM Tokens are undefined or empty for patient ID:', patientId);
        continue; // Skip processing this appointment and move to the next one
      }

      console.log('FCM Tokens:', fcmTokens); // Print the FCM tokens

// Process the appointment using fcmTokens...

      const doctorsRef = db.collection('doctors');
      const doctorId = appointmentData.doctor_id;
      const doctorSnapshot = await doctorsRef.where('doctor_id', '==', doctorId).get();

      if (doctorSnapshot.empty) {
        console.log(`No doctor found with ID ${doctorId}`);
        continue; // Move to the next appointment if doctor not found
      }

      const doctorData = doctorSnapshot.docs[0].data();
      const doctorName = doctorData.doctor_info.name;

      const pushMessage = {
        notification: {
          title: 'Upcoming Appointment',
          body: `Your appointment is scheduled for tomorrow with Dr. ${doctorName}.`,
        },
        tokens: fcmTokens, // Use 'tokens' instead of 'token' for multiple recipients
      };

      // Add a delay before processing the next patient's notification
      await new Promise(resolve => setTimeout(resolve, 2000)); // Adjust the delay time as needed (e.g., 2000 milliseconds)

      // Send push notification using fcmTokens array directly
      notifications.push(sendPushNotification(pushMessage,fcmTokens)); // Removed fcmTokens argument

      const mailOptions = {
        from: 'health.sync19@gmail.com',
        to: patientData.map((patient) => patient.email).join(','), // Comma-separated list of emails
        subject: 'Upcoming Appointment',
        text: `Your appointment is scheduled for tomorrow with Dr. ${doctorName}.`,
      };

      notifications.push(sendEmail(mailOptions));
    }

    await Promise.all(notifications);
    res.status(200).json({ status: 'success', message: 'Notifications sent successfully.' });
  } catch (error) {
    console.error('Error sending notifications:', error);
    res.status(500).json({ status: 'error', message: 'Error sending notifications.' });
  }
});


// API endpoint to send reminders to patients
// API endpoint to send reminders to patients
app.post('/send-reminders', async (req, res) => {
  try {
    const prescriptions = await getListOfPrescriptions();

    const currentDate = new Date();
    const dateFormat = new Intl.DateTimeFormat('en-US', { month: '2-digit', day: '2-digit', year: 'numeric' });

    for (const prescription of prescriptions) {
      const appointmentId = prescription.appointment_id;

      const appointmentRef = db.collection('appointments');
      const appointmentSnapshot = await appointmentRef.where('appointment_id', '==', appointmentId).get();
      let maxDays = 0;

      for (const medicine of Object.values(prescription.medicines)) {
        if (medicine.number_of_days > maxDays) {
          maxDays = medicine.number_of_days;
        }
      }
      console.log(maxDays);

      for (const doc of appointmentSnapshot.docs) {
        const appointment = doc.data();

        // Check if appointment object has required fields
        if (!appointment.date || !appointment.patient_id) {
          continue;
        }

        const appointmentTimestamp = new Date(appointment.date); // Parse timestamp string into Date object
        const appointmentDate = dateFormat.format(appointmentTimestamp);
        let daysDifference = Math.ceil((currentDate.getTime()-appointmentTimestamp.getTime()) / (1000 * 60 * 60 * 24));
        if (Object.is(daysDifference, -0)) {
          daysDifference = 0;
        }
        console.log('Processing appointment:', appointmentId);
        console.log('Appointment date:', appointmentDate);
        console.log('Days difference:', daysDifference);

        if (daysDifference >= 0 && daysDifference <= maxDays) {
          const patientId = appointment.patient_id;


          console.log('Patient ID:', patientId);

          // Fetch the token from the patients table using patient ID
          const fcmToken = await getFCMTokenByPatientId(patientId);
          if (!fcmToken) {
            console.log('FCM Token not found for patient:', patientId);
            // Skip processing this appointment and move to the next one
            continue;
          }

          console.log('FCM Token:', fcmToken);

          // Send reminder to patient using the fetched FCM token
          const sendResult = await sendReminderToPatient(fcmToken, prescription.prescription_id);
          console.log(sendResult.message);
        }
      }
    }

    res.status(200).json({ status: 'success', message: 'Reminders sent successfully.' });
  } catch (error) {
    console.error('Error sending reminders:', error);
    res.status(500).json({ status: 'error', message: 'Error sending reminders.' });
  }
});


// Function to fetch the list of prescriptions
async function getListOfPrescriptions() {
  const prescriptionsSnapshot = await db.collection('prescriptions').get();
  const prescriptions = [];
  prescriptionsSnapshot.forEach((doc) => {
    prescriptions.push(doc.data());
  });
  return prescriptions;
}

// Function to fetch FCM token by patient ID
async function getFCMTokenByPatientId(patientId) {
  try {
    const patientsRef = db.collection('patients');
    const querySnapshot = await patientsRef.where('patient_id', '==', patientId).get();

    if (!querySnapshot.empty) {
      const patientDoc = querySnapshot.docs[0]; // Assuming patient_id is unique
      const patientData = patientDoc.data();
      return patientData.token;
    } else {
      return null; // No patient document found with the specified patient_id
    }
  } catch (error) {
    console.error('Error fetching FCM token by patient ID:', error);
    return null; // Return null in case of an error
  }
}

// Function to send reminder to patient using FCM token and include prescription ID
async function sendReminderToPatient(fcmToken, prescriptionId) {
  const message = {
    notification: {
      title: 'Reminder',
      body: 'Did you take your medicine as prescribed.',
    },
    data: {
      sendReminder: 'medicine',
      instructions: 'Take 1 tablet after breakfast and 1 tablet before bedtime.',
      prescriptionId: prescriptionId.toString(), // Include prescription ID in data payload
    },
    token: fcmToken,
  };

  try {
    const response = await admin.messaging().send(message);
    console.log('Notification sent successfully:', response);
    return { status: 'success', message: 'Notification sent successfully.' };
  } catch (error) {
    console.error('Error sending notification:', error);
    return { status: 'error', message: 'Error sending notification.' };
  }
}


// Start the server
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});

//       let maxNumberOfDays = 0;
    //       let medicineWithMaxDays = '';

    //     for (const medicineKey in prescription.medicines) {
    //     const medicine = prescription.medicines[medicineKey];
    //     console.log(medicine.name); // Log the medicine name

    //     if (medicine.number_of_days > maxNumberOfDays) {
    //         maxNumberOfDays = medicine.number_of_days;
    //         medicineWithMaxDays = medicine.name;
    //     }
    // }

// console.log(`Medicine with maximum number of days: ${medicineWithMaxDays}`);
// console.log(`Maximum number of days: ${maxNumberOfDays}`);
