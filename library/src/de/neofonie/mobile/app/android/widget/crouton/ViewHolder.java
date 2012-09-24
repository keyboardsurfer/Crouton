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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.graphics.drawable.Drawable;
import android.content.res.Resources;
import android.app.Activity;

/**
 * The {@link ViewHolder} contains a view that can be used to display a {@link Crouton}.
 */
final class ViewHolder {
  private static final int IMAGE_PADDING = 10;

  private static int defaultTextColor;
  private static RelativeLayout.LayoutParams layoutParams;

  private RelativeLayout view;
  private TextView text;
  private ImageView background;
  private ImageView image;

  private static ViewHolder viewHolder;

  private ViewHolder(Crouton crouton) {
    setUpLayoutParams(crouton);
    setUpDefaultTextColor(crouton);

    setUpView(crouton);
  }

  private void setUpLayoutParams(Crouton crouton) {
    if (layoutParams != null) {
      return;
    }

    int croutonHeightInPixels = crouton.getStyle().heightInPixels;
    layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, croutonHeightInPixels);
  }

  private void setUpDefaultTextColor(Crouton crouton) {
    if (defaultTextColor != 0) {
      return;
    }

    TextView defaultTextView = new TextView(crouton.getActivity());
    defaultTextColor = defaultTextView.getTextColors().getDefaultColor();
  }

  private void setUpView(Crouton crouton) {
    view = buildView(crouton);
    text = buildText(crouton);
    image = buildImage(crouton);
    background = buildBackground(crouton);

    view.addView(background);
    view.addView(text);
    view.addView(image, buildImageLayoutParams(crouton));
  }

  private RelativeLayout buildView(Crouton crouton) {
    RelativeLayout view = new RelativeLayout(crouton.getActivity());

    view.setLayoutParams(buildViewLayoutParams(crouton));

    return view;
  }

  private RelativeLayout.LayoutParams buildViewLayoutParams(Crouton crouton) {
    return new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, crouton.getStyle().heightInPixels);
  }

  private TextView buildText(Crouton crouton) {
    TextView text = new TextView(crouton.getActivity());

    text.setLayoutParams(buildTextLayoutParams(crouton));
    text.setText(crouton.getText());
    text.setTypeface(Typeface.DEFAULT_BOLD);
    text.setPadding(IMAGE_PADDING, IMAGE_PADDING, IMAGE_PADDING, IMAGE_PADDING);
    text.setGravity(crouton.getStyle().gravity);

    // Set the text size. If the user has set a text size and text appearance, the text size in the text appearance
    // will override this.
    if (crouton.getStyle().textSize != 0) {
      text.setTextSize(TypedValue.COMPLEX_UNIT_SP, crouton.getStyle().textSize);
    }

    // Setup the shadow if requested
    if (crouton.getStyle().textShadowColorResId != 0) {
      int textShadowColor = crouton.getActivity().getResources().getColor(crouton.getStyle().textShadowColorResId);
      float textShadowRadius = crouton.getStyle().textShadowRadius;
      float textShadowDx = crouton.getStyle().textShadowDx;
      float textShadowDy = crouton.getStyle().textShadowDy;
      text.setShadowLayer(textShadowRadius, textShadowDx, textShadowDy, textShadowColor);
    }

    // Set the text appearance
    if (crouton.getStyle().textAppearanceResId != 0) {
      text.setTextAppearance(crouton.getActivity(), crouton.getStyle().textAppearanceResId);
    }

    return text;
  }

  private RelativeLayout.LayoutParams buildTextLayoutParams(Crouton crouton) {
    RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(layoutParams);

    return textLayoutParams;
  }

  private ImageView buildImage(Crouton crouton) {
    ImageView image = new ImageView(crouton.getActivity());

    image.setPadding(IMAGE_PADDING, IMAGE_PADDING, IMAGE_PADDING, IMAGE_PADDING);
    image.setAdjustViewBounds(true);
    image.setScaleType(crouton.getStyle().imageScaleType);

    return image;
  }

  private ImageView buildBackground(Crouton crouton) {
    ImageView background = new ImageView(crouton.getActivity());

    background.setLayoutParams(layoutParams);

    return background;
  }

  private RelativeLayout.LayoutParams buildImageLayoutParams(Crouton crouton) {
    int croutonHeightInPixels = crouton.getStyle().heightInPixels;

    RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(croutonHeightInPixels,
        croutonHeightInPixels);

    imageLayoutParams.addRule(RelativeLayout.LEFT_OF, text.getId());

    return imageLayoutParams;
  }

  /**
   * Creates a view for a {@link Crouton}.
   *
   * @param crouton The {@link Crouton} that the view should be attached to.
   * @return The view for this {@link Crouton};
   */
  public static View buildViewForCrouton(Crouton crouton) {
    setUpViewHolder(crouton);

    viewHolder.view.setBackgroundColor(getCroutonBackgroundColor(crouton));
    viewHolder.text.setTextColor(getCroutonTextColor(crouton));
    viewHolder.background.setBackgroundDrawable(getCroutonBackgroundDrawable(crouton));
    if (crouton.getStyle().imageDrawable != null) {
      viewHolder.image.setImageDrawable(crouton.getStyle().imageDrawable);
    }
    if (crouton.getStyle().imageResId != 0) {
      viewHolder.image.setImageResource(crouton.getStyle().imageResId);
    }

    return viewHolder.view;
  }

  private static void setUpViewHolder(Crouton crouton) {
    if (viewHolder == null) {
      viewHolder = new ViewHolder(crouton);
    } else {
      viewHolder.text.setText(crouton.getText());
    }
  }

  private static int getCroutonBackgroundColor(Crouton crouton) {
    Resources croutonActivityResources = crouton.getActivity().getResources();

    return croutonActivityResources.getColor(crouton.getStyle().backgroundColorResourceId);
  }

  private static int getCroutonTextColor(Crouton crouton) {
    if (crouton.getStyle().textColorResourceId == 0) {
      return defaultTextColor;
    }

    Activity croutonActivity = crouton.getActivity();
    int croutonTextColorResourceId = crouton.getStyle().textColorResourceId;

    return croutonActivity.getResources().getColor(croutonTextColorResourceId);
  }

  private static Drawable getCroutonBackgroundDrawable(Crouton crouton) {
    if (crouton.getStyle().backgroundDrawableResourceId == 0) {
      return null;
    }

    Resources croutonActivityResources = crouton.getActivity().getResources();

    Bitmap backgroundBitmap = BitmapFactory.decodeResource(croutonActivityResources,
        crouton.getStyle().backgroundDrawableResourceId);
    BitmapDrawable backgroundDrawable = new BitmapDrawable(croutonActivityResources, backgroundBitmap);

    if (crouton.getStyle().isTileEnabled) {
      backgroundDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    }

    return backgroundDrawable;
  }
}
