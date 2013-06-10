# Crouton
![Crouton](https://raw.github.com/keyboardsurfer/Crouton/master/sample/res/drawable-xhdpi/ic_launcher.png "Crouton logo")

Context sensitive notifications for Android

## Overview

**Crouton** is a class that can be used by Android developers that feel the need for an **alternative to the Context insensitive [Toast](http://developer.android.com/reference/android/widget/Toast.html)**.

A Crouton will be displayed at the position the developer decides.
Standard will be the of an application window.
You can line up multiple Croutons for display, that will be shown one after another.

You can check some features in the Crouton Demo.

<a href="http://play.google.com/store/apps/details?id=de.keyboardsurfer.app.demo.crouton">
  <img alt="Crouton Demo on Google Play"
         src="http://developer.android.com/images/brand/en_generic_rgb_wo_60.png" />
</a>

If you're already using Crouton and just want to download the latest version of the library, follow [this link](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22de.keyboardsurfer.android.widget%22).

### Changelog
#### Current version: 1.8.1

####[1.8](https://github.com/keyboardsurfer/Crouton/tree/1.8)

- Improves support for custom views
- Smoothing out animations for multiple line Croutons
- Cleans up Style
  - Configuration is now available for non-UI information
  - Style only holds UI-relevant information
- Introduces DURATION_SHORT and DURATION_LONG constants

####[1.7](https://github.com/keyboardsurfer/Crouton/tree/1.7)

- `Crouton.setOnClickListener(OnClickListener)` has been introduced.
- Infinite display of Crouton is possible via `Style.setDuration(Style.DURATION_INFINITE)`
- Via `Crouton.hide(Crouton)` a Crouton can be hidden.

####[1.6](https://github.com/keyboardsurfer/Crouton/tree/1.6)

- Crouton now can be used on any Android device with **API level 4+**.
- Changes the package name to `de.keyboardsurfer.android.widget`
- Adds possibility to set a custom width
- Can now be added to any ViewGroup (@coreform)
- Integration with TalkBack (@coreform)
- Adds Accessibility features (@coreform)
- Fixes bug that got Crouton out of sync with reality (@coreform)
- New [LifecycleCallback](https://github.com/keyboardsurfer/Crouton/blob/master/library/src/de/keyboardsurfer/android/widget/crouton/LifecycleCallback.java) (@coreform)
- initializeCroutonView was refactored, to make it easier on the eyes
- removes redundant initialization within Style.Builder
- documentation improvments

#### older versions

Please see the `git log`

## Usage

The API is kept as simple as the Toast API:

Create a Crouton for any CharSequence:

    Crouton.makeText(Activity, CharSequence, [Style]).show();
    
Create a Crouton with a String from your application's resources:

    Crouton.makeText(Activity, int, Style).show();
    
Further you can attach a Crouton to any view like this:

    Crouton.makeText(Activity, int, Style, int).show();

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

The whole design of a Crouton is defined by [Style](https://github.com/keyboardsurfer/Crouton/blob/master/library/src/de/keyboardsurfer/android/widget/crouton/Style.java).

You can use one of the styles Crouton ships with: **Style.ALERT**, **Style.CONFIRM** and **Style.INFO**. Or you can create your own Style.

In general you can modify

- display duration
- dimension settings
- options for the text to display
- custom Views
- appearance & disappearance Animation
- displayed Image

Since [Style](https://github.com/keyboardsurfer/Crouton/blob/master/library/src/de/keyboardsurfer/android/widget/crouton/Style.java) is the general entry point for tweaking Croutons, go and see for yourself what can be done with it.


## Maven

### From maven central

Crouton is available in the maven central repository.

To use crouton simply add

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
	        <groupId>com.google.android</groupId>
	        <artifactId>support-v4</artifactId>
	    </exclusion>
	</exclusions>
</dependency>
```

### DIY

The build requires Maven. Operations are very simple:

* `mvn -f library/pom.xml clean package` will build a `jar` library;
* `mvn clean package` will build a `jar` library and the sample application `apk`;
* `mvn -f library/pom.xml clean install` will put Crouton in your local Maven repository.

After putting Crouton in the repository you can add it as a dependency.

```xml
<dependency>
  <artifactId>crouton</artifactId>
  <version>${crouton.version}</version>
  <groupId>de.keyboardsurfer.android.widget</groupId>
</dependency>
```

## Contribution

###Questions

Questions regarding Crouton can be asked on [StackOverflow, using the crouton tag](http://stackoverflow.com/questions/tagged/crouton).

### Pull requests welcome

Feel free to contribute to Crouton.

Either you found a bug or have created a new and awesome feature, just create a pull request.

If you want to start to create a new feature or have any other questions regarding Crouton, [file an issue](https://github.com/keyboardsurfer/Crouton/issues/new).
I'll try to answer as soon as I find the time.

### Formatting

For contributors using Eclipse there's a formatter available at the [download section](https://github.com/downloads/keyboardsurfer/Crouton/Crouton_Eclipseformatter.xml).

In order to reduce merging pains on my end, please use this formatter or format your commit in a way similar to it's example.

If you're using IDEA, the Eclipse Formatter plugin should allow you to use the formatter as well.

## License

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

## Attributions

The initial version was written by  <a href="https://plus.google.com/u/0/117509657298845443204?rel=author">Benjamin Weiss</a> at [Neofonie Mobile GmbH](http://mobile.neofonie.de).

The name and the idea of [Crouton](https://github.com/keyboardsurfer/Crouton/blob/master/library/src/de/keyboardsurfer/android/widget/crouton/Crouton.java) originates in a [blog article](http://android.cyrilmottier.com/?p=773) by Cyril Mottier.

The Crouton logo has been created by [Marie Schweiz](http://marie-schweiz.de).
