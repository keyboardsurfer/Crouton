/*
 * Copyright 2012 Neofonie Mobile GmbH
 *  
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *  
 *         http://www.apache.org/licenses/LICENSE-2.0
 *  
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package de.keyboardsurfer.app.demo.crouton;

import android.widget.Spinner;
import android.widget.EditText;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.view.View.OnClickListener;
import android.view.View;
import android.text.TextUtils;
import android.os.Bundle;
import android.app.Activity;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;
import de.neofonie.mobile.app.android.widget.crouton.Style;

public class CroutonDemo extends Activity implements OnClickListener, OnItemSelectedListener {
  private EditText duration, text;
  private Spinner  styleSpinner;
  private View     customContainer;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    findViewById(R.id.button_show).setOnClickListener(this);
    duration = (EditText) findViewById(R.id.edit_text_duration);
    text = (EditText) findViewById(R.id.edit_text_text);
    styleSpinner = (Spinner) findViewById(R.id.spinner_style);
    styleSpinner.setOnItemSelectedListener(this);
    customContainer = findViewById(R.id.container_custom);
  }

  /*
   * (non-Javadoc)
   * 
   * @see android.view.View.OnClickListener#onClick(android.view.View)
   */
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.button_show:
        final String textValue = TextUtils.isEmpty(text.getText()) ? getString(R.string.demo_text)
                                                                  : text.getText().toString();
        // Display the Crouton with a custom style
        Style style = getSelectedStyleFromSpinner();
        if (style == null) {
          if (TextUtils.isEmpty(duration.getText())) {
            Crouton.makeText(this, R.string.missing_duration, Style.ALERT).show();
            break;
          } else {
            final int durationValue = Integer.parseInt(duration.getText().toString());
            style = new Style.Builder().setDuration(durationValue).build();
          }
        }
        Crouton.makeText(this, textValue, style).show();

        break;

      default:
        break;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android
   * .widget.AdapterView, android.view.View, int, long)
   */
  public void onItemSelected(AdapterView<?> adapterView, View view, int arg2, long position) {
    if (position == 3) {
      customContainer.setVisibility(View.VISIBLE);
    } else {
      customContainer.setVisibility(View.GONE);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android
   * .widget.AdapterView)
   */
  public void onNothingSelected(AdapterView<?> adapterView) {
    // NOTHING
  }

  /**
   * @return The selected item from the spinner or null, if custom or none was selected.
   */
  private Style getSelectedStyleFromSpinner() {
    long selectedItem = styleSpinner.getSelectedItemId();
    if (selectedItem == 0) {
      return Style.ALERT;
    } else if (selectedItem == 1) {
      return Style.CONFIRM;
    } else if (selectedItem == 2) {
      return Style.INFO;
    }
    return null;
  }
}
