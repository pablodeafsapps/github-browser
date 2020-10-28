# Github Browser

<img src="docs/images/github-logo.png" alt="Github logo" width="200">

**Github Browser** is a sample baseline project which aims to show a standard state-of-the-art proposal for Android development.


## Installation
Clone this repository and import into **Android Studio**

##### Clone with SSH
```bash
> git clone git@github.com:pablodeafsapps/github-browser.git
```

##### Clone with HTTPS
```bash
> git clone https://github.com/pablodeafsapps/github-browser.git
```

##### Configure Project
In order to synchronize, build, and run the application, you need to create a `keystore.properties` file in the project root. This mandatory file must have the following structure:

```
storeFile=storeFile
storePassword=storePassword
keyAlias=keyAlias
keyPassword=keyPassword
```

The values of the above fields are irrelevant for _debug_ mode. However, you need the actual values of your Google account when on _release_ mode.


## Generating signed APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(you only need to do this once manually and then let Android Studio remember it)*


## Architecture and project organization
To This app makes employs a class hierarchy based on the [**Clean Architecture**](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) paradigm, a concept with an increasing popularity thanks to Robert C. Martin (Uncle Bob).

<img src="docs/images/clean-architecture-cejas-1.png" alt="Clean Architecture" width="500">

### Class hierarchy
Among the different implementations for Android applications of the aforementioned paradigm, there are remarkable contributions such as the ones from [Antonio Leiva](https://antonioleiva.com/clean-architecture-android/) and [Fernando Cejas](https://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/). Precisely, this latter work has served as the main inspiration for this application architecture.

![alt text](docs/images/clean-architecture-cejas-2.png)

Therefore, the prior idea behind **Github Browser** is concern-layers separation. Each of this entities is in charge of certain responsibilities, which are handled in isolation. These layers get interconnected thanks through interfaces, which allow to achieve the necessary abstraction between them.

* **Presentation**
This layer's duties consist of managing the events caused by the user interactions, and rendering the information coming from the _domain_ layer. In this particular case, the team has opted for using the Model-View-ViewModel ([MVVM](https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-1-604f50cda1)) architecture pattern. This entity "sees" the _domain_ layer.

* **Domain**
This layer is in charge of the application business logic. It is built upon _use-cases_ and repositories (_repository pattern_). The _domain_ layer obtains data from the _data_ module, use them to perform all the required operations, and format the outcomes to later deliver them to the _presentation_ layer. This entity only contains Kotlin code, and thus testing should only consist of **Unit Tests**. This layer represents the most inner entity, and thus it does not "see" anyone but itself.

* **Data**
This layer simply contains libraries and frameworks which provide data to the application (data sources). Among them, stand out service-query frameworks (_Retrofit_), local databases (_Room_), events tracking (_Omniture_), etc. This layer "sees" the _domain_ layer.

The usage of this class hierarchy and package organization pursues grasping the **SOLID** principles, getting more flexible when implementing new functionality, and easing code testing.

### Inversion of Control
In order to facilitate the interaction between the above described layers, Github Browser uses a Dependency Injection scheme. **[Dagger](https://dagger.dev/dev-guide/android)** is a framework which allows to abstract type injection in a neat and clear manner.

### Coroutines
Since _multithreading_ has historically been a challenge in Android Development, [coroutines](https://codelabs.developers.google.com/codelabs/kotlin-coroutines/#0) are extensively utilized. This is one of the most interesting and appealing features recently introduced in Kotlin.

<img src="docs/images/coroutines-scheme.jpg" alt="Coroutines" width="500">

The main advantage that supports the usage of _coroutines_ is an easy and enhanced multithreading management. _Coroutines_  allow to turn asynchronous operations in synchronous code, without affecting the application overall performance.

From the _execution-flow_ perspective, every task is undertaken in the main thread (UI thread), until a _use-case_ is invoked. From that moment onwards, operations are handled in worker threads, to later retrieve the computed results in the main thread again.

### Functional Programming and Arrow

Functional Programming (FP) is a paradigm from the 1950s which is based upon the principals of declarative programming. It comprises certain prior foundations such as immutability, pure functions and no side effects/disciplined states, and referential transparency. Contrary to Object Oriented Programming (OOP), everything is meant to be a function (instead of an object). Bringing these concepts into an application allows to make it more flexible, understandable, and easily scalable.

<img src="docs/images/arrow-logo.png" alt="Arrow" width="500">

[<b>Arrow</b>](https://arrow-kt.io/) is a functional programming suite written in Kotlin which aims to bring functional programming into Kotlin applications, such as Android ones. According to the [official documentation](https://arrow-kt.io/docs/core/), <i>"Arrow is a modular set of libraries that build on top of each other to provide increasingly higher level features"</i>.

So far, only the `Either` data type is used, allowing to parametrize any data source query available.

### Testing

As one main aspect in the current state-of-the-art in software development, **Github Browser** does include a good number of Unit Tests and a few Instrumentation Tests. The former cover the *domain* and *data* layers, whereas the latter are included in the *presentation* layer.


## License
This project is under the MIT license. Check /LICENSE.md for further details.


## Documentation
The documentation is managed in two different ways:
* Using this **README.md** file to give a quick overview of the project.
* Using **dokka**, which is a framework for Kotlin projects, similar to Javadoc.

For the latter, the documentation can be find in the *docs/dokka* folder, organized in modules. To generate it, simply run the next command from a terminal:
```bash
> ./gradlew dokka
```

When finished, open up `index.html` using your favourite browser.


## Maintainers
This project is mantained by:
* [Pablo L. Sordo Martínez](http://github.com/pablodeafsapps)


## Issues
If you happen to find any issue or suggestion, feel free to start a new thread on the _Issues_ section of the repository. I will try to address it as soon as possible.


## Contributing
1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Push your branch (git push origin my-new-feature)
5. Create a new Pull Request
