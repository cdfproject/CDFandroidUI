package cdfproject.com.github.CDFandroidUI;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import cdfproject.com.github.CDFandroidUI.view.LGradualView;
import cdfproject.com.github.CDFandroidUI.view.LPieView;
import cdfproject.com.github.CDFandroidUI.view.LProgressButton;
import cdfproject.com.github.CDFandroidUI.view.LThermometerView;
import cdfproject.com.github.CDFandroidUI.view.LXiuXiu;
import cdfproject.com.github.CDFandroidUI.R;
import cdfproject.com.github.CDFandroidUI.option.LLineChartViewOption;
import cdfproject.com.github.CDFandroidUI.option.LProgressButtonOption;
import cdfproject.com.github.CDFandroidUI.option.MyApplication;
import cdfproject.com.github.CDFandroidUI.view.LLineChartView;
import cdfproject.com.github.CDFandroidUI.view.LRadarView;
import cdfproject.com.github.CDFandroidUI.view.LScrollingTextView;
import cdfproject.com.github.CDFandroidUI.view.LSlideButtonView;

public class ViewTest extends Activity implements LScrollingTextView.LScrollingTextViewListener {

	private LPieView pieView;
	private LRadarView radarView;
	private LSlideButtonView buttonView,buttonView2,buttonView3;
	private LThermometerView thermometerView;
	private LProgressButton progressButton;
	private LXiuXiu lXiuXiu1,lXiuXiu2,lXiuXiu3,lXiuXiu4;
	private MyApplication application;
	private MyHandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		handler = new MyHandler();
		application = (MyApplication) getApplicationContext();
		switch (intent.getIntExtra("type", 0)) {
			case 0:
				setContentView(R.layout.pie);
				pieView = (LPieView) findViewById(R.id.pie);
				pieView.setGreenSize(394);
				pieView.setRedSize(765);
				break;
			case 2:
				setContentView(R.layout.radar);
				radarView = (LRadarView) findViewById(R.id.r);
				ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
				for (int i = 0; i < 7; i++) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put(LRadarView.VALUE_NAME, "丹凤" + i + "号");
					map.put(LRadarView.VALUE_PERCENT, i * 20);
					map.put(LRadarView.VALUE_VALUE, i * 20);
					list.add(map);
				}
				radarView.setDataArray(list);
				break;
			case 3:
				setContentView(R.layout.round);
				break;
			case 4:
				setContentView(R.layout.btn);
				buttonView = (LSlideButtonView) findViewById(R.id.b);
				buttonView.setOnSlideListener(new LSlideButtonView.SlideButtonViewListener() {
					@Override
					public void SlideButtonOnClick(LSlideButtonView SlideButtonView, boolean isOpen) {
						Toast.makeText(ViewTest.this, isOpen + "", Toast.LENGTH_SHORT).show();
					}
				});
				buttonView2 = (LSlideButtonView) findViewById(R.id.btn);
				buttonView2.setOnSlideListener(new LSlideButtonView.SlideButtonViewListener() {
					@Override
					public void SlideButtonOnClick(LSlideButtonView SlideButtonView, boolean isOpen) {
						Toast.makeText(ViewTest.this, isOpen + "", Toast.LENGTH_SHORT).show();
					}
				});
				buttonView3 = (LSlideButtonView) findViewById(R.id.btn2);
				buttonView3.setBtnType(false);
				buttonView3.setBtnColor(Color.WHITE);
				buttonView3.setOffColor(Color.GRAY);
				buttonView3.setTextColor(Color.GRAY);
				buttonView3.setOnSlideListener(new LSlideButtonView.SlideButtonViewListener() {
					@Override
					public void SlideButtonOnClick(LSlideButtonView SlideButtonView, boolean isOpen) {
						Toast.makeText(ViewTest.this, isOpen + "", Toast.LENGTH_SHORT).show();
					}
				});
				break;
			case 5:
				setContentView(R.layout.thermometer);
				thermometerView = (LThermometerView) findViewById(R.id.t);
				thermometerView.setMax(100);
				break;
			case 6:
				setContentView(R.layout.progress_button);
				progressButton = (LProgressButton) findViewById(R.id.pb);
				LProgressButtonOption.Builder builder = new LProgressButtonOption.Builder();

				builder.setBtnStartText("开始")
						.setBtnEndText("完成")
						.setShowPercent(true)
						.setBtnErrorIcon(R.mipmap.ic_launcher);

