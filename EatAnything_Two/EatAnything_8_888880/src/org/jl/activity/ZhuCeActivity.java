package org.jl.activity;

import org.jl.custom.CustomToast;

import com.example.eatanything_1_0_1.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 登录界面
 * 
 * @author acer
 * 
 */

public class ZhuCeActivity extends Activity {
	private TextView zhuce_userName;
	private TextView zhuce_passWord;
	private TextView zhuce_repassWord;
	private Button zhuce_btn_zhuce;

	// 设置配置信息
	String PREFS_NAME = "eatAnything";
	SharedPreferences settings;
	SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_zhuce);
		// 设置导航栏事件
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
		tittle.setText("注冊");
		init();
	}

	private void init() {
		zhuce_userName = (TextView) this.findViewById(R.id.zhuce_username);
		zhuce_passWord = (TextView) this.findViewById(R.id.zhuce_password);
		zhuce_repassWord = (TextView) this.findViewById(R.id.zhuce_repassword);
		zhuce_btn_zhuce = (Button) this.findViewById(R.id.zhuce_btn_zhuce);
		zhuce_btn_zhuce.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String userName = zhuce_userName.getText().toString();
				String password = zhuce_passWord.getText().toString();
				String repassword = zhuce_repassWord.getText().toString();
				if (password.equals(repassword) && !userName.equals("")
						&& !password.equals("") && !repassword.equals("")) {
					CustomToast.makeText(getApplicationContext(), "注冊成功",
							CustomToast.LENGTH_SHORT).show();
					settings = getApplicationContext().getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
					editor = settings.edit();
					editor.putString(zhuce_userName.getText().toString(),
							zhuce_passWord.getText().toString());
					editor.commit();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

//					Intent intent = getIntent();
//
//					ZhuCeActivity.this.setResult(0, intent);
//					ZhuCeActivity.this.finish();
					finish();
				} else {
					Toast.makeText(getApplicationContext(), "輸入不正確",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
