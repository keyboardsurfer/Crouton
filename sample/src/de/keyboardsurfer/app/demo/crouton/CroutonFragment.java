/*
 * Copyright 2012 Benjamin Weiss
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

package de.keyboardsurfer.app.demo.crouton;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import de.keyboardsurfer.android.widget.crouton.Style.Builder;

/**
 * @author keyboardsurfer
 * @since 14.12.12
 */
public class CroutonFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
   private CheckBox displayOnTop;
   private Spinner styleSpinner;
   private EditText croutonTextEdit;
   private EditText croutonDurationEdit;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      return inflater.inflate(R.layout.crouton_demo, null);
   }

   @Override
   public void onViewCreated(View view, Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      view.findViewById(R.id.button_show).setOnClickListener(this);
      croutonTextEdit = (EditText) view.findViewById(R.id.edit_text_text);
      croutonDurationEdit = (EditText) view.findViewById(R.id.edit_text_duration);
      styleSpinner = (Spinner) view.findViewById(R.id.spinner_style);
      styleSpinner.setOnItemSelectedListener(this);
      displayOnTop = (CheckBox) view.findViewById(R.id.display_on_top);
   }

   @Override
   public void onClick(View view) {
      switch (view.getId()) {
         case R.id.button_show: {
            showCrouton();
            break;
         }
         default: {
            break;
         }
      }
   }

   private void showCrouton() {
      Style croutonStyle = getSelectedStyleFromSpinner();

      if (croutonStyle != null) {
         showNonCustomCrouton();
      } else {
         showCustomCrouton();
      }
   }

   private Style getSelectedStyleFromSpinner() {

      switch ((int) styleSpinner.getSelectedItemId()) {
         case 0: {
            return Style.ALERT;
         }

         case 1: {
            return Style.CONFIRM;
         }

         case 2: {
            return Style.INFO;
         }

         case 3: {
            return new Builder().setDuration(5000).setBackgroundColorValue(Style.holoBlueLight)
                     .setHeight(LayoutParams.WRAP_CONTENT).setProgressEnabled(true).build();
         }

         default: {
            return null;
         }
      }
   }

   private void showNonCustomCrouton() {
      Style croutonStyle = getSelectedStyleFromSpinner();
      String croutonText = getCroutonText();

      showCrouton(croutonText, croutonStyle);
   }

   private String getCroutonText() {

      String croutonText = croutonTextEdit.getText().toString().trim();

      if (TextUtils.isEmpty(croutonText)) {
         croutonText = getString(R.string.text_demo);
      }

      return croutonText;
   }

   private void showCustomCrouton() {
      String croutonDurationString = getCroutonDurationString();

      if (TextUtils.isEmpty(croutonDurationString)) {
         showCrouton(getString(R.string.warning_duration), Style.ALERT);
         return;
      }

      int croutonDuration = Integer.parseInt(croutonDurationString);
      Style croutonStyle = new Style.Builder().setDuration(croutonDuration).build();

      String croutonText = getCroutonText();

      showCrouton(croutonText, croutonStyle);
   }

   private String getCroutonDurationString() {
      return croutonDurationEdit.getText().toString().trim();
   }

   private void showCrouton(String croutonText, Style croutonStyle) {
      final Crouton crouton;
      if (displayOnTop.isChecked()) {
         crouton = Crouton.makeText(getActivity(), croutonText, croutonStyle);
      } else {
         crouton = Crouton.makeText(getActivity(), croutonText, croutonStyle, R.id.alternate_view_group);
      }
      crouton.show();
   }

   @Override
   public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
      switch ((int) id) {
         case 4: {
            croutonDurationEdit.setVisibility(View.VISIBLE);
            break;
         }

         default: {
            croutonDurationEdit.setVisibility(View.GONE);
            break;
         }
      }
   }

   @Override
   public void onNothingSelected(AdapterView<?> adapterView) {
      /* no-op */
   }
}
