# Hyperion-Attr-Lottie
[![](https://jitpack.io/v/ykoyano/hyperion-attr-lottie.svg)](https://jitpack.io/#ykoyano/hyperion-attr-lottie)

[Hyperion](https://github.com/willowtreeapps/Hyperion-Android) Plugin for Inspecting Attribute of [Lottie-Android](https://github.com/airbnb/lottie-android) base on [Hyperion-Attr](https://github.com/willowtreeapps/Hyperion-Android/tree/develop/hyperion-attr). 

# Feature

- Changing Lottie animation file dynamically by typing URL or scanning QR code via network
- Displaying all [KeyPathElements](https://github.com/airbnb/lottie-android/blob/c476cc65bf9abc9f2a1e371490112d558b465a58/lottie/src/main/java/com/airbnb/lottie/model/KeyPathElement.java) with KeyPath of selected Lottie animation file.
- Changing any [LottieProperty](https://github.com/airbnb/lottie-android/blob/f87b1fdd8fb1cb4fd0e0931e53330caaa386da3c/lottie/src/main/java/com/airbnb/lottie/LottieProperty.java) of any KeyPathElements of selected Lottie animation file via [LottieAnimationView#addValueCallback](https://github.com/airbnb/lottie-android/blob/c15c126a6da9c53c277b67462ae128083215ef1f/lottie/src/main/java/com/airbnb/lottie/LottieAnimationView.java#L693).


# Installation
STEP 1 : Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

STEP 2 : Add the dependency

```groovy
dependencies {
    debugImplementation 'com.github.ykoyano:hyperion-attr-lottie:0.1.0'
}
```

# Usage


STEP 1 : Add the permission to `app/src/debug/AndroidManifest.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="com.github.ykoyano.hyperion.plugin.attr.lottie">

    <application
        android:name=".DebugApp"
        tools:replace="android:name" />

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.VIBRATE" />

</manifest>
```

STEP 2 : Add the request of camera permission to your main activity class for a scanning QR code.

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {

    ... // some code

    if (BuildConfig.DEBUG &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1234)
    }
}
```

# Reference

This plugin was implemented based on [Hyperion-Attr](https://github.com/willowtreeapps/Hyperion-Android/tree/develop/hyperion-attr) for specialized for debugging Lottie.

And sample application in this repository uses bellow Lottie animations for demonstration.
-  [AndroidWave.json](https://github.com/airbnb/lottie-android/blob/master/LottieSample/src/main/assets/AndroidWave.json)
-  [Star Success](https://lottiefiles.com/3152-star-success) created by [Taras Chernenko](https://lottiefiles.com/animissimo)
-  [star](https://lottiefiles.com/939-star) created by [nanaki](https://lottiefiles.com/nanaki14)
-  [Star Fav Animation](https://lottiefiles.com/900-star-fav-animation) created by [Taiki IKEDA](https://lottiefiles.com/itiekey)
-  [Trip](https://lottiefiles.com/4138-trip) created by [Pratik Malvi](https://lottiefiles.com/pratikmalvispec)
-  [Header for contact us](https://lottiefiles.com/4136-header-for-contact-us) created by [Aris Desainbagus](https://lottiefiles.com/desainbagus)
-  [Indian Neighborhoo](https://lottiefiles.com/4126-indian-neighborhood) created by [Gaurav Pant](https://lottiefiles.com/catofilm)

