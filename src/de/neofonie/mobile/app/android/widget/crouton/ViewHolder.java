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

import android.graphics.Typeface;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

/**
 * ViewHolder <br>
 * <br>
 * <br>
 * The {@link ViewHolder} contains a view that can be used to display a {@link Crouton}.
 * 
 * @author weiss@neofonie.de
 * 
 */
final class ViewHolder {
  private static final int          PADDING = 10;
  private static final LayoutParams PARAMS  = new LayoutParams(LayoutParams.MATCH_PARENT,
                                                               LayoutParams.WRAP_CONTENT);
  private TextView                  view;

  private static ViewHolder         holder;

  private ViewHolder(Crouton crouton) {
    initView(crouton);
  }

  public static TextView viewForCrouton(Crouton crouton) {
    if (holder == null) {
      holder = new ViewHolder(crouton);
    } else {
      holder.view.setText(crouton.getText());
    }
    holder.view.setBackgroundColor(crouton.getActivity().getResources().getColor(crouton.getStyle().color));
    return holder.view;
  }

  private void initView(Crouton crouton) {
    view = new TextView(crouton.getActivity());
    view.setText(crouton.getText());
    view.setLayoutParams(PARAMS);
    view.setTypeface(Typeface.DEFAULT_BOLD);
    view.setPadding(PADDING, PADDING, PADDING, PADDING);
    view.setGravity(Gravity.CENTER);
  }
}
