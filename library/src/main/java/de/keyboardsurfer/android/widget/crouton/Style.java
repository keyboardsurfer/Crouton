/*
 * Copyright 2012 - 2014 Benjamin Weiss
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

package de.keyboardsurfer.android.widget.crouton;

import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;


/** The style for a {@link Crouton}. */
public class Style {
  
  public static final int NOT_SET = -1;

  public static final int holoRedLight = 0xffff4444;
  public static final int holoGreenLight = 0xff99cc00;
  public static final int holoBlueLight = 0xff33b5e5;

  /** Default style for alerting the user. */
  public static final Style ALERT;
  /** Default style for confirming an action. */
  public static final Style CONFIRM;
  /** Default style for general information. */
  public static final Style INFO;

  static {
    ALERT = new Builder()
      .setBackgroundColorValue(holoRedLight)
      .build();
    CONFIRM = new Builder()
      .setBackgroundColorValue(holoGreenLight)
      .build();
    INFO = new Builder()
      .setBackgroundColorValue(holoBlueLight)
      .build();
  }

  /**
   * The {@link Configuration} for this {@link Style}.
   * It can be overridden via {@link Crouton#setConfiguration(Configuration)}.
   */
  final Configuration configuration;

  /**
   * The resource id of the backgroundResourceId.
   * <p/>
   * 0 for no backgroundResourceId.
   */
  final int backgroundColorResourceId;

  /**
   * The resource id of the backgroundDrawableResourceId.
   * <p/>
   * 0 for no backgroundDrawableResourceId.
   */
  final int backgroundDrawableResourceId;

  /**
   * The backgroundColorResourceValue's e.g. 0xffff4444;
   * <p/>
   * NOT_SET for no value.
   */
  final int backgroundColorValue;

  /** Whether we should isTileEnabled the backgroundResourceId or not. */
  final boolean isTileEnabled;

  /**
   * The text colorResourceId's resource id.
   * <p/>
   * 0 sets the text colorResourceId to the system theme default.
   */
  final int textColorResourceId;

  /**
   * The textColorResourceValue's e.g. 0xffff4444;
   * <p/>
   * NOT_SET for no value.
   */
  final int textColorValue;

  /** The height of the {@link Crouton} in pixels. */
  final int heightInPixels;

  /** Resource ID for the height of the {@link Crouton}. */
  final int heightDimensionResId;

  /** The width of the {@link Crouton} in pixels. */
  final int widthInPixels;

  /** Resource ID for the width of the {@link Crouton}. */
  final int widthDimensionResId;

  /** The text's gravity as provided by {@link Gravity}. */
  final int gravity;

  /** An additional image to display in the {@link Crouton}. */
  final Drawable imageDrawable;

  /** An additional image to display in the {@link Crouton}. */
  final int imageResId;

  /** The {@link ImageView.ScaleType} for the image to display in the {@link Crouton}. */
  final ImageView.ScaleType imageScaleType;

  /**
   * The text size in sp
   * <p/>
   * 0 sets the text size to the system theme default
   */
  final int textSize;

  /** The text shadow color's resource id */
  final int textShadowColorResId;

  /** The text shadow radius */
  final float textShadowRadius;

  /** The text shadow vertical offset */
  final float textShadowDy;

  /** The text shadow horizontal offset */
  final float textShadowDx;

  /** The text appearance resource id for the text. */
  final int textAppearanceResId;

  /** The padding for the crouton view content in pixels */
  final int paddingInPixels;

  /** The resource id for the padding for the view content */
  final int paddingDimensionResId;

  /** The file path and font name for the view content */
  final String fontName;

  /** The file path and font name resource id for the view content */
  final int fontNameResId;

