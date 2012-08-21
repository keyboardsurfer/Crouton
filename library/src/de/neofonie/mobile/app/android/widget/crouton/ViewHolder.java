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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

/**
 * ViewHolder <br>
 * <br>
 * <br>
 * The {@link ViewHolder} contains a view that can be used to display a
 * {@link Crouton}.
 * 
 * @author weiss@neofonie.de
 * 
 */
final class ViewHolder {
	private static final int PADDING = 10;
	private static int sDefaultTextColor;
	private static LayoutParams sParams;
	private FrameLayout view;
	private TextView text;

	private static ViewHolder holder;

	private ViewHolder(Crouton crouton) {
		if (sParams == null) {
			sParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            sParams.gravity = Gravity.CENTER;
		}
		
		if (sDefaultTextColor == 0) {
			sDefaultTextColor = new TextView(crouton.getActivity()).getTextColors().getDefaultColor();
		}
		
		initView(crouton);
	}

	/**
	 * Creates a view for a {@link Crouton}.
	 * 
	 * @param crouton
	 *            The {@link Crouton} that the view should be attached to.
	 * @return The view for this {@link Crouton};
	 */
	public static View viewForCrouton(Crouton crouton) {
		if (holder == null) {
			holder = new ViewHolder(crouton);
		} else {
			holder.text.setText(crouton.getText());
		}

        // Set the text color. If the user has set a text color and text appearance, the color in the text appearance
        // will override this.
        if (crouton.getStyle().textColorResId != 0) {
            holder.text.setTextColor(crouton.getActivity().getResources()
                    .getColor(crouton.getStyle().textColorResId));
        } else {
            holder.text.setTextColor(sDefaultTextColor);
        }

        // Set the text size. If the user has set a text size and text appearance, the text size in the text appearance
        // will override this.
        if (crouton.getStyle().textSize != 0) {
            holder.text.setTextSize(TypedValue.COMPLEX_UNIT_SP, crouton.getStyle().textSize);
        }

        // Setup the shadow if requested
        if (crouton.getStyle().textShadowColorResId != 0) {
            int textShadowColor = crouton.getActivity().getResources().getColor(crouton.getStyle().textShadowColorResId);
            float textShadowRadius = crouton.getStyle().textShadowRadius;
            float textShadowDx = crouton.getStyle().textShadowDx;
            float textShadowDy = crouton.getStyle().textShadowDy;
            holder.text.setShadowLayer(textShadowRadius, textShadowDx, textShadowDy, textShadowColor);
        }

        // Set the text appearance
        if (crouton.getStyle().textAppearanceResId != 0) {
            holder.text.setTextAppearance(crouton.getActivity(), crouton.getStyle().textAppearanceResId);
        }

        // Set the background color. If the user hasn't set this just set it to be transparent
        if (crouton.getStyle().backgroundColorResId != 0) {
            holder.view.setBackgroundColor(crouton.getActivity().getResources()
                    .getColor(crouton.getStyle().backgroundColorResId));
        } else {
            holder.view.setBackgroundColor(0x00000000);
        }

        // Set the background resource. This will override any background color previously set
		if (crouton.getStyle().backgroundResId != 0) {
			Bitmap bm = BitmapFactory.decodeResource(crouton.getActivity().getResources(),
					crouton.getStyle().backgroundResId);
			BitmapDrawable bd = new BitmapDrawable(crouton.getActivity().getResources(), bm);
			
			if (crouton.getStyle().tile)
				bd.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
			
			holder.view.setBackgroundDrawable(bd);
		} else {
			holder.view.setBackgroundDrawable(null);
		}
			
		return holder.view;
	}

	private void initView(Crouton crouton) {
		view = new FrameLayout(crouton.getActivity());
		text = new TextView(crouton.getActivity());

		view.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, crouton.getStyle().height));
		
		text.setText(crouton.getText());
		text.setLayoutParams(sParams);
		text.setTypeface(Typeface.DEFAULT_BOLD);
		text.setPadding(PADDING, PADDING, PADDING, PADDING);
		text.setGravity(Gravity.CENTER);

		view.addView(text);
	}
}
