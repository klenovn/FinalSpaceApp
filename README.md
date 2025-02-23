# FinalSpaceApp

**FinalSpaceApp** is an Android pet project to explore characters, locations, and favorite items from the **Final Space** universe. The application is fully written in **Kotlin** using **Jetpack Compose**, featuring local caching via **Room** (for “liked” characters) and navigation with **Compose Navigation**. It also comes with a modern **dark theme** UI.

<p align="center">
  <img src="https://github.com/user-attachments/assets/f5abe45f-453d-45d6-889f-e15dbddec3bb" width="30%" />
  <img src="https://github.com/user-attachments/assets/3a026141-5301-4c90-978e-39c36d6ee57d" width="30%" />
  <img src="https://github.com/user-attachments/assets/0cad90e0-4906-4f9e-b46a-0778074c005a" width="30%" />
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/081e9a87-6667-4f12-b869-5262e511142d" width="30%" />
  <img src="https://github.com/user-attachments/assets/65fb83f0-2d76-4a8e-8964-04e99bd65a2b" width="30%" />
  <img src="https://github.com/user-attachments/assets/8dee6a4e-c7b9-4051-abd0-c4523f1e4097" width="30%" />
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/1f380360-06da-432b-ba8f-fa0d35c00371" width="30%" />
</p>

## Main Features

- **Character list**: Displays character cards with name, origin, and image.  
- **Location list**: Shows available locations from the Final Space universe.  
- **Favorites**: Allows you to “like” characters and easily find them in a separate tab.  
- **Detailed info**: Selecting any character or location opens a screen with detailed information.  
- **Dark theme**: The app defaults to a dark UI theme.  

## Tech Stack

1. **Kotlin**, **Jetpack Compose**  
   - Creates the UI using a declarative approach.
2. **Compose Navigation**  
   - Handles screens (CharacterList, CharacterDetail, Favourites, Locations, etc.) within a single Activity.
3. **Hilt (Dagger Hilt)**  
   - Dependency injection (DI) and easy testing (ViewModels, Repositories, Room).
4. **Retrofit + Kotlin Coroutines**  
   - Fetches data from a remote source (`FinalSpaceApi`) using asynchronous calls.
5. **Room**  
   - Caches favorite characters locally (via `CharacterEntity`).
6. **Coil**  
   - Loads images for characters and locations efficiently.

## Architecture & Modules

```bash
finalspaceapp
├── data
│   ├── local          # Room (Entity, DAO, Database)
│   ├── remote         # DTOs, Retrofit API
│   ├── mapper         # Converts DTOs to domain models and vice versa
│   └── repository     # Repository implementations (combining local and remote)
├── domain
│   ├── model          # Domain models (Character, Location, etc.)
│   └── repository     # Repository interfaces (contracts)
├── presentation
│   ├── characters        # Character list screen (CharactersScreen & ViewModel)
│   ├── character_detail  # Character detail screen
│   ├── favourites        # Favourites list screen
│   ├── locations         # Locations list screen
│   ├── location_detail   # Location detail screen
│   └── navigation        # NavGraph.kt with routes and composable destinations
├── di                 # Hilt modules (AppModule, etc.)
├── util               # Utility classes (ResourceState, FinalSpaceApplication)
└── ...
```

## Data Source

This application uses the public [Final Space API](https://finalspaceapi.com/) 
to fetch information about characters and locations from the Final Space universe.


>Disclaimer: All characters and materials belong to their respective owners.