  private Style(final Builder builder) {
    this.configuration = builder.configuration;
    this.backgroundColorResourceId = builder.backgroundColorResourceId;
    this.backgroundDrawableResourceId = builder.backgroundDrawableResourceId;
    this.isTileEnabled = builder.isTileEnabled;
    this.textColorResourceId = builder.textColorResourceId;
    this.textColorValue = builder.textColorValue;
    this.heightInPixels = builder.heightInPixels;
    this.heightDimensionResId = builder.heightDimensionResId;
    this.widthInPixels = builder.widthInPixels;
    this.widthDimensionResId = builder.widthDimensionResId;
    this.gravity = builder.gravity;
    this.imageDrawable = builder.imageDrawable;
    this.textSize = builder.textSize;
    this.textShadowColorResId = builder.textShadowColorResId;
    this.textShadowRadius = builder.textShadowRadius;
    this.textShadowDx = builder.textShadowDx;
    this.textShadowDy = builder.textShadowDy;
    this.textAppearanceResId = builder.textAppearanceResId;
    this.imageResId = builder.imageResId;
    this.imageScaleType = builder.imageScaleType;
    this.paddingInPixels = builder.paddingInPixels;
    this.paddingDimensionResId = builder.paddingDimensionResId;
    this.backgroundColorValue = builder.backgroundColorValue;
    this.fontName = builder.fontName;
    this.fontNameResId = builder.fontNameResId;
  }

  /** Builder for the {@link Style} object. */
  public static class Builder {
    private Configuration configuration;
    private int backgroundColorValue;
    private int backgroundColorResourceId;
    private int backgroundDrawableResourceId;
    private boolean isTileEnabled;
    private int textColorResourceId;
    private int textColorValue;
    private int heightInPixels;
    private int heightDimensionResId;
    private int widthInPixels;
    private int widthDimensionResId;
    private int gravity;
    private Drawable imageDrawable;
    private int textSize;
    private int textShadowColorResId;
    private float textShadowRadius;
    private float textShadowDx;
    private float textShadowDy;
    private int textAppearanceResId;
    private int imageResId;
    private ImageView.ScaleType imageScaleType;
    private int paddingInPixels;
    private int paddingDimensionResId;
    private String fontName;
    private int fontNameResId;

    /** Creates a {@link Builder} to build a {@link Style} upon. */
    public Builder() {
      configuration = Configuration.DEFAULT;
      paddingInPixels = 10;
      backgroundColorResourceId = android.R.color.holo_blue_light;
      backgroundDrawableResourceId = 0;
      backgroundColorValue = NOT_SET;
      isTileEnabled = false;
      textColorResourceId = android.R.color.white;
      textColorValue = NOT_SET;
      heightInPixels = LayoutParams.WRAP_CONTENT;
      widthInPixels = LayoutParams.MATCH_PARENT;
      gravity = Gravity.CENTER;
      imageDrawable = null;
      imageResId = 0;
      imageScaleType = ImageView.ScaleType.FIT_XY;
      fontName = null;
      fontNameResId = 0;
    }

    /**
     * Creates a {@link Builder} to build a {@link Style} upon.
     *
     * @param baseStyle
     *   The base {@link Style} to use for this {@link Style}.
     */
    public Builder(final Style baseStyle) {
      configuration = baseStyle.configuration;
      backgroundColorValue = baseStyle.backgroundColorValue;
      backgroundColorResourceId = baseStyle.backgroundColorResourceId;
      backgroundDrawableResourceId = baseStyle.backgroundDrawableResourceId;
      isTileEnabled = baseStyle.isTileEnabled;
      textColorResourceId = baseStyle.textColorResourceId;
      textColorValue = baseStyle.textColorValue;
      heightInPixels = baseStyle.heightInPixels;
      heightDimensionResId = baseStyle.heightDimensionResId;
      widthInPixels = baseStyle.widthInPixels;
      widthDimensionResId = baseStyle.widthDimensionResId;
      gravity = baseStyle.gravity;
      imageDrawable = baseStyle.imageDrawable;
      textSize = baseStyle.textSize;
      textShadowColorResId = baseStyle.textShadowColorResId;
      textShadowRadius = baseStyle.textShadowRadius;
      textShadowDx = baseStyle.textShadowDx;
      textShadowDy = baseStyle.textShadowDy;
      textAppearanceResId = baseStyle.textAppearanceResId;
      imageResId = baseStyle.imageResId;
      imageScaleType = baseStyle.imageScaleType;
      paddingInPixels = baseStyle.paddingInPixels;
      paddingDimensionResId = baseStyle.paddingDimensionResId;
      fontName = baseStyle.fontName;
      fontNameResId = baseStyle.fontNameResId;
    }
    /**
     * Set the {@link Configuration} option of the {@link Crouton}.
     *
     * @param configuration
     *   The {@link Configuration}.
     *
     * @return the {@link Builder}.
     */
    public Builder setConfiguration(Configuration configuration) {
      this.configuration = configuration;
      return this;
    }

