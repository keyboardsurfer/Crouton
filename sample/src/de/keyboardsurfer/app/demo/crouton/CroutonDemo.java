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
package de.keyboardsurfer.app.demo.crouton;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;
import de.neofonie.mobile.app.android.widget.crouton.Style;


public class CroutonDemo extends Activity implements OnClickListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		findViewById(R.id.button_show).setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button_show:
				showCrouton();
				break;

			default:
				break;
		}
	}

	private void showCrouton() {
		int croutonDuration = readCroutonDuration();
		String croutonText = readCroutonText();

		// Display the Crouton with a custom style
		Crouton.makeText(this, croutonText,
			new Style.Builder().setDuration(croutonDuration).build()).show();
	}

	private int readCroutonDuration() {
		EditText croutonDurationEdit = (EditText) findViewById(
			R.id.edit_text_duration);

		try {
			String croutonDuration = croutonDurationEdit.getText().toString();
			return Integer.parseInt(croutonDuration);
		}
		catch (NumberFormatException e) {
			return 0;
		}
	}

	private String readCroutonText() {
		EditText croutonTextEdit = (EditText) findViewById(R.id.edit_text_text);

		return croutonTextEdit.getText().toString();
	}
}
