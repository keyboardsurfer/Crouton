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
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
	private ImageView background;

	private static ViewHolder holder;

	private ViewHolder(Crouton crouton) {
		if (sParams == null) {
			sParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.FILL_PARENT);
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
		holder.view.setBackgroundColor(crouton.getActivity().getResources()
				.getColor(crouton.getStyle().color));
		
		if (crouton.getStyle().textColor != 0) {
			holder.text.setTextColor(crouton.getActivity().getResources()
					.getColor(crouton.getStyle().textColor));
		} else {
			holder.text.setTextColor(sDefaultTextColor);
		}
		
		if (crouton.getStyle().background != 0) {
			Bitmap bm = BitmapFactory.decodeResource(crouton.getActivity().getResources(),
					crouton.getStyle().background);
			BitmapDrawable bd = new BitmapDrawable(crouton.getActivity().getResources(), bm);
			
			if (crouton.getStyle().tile)
				bd.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
			
			holder.background.setBackgroundDrawable(bd);
		} else {
			holder.background.setBackgroundDrawable(null);
		}
			
		return holder.view;
	}

	private void initView(Crouton crouton) {
		view = new FrameLayout(crouton.getActivity());
		text = new TextView(crouton.getActivity());
		background = new ImageView(crouton.getActivity());
		
		view.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, crouton.getStyle().height));
		
		text.setText(crouton.getText());
		text.setLayoutParams(sParams);
		text.setTypeface(Typeface.DEFAULT_BOLD);
		text.setPadding(PADDING, PADDING, PADDING, PADDING);
		text.setGravity(Gravity.CENTER);
		
		background.setLayoutParams(sParams);
		
		view.addView(background);
		view.addView(text);
	}
}
