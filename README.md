# Metropolitan Museum of Art (MET) Gallery Android App (Artify)

This is an Android app that allows users to browse through the gallery of the Metropolitan Museum of Art (MET) using their public API. The app contains two screens: a search screen and a detail screen.

## Requirements

- The project must be able to compile and run on Android 7.0 and higher.
- The app should follow the official Material Design guidelines.
- The main language is Kotlin.
- The code is organized, efficient, readable, decoupled, and follows best practices.
- The ability to handle configuration changes is required.

## Features

### Search Screen:

- Search text field to enter the keyword.
- Once the search is triggered, displays the list of IDs of matching elements.
- Selecting one item should open the detail screen.

### Detail Screen:

- Consists of a primary image.
- Consists of an additional images gallery, if available.
- Contains an overview with detailed information about the selected item (e.g., department, object name, etc.).

### API:

- Use the following API to get the gallery of the Metropolitan Museum of Art: https://metmuseum.github.io/

## Architecture and Libraries:

- MVVM Architecture
- CLEAN Architecture
- Retrofit for API communication
- Coroutines for asynchronous programming
- Glide for image loading

## Installation:

1. Clone or download the repository.
2. Open the project in Android Studio.
3. Build and run the project.

## Code Quality:

- The code is structured following the MVVM and CLEAN architecture patterns.
- The code is well-commented, efficient, readable, and decoupled.
- The app uses Retrofit for API communication and Glide for image loading.
- Coroutines are used for asynchronous programming.
- The app has been tested for crashes and bugs.

## Layout:

- The app follows the official Material Design guidelines.
- The layout is clean and simple, without any fancy design.

## Challenge

The main challenge encountered during the development of this project was that "The Metropolitan Museum of Art" API does not provide any parameter to limit the number of results. This limitation has been discussed in detail in the following links:
- https://github.com/metmuseum/openaccess/issues/40
- https://metmuseum.github.io/

To overcome this challenge, I decided to implement a pagination logic in the app. The following steps were taken:
1. I called the API to search for the query.
2. All results were retrieved and saved in the PageManager.
3. Only the first 40 results were passed to the UI.
4. When the user scrolls to the end of the page, the next 40 results are fetched from the local database.
5. If the user searches for the same query again, the app checks the local database for any data beyond the first 40 results, and if there is none, it makes a new API call to fetch more data.
