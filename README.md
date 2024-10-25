### Main Features of the App:

<img src="TodoAndroid_git.gif" alt="TodoAppDemo" width="300" height="600"/>

- **Multi-functional To-Do List**: Users can add, edit, delete, and mark tasks as completed or unfinished.
- **Filtering Capabilities**: Easily filter tasks based on their completion status (completed or incomplete).
- **Local Data Storage**: Tasks are stored locally using Room Database, ensuring persistence even after app closure.
- **Modern Development Practices**: The app uses the MVVM (Model-View-ViewModel) architecture to cleanly separate UI and business logic.
- **CI/CD Integration**: Continuous Integration and Continuous Deployment are managed via GitHub Actions, automating APK packaging.

### Technologies/Libraries Used:
- **Kotlin**: 2.0.20
- **Android Jetpack Compose**: Used for UI creation (Compose version: 2024.09.03).
- **Room Database**: 2.6.1, for local persistence.
- **Coroutines**: 1.3.9, for managing asynchronous tasks.
- **Dagger-Hilt**: 2.52, for dependency injection.
- **Testing Libraries**:
  - JUnit: 4.13.2 for unit testing.
  - Turbine: 1.2.0 for testing flows.
  - MockK: 1.13.13 for mock testing.
- **GitHub Actions**: Integrated for CI/CD to automate APK packaging.

### Project Structure:
The app is structured based on the **MVVM architecture**, which organizes the code into clear layers:
1. **Model**: Represents the data layer and interacts with the Room database.
2. **ViewModel**: Contains the app’s logic and exposes live data to the UI.
3. **View (UI)**: Built using Jetpack Compose, the UI layer observes the ViewModel and reacts to data changes.
4. **Repository**: Acts as the middle layer between the ViewModel and the database, handling data operations.

