package org.jl.activity;

import com.example.eatanything_1_0_1.R;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ý���û�б�����
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_about);
		initview();
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	public void initview() {

		// ���õ������¼�
		LinearLayout layout = (LinearLayout) this
				.findViewById(R.id.include_about);
		layout.findViewById(R.id.img_l).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				});
		TextView tittle = (TextView) layout.findViewById(R.id.room_m);
		tittle.setText("���ڳ�ʲô");
		LinearLayout layoutAboutUs = (LinearLayout) this
				.findViewById(R.id.aboutUs);
		layoutAboutUs.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(AboutActivity.this,
						AboutUsActivity.class);
				startActivity(intent);
				// ������activity����
				finish();
			}
		});
		LinearLayout layoutFeedback = (LinearLayout) this
				.findViewById(R.id.feedback);
		layoutFeedback.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(AboutActivity.this,
						FeedbackActivity.class);
				startActivity(intent);
				// ������activity����
				finish();
			}
		});

	}
}
