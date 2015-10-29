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
		Log.e("ok", "����״̬�ı�");
		boolean success = false;
		// ����������ӷ���
		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// State state = connManager.getActiveNetworkInfo().getState();
		State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState(); // ��ȡ��������״̬
		if (State.CONNECTED == state) { // �ж��Ƿ�����ʹ��WIFI����
			success = true;
			Log.i("okok","true gprs");
		}
		state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState(); // ��ȡ��������״̬
		if (State.CONNECTED != state) { // �ж��Ƿ�����ʹ��GPRS����
			success = true;
			Log.i("okok","true gprs");
		}
		if (!success) {
			Toast.makeText(context, "���������������ж�", Toast.LENGTH_LONG).show();
		}
	}

}