				progressButton.setOption(new LProgressButtonOption(builder));
				progressButton.setClickListener(new LProgressButton.LProgressButtonOnClickListener() {
					@Override
					public void LPBOnClick(LProgressButton btn) {
						switch (btn.getType()) {
							case LProgressButton.TYPE_END:
								progressButton.reset();
								break;
							case LProgressButton.TYPE_ERROR:
								progressButton.onEnd();
								break;
							case LProgressButton.TYPE_LOADING:
								progressButton.onError();
								break;
							case LProgressButton.TYPE_PREPARE:
								progressButton.onLoad(100, 100);
								break;
							case LProgressButton.TYPE_START:
								progressButton.onPrepare();
								// progressButton.onError();
								break;
							default:
								break;
						}
					}
				});
				break;
			case 15:
				setContentView(R.layout.xiuxiu);
				lXiuXiu1 = (LXiuXiu) findViewById(R.id.xiu1);
				lXiuXiu1.setStep(5);
				lXiuXiu2 = (LXiuXiu) findViewById(R.id.xiu2);
				lXiuXiu2.setXiuXiuType(LXiuXiu.XiuXiuType.IN);
				lXiuXiu3 = (LXiuXiu) findViewById(R.id.xiu3);
				lXiuXiu3.setStep(3);
				lXiuXiu3.setWaveType(LXiuXiu.WaveType.ALWAYS);
				lXiuXiu4 = (LXiuXiu) findViewById(R.id.xiu4);
				lXiuXiu4.setXiuXiuType(LXiuXiu.XiuXiuType.IN);
				lXiuXiu4.setWaveType(LXiuXiu.WaveType.ALWAYS);
				break;
			case 16:
				setContentView(R.layout.built_in_drawers);
				LinearLayout builtLeft = (LinearLayout) findViewById(R.id.built_in_left);
//				LinearLayout buildRight = (LinearLayout) findViewById(R.id.built_in_right);
				WindowManager m = getWindowManager();
				Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
				ViewGroup.LayoutParams p = builtLeft.getLayoutParams();// getWindow().getAttributes();
				p.width = (int) (d.getWidth() * 0.7);
				builtLeft.setLayoutParams(p);
//				buildRight.setLayoutParams(p);
				break;
			case 17:
				setContentView(R.layout.swipe_drawers);
				LinearLayout swipeLeft = (LinearLayout) findViewById(R.id.swipe_left);
				WindowManager m2 = getWindowManager();
				Display d2 = m2.getDefaultDisplay(); // 获取屏幕宽、高用
				ViewGroup.LayoutParams p2 = swipeLeft.getLayoutParams();// getWindow().getAttributes();
				p2.width = (int) (d2.getWidth() * 0.7);
				swipeLeft.setLayoutParams(p2);
				break;
			case 18:
				setContentView(R.layout.ripple);
				break;
			case 19:
				setContentView(new LGradualView(this));
				break;
			case 24:
				setContentView(R.layout.scrolling_text);
				LScrollingTextView lScrollingTextView1 = (LScrollingTextView) findViewById(R.id.text1);
				LScrollingTextView lScrollingTextView2 = (LScrollingTextView) findViewById(R.id.text2);
				LScrollingTextView lScrollingTextView3 = (LScrollingTextView) findViewById(R.id.text3);
				LScrollingTextView lScrollingTextView4 = (LScrollingTextView) findViewById(R.id.text4);
				ArrayList<String> text = new ArrayList<>();
				for(int i = 0;i<10;i++){
					text.add("这是第"+i+"条滚动条幅");
				}
				lScrollingTextView1.setText(text);
				lScrollingTextView2.setText(text);
				lScrollingTextView3.setText(text);
				lScrollingTextView4.setText(text);
				lScrollingTextView1.setScrolDirection(LScrollingTextView.ScrolDirection.LEFT);
				lScrollingTextView2.setScrolDirection(LScrollingTextView.ScrolDirection.RIGHT);
				lScrollingTextView3.setScrolDirection(LScrollingTextView.ScrolDirection.TOP);
				lScrollingTextView4.setScrolDirection(LScrollingTextView.ScrolDirection.Bottom);
				lScrollingTextView1.setlScrollingTextViewListener(this);
				lScrollingTextView2.setlScrollingTextViewListener(this);
				lScrollingTextView3.setlScrollingTextViewListener(this);
				lScrollingTextView4.setlScrollingTextViewListener(this);
				break;
			case 25:
				setContentView(R.layout.linechart);
				LLineChartView lLineChartView = (LLineChartView) findViewById(R.id.linechart);
				LLineChartView lLineChartView2 = (LLineChartView) findViewById(R.id.linechart2);
				LLineChartViewOption.Builder bu = new LLineChartViewOption.Builder();
				bu.setLable(new String[]{"周一","周二","周三","周四","周五","周六"});
				lLineChartView.setOption(new LLineChartViewOption(bu));
				bu.setIsCurve(false);
				lLineChartView2.setOption(new LLineChartViewOption(bu));
				ArrayList<LLineChartView.LLineChartBean> beans = new ArrayList<>();
				ArrayList<LLineChartView.LLineChartBean> beans2 = new ArrayList<>();
				LLineChartView.LLineChartBean b = lLineChartView.new LLineChartBean(new float[]{32,64,95,0,89,54},Color.WHITE);
				LLineChartView.LLineChartBean b2 = lLineChartView.new LLineChartBean(new float[]{85,36,18,56,32,17},Color.GREEN);
				beans.add(b);
				beans.add(b2);
				beans2.add(b2);
				beans2.add(b);
				lLineChartView.setBeans(beans);
				lLineChartView2.setBeans(beans2);
				break;
		}
	}
	private class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what){
				case 200:
					application.t((String) msg.obj);
					break;
			}
			super.handleMessage(msg);
		}
	}
	@Override
	public void onScrollingTextClick(View v, int i, String s) {
		Message message = new Message();
		message.what = 200;
		message.obj = "ViewID:"+v.getId()+"\n序号："+i+"\n内容："+s;
		handler.sendMessage(message);
	}
}
