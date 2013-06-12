/*
 * Copyright 2012 - 2013 Benjamin Weiss
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
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * @author keyboardsurfer
 * @since 14.12.12
 */
public class CroutonFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

  private static final Style INFINITE = new Style.Builder().
    setBackgroundColorValue(Style.holoBlueLight).build();
  private static final Configuration CONFIGURATION_INFINITE = new Configuration.Builder()
          .setDuration(Configuration.DURATION_INFINITE)
          .build();

  private CheckBox displayOnTop;
  private Spinner styleSpinner;
  private EditText croutonTextEdit;
  private EditText croutonDurationEdit;
  private Crouton infiniteCrouton;

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
        if (infiniteCrouton != null) {
          Crouton.hide(infiniteCrouton);
          infiniteCrouton = null;
        }
        break;
      }
    }
  }

  private void showCrouton() {
    Style croutonStyle = getSelectedStyleFromSpinner();

    if (croutonStyle != null) {
      showBuiltInCrouton(croutonStyle);
    } else {
      showAdvancedCrouton();
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
        return INFINITE;
      }

      default: {
        return null;
      }
    }
  }
  
  private String getCroutonText() {
    String croutonText = croutonTextEdit.getText().toString().trim();

    if (TextUtils.isEmpty(croutonText)) {
      croutonText = getString(R.string.text_demo);
    }
    return croutonText;
  }

  private void showBuiltInCrouton(final Style croutonStyle) {
    String croutonText = getCroutonText();
    showCrouton(croutonText, croutonStyle, Configuration.DEFAULT);
  }

  private void showAdvancedCrouton() {
    switch (styleSpinner.getSelectedItemPosition()) {
      case 4: {
        showCustomCrouton();
        break;
      }

      case 5: {
        showCustomViewCrouton();
        break;
      }
    }
  }

  private void showCustomCrouton() {
    String croutonDurationString = getCroutonDurationString();

    if (TextUtils.isEmpty(croutonDurationString)) {
      showCrouton(getString(R.string.warning_duration), Style.ALERT, Configuration.DEFAULT);
      return;
    }

    int croutonDuration = Integer.parseInt(croutonDurationString);
    Style croutonStyle = new Style.Builder().build();
    Configuration croutonConfiguration = new Configuration.Builder().setDuration(croutonDuration).build();

    String croutonText = getCroutonText();

    showCrouton(croutonText, croutonStyle, croutonConfiguration);
  }

  private void showCustomViewCrouton() {
    View view = getLayoutInflater(null).inflate(R.layout.crouton_custom_view, null);
    final Crouton crouton;
    if (displayOnTop.isChecked()) {
      crouton = Crouton.make(getActivity(), view);
    } else {
      crouton = Crouton.make(getActivity(), view, R.id.alternate_view_group);
    }
    crouton.show();
  }

  private String getCroutonDurationString() {
    return croutonDurationEdit.getText().toString().trim();
  }

  private void showCrouton(String croutonText, Style croutonStyle, Configuration configuration) {
    final boolean infinite = INFINITE == croutonStyle;
    
    if (infinite) {
      croutonText = getString(R.string.infinity_text);
    }
    
    final Crouton crouton;
    if (displayOnTop.isChecked()) {
      crouton = Crouton.makeText(getActivity(), croutonText, croutonStyle);
    } else {
      crouton = Crouton.makeText(getActivity(), croutonText, croutonStyle, R.id.alternate_view_group);
    }
    if (infinite) {
      infiniteCrouton = crouton;
    }
    crouton.setOnClickListener(this).setConfiguration(infinite ? CONFIGURATION_INFINITE : configuration).show();
  }

  @Override
  public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
    switch ((int) id) {
      
      case 4: { // Custom Style
        croutonTextEdit.setVisibility(View.VISIBLE);
        croutonDurationEdit.setVisibility(View.VISIBLE);
        break;
      }

      case 5: { // Custom View
        croutonTextEdit.setVisibility(View.GONE);
        croutonDurationEdit.setVisibility(View.GONE);
        break;
      }

      default: {
        croutonTextEdit.setVisibility(View.VISIBLE);
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
