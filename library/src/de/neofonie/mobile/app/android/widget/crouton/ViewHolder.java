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

import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Typeface;
import android.graphics.Shader;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.content.res.Resources;
import android.app.Activity;

/**
 * ViewHolder <br>
 * <br>
 * <br>
 * The {@link ViewHolder} contains a view that can be used to display a {@link Crouton}.
 */
final class ViewHolder {
  private static final int                   IMAGE_PADDING = 10;

  private static int                         defaultTextColor;
  private static RelativeLayout.LayoutParams layoutParams;

  private RelativeLayout                     view;
  private TextView                           text;
  private ImageView                          background;
  private ImageView                          image;

  private static ViewHolder                  viewHolder;

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

    return text;
  }

  private RelativeLayout.LayoutParams buildTextLayoutParams(Crouton crouton) {
    RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(layoutParams);

    if (crouton.getStyle().heightInPixels > 0) {
      textLayoutParams.setMargins(crouton.getStyle().heightInPixels, 0, 0, 0);
    }

    return textLayoutParams;
  }

  private ImageView buildImage(Crouton crouton) {
    ImageView image = new ImageView(crouton.getActivity());

    image.setPadding(IMAGE_PADDING, IMAGE_PADDING, IMAGE_PADDING, IMAGE_PADDING);
    image.setAdjustViewBounds(true);
    image.setScaleType(ImageView.ScaleType.FIT_XY);

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
   * @param crouton
   *          The {@link Crouton} that the view should be attached to.
   * @return The view for this {@link Crouton};
   */
  public static View buildViewForCrouton(Crouton crouton) {
    setUpViewHolder(crouton);

    viewHolder.view.setBackgroundColor(crouton.getStyle().colorResourceId);
    viewHolder.text.setTextColor(getCroutonTextColor(crouton));
    viewHolder.background.setBackgroundDrawable(getCroutonBackground(crouton));
    if (crouton.getStyle().image != null) {
      viewHolder.image.setImageDrawable(crouton.getStyle().image);
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

  private static int getCroutonTextColor(Crouton crouton) {
    if (crouton.getStyle().textColorResourceId == 0) {
      return defaultTextColor;
    }

    Activity croutonActivity = crouton.getActivity();
    int croutonTextColorResourceId = crouton.getStyle().textColorResourceId;

    return croutonActivity.getResources().getColor(croutonTextColorResourceId);
  }

  private static Drawable getCroutonBackground(Crouton crouton) {
    if (crouton.getStyle().backgroundResourceId == 0) {
      return null;
    }

    Resources croutonActivityResources = crouton.getActivity().getResources();

    Bitmap backgroundBitmap = BitmapFactory.decodeResource(croutonActivityResources,
                                                           crouton.getStyle().backgroundResourceId);
    BitmapDrawable backgroundDrawable = new BitmapDrawable(croutonActivityResources, backgroundBitmap);

    if (crouton.getStyle().isTileEnabled) {
      backgroundDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    }

    return backgroundDrawable;
  }
}
