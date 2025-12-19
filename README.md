# Offline Music Player

A beautiful, functional, and feature-rich offline Android music player application with modern Material Design UI.

## Features

✨ **Beautiful UI**
- Modern Material Design 3 interface
- - Smooth animations and transitions
  - - Dark and light theme support
    - - Responsive layouts
     
      - 🎵 **Music Playback**
      - - Play local MP3, WAV, FLAC, and OGG audio files
        - - Shuffle and repeat modes
          - - Seek functionality with progress bar
            - - Volume control
              - - Currently playing song display
               
                - 📋 **Playlist Management**
                - - Browse device music library
                  - - Create and manage playlists
                    - - Sort songs by name, artist, album
                      - - Quick add/remove from playlist
                        - - Persistent playlist storage
                         
                          - 🎨 **Interactive Features**
                          - - Real-time audio visualizer
                            - - Now playing screen
                              - - Song queue display
                                - - Album art display
                                  - - Swipe gestures for navigation
                                    - - Search and filter songs
                                     
                                      - ## Tech Stack
                                     
                                      - - **Language**: Kotlin
                                        - - **Architecture**: MVVM with Jetpack
                                          - - **UI**: Jetpack Compose
                                            - - **Database**: Room Database
                                              - - **Audio**: Android MediaPlayer API
                                                - - **Build System**: Gradle
                                                 
                                                  - ## Project Structure
                                                 
                                                  - ```
                                                    OfflineMusicPlayer/
                                                    ├── app/
                                                    │   ├── src/
                                                    │   │   ├── main/
                                                    │   │   │   ├── java/com/musicplayer/
                                                    │   │   │   │   ├── MainActivity.kt
                                                    │   │   │   │   ├── ui/
                                                    │   │   │   │   ├── viewmodel/
                                                    │   │   │   │   ├── repository/
                                                    │   │   │   │   ├── database/
                                                    │   │   │   │   └── util/
                                                    │   │   │   └── res/
                                                    │   │   └── test/
                                                    │   ├── build.gradle
                                                    │   └── AndroidManifest.xml
                                                    ├── .github/
                                                    │   └── workflows/
                                                    │       ├── build.yml
                                                    │       └── release.yml
                                                    ├── build.gradle
                                                    ├── settings.gradle
                                                    ├── gradle.properties
                                                    └── README.md
                                                    ```

                                                    ## Building the Project

                                                    ### Prerequisites
                                                    - Android Studio 2024.1 or higher
                                                    - - Java 17 or higher
                                                      - - Android SDK 21 or higher (target SDK 35)
                                                       
                                                        - ### Local Build
                                                        - ```bash
                                                          # Clone the repository
                                                          git clone https://github.com/aiprouser7/OfflineMusicPlayer.git
                                                          cd OfflineMusicPlayer

                                                          # Build debug APK
                                                          ./gradlew assembleDebug

                                                          # Build release APK
                                                          ./gradlew assembleRelease

                                                          # Install APK
                                                          adb install app/build/outputs/apk/debug/app-debug.apk
                                                          ```

                                                          ## CI/CD Pipeline

                                                          This project uses GitHub Actions for automated builds and releases.

                                                          ### Workflows

                                                          1. **Build Workflow** (`build.yml`)
                                                          2.    - Triggers on: Push to main branch, Pull Requests
                                                                -    - Builds debug APK
                                                                     -    - Runs unit tests
                                                                          -    - Uploads APK as artifact
                                                                           
                                                                               - 2. **Release Workflow** (`release.yml`)
                                                                                 3.    - Triggers on: Version tag push (v*)
                                                                                       -    - Builds optimized release APK
                                                                                            -    - Creates GitHub release
                                                                                                 -    - Uploads APK as release asset
                                                                                                  
                                                                                                      - ### Getting Started with CI/CD
                                                                                                  
                                                                                                      - 1. Push code to repository:
                                                                                                        2. ```bash
                                                                                                           git push origin main
                                                                                                           ```
                                                                                                           
                                                                                                           2. Create a release tag:
                                                                                                           3. ```bash
                                                                                                              git tag -a v1.0.0 -m "Release version 1.0.0"
                                                                                                              git push origin v1.0.0
                                                                                                              ```
                                                                                                              
                                                                                                              3. Check Actions tab for build status
                                                                                                              4. 4. Download APK from release or artifacts
                                                                                                                
                                                                                                                 5. ## Installing the APK
                                                                                                                
                                                                                                                 6. ### From GitHub Release
                                                                                                                 7. 1. Go to Releases page
                                                                                                                    2. 2. Download the latest APK
                                                                                                                       3. 3. Install on Android device: `adb install app.apk`
                                                                                                                         
                                                                                                                          4. ### From GitHub Actions Artifacts
                                                                                                                          5. 1. Go to Actions tab
                                                                                                                             2. 2. Select the latest successful workflow run
                                                                                                                                3. 3. Download APK from artifacts
                                                                                                                                  
                                                                                                                                   4. ## Permissions Required
                                                                                                                                  
                                                                                                                                   5. - `READ_EXTERNAL_STORAGE` - Read music files
                                                                                                                                      - - `WRITE_EXTERNAL_STORAGE` - Cache management (Android 10+)
                                                                                                                                        - - `INTERNET` - Future features (disabled for offline-first design)
                                                                                                                                         
                                                                                                                                          - ## Development
                                                                                                                                         
                                                                                                                                          - ### Code Style
                                                                                                                                          - - Kotlin naming conventions
                                                                                                                                            - - 4-space indentation
                                                                                                                                              - - Documentation for public APIs
                                                                                                                                               
                                                                                                                                                - ### Testing
                                                                                                                                                - - Unit tests for ViewModels
                                                                                                                                                  - - Instrumented tests for UI
                                                                                                                                                    - - Integration tests for database
                                                                                                                                                     
                                                                                                                                                      - ### Contributing
                                                                                                                                                      - 1. Fork the repository
                                                                                                                                                        2. 2. Create a feature branch
                                                                                                                                                           3. 3. Commit your changes
                                                                                                                                                              4. 4. Push to the branch
                                                                                                                                                                 5. 5. Create a Pull Request
                                                                                                                                                                   
                                                                                                                                                                    6. ## License
                                                                                                                                                                   
                                                                                                                                                                    7. This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
                                                                                                                                                                   
                                                                                                                                                                    8. ## Screenshots
                                                                                                                                                                   
                                                                                                                                                                    9. Coming soon!
                                                                                                                                                                   
                                                                                                                                                                    10. ## Troubleshooting
                                                                                                                                                                   
                                                                                                                                                                    11. ### APK Installation Failed
                                                                                                                                                                    12. - Ensure device has sufficient storage
                                                                                                                                                                        - - Install from trusted sources only
                                                                                                                                                                          - - Check Android version compatibility
                                                                                                                                                                           
                                                                                                                                                                            - ### App Crashes on Startup
                                                                                                                                                                            - - Grant required permissions
                                                                                                                                                                              - - Check Android logs: `adb logcat`
                                                                                                                                                                                - - Clear app cache and data
                                                                                                                                                                                 
                                                                                                                                                                                  - ## Support
                                                                                                                                                                                 
                                                                                                                                                                                  - For issues, feature requests, or questions:
                                                                                                                                                                                  - 1. Check existing GitHub issues
                                                                                                                                                                                    2. 2. Create a new issue with detailed information
                                                                                                                                                                                       3. 3. Include device model and Android version
                                                                                                                                                                                         
                                                                                                                                                                                          4. ## Changelog
                                                                                                                                                                                         
                                                                                                                                                                                          5. ### v1.0.0
                                                                                                                                                                                          6. - Initial release
                                                                                                                                                                                             - - Basic playback functionality
                                                                                                                                                                                               - - Playlist management
                                                                                                                                                                                                 - - Beautiful UI with Material Design 3
                                                                                                                                                                                                   - - GitHub Actions CI/CD
                                                                                                                                                                                                    
                                                                                                                                                                                                     - ---
                                                                                                                                                                                                     
                                                                                                                                                                                                     **Built with ❤️ for music lovers everywhere**
