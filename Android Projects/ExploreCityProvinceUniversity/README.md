# Province and City Explorer Android Application

## Description
The "Province and City Explorer" Android application is designed to allow users to explore a province and its cities. It includes features such as login functionality, province information display, city exploration, and navigation options.

## Objectives
The main objectives of this assignment are:
- Create a login screen as the entry point of the application.
- Implement a main page with navigation options for exploring the province and its cities.
- Utilize ViewPager and TabView for displaying province information and city details.
- Enhance user experience with navigation toolbar and background images.

## Functionalities
The application consists of the following functionalities:

### 1. LoginActivity
- Acts as the entry point with a simple login screen.
- Grants access to the MainActivity upon successful login (hardcoded username and password).

### 2. MainActivity
- Includes a NavigationToolbar with options for "Province Info" and "Cities".
- Displays an image of the selected country as the background.

### 3. ProvinceInfoActivity
- Utilizes a ViewPager to display different views or fragments of province information.
- Accessed when the user selects "Province Info" from the NavigationToolbar.

### 4. CitiesActivity
- Contains a TabView with three tabs, each representing a city in the province.
- Displays a picture of the corresponding city in each tab.
- Accessed when the user selects "Cities" from the NavigationToolbar.

### Optional Enhancements
- Additional options in the navigation panel or improved design may earn bonus points.

## Implementation
The project implementation includes:
- Design and implementation of LoginActivity, MainActivity, ProvinceInfoActivity, and CitiesActivity.
- Integration of ViewPager and TabView for content display.
- NavigationToolbar setup for easy navigation within the app.
- Background image inclusion for aesthetic appeal.

## Requirements
- Android Studio
- Basic knowledge of Kotlin programming and Android app development

## Usage
1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the application on an Android emulator or physical device.
4. Log in with the hardcoded credentials to explore the province and its cities.

## Credits
This application was developed as part of the CSCI 4176/CSCI 5708 Mobile Computing course . Credits to the course instructors and materials for the project specifications and guidance.
