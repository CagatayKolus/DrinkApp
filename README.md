# DrinkApp
An android application which uses Punkapi service to getting and listing drinks.

## Prerequisites

#### 1. Check the endpoint

If the app cannot get any item, check the endpoint on DrinkService class.

	https://api.punkapi.com/v2/beers/random

#### 2. Ready to run.

## Features
- Get random drink from service
- Caching result (offline capability)
- Pull to refresh
- Unit tests

## Tech Stack
- **Kotlin** - Officially supported programming language for Android development by Google
- **Kotlin DSL** - Alternative syntax to the Groovy DSL
- **Coroutines** - Perform asynchronous operations
- **Flow** - Handle the stream of data asynchronously
- **Android Architecture Components**
  - **LiveData** - Notify views about data changes
  - **Room** - Persistence library
  - **ViewModel** - UI related data holder
  - **ViewBinding** - Allows to more easily write code that interacts with views
- **Hilt** - Dependency Injection framework
- **Retrofit** - Networking library
- **Moshi** - A modern JSON library for Kotlin and Java
 
## Local Unit Tests
- The project uses MockWebServer (scriptable web server) to test API interactions.

## Screenshots
![drink_app](https://user-images.githubusercontent.com/25778714/133906892-f7120649-4e19-4e3a-8e8d-af7b71598491.jpg)

## Architecture
![arch500](https://user-images.githubusercontent.com/25778714/113482640-3801f100-94a8-11eb-98d6-e15cb21a905b.png)
