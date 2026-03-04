# Flicks 🎬

Flicks is a modern Android movie tracking application built to explore scalable Android architecture inspired by Google's **Now in Android** sample.

The goal of this project is to understand how large production Android apps are structured using modular architecture, reactive data flow, and offline-first design.

---

## Features

- Browse movies
- Add movies to watchlist
- Mark movies as watched
- Track watch history
- Store user preferences
- Local recommendation engine (future)

---

## Tech Stack

Language
- Kotlin

UI
- Jetpack Compose

Architecture
- MVVM
- Unidirectional Data Flow

Concurrency
- Kotlin Coroutines
- Kotlin Flow

Networking
- Retrofit

Database
- Room

Dependency Injection
- Hilt

Preferences
- DataStore

Navigation
- Compose Navigation

Image Loading
- Coil

---

## Architecture

The project follows a **modular architecture** inspired by the Now in Android sample.
`
app
│
├── feature-movies
├── feature-watchlist
├── feature-watched
├── feature-recommendations
│
├── core-data
├── core-database
├── core-network
├── core-model
└── core-ui
`

### Data Flow

Network → Repository → Database → Flow → ViewModel → UI

The UI observes database flows rather than directly calling network APIs.

This ensures the application works **offline-first**.

---

## Data Source

Movie data is provided by **TMDB API**.

https://www.themoviedb.org/

---

## Future Work

- Recommendation engine using content-based filtering
- Collaborative filtering experiments
- Movie rating system
- Offline recommendation model

---

## Learning Goals

This project focuses on learning:

- Modular Android architecture
- Clean separation of concerns
- Reactive programming with Flow
- Offline-first data strategy
- Scalable feature modules

---

## Inspiration

Architecture inspired by:

**Now in Android**
https://github.com/android/nowinandroid

---

## License

MIT