# FinalSpaceApp

**FinalSpaceApp** is an Android pet project to explore characters, locations, and favorite items from the **Final Space** universe. The application is fully written in **Kotlin** using **Jetpack Compose**, featuring local caching via **Room** (for “liked” characters) and navigation with **Compose Navigation**. It also comes with a modern **dark theme** UI.

<p align="center">
  <img src="https://github.com/user-attachments/assets/803dadb2-8eed-49ab-82c2-84467ef52234" width="30%" />
  <img src="https://github.com/user-attachments/assets/68e01cdf-4ebe-4fd2-b5f8-c09ff5e3020d" width="30%" />
  <img src="https://github.com/user-attachments/assets/d2018ed8-55bf-433a-aff6-3fdc0a66bdb7" width="30%" />
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/a6290437-a057-4028-9253-4d144f54bfa5" width="30%" />
  <img src="https://github.com/user-attachments/assets/ec7b4cb3-bd39-46e9-b1f1-d813d7eed67e" width="30%" />
  <img src="https://github.com/user-attachments/assets/7eef41d6-a163-4ddf-8664-358c3fb2ca2e" width="30%" />
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/01932c5c-c2f7-49dc-a124-54587f8ab72d" width="30%" />
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
