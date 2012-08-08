/*
 * Copyright 2012 Neofonie Mobile GmbH
 *	
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package de.neofonie.mobile.app.android.widget.crouton;

import android.view.ViewGroup.LayoutParams;

/**
 * Style <br>
 * <br>
 * 
 * The style for a {@link Crouton}.
 * 
 * @author weiss@neofonie.de
 * 
 */

public class Style {
  public static final Style ALERT   = new Style(5000,
                                                android.R.color.holo_red_light,
                                                LayoutParams.WRAP_CONTENT);
  public static final Style CONFIRM = new Style(3000,
                                                android.R.color.holo_green_light,
                                                LayoutParams.WRAP_CONTENT);
  public static final Style INFO    = new Style(3000,
                                                android.R.color.holo_blue_bright,
                                                LayoutParams.WRAP_CONTENT);
  /**
   * The duration the {@link Crouton} will be displayed in milliseconds.
   */
  final int                 duration;
  /**
   * The color's resource id.
   */
  final int                 color;
  /**
   * The height of the {@link Crouton} in pixels.
   */
  final int                 height;
  /**
   * The resource id of the background.
   * 
   * 0 for no background.
   */
  final int                 background;
  /**
   * Whether we should tile the background or not.
   */
  final boolean             tile;
  /**
   * The text color's resource id.
   * 
   * 0 sets the text color to the system theme default.
   */
  final int                 textColor;

  /**
   * Creates a new {@link Crouton} with the provided parameters.
   * 
   * @param duration
   *          The duration the crouton will be displayed {@link Crouton} in milliseconds.
   * @param color
   *          The color's resource id.
   * @param height
   *          The height of the {@link Crouton}. Either {@link LayoutParams#MATCH_PARENT} or
   *          {@link LayoutParams#WRAP_CONTENT}.
   */
  public Style(int duration, int color, int height) {
    this(duration, color, height, 0, false, 0);
  }

  /**
   * Creates a new {@link Crouton} with the provided parameters
   * 
   * @param duration
   *          The duration the crouton will be displayed {@link Crouton} in milliseconds.
   * @param color
   *          The color's resource id.
   * @param height
   *          The height of the {@link Crouton}. Either {@link LayoutParams#MATCH_PARENT} or
   *          {@link LayoutParams#WRAP_CONTENT}.
   * @param textColor
   *          The resource id of the text's color.
   */
  public Style(int duration, int color, int height, int textColor) {
    this(duration, color, height, 0, false, textColor);
  }

  /**
   * Creates a new {@link Crouton} with the provided parameters.
   * 
   * @param duration
   *          The duration the crouton will be displayed {@link Crouton} in milliseconds.
   * @param color
   *          The color's resource id.
   * @param height
   *          The height of the {@link Crouton}. Either {@link LayoutParams#MATCH_PARENT} or
   *          {@link LayoutParams#WRAP_CONTENT}.
   * @param background
   *          A background image drawable's resource id.
   * @param tile
   *          <code>true</code> if you want the background to be tiled, else <code>false</code>.
   * @param textColor
   *          The resource id of the text's color.
   */
  public Style(int duration, int color, int height, int background, boolean tile, int textColor) {
    this.duration = duration;
    this.color = color;
    this.height = height;
    this.background = background;
    this.tile = tile;
    this.textColor = textColor;
  }
}

