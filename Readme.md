# **Pronote**

Pronote is a sample project that present a modern approach to [Android](https://en.wikipedia.org/wiki/Android_(operating_system)) application development with up to date tech-stack

The goal of the project is to demonstrate best practices by using up to date tech-stack and presenting modern Android application Architecture that is modular, scalable, maintainable, and testable

## **Project characteristics**

- 100% [Kotlin](https://kotlinlang.org/)
- MVVM architecture
- [Android Jetpack](https://developer.android.com/jetpack)
- Reactive UI
- Dependency Injection
- Material Design


## Tech-stack

Min API level is set to [21](https://android-arsenal.com/api?level=21#l21), so the presented approach is suitable for over 85% of devices running Android. This project takes advantage of many popular libraries and tools of the Android ecosystem. Most of the libraries are in the stable version unless there is a good reason to use non-stable dependency.

- Tech-stack
    * [Kotlin](https://kotlinlang.org/)
    * [Dagger](https://dagger.dev/) - dependency injection
    * [Retrofit](https://square.github.io/retrofit/) - networking
    * [Android Jetpack](https://developer.android.com/jetpack)
        * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - deal with whole in-app navigation
        * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - notify views about database change
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
    * [Glide](https://bumptech.github.io/glide/) - image loading library
- Architecture
    * MVVM Architecture
    * Android Architecture components([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/))


## Authors

- __Ulvi Jabbarli__ - Android Developer

## License
```
MIT License

Copyright (c) 2020 Ulvi Jabbarli

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```