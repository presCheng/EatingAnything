package org.jl.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import org.jl.activity.PositionActivity;

public class MyMapView extends MapView {
	public MyMapView map = this;

	public MyMapView(Context arg0, AttributeSet arg1) {
		super(arg0, arg1);
		MyThread t =new MyThread();
		t.start();
	}

	class MyThread extends Thread {
		@Override
		public void run() {
			super.run();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			GeoPoint g = map.getProjection().fromPixels(210, 322);
			PositionActivity.getPosition(g);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		GeoPoint g = this.getProjection().fromPixels(210, 322);
		PositionActivity.getPosition(g);
		return super.onTouchEvent(arg0);
	}
}
