# Crouton

Context sensitive notifications for Android

## Overview

**Crouton**, is a class that can be used by Android developers that feel the need for an **alternative to the Context insensitive [Toast](http://developer.android.com/reference/android/widget/Toast.html)**.

The name and the idea of [Crouton](https://github.com/neofoniemobile/Crouton/blob/master/src/de/neofonie/mobile/app/android/widget/crouton/Crouton.java) originates in a [blog article](http://android.cyrilmottier.com/?p=773) by Cyril Mottier.

A Crouton will be displayed at the top of an application window.
You can line up multiple Croutons for display, that will be shown one after another.

The current implementation requires the Holo theme to be present on the device.

### Usage

The API is kept as simple as the Toast API:

Create a Crouton for any CharSequence:

    Crouton.makeText(Activity, CharSequence, Style).show();
    
Create a Crouton with a String from your application's resources:

    Crouton.makeText(Activity, int, Style).show();

You can also cancel scheduled Croutons by calling:

    Crouton.cancelAllCroutons();

### Examples
Currently you can use the three different Style attributes displayed below:

![Alert](https://github.com/neofoniemobile/Crouton/raw/master/res/Alert.png "Example of Style.ALERT")

![Confirm](https://github.com/neofoniemobile/Crouton/raw/master/res/Confirm.png "Example of Style.CONFIRM")

![Info](https://github.com/neofoniemobile/Crouton/raw/master/res/Info.png "Example of Style.INFO")

### Extension and Modification

Color and display duration of a Crouton is defined by [Style](https://github.com/neofoniemobile/Crouton/blob/master/src/de/neofonie/mobile/app/android/widget/crouton/Style.java).
You can easily create your own Styles by adding them to the existing enum.

If you want to modify the general appearance you might want to have a look at the [ViewHolder](https://github.com/neofoniemobile/Crouton/blob/master/src/de/neofonie/mobile/app/android/widget/crouton/ViewHolder.java).

### License

######Croutons are licensed under the **[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)**.

Copyright 2012 Neofonie Mobile GmbH

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

####The initial version was written by Benjamin Weiss at Neofonie Mobile GmbH.