package de.neofonie.mobile.app.android.crouton.sample;

import de.neofonie.mobile.app.android.crouton.sample.R;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;
import de.neofonie.mobile.app.android.widget.crouton.Style;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity implements OnClickListener {

	EditText mEditText;
	Button mAlertButton;
	Button mConfirmButton;
	Button mInfoButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		mEditText = (EditText) findViewById(R.id.editText1);
		
		mAlertButton = (Button) findViewById(R.id.alert_button);
		mAlertButton.setOnClickListener(this);
		mConfirmButton = (Button) findViewById(R.id.confirm_button);
		mConfirmButton.setOnClickListener(this);
		mInfoButton = (Button) findViewById(R.id.info_button);
		mInfoButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Style style = Style.ALERT;
		
		if (v == mConfirmButton) {
			style = Style.CONFIRM;
		}
		else if (v == mInfoButton) {
			style = Style.INFO;
		}
		
		Crouton.makeText(this, mEditText.getText(), style).show();
	}

}
