# Crouton
![Crouton](https://raw.github.com/keyboardsurfer/Crouton/master/sample/res/drawable-xhdpi/ic_launcher.png "Crouton logo")

Context sensitive notifications for Android

## Overview

**Crouton** is a class that can be used by Android developers that feel the need for an **alternative to the Context insensitive [Toast](http://developer.android.com/reference/android/widget/Toast.html)**.

A Crouton will be displayed at the top of an application window.
You can line up multiple Croutons for display, that will be shown one after another.

**As of version 1.5 Crouton can be used on any Android device without API level limitations.**

A demo application is available on Google Play

<a href="http://play.google.com/store/apps/details?id=de.keyboardsurfer.app.demo.crouton">
  <img alt="Crouton Demo on Google Play"
         src="http://developer.android.com/images/brand/en_generic_rgb_wo_60.png" />
</a>

### Usage

The API is kept as simple as the Toast API:

Create a Crouton for any CharSequence:

    Crouton.makeText(Activity, CharSequence, Style).show();
    
Create a Crouton with a String from your application's resources:

    Crouton.makeText(Activity, int, Style).show();

Create a Crouton using the Builer class:

    Style style = new Style.Builder()
          .setBackgroundColor(R.color.awesome_pink)
          .setDuration(1337)
          .setGravity(Gravity.CENTER)
          .setTextColor(android.R.color.black)
          .build();
    Crouton.makeText(
           this, 
           getString(R.string.crouton_message), 
           style)
         .show();

You can also cancel scheduled Croutons by calling:

    Crouton.cancelAllCroutons();

### Examples
Currently you can use the three different Style attributes displayed below out of the box:

![Alert](https://github.com/keyboardsurfer/Crouton/raw/master/res/Alert.png "Example of Style.ALERT")

![Confirm](https://github.com/keyboardsurfer/Crouton/raw/master/res/Confirm.png "Example of Style.CONFIRM")

![Info](https://github.com/keyboardsurfer/Crouton/raw/master/res/Info.png "Example of Style.INFO")

### Extension and Modification

The whole design of a Crouton is defined by [Style](https://github.com/keyboardsurfer/Crouton/blob/master/library/src/de/neofonie/mobile/app/android/widget/crouton/Style.java).
You can easily create your own Styles by calling one of the constructors of the Style class or use one of the already provided styles: Style.ALERT, Style.CONFIRM and Style.INFO.

If you want to modify the general appearance you might want to have a look at the [ViewHolder](https://github.com/keyboardsurfer/Crouton/blob/master/library/src/de/neofonie/mobile/app/android/widget/crouton/ViewHolder.java).

### Building

The build requires Maven. Operations are very simple:

* `mvn -f library/pom.xml clean package` will build `jar` library;
* `mvn clean package` will build `jar` library and sample application `apk` package;
* `mvn -f library/pom.xml clean install` will put Crouton in your local Maven repository.

After putting Crouton in the repository you can add it as a dependency.

```xml
<dependency>
  <artifactId>crouton</artifactId>
  <version>1.4</version>
  <groupId>de.keyboardsurfer.android.widget</groupId>
</dependency>
```

### Contribution

This section is subject to changes.

For contributors using Eclipse there's a formatter available at the [download section](https://github.com/downloads/keyboardsurfer/Crouton/Crouton_Eclipseformatter.xml).
If you want to contribute and create pull requests, make sure to use this formatter or format your commit in a way similar to it's example.

### License

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

### Attributions

The name and the idea of [Crouton](https://github.com/keyboardsurfer/Crouton/blob/master/library/src/de/neofonie/mobile/app/android/widget/crouton/Crouton.java) originates in a [blog article](http://android.cyrilmottier.com/?p=773) by Cyril Mottier.

The Crouton logo has been created by [Marie Schweiz](http://marie-schweiz.de).

The initial version was written by [Benjamin Weiss](https://plus.google.com/117509657298845443204) at [Neofonie Mobile GmbH](http://mobile.neofonie.de).
