package org.jl.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jl.domain.MenuName;
import org.jl.domain.Restaurant;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

/**
 * Utility methods for Views.
 */
public class UserUtils {
	private UserUtils() {
	}

	/**
	 * 
	 * Function: ����json��֮���listview��ֵ
	 * 
	 * @author ��־�� DateTime 2014-4-7 ����1:36:54
	 * @param context
	 *            ����������
	 * @param listView
	 *            �����listview
	 * @param jsonData
	 *            �����json����
	 */
	public static void initListView(final Context context, ListView listView,
			List<Restaurant> resList) {

	}

	public static List<Map<String, Object>> getData(List<MenuName> menuList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < menuList.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			if (i == menuList.size() - i) {
				break;
			}
			int lastNum = menuList.size() - i - 1;
			// �������Ŀ���跹�࣬��Ҫ��Ԫȥ��
			try {
				Integer.parseInt(menuList.get(i).getMoney().trim());
				map.put("left", menuList.get(i).getName() + "  "
						+ menuList.get(i).getMoney() + "Ԫ");
			} catch (Exception e) {
				map.put("left", menuList.get(i).getName() + "  "
						+ menuList.get(i).getMoney());
			}
			try {
				Integer.parseInt(menuList.get(lastNum).getMoney().trim());
				map.put("right", menuList.get(lastNum).getName() + "  "
						+ menuList.get(lastNum).getMoney() + "Ԫ");
			} catch (Exception e) {
				map.put("right", menuList.get(lastNum).getName() + "  "
						+ menuList.get(lastNum).getMoney());
			}

			list.add(map);
		}
		return list;
	}

	/**
	 * ת��tab��flag��String
	 * 
	 * @return
	 */
	public static String tabChange(String id) {
		try {
			switch (Integer.parseInt(id.trim())) {
			case 1:
				return MenuFlagUtils.jiancan;
			case 2:
				return MenuFlagUtils.hanbao;
			case 3:
				return MenuFlagUtils.zhapin;
			case 4:
				return MenuFlagUtils.shaguo;
			case 5:
				return MenuFlagUtils.tesefan;
			case 6:
				return MenuFlagUtils.guoqiaomixian;
			case 7:
				return MenuFlagUtils.hefanlei;
			case 8:
				return MenuFlagUtils.xiaoshilei;
			case 9:
				return MenuFlagUtils.chaomianlei;
			case 10:
				return MenuFlagUtils.gaifanlei;
			case 11:
				return MenuFlagUtils.chaofanlei;
			case 12:
				return MenuFlagUtils.chaocailei;
			case 13:
				return MenuFlagUtils.shuijiaolei;
			case 14:
				return MenuFlagUtils.liangbancailei;
			case 15:
				return MenuFlagUtils.mianlei;
			case 16:
				return MenuFlagUtils.kaoroubanfanlei;
			case 17:
				return MenuFlagUtils.tesefan;
			case 18:
				return MenuFlagUtils.gaijiaofanlei;
			case 19:
				return MenuFlagUtils.qitalei;
			default:
				return "������";
			}
		} catch (Exception e) {
			return "������";
		}
	}

	/**
	 * ��ȡ�汾��
	 * 
	 * @param context
	 * @return
	 */
	public static String GetVersion(Context context) {
		try {
			PackageInfo manager = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			return manager.versionName;
		} catch (NameNotFoundException e) {
			return "Unknown";
		}
	}

	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * ���ر����ֻ���
	 * 
	 * @param activity
	 * @return
	 */
	public static String getPhoneNum(Activity activity) {
		TelephonyManager tm = (TelephonyManager) activity
				.getSystemService(Context.TELEPHONY_SERVICE);
		String phoneNum = tm.getLine1Number();
		Log.i("num", phoneNum);
		return phoneNum;
	}

	/**
	 * ����ͼƬԲ��
	 * 
	 * @param bitmap
	 * @param pixels
	 * @return
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	/**
	 * �����豸��Ϣ
	 * 
	 * @param context
	 * @return
	 */
	public static String getDeviceInfo(Context context) {
		try {
			org.json.JSONObject json = new org.json.JSONObject();
			android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);

			String device_id = tm.getDeviceId();

			android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);

			String mac = wifi.getConnectionInfo().getMacAddress();
			json.put("mac", mac);

			if (TextUtils.isEmpty(device_id)) {
				device_id = mac;
			}

			if (TextUtils.isEmpty(device_id)) {
				device_id = android.provider.Settings.Secure.getString(
						context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);
			}

			json.put("device_id", device_id);

			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * �����뵱ǰʱ�����minute���ӵ�ʱ�� HH:mm getTimeMinute
	 * 
	 * @param minute
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public static String getTimeMinute(int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minute); // ����
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd,HH:mm");
		return df.format(date);
	}
}
