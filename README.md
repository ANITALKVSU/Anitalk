# AniTalk Android Studio Prototype (Drop-In Version)

This is a **running Jetpack Compose prototype** for your AniTalk presentation.
It is designed to be the fastest way to get something working in Android Studio.

## What this prototype includes
- Welcome screen
- Login screen
- Sign-up screen
- Home screen with top tabs:
  - Trending Now
  - Create a Post
- Bottom navigation:
  - Profile
  - Messenger
  - Home
  - Events
  - Settings
- Mock anime trends, mock posts, mock messages, mock events, and a profile page

## Fastest setup in Android Studio
1. Open **Android Studio**.
2. Create a new project:
   - Template: **Empty Activity**
   - Language: **Kotlin**
   - Minimum SDK: **24** or higher
3. Let Android Studio finish building the starter project.
4. Replace the generated files with the files from this folder:
   - `app/build.gradle.kts`
   - `app/src/main/AndroidManifest.xml`
   - everything inside `app/src/main/java/com/example/anitalkprototype/`
   - everything inside `app/src/main/res/values/`
5. Make sure your package name is:
   - `com.example.anitalkprototype`
6. Click **Sync Now**.
7. Run the app on the emulator.

## Presentation notes
This is a prototype, so it uses local mock data only.
That is okay for a class presentation because it demonstrates:
- user flow
- navigation
- screen layout
- core features
- overall product vision

## Suggested talking points
- "We focused on a clickable prototype that proves the user journey works."
- "Our next phase would connect authentication, posts, chat, and events to Firebase or another backend."
- "For now, the prototype is intentionally lightweight so it runs smoothly for demo day."

## Important
If Android Studio generated a different package name, either:
- rename the package to `com.example.anitalkprototype`, or
- use Find/Replace on the Kotlin files to match your package.
