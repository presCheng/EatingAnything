package org.jl.activity;

import java.util.LinkedList;
import java.util.List;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class MyApplication extends Application{
	static MyApplication myApplication;
	BMapManager mBMapManager = null;
	String mStrKey = "BB4FEFE6EE3ADDA18993E36DC6A4F4827F30C604";
	boolean m_bKeyRight = true; // ��ȨKey��ȷ����֤ͨ��
	private List<Activity> activityList = new LinkedList<Activity>();

	@Override
	public void onCreate() {
		myApplication = this;

		initEngineManager(this);
		super.onCreate();
	}

	public void initEngineManager(Context context) {
		if (mBMapManager == null) {
			mBMapManager = new BMapManager(context);
			Toast.makeText(MyApplication.getInstance().getApplicationContext(),
					"BMapManager  ��ʼ��SUCSUC!", Toast.LENGTH_LONG).show();
		}

		if (!mBMapManager.init(mStrKey, new MyGeneralListener())) {
			Toast.makeText(MyApplication.getInstance().getApplicationContext(),
					"BMapManager  ��ʼ������!", Toast.LENGTH_LONG).show();
		}
	}

	public static MyApplication getInstance() {
		if (myApplication == null) {
			myApplication = new MyApplication();
		}
		return myApplication;
	}

	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	public void exit() {

		for (Activity activity : activityList) {
			activity.finish();
		}

		System.exit(0);

	}

	// �����¼���������������ͨ�������������Ȩ��֤�����
	static class MyGeneralListener implements MKGeneralListener {

		public void onGetNetworkState(int iError) {
			if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
				Toast.makeText(
						MyApplication.getInstance().getApplicationContext(),
						"���������������", Toast.LENGTH_LONG).show();
			} else if (iError == MKEvent.ERROR_NETWORK_DATA) {
				Toast.makeText(
						MyApplication.getInstance().getApplicationContext(),
						"������ȷ�ļ���������", Toast.LENGTH_LONG).show();
			}
			// ...
		}

		public void onGetPermissionState(int iError) {
			if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
				// ��ȨKey����
				Toast.makeText(
						MyApplication.getInstance().getApplicationContext(),
						"���� DemoApplication.java�ļ�������ȷ����ȨKey��",
						Toast.LENGTH_LONG).show();
				MyApplication.getInstance().m_bKeyRight = false;
			}
		}

	}
}
