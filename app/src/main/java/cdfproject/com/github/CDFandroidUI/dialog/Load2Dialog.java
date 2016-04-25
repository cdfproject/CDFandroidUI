package cdfproject.com.github.CDFandroidUI.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import cdfproject.com.github.CDFandroidUI.R;
import cdfproject.com.github.CDFandroidUI.option.LLoadView2Option;
import cdfproject.com.github.CDFandroidUI.view.LLoadView2;

public class Load2Dialog extends Dialog {

	private LLoadView2 load2;

	public Load2Dialog(Context context) {
		super(context);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去除屏幕title
		setContentView(R.layout.dialog_load2);
		getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		load2 = (LLoadView2) findViewById(R.id.load2);
		load2.setOption(new LLoadView2Option(new LLoadView2Option.Builder()));
	}
}
