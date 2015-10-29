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
 * 一般建议在自定义 Application 类里初始化。也可以在主 Activity 里。
 */
public class ExampleApplication extends Application {
	private static final String TAG = "JPush";

	@Override
	public void onCreate() {
		Log.d(TAG, "[ExampleApplication] onCreate");
		super.onCreate();
		JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志
		JPushInterface.init(this); // 初始化 JPush
		setStyleBasic();
	}

	// 设置样式
	private void setStyleBasic() {
		BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(
				ExampleApplication.this);
		builder.statusBarDrawable = R.drawable.logo;
		builder.notificationFlags = Notification.FLAG_AUTO_CANCEL; // 设置为点击后自动消失
		builder.notificationDefaults = Notification.DEFAULT_SOUND; // 设置为铃声（
																	// Notification.DEFAULT_SOUND）或者震动（
																	// Notification.DEFAULT_VIBRATE）
		JPushInterface.setPushNotificationBuilder(1, builder);
		// Toast.makeText(ExampleApplication.this, "Basic Builder - 1",
		// Toast.LENGTH_SHORT).show();
	}
}
