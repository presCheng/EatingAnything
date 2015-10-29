package org.jl.activity;

import com.directionalviewpager.DirectionalViewPager;
import com.example.eatanything_1_0_1.R;
import com.umeng.analytics.MobclickAgent;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import org.jl.custom.GuideFragmentAdapter;
/**
 * 类描述  : 第一次下载时的引导界面
 * @author 程其春
 *
 */
public class GuideActivity extends FragmentActivity {
	private DirectionalViewPager mDirectionalViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去掉信息栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 设置界面没有标题栏
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		// Set up the pager
		mDirectionalViewPager = (DirectionalViewPager) findViewById(R.id.pager);
		mDirectionalViewPager.setAdapter(new GuideFragmentAdapter(
				getSupportFragmentManager()));
		//设置 pager 向上滑动
		mDirectionalViewPager.setOrientation(DirectionalViewPager.VERTICAL);
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

}