    /**
     * Set the backgroundColorResourceId option of the {@link Crouton}.
     *
     * @param backgroundColorResourceId
     *   The backgroundColorResourceId's resource id.
     *
     * @return the {@link Builder}.
     */
    public Builder setBackgroundColor(int backgroundColorResourceId) {
      this.backgroundColorResourceId = backgroundColorResourceId;

      return this;
    }

    /**
     * Set the backgroundColorResourceValue option of the {@link Crouton}.
     *
     * @param backgroundColorValue
     *   The backgroundColorResourceValue's e.g. 0xffff4444;
     *
     * @return the {@link Builder}.
     */
    public Builder setBackgroundColorValue(int backgroundColorValue) {
      this.backgroundColorValue = backgroundColorValue;
      return this;
    }

    /**
     * Set the backgroundDrawableResourceId option for the {@link Crouton}.
     *
     * @param backgroundDrawableResourceId
     *   Resource ID of a backgroundDrawableResourceId image drawable.
     *
     * @return the {@link Builder}.
     */
    public Builder setBackgroundDrawable(int backgroundDrawableResourceId) {
      this.backgroundDrawableResourceId = backgroundDrawableResourceId;

      return this;
    }

    /**
     * Set the heightInPixels option for the {@link Crouton}.
     *
     * @param height
     *   The height of the {@link Crouton} in pixel. Can also be
     *   {@link LayoutParams#MATCH_PARENT} or
     *   {@link LayoutParams#WRAP_CONTENT}.
     *
     * @return the {@link Builder}.
     */
    public Builder setHeight(int height) {
      this.heightInPixels = height;

      return this;
    }

    /**
     * Set the resource id for the height option for the {@link Crouton}.
     *
     * @param heightDimensionResId
     *   Resource ID of a dimension for the height of the {@link Crouton}.
     *
     * @return the {@link Builder}.
     */
    public Builder setHeightDimensionResId(int heightDimensionResId) {
      this.heightDimensionResId = heightDimensionResId;

      return this;
    }

    /**
     * Set the widthInPixels option for the {@link Crouton}.
     *
     * @param width
     *   The width of the {@link Crouton} in pixel. Can also be
     *   {@link LayoutParams#MATCH_PARENT} or
     *   {@link LayoutParams#WRAP_CONTENT}.
     *
     * @return the {@link Builder}.
     */
    public Builder setWidth(int width) {
      this.widthInPixels = width;

      return this;
    }

    /**
     * Set the resource id for the width option for the {@link Crouton}.
     *
     * @param widthDimensionResId
     *   Resource ID of a dimension for the width of the {@link Crouton}.
     *
     * @return the {@link Builder}.
     */
    public Builder setWidthDimensionResId(int widthDimensionResId) {
      this.widthDimensionResId = widthDimensionResId;

      return this;
    }

    /**
     * Set the isTileEnabled option for the {@link Crouton}.
     *
     * @param isTileEnabled
     *   <code>true</code> if you want the backgroundResourceId to be
     *   tiled, else <code>false</code>.
     *
     * @return the {@link Builder}.
     */
    public Builder setTileEnabled(boolean isTileEnabled) {
      this.isTileEnabled = isTileEnabled;

      return this;
    }

    /**
     * Set the textColorResourceId option for the {@link Crouton}.
     *
     * @param textColor
     *   The resource id of the text colorResourceId.
     *
     * @return the {@link Builder}.
     */
    public Builder setTextColor(int textColor) {
      this.textColorResourceId = textColor;

      return this;
    }

    /**
     * Set the textColorResourceValue option of the {@link Crouton}.
     *
     * @param textColorValue
     *   The textColorResourceValue's e.g. 0xffff4444;
     *
     * @return the {@link Builder}.
     */
    public Builder setTextColorValue(int textColorValue) {
      this.textColorValue = textColorValue;
      return this;
    }

