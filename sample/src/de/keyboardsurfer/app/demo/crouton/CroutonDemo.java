package de.keyboardsurfer.app.demo.crouton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;
import de.neofonie.mobile.app.android.widget.crouton.Style;

public class CroutonDemo extends Activity implements OnClickListener {
	private EditText duration, text;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViewById(R.id.button_show).setOnClickListener(this);
		duration = (EditText) findViewById(R.id.edit_text_duration);
		text = (EditText) findViewById(R.id.edit_text_text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_show:
			final int durationValue = Integer.parseInt(duration.getText()
					.toString());
			final String textValue = text.getText().toString();
			// Display the Crouton with a custom style
			Crouton.makeText(this, textValue,
					new Style.Builder().setDuration(durationValue).build())
					.show();
			break;

		default:
			break;
		}

	}
}
