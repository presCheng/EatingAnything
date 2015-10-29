package org.jl.custom;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends android.content.BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e("ok", "网络状态改变");
		boolean success = false;
		// 获得网络连接服务
		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// State state = connManager.getActiveNetworkInfo().getState();
		State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState(); // 获取网络连接状态
		if (State.CONNECTED == state) { // 判断是否正在使用WIFI网络
			success = true;
			Log.i("okok","true gprs");
		}
		state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState(); // 获取网络连接状态
		if (State.CONNECTED != state) { // 判断是否正在使用GPRS网络
			success = true;
			Log.i("okok","true gprs");
		}
		if (!success) {
			Toast.makeText(context, "您的网络连接已中断", Toast.LENGTH_LONG).show();
		}
	}

}
