# Notification and Reminder System with Firebase Admin SDK and Nodemailer

## Overview
This project implements a notification and reminder system using Firebase Admin SDK and Nodemailer within an Express.js application. It includes functionalities for sending push notifications and email reminders to users based on certain criteria.

## Features
- **Push Notifications:** Sends push notifications to users with upcoming appointments.
- **Email Reminders:** Sends email reminders to patients about their prescriptions.
- **Firebase Admin SDK:** Manages user authentication and messaging services.
- **Nodemailer:** Handles sending emails through SMTP.

## Setup Instructions
1. Clone the repository and navigate to the project directory.
2. Install dependencies using `npm install`.
3. Replace placeholder values in the code with your actual credentials and configuration.
4. Start the server using `node app.js` or `npm start`.

## Usage
1. **Sending Notifications to Patients:**
    - Endpoint: `/send-notifications`
    - Method: POST
    - Description: Sends push notifications to patients with appointments scheduled for the next day.
    - Request Body: JSON object containing appointment details.
    - Response: JSON response indicating success or error status.

2. **Sending Reminders to Patients:**
    - Endpoint: `/send-reminders`
    - Method: POST
    - Description: Sends email reminders to patients about their prescriptions.
    - Request Body: JSON object containing prescription details.
    - Response: JSON response indicating success or error status.

## Configuration
1. **Firebase Admin SDK:**
    - Replace `serviceAccountKey.json` with your actual Firebase service account key file.
    - Update `databaseURL` with your Firebase Realtime Database URL.

2. **Nodemailer:**
    - Update SMTP server configuration in `app.js` with your email provider's details.
    - Replace `from` email address and authentication credentials.

3. **Express.js Server:**
    - Modify port number and other server configurations as needed in `app.js`.

## Dependencies
- express: ^4.17.2
- firebase-admin: ^9.10.0
- nodemailer: ^6.7.2

