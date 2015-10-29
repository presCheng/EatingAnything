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

public class LoginActivity extends Activity {
	private TextView userName;
	private TextView passWord;
	private Button login;
	// 设置配置信息
		String PREFS_NAME = "eatAnything";
		SharedPreferences settings;
		SharedPreferences.Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
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
		userName = (TextView) this.findViewById(R.id.username);
		passWord = (TextView) this.findViewById(R.id.password);
		tittle.setText("登录");
		init();
	}

	private void init() {
		login = (Button) this.findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			 String u=	userName.getText().toString();
			 String p =passWord.getText().toString();
				if (isOK(u,p)){
					CustomToast.makeText(getApplicationContext(), "登录成功",
							CustomToast.LENGTH_SHORT).show();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Intent intent = getIntent();
					intent.putExtra("123456", "123456");
					LoginActivity.this.setResult(0, intent);
					LoginActivity.this.finish();
				} else {
					Toast.makeText(getApplicationContext(), "密码错误或帐号不存在",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	/**
	 * 驗證登陸信息
	 * @return
	 */
	private boolean isOK(String u,String p){
		settings = getApplicationContext().getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
		String pwd = settings.getString(u,"*");
		if(!pwd.equals("*")){
			if(p.equals(pwd)){
				return true;
			}
		}
		return false;
	}
}
