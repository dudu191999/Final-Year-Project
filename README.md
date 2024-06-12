**Project Description: Guide key App**

**The Guide Key app** is designed to be a digital guide for visitors and residents to explore Sao Tome and Principe (currently using MyCity app an example). 
It provides information about the city's history, culture, and culinary scene.  
The app is built using modern Android development technologies and features an intuitive user interface.


 





**How It Works**

**Splash Screen:** Welcomes users with a visually appealing logo and the city's name.
**Home Screen:** Presents a featured image of the city and category cards (History, Culture, Food) that lead to detailed sections.
**Category Screens (History, Culture, Food):** Each screen showcases images and text about the chosen aspect of the city. A gallery component is used to display multiple images.
**Onboarding Screen:** Introduces users to the app's features through a carousel of images.
**Tour Guide Screen:** Incorporates QR code functionality. Users can generate a QR code that potentially links to a tour guide service.
**Contact Us Screen:** Provides contact information for the city's tourism department or relevant authorities.


**Technologies Used**

**Jetpack Compose:** A modern UI toolkit for building native Android apps. It simplifies and accelerates UI development by using a declarative approach.
**Navigation Component:** Manages navigation between different screens within the app.
**Material Design 3:** Provides a set of design guidelines and pre-built UI components for a consistent and visually appealing user experience.
**QR Code Generation (ZXing Library):** Generates QR codes on the fly to store URLs or other information.
**Coroutines:** Used for managing asynchronous tasks, especially when loading images or generating QR codes.
**ViewModel:** Handles the UI state and data persistence across configuration changes (screen rotations, etc.).

**Data Structures**

**Data Classes:**
**BottomBarItem:** Holds information about each item in the bottom navigation bar (title, icon, route).
**CategoryItem:** Represents each category on the home screen (title, image resource ID).
**MyCityUiState:** Stores the current UI state of the app, including QR code bitmaps.


**bottomBarItems:** A list of BottomBarItem objects defining the navigation bar items.
**categories:** A list of CategoryItem objects for the home screen category cards.
**images:** Lists of image resource IDs for the gallery components.






