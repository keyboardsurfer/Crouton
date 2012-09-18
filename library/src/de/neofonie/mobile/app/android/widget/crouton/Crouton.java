/*
 * Copyright 2012 Neofonie Mobile GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.neofonie.mobile.app.android.widget.crouton;

import android.widget.Toast;
import android.view.View;
import android.app.Activity;

/*
 * Based on an article by Cyril Mottier (http://android.cyrilmottier.com/?p=773) <br>
 */

/**
 * Displays information in a non-invasive context related manner. Like {@link Toast}, but better.
 */
public final class Crouton {
  private final Activity     activity;
  private final CharSequence text;
  private final Style        style;

  private View               view;

  /**
   * Creates the {@link Crouton}.
   * 
   * @param activity
   *          The {@link Activity} that the {@link Crouton} should be attached to.
   * @param text
   *          The text you want to display.
   * @param style
   *          The style that this {@link Crouton} should be created with.
   */
  private Crouton(Activity activity, CharSequence text, Style style) {
    if (activity == null || text == null || style == null) {
      throw new IllegalArgumentException("Null parameters are not accepted");
    }

    this.activity = activity;
    this.text = text;
    this.style = style;
  }

  /**
   * Creates a {@link Crouton} with provided text and style for a given activity.
   * 
   * @param activity
   *          The {@link Activity} that the {@link Crouton} should be attached to.
   * @param text
   *          The text you want to display.
   * @param style
   *          The style that this {@link Crouton} should be created with.
   * @return The created {@link Crouton}.
   */
  public static Crouton makeText(Activity activity, CharSequence text, Style style) {
    return new Crouton(activity, text, style);
  }

  /**
   * Creates a {@link Crouton} with provided text-resource and style for a given activity.
   * 
   * @param activity
   *          The {@link Activity} that the {@link Crouton} should be attached to.
   * @param textResourceId
   *          The resource id of the text you want to display.
   * @param style
   *          The style that this {@link Crouton} should be created with.
   * @return The created {@link Crouton}.
   */
  public static Crouton makeText(Activity activity, int textResourceId, Style style) {
    return makeText(activity, activity.getString(textResourceId), style);
  }

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
  public static void showText(Activity activity, CharSequence text, Style style) {
    makeText(activity, text, style).show();
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
  public static void showText(Activity activity, int textResourceId, Style style) {
    showText(activity, activity.getString(textResourceId), style);
  }

  /**
   * Cancels all queued {@link Crouton}s. If there is a {@link Crouton} displayed currently, it will be the
   * last one displayed.
   */
  public static void cancelAllCroutons() {
    Manager.getInstance().clearCroutonQueue();
  }

  /**
   * Displays the {@link Crouton}. If there's another {@link Crouton} visible at the time, this
   * {@link Crouton} will be displayed afterwards.
   */
  public void show() {
    Manager.getInstance().add(this);
  }

  /**
   * Cancels a {@link Crouton} immediately.
   */
  private void cancel() {
    // TODO think about exporting after Manager#removeCroutonImmediately has
    // been implemented.

    Manager.getInstance().removeCroutonImmediately(this);
  }

  /**
   * @return <code>true</code> if the {@link Crouton} is being displayed, else <code>false</code>.
   */
  boolean isShowing() {
    return view != null && view.getParent() != null;
  }

  /**
   * @return the activity
   */
  Activity getActivity() {
    return activity;
  }

  /**
   * @return the style
   */
  Style getStyle() {
    return style;
  }

  /**
   * @return the text
   */
  CharSequence getText() {
    return text;
  }

  /**
   * @return the view
   */
  View getView() {
    return view;
  }

  /**
   * @param view
   *          the view to set
   */
  void setView(View view) {
    this.view = view;
  }
}
