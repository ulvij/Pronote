# **Pronote**

<p align="center">
    <img src="/preview/screenshot.png"/>
</p>

**Pronote** is a sample project that present a modern approach to [Android](https://en.wikipedia.org/wiki/Android_(operating_system)) application development with modern tech-stack.

The goal of the project is to demonstrate  the **Testing** of these technologies by using the best practices for developing and presenting modern Android application Architecture that is modular, scalable, maintainable, and testable

## **Project characteristics**

- 100% [Kotlin](https://kotlinlang.org/)
- MVVM architecture
- [Android Jetpack](https://developer.android.com/jetpack)
- Reactive UI
- Dependency Injection
- Material Design


## Tech-stack

Min API level is set to [21](https://android-arsenal.com/api?level=21#l21), so the presented approach is suitable for over 85% of devices running Android. This project takes advantage of many popular libraries and tools of the Android ecosystem. Most of the libraries are in the stable version unless there is a good reason to use non-stable dependency.

- **Tech-stack**
    * [Kotlin](https://kotlinlang.org/)
    * [Dagger](https://dagger.dev/) - dependency injection
    * [RxJava](https://github.com/ReactiveX/RxJava)-[RxAndroid](https://github.com/ReactiveX/RxAndroid) - deal with reactive use cases
    * [Retrofit](https://square.github.io/retrofit/) - networking
    * [Android Jetpack](https://developer.android.com/jetpack)
        * [Room](https://developer.android.com/topic/libraries/architecture/room) - keep entire data inside the application
        * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - deal with whole in-app navigation
        * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - notify views about database change
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way

- **Architecture**
    * MVVM Architecture
    * Android Architecture components([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/))

- **Testing**
    * Unit Test
      * [JUnit](https://junit.org/junit5/) - JUnit is a unit testing framework
      * [Robolectric](https://github.com/robolectric/robolectric) - Robolectric is the industry-standard unit testing framework for Android.
      * [Mockito](https://github.com/mockito/mockito) - Mockito is a mocking framework
      * [Truth](https://truth.dev/) - Truth is a library for performing assertions in tests
    * Instrumentation Test
      * [Espresso](https://developer.android.com/training/testing/espresso) -Espresso is an entire testing framework that allows you to test the UI of your Android app.


## License
```
Designed and developed by 2020 ulvij (Ulvi Jabbarli)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
