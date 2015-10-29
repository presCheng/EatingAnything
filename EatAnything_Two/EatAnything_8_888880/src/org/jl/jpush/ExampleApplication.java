package org.jl.jpush;

import com.example.eatanything_1_0_1.R;

import android.app.Application;
import android.app.Notification;
import android.util.Log;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

/**
 * For developer startup JPush SDK
 * 
 * һ�㽨�����Զ��� Application �����ʼ����Ҳ�������� Activity �
 */
public class ExampleApplication extends Application {
	private static final String TAG = "JPush";

	@Override
	public void onCreate() {
		Log.d(TAG, "[ExampleApplication] onCreate");
		super.onCreate();
		JPushInterface.setDebugMode(true); // ���ÿ�����־,����ʱ��ر���־
		JPushInterface.init(this); // ��ʼ�� JPush
		setStyleBasic();
	}

	// ������ʽ
	private void setStyleBasic() {
		BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(
				ExampleApplication.this);
		builder.statusBarDrawable = R.drawable.logo;
		builder.notificationFlags = Notification.FLAG_AUTO_CANCEL; // ����Ϊ������Զ���ʧ
		builder.notificationDefaults = Notification.DEFAULT_SOUND; // ����Ϊ������
																	// Notification.DEFAULT_SOUND�������𶯣�
																	// Notification.DEFAULT_VIBRATE��
		JPushInterface.setPushNotificationBuilder(1, builder);
		// Toast.makeText(ExampleApplication.this, "Basic Builder - 1",
		// Toast.LENGTH_SHORT).show();
	}
}
