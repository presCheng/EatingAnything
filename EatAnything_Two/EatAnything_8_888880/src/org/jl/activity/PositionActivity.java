package org.jl.activity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.example.eatanything_1_0_1.R;

import org.jl.utils.MyMapView;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class PositionActivity extends Activity {
	public MyApplication app;
	static MyMapView mMapView = null;

	public MKMapViewListener mMapListener = null;
	MyLocationOverlay myLocationOverlay = null;
	// 定位相关
	LocationClient mLocClient;
	public NotifyLister mNotifyer = null;
	public MyLocationListenner myListener = new MyLocationListenner();
	LocationData locData = null;
	private MapController mMapController = null;

	static MKSearch mkSerach;
	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				showAddr.setText(mmmsg);
				break;
			default:
				break;
			}
			return false;
		};
	});
	private String mmmsg;
	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Toast.makeText(PositionActivity.this, "msg:" + msg.what,
					Toast.LENGTH_SHORT).show();
		};
	};
	static TextView showAddr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main01);
		showAddr = (TextView) findViewById(R.id.showAddr);
		

		mMapView = (MyMapView) findViewById(R.id.bmapsView);
		mMapController = mMapView.getController();

		initMapView();
		app = MyApplication.getInstance();
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		
		//搜索初始化
		mkSerach = new MKSearch();
		mkSerach.init(app.mBMapManager, new MKSearchListener() {
			@Override
			public void onGetWalkingRouteResult(MKWalkingRouteResult arg0,
					int arg1) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onGetTransitRouteResult(MKTransitRouteResult arg0,
					int arg1) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onGetPoiDetailSearchResult(int arg0, int arg1) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onGetDrivingRouteResult(MKDrivingRouteResult arg0,
					int arg1) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onGetAddrResult(MKAddrInfo info, int arg1) {
				mmmsg=info.strAddr;
				handler.sendEmptyMessage(1);
			}
		});
		//设置地图相关
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setCoorType("bd09ll");
		option.setScanSpan(300000);
		mLocClient.setLocOption(option);
		mLocClient.start();
		mMapView.getController().setZoom(16);
		mMapView.getController().enableClick(true);
		mMapView.displayZoomControls(true);
		mMapListener = new MKMapViewListener() {
			public void onMapMoveFinish() {
			}
			public void onClickMapPoi(MapPoi mapPoiInfo) {
			}
		};
		mMapView.regMapViewListener(MyApplication.getInstance().mBMapManager,
				mMapListener);
		myLocationOverlay = new MyLocationOverlay(mMapView);
		locData = new LocationData();
		myLocationOverlay.setData(locData);
		mMapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableCompass();
		mMapView.refresh();
	}

	private void initMapView() {
		mMapView.setLongClickable(true);
	}

	/**
	 * 监听函数，又新位置的时候，格式化成字符串，输出到屏幕中
	 */
	public class MyLocationListenner implements BDLocationListener {
		public void onReceiveLocation(BDLocation location) {
			Log.i("================", " null ============ null" + location);
			if (location == null)
				return;
			Log.i("================", "not null ============not null");
			// 31.192695,121.42712,3000
			locData.latitude = location.getLatitude();
			locData.longitude = location.getLongitude();
			locData.direction = 2.0f;
			locData.accuracy = location.getRadius();
			locData.direction = location.getDerect();
			Log.d("loctest",
					String.format("before: lat: %f lon: %f",
							location.getLatitude(), location.getLongitude()));
			myLocationOverlay.setData(locData);
			mMapView.refresh();
			mMapController
					.animateTo(new GeoPoint((int) (locData.latitude * 1e6),
							(int) (locData.longitude * 1e6)), mHandler
							.obtainMessage(1));
		}
		public void onReceivePoi(BDLocation poiLocation) {
			if (poiLocation == null) {
				return;
			}
		}
	}
	public class NotifyLister extends BDNotifyListener {
		public void onNotify(BDLocation mlocation, float distance) {
		}
	}
	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}
	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mMapView.onSaveInstanceState(outState);
	}
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mMapView.onRestoreInstanceState(savedInstanceState);
	}
	public static void getPosition(GeoPoint g) {
		mkSerach.reverseGeocode(g);
	}
}
