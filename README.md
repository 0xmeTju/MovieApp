# Movie App

This is a simple movie search application that allows users to search for movies using the OMDB API. The app follows modern Android architecture practices, such as the **MVVM** pattern. Using **Hilt** for dependency injection, **StateFlows** for managing UI state, and **Coroutines** for asynchronous tasks.

## Architecture

This project follows the architecture recommended by Google, using the **MVVM** pattern:

- **Model**: Handles the business logic of the app, interacting with the OMDB API.
- **ViewModel**: Provides data to the UI (View) and manages the UI-related logic. The `ViewModel` exposes states through `StateFlow` and manages asynchronous operations via Kotlin **Coroutines**.
- **View**: The Composable UI, which reacts to changes in the state provided by the `ViewModel`.

This approach ensures a clear separation of concerns, making the code more maintainable and testable.

### Dependency Injection

The project uses **Hilt** for dependency injection. Hilt simplifies dependency injection by reducing the boilerplate code. It injects the necessary objects (such as the `ApiService`) into the `ViewModel` and repository classes.

---

## Handling the API Key
The app retrieves data from the OMDB API. The API key is sensitive and should not be hardcoded directly so you will need to insert your own key.

### Storing API Key in `gradle.properties`
The API Key is stored in `gradle.properties`, before building the project, you need to enter you API Key there.

#### Add the API Key to `gradle.properties`
Go to the file and add you own OMDB API key:

   ```properties 
   OMDB_API_KEY=enter_your_api_key
```
**After** inserting your own API Key, **build** the project.

---
## Running the Project

1. Clone the repository.
2. Add your OMDB API key to the `gradle.properties` file
3. Build and run the project inside the Android Studio(gradle version 8.6 supported)

## Libraries Used
- **Hilt**: For dependency injection.
- **Retrofit**: For making HTTP requests to the OMDB API.
- **Coroutines**: For managing background tasks.
- **StateFlow**: For managing and observing UI state changes.
- **Jetpack Compose**: For building the UI.
- **Gson**: For parsing JSON responses from the API.
