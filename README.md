# NASA-APOD (Astronomy Picture of the Day)
This is a sample android application, which shows hardcoded list of [APOD](https://apod.nasa.gov/apod/astropix.html) to user.

SDK levels supported 
--------------

- Minimum SDK 23
- Target SDK 30

Built With 🛠
--------------

- [Kotlin](https://kotlinlang.org/) - Officially supported programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
- [ViewBinding](https://developer.android.com/topic/libraries/view-bindingg) - Generates a binding class for each XML layout.
- [Navigation](https://developer.android.com/guide/navigation) - For naviation between activities and fragments.
- [ViewPager2](https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2) - Layout manager that allows the user to flip left and right through pages of data.
- [Hilt](https://dagger.dev/hilt/) - Dependency Injection Framework
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
- [Coil](https://coil-kt.github.io/coil/) - To work with images.
- [Moshi](https://github.com/square/moshi) - Moshi is a modern JSON library for Android and Java. To ease the process of parsing JSON
- [ThreeTenABP](https://github.com/JakeWharton/ThreeTenABP) - An adaptation of the JSR-310 backport for Android.

Package structure
-----------------
```
app/src/main/java/com/example/nasaapod
├── data                                # Data layer
│   ├── model                           # Models represented in data class
│   └── repository                      # Repository classes which acts as a single source of truth for the ViewModel
├── di                                  # Dependency Injection setup
├── ui                                  # UI layer
│   ├── details                         # UI for details screen
│   └── home                            # UI for home screen
└── utils                               # Helper functions and classes
```

Miscellaneous Information
----------------

This app is written while keeping in mind the [MVVM architecture](https://developer.android.com/jetpack/guide), there are Unit test written for ViewModel and Repository Layer, the UI layer is untested due to my lack of knowledge in the specific domain.
