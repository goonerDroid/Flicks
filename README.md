# Flicks 🎬

Flicks is a modern Android movie tracking application built to explore scalable Android architecture inspired by Google's **Now in Android** sample.

The goal of this project is to understand how large production Android apps are structured using modular architecture, reactive data flow, and offline-first design.

---

# Features

• Browse movies
• Add movies to a watchlist
• Mark movies as watched
• Track watch history
• Store user preferences
• Local movie recommendation engine *(planned)*

---

# Tech Stack

### Language

* Kotlin

### UI

* Jetpack Compose

### Architecture

* MVVM
* Unidirectional Data Flow

### Concurrency

* Kotlin Coroutines
* Kotlin Flow

### Dependency Injection

* Hilt

### Networking

* Retrofit

### Database

* Room

### Preferences

* DataStore

### Navigation

* Compose Navigation

### Image Loading

* Coil

---

# Architecture

The project follows a **modular architecture** inspired by Google's **Now in Android** sample.

```
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
```

---

# Module Dependency Direction

Feature modules depend only on core modules.
Core modules never depend on feature modules.

```
                app
                 │
        ┌────────┴────────┐
        │                 │
   feature-* modules   core-ui
        │
        ▼
      core-data
        │
   ┌────┴────┐
   ▼         ▼
core-network core-database
        │
        ▼
     core-model
```

This structure ensures:

• clear separation of concerns
• scalable feature development
• easier testing and maintainability

---

# Data Flow

The application follows an **offline-first data architecture**.

```
Network → Repository → Database → Flow → ViewModel → UI
```

### Flow Explanation

1. Data is fetched from the **TMDB API**
2. The **Repository** saves the data into **Room**
3. The UI observes **database flows**
4. The UI updates reactively whenever the database changes

This allows the app to:

• work offline
• survive process death
• avoid unnecessary network calls

---

# Data Source

Movie data is provided by the **TMDB API**.

https://www.themoviedb.org/

---

# Future Work

• Local recommendation engine
• Content-based movie recommendations
• Collaborative filtering experiments
• Movie rating system
• Watch analytics and insights

---

# Learning Goals

This project focuses on learning modern Android engineering practices:

• Modular architecture
• Reactive programming with Kotlin Flow
• Offline-first data strategies
• Separation of domain, network, and database layers
• Scalable feature modules

---

# Inspiration

Architecture inspired by:

**Now in Android**

https://github.com/android/nowinandroid

---

# License

MIT
