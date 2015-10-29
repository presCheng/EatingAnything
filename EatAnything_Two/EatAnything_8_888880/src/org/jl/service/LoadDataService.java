package org.jl.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import org.jl.utils.HttpUtils;
import org.jl.utils.SqliteUtils;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class LoadDataService extends IntentService {

	private static String TAG = "LoadDataService";

	public LoadDataService(String name) {
		super(name);
		Log.i(TAG, "LoadDataService..............");
	}

	public LoadDataService() {
		super("LoadDataService");
		Log.i(TAG, "LoadDataService..............");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i(TAG, "onHandleIntent..............");

		try {
			String pathImg = "http://chishenme521.duapp.com/SaeServlet/andoridServlet.jsp?flag=imgurl";
			String jsonImg = HttpUtils.getJsonContent(getApplicationContext(),
					pathImg);
			SqliteUtils.saveImgDataBaseList(getApplicationContext(), jsonImg);
			String pathTopImg = "http://chishenme521.duapp.com/SaeServlet/andoridServlet.jsp?flag=topimgurl";
			String jsonTopImg = HttpUtils.getJsonContent(
					getApplicationContext(), pathTopImg);
			SqliteUtils.saveTopImgDataBaseList(getApplicationContext(),
					jsonTopImg);
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "数据获取异常", Toast.LENGTH_SHORT).show();

		}
	}

}
