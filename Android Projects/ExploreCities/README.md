# Explore Cities Android Application

## Description
The "Explore Cities" Android application is designed to provide information about different cities across various continents. It utilizes Android Studio and incorporates multiple activities and a fragment to achieve its functionalities.

## Objectives
The main objectives of this project are:
- Practice using Android Studio and developing Android Applications.
- Understand how to create and manage multiple activities in an app.
- Learn how to use ListViews and RecyclerViews to display lists of data.
- Understand how to design and use Fragments in an activity.
- Understand how to use CardViews to create complex list items.
- Learn how to pass data between activities using Intents and between an activity and a fragment using arguments.

## Functionalities
The application consists of the following functionalities:

### 1. MainActivity
- Utilizes a ListView to display a list of continents.
- Allows users to select a continent, which then navigates to the ContinentActivity.

### 2. ContinentActivity
- Hosts a RecyclerView populated with CardViews representing cities from the chosen continent.
- Each CardView displays the name of the city and an image.
- Selection of a city navigates to the CityInfoFragment within the CityInfoActivity.

### 3. CityInfoActivity
- Hosts the CityInfoFragment.

### 4. CityInfoFragment
- Displays detailed information about the selected city from the ContinentActivity.
- Includes an ImageView for a city picture and multiple TextViews for city details such as population, language spoken, etc.

### Data Passing
- Activities pass data like chosen continent and city using Intents.
- CityInfoActivity passes data to CityInfoFragment via arguments.

## Implementation
The project implementation includes:
- Use of ListView and RecyclerView for list display.
- Utilization of CardView for each city item.
- Implementation of CityInfo Activity and Fragment for detailed city information.

## Project Structure
The project follows a structured layout with clear separation of activities and fragments for seamless navigation and data presentation.

## Requirements
- Android Studio
- Basic knowledge of Kotlin programming and Android app development

## Usage
1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the application on an Android emulator or physical device.
4. Explore the different continents, cities, and detailed city information within the app.

## Credits
This application was developed as part of the CSCI 4176/CSCI 5708 Mobile Computing course at [University Name]. Credits to the course instructors and materials for the project specifications and guidance.
