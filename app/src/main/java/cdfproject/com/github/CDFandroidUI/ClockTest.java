package cdfproject.com.github.CDFandroidUI;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import cdfproject.com.github.CDFandroidUI.view.LClockView;

public class ClockTest extends Activity implements LClockView.ClockViewListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LClockView clockView = new LClockView(this);
		setContentView(clockView);
		Intent intent = getIntent();
		clockView.setType(intent.getIntExtra("type", LClockView.TYPE_HOURS));
	}

	@Override
	public void onClockChange(int t) {
		Toast.makeText(this, ""+t, Toast.LENGTH_SHORT).show();
	}
}
