/*
 * Copyright 2012 Neofonie Mobile GmbH
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package de.neofonie.mobile.app.android.widget.crouton;

import android.app.Activity;

/**
 * An AutoCrouton works just like a {@link Crouton}, but developers don't have to call the show method any
 * more.<br>
 * 
 */
public class AutoCrouton {

  /**
   * Creates a {@link Crouton} with provided text and style for a given activity and displays it directly.
   * 
   * @param activity
   *          The {@link android.app.Activity} that the {@link Crouton} should be attached to.
   * @param text
   *          The text you want to display.
   * @param style
   *          The style that this {@link Crouton} should be created with.
   * 
   */
  public static void makeText(Activity activity, CharSequence text, Style style) {
    Crouton.makeText(activity, text, style).show();
  }

  /**
   * Creates a {@link Crouton} with provided text-resource and style for a given activity and displays it
   * directly.
   * 
   * @param activity
   *          The {@link Activity} that the {@link Crouton} should be attached to.
   * @param textResourceId
   *          The resource id of the text you want to display.
   * @param style
   *          The style that this {@link Crouton} should be created with.
   * 
   */
  public static void makeText(Activity activity, int textResourceId, Style style) {
    makeText(activity, activity.getString(textResourceId), style);
  }
}
