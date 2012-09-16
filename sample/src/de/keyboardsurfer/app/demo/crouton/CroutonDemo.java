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

package de.keyboardsurfer.app.demo.crouton;


import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;
import de.neofonie.mobile.app.android.widget.crouton.Style;


public class CroutonDemo extends Activity implements OnClickListener, OnItemSelectedListener
{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		setUpShowButtonListener();
		setUpSpinnerListener();
	}

	private void setUpShowButtonListener() {
		Button showButton = (Button) findViewById(R.id.button_show);

		showButton.setOnClickListener(this);
	}

	private void setUpSpinnerListener() {
		Spinner styleSpinner = (Spinner) findViewById(R.id.spinner_style);

		styleSpinner.setOnItemSelectedListener(this);
	}

	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.button_show:
				showCrouton();
				break;

			default:
				break;
		}
	}

	private void showCrouton() {
		Style croutonStyle = getSelectedStyleFromSpinner();

		if (croutonStyle != null) {
			showNonCustomCrouton();
		}
		else {
			showCustomCrouton();
		}
	}

	private Style getSelectedStyleFromSpinner() {
		Spinner styleSpinner = (Spinner) findViewById(R.id.spinner_style);

		switch ((int) styleSpinner.getSelectedItemId()) {
			case 0:
				return Style.ALERT;

			case 1:
				return Style.CONFIRM;

			case 2:
				return Style.INFO;

			default:
				return null;
		}
	}

	private void showNonCustomCrouton() {
		Style croutonStyle = getSelectedStyleFromSpinner();
		String croutonText = getCroutonText();

		Crouton.makeText(this, croutonText, croutonStyle).show();
	}

	private String getCroutonText() {
		EditText croutonTextEdit = (EditText) findViewById(R.id.edit_text_text);

		String croutonText = croutonTextEdit.getText().toString().trim();

		if (TextUtils.isEmpty(croutonText)) {
			croutonText = getString(R.string.demo_text);
		}

		return croutonText;
	}

	private void showCustomCrouton() {
		String croutonDurationString = getCroutonDurationString();

		if (TextUtils.isEmpty(croutonDurationString)) {
			Crouton.makeText(this, R.string.missing_duration, Style.ALERT).show();
			return;
		}

		int croutonDuration = Integer.parseInt(croutonDurationString);
		Style croutonStyle = new Style.Builder().setDuration(croutonDuration).build();

		String croutonText = getCroutonText();

		Crouton.makeText(this, croutonText, croutonStyle).show();
	}

	private String getCroutonDurationString() {
		EditText croutonDurationEdit = (EditText) findViewById(R.id.edit_text_duration);

		return croutonDurationEdit.getText().toString().trim();
	}

	public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
		EditText croutonDurationEdit = (EditText) findViewById(R.id.edit_text_duration);

		switch ((int) id) {
			case 3:
				croutonDurationEdit.setVisibility(View.VISIBLE);
				break;

			default:
				croutonDurationEdit.setVisibility(View.GONE);
				break;
		}
	}

	public void onNothingSelected(AdapterView<?> adapterView) {
	}
}
