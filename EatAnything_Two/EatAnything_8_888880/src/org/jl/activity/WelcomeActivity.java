package org.jl.activity;

import java.io.InputStream;

import org.jl.sqlite.CopySqliteForSdCard;
import org.jl.sqlite.DatabaseContext;
import org.jl.utils.UserUtils;

import cn.jpush.android.api.InstrumentedActivity;

import com.example.eatanything_1_0_1.R;
import com.umeng.analytics.MobclickAgent;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout;

/**
 * 
 * Class Name: WelcomeActivity.java Function: ��ӭ���� Modifications:
 * 
 * @author ��־�� DateTime 2014-4-8 ����8:19:05
 * @version 1.0
 */
public class WelcomeActivity extends InstrumentedActivity {
	Intent intent;
	// ����������Ϣ
	String PREFS_NAME = "eatAnything";
	SharedPreferences settings;
	SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		// MobclickAgent.setDebugMode(true);
		MobclickAgent.updateOnlineConfig(this);
		Log.i("umeng", UserUtils.getDeviceInfo(this));
		// getjson();
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

	/**
	 * 
	 * Function: ��ʼ����Ϣ
	 */
	private void init() {
		// ȥ����Ϣ��
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// ���ý���û�б�����
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_wel);
		AlphaAnimation animation = new AlphaAnimation(1.0f, 1.0f);
		// ����ʱ��
		animation.setDuration(2000);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				if (isFirst()) {
					writeSqlite();
					Log.i("qqq", "��һ�ν���");
					// ��һ�ν�����������
					intent = new Intent(WelcomeActivity.this,
							GuideActivity.class);
				} else {
					Log.i("qqq", "���ǵ�һ�ν���");
					intent = new Intent(WelcomeActivity.this,
							LeftOrRightActivity.class);
				}
				startActivity(intent);
				// ������activity����
				finish();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}
		});
		RelativeLayout relativeLayout = (RelativeLayout) this
				.findViewById(R.id.welcome);
		relativeLayout.setAnimation(animation);
	}

	/**
	 * 
	 * Function: ����������ж��ǲ��ǵ�һ�ΰ�װ����������ǵ�һ�εĻ��� �Ժ�Ͳ����ڰ�Ӧ���е����ݿ⵼���ļ�������
	 * 
	 * @return boolean
	 */
	private boolean isFirst() {
		settings = this.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
		if (settings.getBoolean("isFirst", true)) {
			editor = settings.edit();
			editor.putBoolean("isFirst", false);
			editor.commit();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Function: ��һ�ν���д��sql
	 * 
	 * @author ��־�� DateTime 2014-4-7 ����4:24:38
	 */
	private void writeSqlite() {
		String dataBase_name = "eatAnything.db3";
		InputStream input = getResources().openRawResource(R.raw.eatanything);
		String dbDir = DatabaseContext.getDbDir();
		String dbPath = DatabaseContext.getDbPath() + dataBase_name;
		CopySqliteForSdCard csfs = new CopySqliteForSdCard(input, dbDir, dbPath);
		csfs.copyFile();
	}
}