    /**
     * Set the gravity option for the {@link Crouton}.
     *
     * @param gravity
     *   The text's gravity as provided by {@link Gravity}.
     *
     * @return the {@link Builder}.
     */
    public Builder setGravity(int gravity) {
      this.gravity = gravity;

      return this;
    }

    /**
     * Set the image option for the {@link Crouton}.
     *
     * @param imageDrawable
     *   An additional image to display in the {@link Crouton}.
     *
     * @return the {@link Builder}.
     */
    public Builder setImageDrawable(Drawable imageDrawable) {
      this.imageDrawable = imageDrawable;

      return this;
    }

    /**
     * Set the image resource option for the {@link Crouton}.
     *
     * @param imageResId
     *   An additional image to display in the {@link Crouton}.
     *
     * @return the {@link Builder}.
     */
    public Builder setImageResource(int imageResId) {
      this.imageResId = imageResId;

      return this;
    }

    /** The text size in sp. */
    public Builder setTextSize(int textSize) {
      this.textSize = textSize;
      return this;
    }

    /** The text shadow color resource id. */
    public Builder setTextShadowColor(int textShadowColorResId) {
      this.textShadowColorResId = textShadowColorResId;
      return this;
    }

    /** The text shadow radius. */
    public Builder setTextShadowRadius(float textShadowRadius) {
      this.textShadowRadius = textShadowRadius;
      return this;
    }

    /** The text shadow horizontal offset. */
    public Builder setTextShadowDx(float textShadowDx) {
      this.textShadowDx = textShadowDx;
      return this;
    }

    /** The text shadow vertical offset. */
    public Builder setTextShadowDy(float textShadowDy) {
      this.textShadowDy = textShadowDy;
      return this;
    }

    /** The text appearance resource id for the text. */
    public Builder setTextAppearance(int textAppearanceResId) {
      this.textAppearanceResId = textAppearanceResId;
      return this;
    }

    /** The {@link android.widget.ImageView.ScaleType} for the image. */
    public Builder setImageScaleType(ImageView.ScaleType imageScaleType) {
      this.imageScaleType = imageScaleType;
      return this;
    }

    /** The padding for the crouton view's content in pixels. */
    public Builder setPaddingInPixels(int padding) {
      this.paddingInPixels = padding;
      return this;
    }

    /** The resource id for the padding for the crouton view's content. */
    public Builder setPaddingDimensionResId(int paddingResId) {
      this.paddingDimensionResId = paddingResId;
      return this;
    }

    /** The file path and name of the font for the crouton view's content. */
    public Builder setFontName(String fontName) {
      this.fontName = fontName;
      return this;
    }

    /** The resource id for the file path and name of the font for the crouton view's content. */
    public Builder setFontNameResId(int fontNameResId) {
      this.fontNameResId = fontNameResId;
      return this;
    }

    /** @return a configured {@link Style} object. */
    public Style build() {
      return new Style(this);
    }
  }

  @Override
  public String toString() {
    return "Style{" +
      "configuration=" + configuration +
      ", backgroundColorResourceId=" + backgroundColorResourceId +
      ", backgroundDrawableResourceId=" + backgroundDrawableResourceId +
      ", backgroundColorValue=" + backgroundColorValue +
      ", isTileEnabled=" + isTileEnabled +
      ", textColorResourceId=" + textColorResourceId +
      ", textColorValue=" + textColorValue +
      ", heightInPixels=" + heightInPixels +
      ", heightDimensionResId=" + heightDimensionResId +
      ", widthInPixels=" + widthInPixels +
      ", widthDimensionResId=" + widthDimensionResId +
      ", gravity=" + gravity +
      ", imageDrawable=" + imageDrawable +
      ", imageResId=" + imageResId +
      ", imageScaleType=" + imageScaleType +
      ", textSize=" + textSize +
      ", textShadowColorResId=" + textShadowColorResId +
      ", textShadowRadius=" + textShadowRadius +
      ", textShadowDy=" + textShadowDy +
      ", textShadowDx=" + textShadowDx +
      ", textAppearanceResId=" + textAppearanceResId +
      ", paddingInPixels=" + paddingInPixels +
      ", paddingDimensionResId=" + paddingDimensionResId +
      ", fontName=" + fontName +
      ", fontNameResId=" + fontNameResId +
      '}';
  }
}
