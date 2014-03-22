# Crouton
![Crouton](https://raw.github.com/keyboardsurfer/Crouton/master/sample/src/main/res/drawable-xhdpi/ic_launcher.png "Crouton logo")

Context sensitive notifications for Android

## Overview

**Crouton** is a class that can be used by Android developers that feel the need for an **alternative to the Context insensitive [Toast](http://developer.android.com/reference/android/widget/Toast.html)**.

A Crouton will be displayed at the position the developer decides.
Standard will be the top of an application window.
You can line up multiple Croutons for display, that will be shown one after another.

You can check some features in the Crouton Demo.

<a href="http://play.google.com/store/apps/details?id=de.keyboardsurfer.app.demo.crouton">
  <img alt="Crouton Demo on Google Play"
         src="http://developer.android.com/images/brand/en_generic_rgb_wo_60.png" />
</a>

If you're already using Crouton and just want to download the latest version of the library, follow [this link](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22de.keyboardsurfer.android.widget%22).

## [Changelog](https://github.com/keyboardsurfer/Crouton/wiki/Changelog)
### Current version: 1.8.4

## Usage

The API is kept as simple as the Toast API:

Create a Crouton for any CharSequence:

    Crouton.makeText(Activity, CharSequence, Style).show();
    
Create a Crouton with a String from your application's resources:

    Crouton.makeText(Activity, int, Style).show();
    
Further you can attach a Crouton to any ViewGroup like this:

    Crouton.makeText(Activity, int, Style, int).show();

    Crouton.makeText(Activity, int, Style, ViewGroup).show();
    
Also `Crouton.show(...)` methods are available for convenient fire and forget display of Croutons. 

If you would like a more graphical introduction to Crouton check out [this presentation](https://speakerdeck.com/keyboardsurfer/crouton-devfest-berlin-2012).

##Important!

In your Activity.onDestroy() make sure to call

    Crouton.cancelAllCroutons();
    
to cancel cancel all scheduled Croutons.

This is a workaround and further description is available in [issue #24](https://github.com/keyboardsurfer/Crouton/issues/24).

## Basic Examples
Currently you can use the three different Style attributes displayed below out of the box:

![Alert](https://github.com/keyboardsurfer/Crouton/raw/master/res/Alert.png "Example of Style.ALERT")

![Confirm](https://github.com/keyboardsurfer/Crouton/raw/master/res/Confirm.png "Example of Style.CONFIRM")

![Info](https://github.com/keyboardsurfer/Crouton/raw/master/res/Info.png "Example of Style.INFO")

## Extension and Modification

The whole design of a Crouton is defined by  [Style](https://github.com/keyboardsurfer/Crouton/blob/master/library/src/main/java/de/keyboardsurfer/android/widget/crouton/Style.java).

You can use one of the styles Crouton ships with: **Style.ALERT**, **Style.CONFIRM** and **Style.INFO**. Or you can create your own Style.

In general you can modify

- display duration
- dimension settings
- options for the text to display
- custom Views
- appearance & disappearance Animation
- displayed Image

Since [Style](https://github.com/keyboardsurfer/Crouton/blob/master/library/src/de/keyboardsurfer/android/widget/crouton/Style.java) is the general entry point for tweaking Croutons, go and see for yourself what can be done with it.

## Building
### Gradle

#### From maven central

Add maven central to your `build.gradle`:

```groovy
buildscript {
  repositories {
    mavenCentral()
  }
}
 
repositories {
  mavenCentral()
}
```

Then declare Crouton within your dependencies:

```groovy
dependencies {
  ...
  compile('de.keyboardsurfer.android.widget:crouton:1.8.4') {
    // exclusion is not neccessary, but generally a good idea.
    exclude group: 'com.google.android', module: 'support-v4'
  }
  ...
}
```


### Maven

#### From maven central

To use crouton within your maven build simply add

```xml
<dependency>
  <artifactId>crouton</artifactId>
  <version>${crouton.version}</version>
  <groupId>de.keyboardsurfer.android.widget</groupId>
</dependency>
```

to your pom.xml

If you also want the sources or javadoc add the respective classifier  

```xml
  <classifier>sources</classifier>
```

or

```xml
  <classifier>javadoc</classifier>
```
to the dependency.

If you are referencing a newer version of the Android Support Library in your application, you might want to exclude Crouton's dependency like this:

```xml
<dependency>
	<artifactId>crouton</artifactId>
	<version>${crouton.version}</version>
	<groupId>de.keyboardsurfer.android.widget</groupId>
	<exclusions>
	    <exclusion>
	        <groupId>com.android.support</groupId>
	        <artifactId>support-v4</artifactId>
	    </exclusion>
	</exclusions>
</dependency>
```

### DIY

The build requires Gradle. Operations are very simple:

* install [gradle](http://www.gradle.org/)
* `gradle assemble` builds all artifacts
* `gradle jar` builds the jar

After putting Crouton in a repository you can add it as dependency.

```groovy
compile('de.keyboardsurfer.android.widget:crouton:1.8.4') {
  exclude group: 'com.google.android', module: 'support-v4'
}
```

###Building and Signing

In order to build and sign Crouton locally you'll need to rename `gradle.properties.sample` to `gradle.properties`.

## Contribution

###Questions

Questions regarding Crouton can be asked on [StackOverflow, using the crouton tag](http://stackoverflow.com/questions/tagged/crouton).

### Pull requests welcome

Feel free to contribute to Crouton.

Either you found a bug or have created a new and awesome feature, just create a pull request.

If you want to start to create a new feature or have any other questions regarding Crouton, [file an issue](https://github.com/keyboardsurfer/Crouton/issues/new).
I'll try to answer as soon as I find the time.

Please note, if you're working on a pull request, make sure to use the [develop branch](https://github.com/keyboardsurfer/Crouton/tree/develop) as your base.

### Formatting

For contributors using Eclipse there's a formatter available at the [download section](https://github.com/downloads/keyboardsurfer/Crouton/Crouton_Eclipseformatter.xml).

In order to reduce merging pains on my end, please use this formatter or format your commit in a way similar to it's example.

If you're using IDEA, the Eclipse Formatter plugin should allow you to use the formatter as well.

## License

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

## Attributions

The initial version was written by  <a href="https://plus.google.com/u/0/117509657298845443204?rel=author">Benjamin Weiss</a>.
The name and the idea of [Crouton](https://github.com/keyboardsurfer/Crouton/blob/master/library/src/de/keyboardsurfer/android/widget/crouton/Crouton.java) originates in a [blog article](http://android.cyrilmottier.com/?p=773) by Cyril Mottier.

The Crouton logo has been created by [Marie Schweiz](http://marie-schweiz.de).
