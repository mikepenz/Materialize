# Materialize  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.mikepenz/materialize/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.mikepenz/materialize)[![Join the chat at https://gitter.im/mikepenz/Materialize](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/mikepenz/Materialize?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)


**Materialize** comes with useful helper classes, the complete material color palette, and many additional utils. Use its theme as base. Let it manage your StatusBar, NavigationBar, Fullscreen behavior...

# What's in?
* Never worry about API specific differences again (**minSdk 14**)

* MaterializeBuilder -> Materialize
 * Let the library handle if you want
  * your activity as fullscreen
  * your activity with a transparent navigationBar
 * Let it handle the statusBarBackground color
  * It uses a ScrimInsetsFrameLayout

* Complete Material Color palette
 * As resource
 * As class

* A feature complete BaseTheme
 * AppCompat base
 * Defines all colors
 * Comes with many variations
 * All compatible down to **minSdk 14**

* A huge collection of helper methods via the UIUtils, safe down to API 10
 * getThemeColor
 * setBackground
 * getThemeColorFromAttrOrRes
 * getActionBarHeight

* KeyboardUtil
 * Handle the Layout (adjust_resize) if you use the FULL_SCREEN flag

# Preview
## Demo
To follow

## Screenshots
![Image](https://raw.githubusercontent.com/mikepenz/Materialize/develop/DEV/screenshots/screenshot1_small.png)
![Image](https://raw.githubusercontent.com/mikepenz/Materialize/develop/DEV/screenshots/screenshot2_small.png)

![Image](https://raw.githubusercontent.com/mikepenz/Materialize/develop/DEV/screenshots/screenshot3_small.png)


# Include in your project
## Using Maven
The Materialize Library is pushed to [Maven Central](http://search.maven.org/#search|ga|1|g%3A%22com.mikepenz%22), so you just need to add the following dependency to your `build.gradle`.

```javascript
// the following uses the new androidx dependencies
implementation "com.mikepenz:materialize:1.2.1"
implementation "com.google.android.material:material:${materialVersion}"
implementation "androidx.appcompat:appcompat:${androidXVersion}"
```

To use appcompat please use a version smaller than 1.2.0. (See the releases on GitHub)

## How to use
### MaterializeBuilder

```java
    //minimal configuration

    new MaterializeBuilder().withActivity(this).build();
```

```java
    //fullscreen activity with small modifications

    new MaterializeBuilder()
        .withActivity(this)
        .withFullscreen(true)
        .withTranslucentStatusBarProgrammatically(true)
        .withTintedStatusBar(true).build();
```

### Materialize Theme

To use one of the provided themes just use a `MaterialTheme`, `MaterialTheme.*` theme as parent

# Developed By

* Mike Penz 
 * [mikepenz.com](http://mikepenz.com) - <mikepenz@gmail.com>
 * [paypal.me/mikepenz](http://paypal.me/mikepenz)

# License

    Copyright 2017 Mike Penz

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
