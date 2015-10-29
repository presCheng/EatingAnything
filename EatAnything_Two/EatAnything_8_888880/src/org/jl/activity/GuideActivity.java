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
 * ������  : ��һ������ʱ����������
 * @author ���䴺
 *
 */
public class GuideActivity extends FragmentActivity {
	private DirectionalViewPager mDirectionalViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ȥ����Ϣ��
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// ���ý���û�б�����
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		// Set up the pager
		mDirectionalViewPager = (DirectionalViewPager) findViewById(R.id.pager);
		mDirectionalViewPager.setAdapter(new GuideFragmentAdapter(
				getSupportFragmentManager()));
		//���� pager ���ϻ���
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
