package org.jl.activity;

import java.util.HashSet;
import java.util.Set;

import org.jl.custom.CustomToast;
import org.jl.custom.WdddAdapter;
import org.jl.utils.UserTools;
import org.jl.utils.UserUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.eatanything_1_0_1.R;
import com.slidingmenu.lib.SlidingMenu;
import com.umeng.update.UmengUpdateAgent;

public class LeftOrRightActivity extends Activity {
	private long exitTime = 0;
	// 设置配置信息
	String PREFS_NAME = "eatAnythingU";
	SharedPreferences settings;
	SharedPreferences.Editor editor;

	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			// 修改头像和名字
			case 1:
				LinearLayout layout = (LinearLayout) LeftOrRightActivity.this
						.findViewById(R.id.grzx_layout);
				ImageView iV = (ImageView) layout.findViewById(R.id.grzx_tx);
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.logintx);
				bitmap = UserTools.toRoundBitmap(bitmap);
				Drawable drawable = UserTools.bitmap2Drawable(bitmap);
				iV.setBackground(drawable);
				TextView tv = (TextView) layout.findViewById(R.id.grzx_qdl);
				tv.setText("多吃点吧");
				// -----------修改侧边栏
				ImageView ivv = (ImageView) findViewById(R.id.s_tx);
				TextView tvv = (TextView) findViewById(R.id.s_qdl);
				tvv.setText("多吃点吧");
				ivv.setBackground(drawable);
				isXgyh(true);// 头像和名字配置
				break;
			// 即将上映
			case 2:
				break;
			default:
				break;
			}
			return false;
		};
	});

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 设置界面没有标题栏
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_grzx);
		LinearLayout layout = (LinearLayout) this
				.findViewById(R.id.grzx_layout);
		// ---判断是否登录
		if (!isXgyh(false)) {
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.logintx);
			bitmap = UserTools.toRoundBitmap(bitmap);
			Drawable drawable = UserTools.bitmap2Drawable(bitmap);
			ImageView iV = (ImageView) layout.findViewById(R.id.grzx_tx);
			iV.setBackground(drawable);
			TextView tv = (TextView) layout.findViewById(R.id.grzx_qdl);
			TextView tv_dlh = (TextView) findViewById(R.id.grzx_dlh);
			tv_dlh.setText("您已登录，请去订餐吧");
			tv.setText("多吃点吧");
		} else {
			// 如果没登录 个人登录
			layout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					Intent intent = new Intent(LeftOrRightActivity.this,
							LoginActivity.class);
					startActivityForResult(intent, 0);
				}
			});
		}
		setNavigation_zhuCeAndZhuXiao("首页");
		// ---------------------------------------------------------------
		setSliding();
		setSlide();
	}

	private void setSlide() {
		setUserTxAndName();
		LinearLayout grzx = (LinearLayout) this.findViewById(R.id.l_grzx);
		LinearLayout wydc = (LinearLayout) this.findViewById(R.id.l_wydc);
		LinearLayout wddd = (LinearLayout) this.findViewById(R.id.l_wddd);
		LinearLayout jcgx = (LinearLayout) this.findViewById(R.id.l_jcgx);
		LinearLayout gywm = (LinearLayout) this.findViewById(R.id.l_gywm);
		grzx.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				setGrzx();
			}
		});
		wydc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				setWydc();
			}
		});
		wddd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				setWddd();
			}
		});
		jcgx.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				setJcgc();
			}
		});

		gywm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				setGywm();

			}
		});
	}

	/**
	 * 个人中心
	 */
	@SuppressLint("NewApi")
	private void setGrzx() {
		setContentView(R.layout.activity_grzx);
		LinearLayout layout = (LinearLayout) this
				.findViewById(R.id.grzx_layout);
		// ---判断是否登录
		if (!isXgyh(false)) {
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.logintx);
			bitmap = UserTools.toRoundBitmap(bitmap);
			Drawable drawable = UserTools.bitmap2Drawable(bitmap);
			ImageView iV = (ImageView) layout.findViewById(R.id.grzx_tx);
			iV.setBackground(drawable);
			TextView tv = (TextView) layout.findViewById(R.id.grzx_qdl);
			TextView tv_dlh = (TextView) findViewById(R.id.grzx_dlh);
			tv_dlh.setText("您已登录，请去订餐吧");
			tv.setText("多吃点吧");
		} else {
			// 如果没登录 个人登录
			layout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					Intent intent = new Intent(LeftOrRightActivity.this,
							LeftOrRightActivity.class);
					startActivityForResult(intent, 0);
				}
			});
		}
		setNavigation_zhuCeAndZhuXiao("个人中心");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0 && requestCode == 0 && null != data) {
			// Bundle dataBundle =data.getExtras();
			// String result =dataBundle.getString("city");
			// //登录通过,修改头像
			handler.sendEmptyMessage(1);
		}
	}

	/**
	 * 设置用户头像和名字
	 */
	private void setUserTxAndName() {
		// 说明登录了用户
		if (!isXgyh(false)) {
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.logintx);
			bitmap = UserTools.toRoundBitmap(bitmap);
			Drawable drawable = UserTools.bitmap2Drawable(bitmap);
			// -----------修改侧边栏
			ImageView ivv = (ImageView) findViewById(R.id.s_tx);
			TextView tvv = (TextView) findViewById(R.id.s_qdl);
			tvv.setText("多吃点吧");
			ivv.setBackground(drawable);
		}
	}

	/**
	 * 我要订餐
	 */
	private void setWydc() {
		Intent intent = new Intent(LeftOrRightActivity.this, MenuActivity.class);
		startActivity(intent);
	}

	/**
	 * 我的订单
	 */
	private void setWddd() {
		setContentView(R.layout.activity_dd);
		LinearLayout layout = (LinearLayout) this.findViewById(R.id.dd_linear);
		ListView list = (ListView) this.findViewById(R.id.dd_list);
		settings = this.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
		Set<String> siteno = new HashSet<String>();
		siteno = settings.getStringSet("dd", siteno);
		if (siteno.size() == 0) {

		} else {
			layout.setVisibility(LinearLayout.GONE);
			String[] data = (String[]) siteno
					.toArray(new String[siteno.size()]);
			WdddAdapter adapter = new WdddAdapter(LeftOrRightActivity.this,
					data);
			list.setAdapter(adapter);
		}
		setNavigation_wddd("我的订单");
	}

	/**
	 * 检查更新
	 */
	private void setJcgc() {
		UmengUpdateAgent.forceUpdate(LeftOrRightActivity.this);
		// setContentView(R.layout.ww);
		Toast.makeText(getApplicationContext(), "正在检查最新版本", Toast.LENGTH_SHORT)
				.show();
		// setSliding();
	}

	/**
	 * 关于我们
	 */
	private void setGywm() {
		setContentView(R.layout.activity_about_us);
		setNavigation("关于我们");
		setGywmWel();
		setGywmQlhc();
		setGywmYjfk();
	}

	/**
	 * 关于我们-欢迎页
	 */
	private void setGywmWel() {
		LinearLayout layout_text = (LinearLayout) this
				.findViewById(R.id.about_text);
		TextView wel = (TextView) layout_text.findViewById(R.id.about_text_wel);
		wel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LeftOrRightActivity.this,
						GuideActivity.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * 关于我们-清理缓存
	 */
	private void setGywmQlhc() {
		LinearLayout layout_text = (LinearLayout) this
				.findViewById(R.id.about_text);
		TextView qlhc = (TextView) layout_text
				.findViewById(R.id.about_text_qlhc);
		qlhc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				CustomToast.makeText(getApplicationContext(), "清理成功",
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	/**
	 * 关于我们-意见反馈
	 */
	private void setGywmYjfk() {
		LinearLayout layout_text = (LinearLayout) this
				.findViewById(R.id.about_text);
		TextView yjfk = (TextView) layout_text
				.findViewById(R.id.about_text_yjfk);
		yjfk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LeftOrRightActivity.this,
						FeedbackActivity.class);
				startActivity(intent);
			}
		});

	}

	/**
	 * 设置侧边栏
	 */
	private SlidingMenu setSliding() {
		SlidingMenu menu = new SlidingMenu(getApplicationContext());
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setBehindWidth(380);
		menu.setFadeDegree(0.35f);
		menu.showMenu();
		menu.attachToActivity(LeftOrRightActivity.this,
				SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.activity_mainslide);
		setSlide();
		return menu;
	}

	/**
	 * 设置导航栏事件
	 * 
	 * @param name
	 */
	private void setNavigation(String name) {
		final SlidingMenu menu = setSliding();
		LinearLayout layoutDh = (LinearLayout) this
				.findViewById(R.id.include_about);
		layoutDh.findViewById(R.id.img_l).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						menu.toggle();
					}
				});
		TextView tittle = (TextView) layoutDh.findViewById(R.id.room_m);
		tittle.setText(name);
	}

	/**
	 * 设置个人中心的导航栏事件
	 * 
	 * @param name
	 */
	private void setNavigation_zhuCeAndZhuXiao(String name) {
		final SlidingMenu menu = setSliding();
		LinearLayout layoutDh = (LinearLayout) this
				.findViewById(R.id.include_about);
		layoutDh.findViewById(R.id.img_l).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						menu.toggle();
					}
				});
		Button btn_dl = (Button) layoutDh.findViewById(R.id.top_btn_dl);
		Button btn_zx = (Button) layoutDh.findViewById(R.id.top_btn_zx);
		TextView tittle = (TextView) layoutDh.findViewById(R.id.room_m);
		btn_dl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LeftOrRightActivity.this,
						ZhuCeActivity.class);
				startActivity(intent);
			}
		});
		btn_zx.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (zxDl()) {
					CustomToast.makeText(getApplicationContext(), "注銷成功",
							CustomToast.LENGTH_SHORT).show();
					setGrzx();
					setUserTxAndName();
				} else {
					Toast.makeText(getApplicationContext(), "未登錄",
							CustomToast.LENGTH_SHORT).show();
				}
			}
		});
		tittle.setText(name);
	}

	/**
	 * 设置我的订单的导航栏事件
	 * 
	 * @param name
	 */
	private void setNavigation_wddd(String name) {
		final SlidingMenu menu = setSliding();
		LinearLayout layoutDh = (LinearLayout) this
				.findViewById(R.id.include_about);
		layoutDh.findViewById(R.id.img_l).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						menu.toggle();
					}
				});
		Button btn_qk = (Button) layoutDh.findViewById(R.id.top_btn_qk);
		TextView tittle = (TextView) layoutDh.findViewById(R.id.room_m);
		btn_qk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//清空配置
				editor = settings.edit();
				editor.putStringSet("dd", null);
				editor.commit();
				//刷新页面
				setWddd();
				CustomToast.makeText(getApplicationContext(), "清除成功",
						Toast.LENGTH_SHORT).show();
			}
		});
		tittle.setText(name);
	}

	/**
	 * 退出程序
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * m 注銷登陸
	 * 
	 * @return
	 */
	private boolean zxDl() {
		settings = this.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
		if (!settings.getBoolean("xgyh", false)) {
			// 説明還未登陸
			return false;
		} else {
			editor = settings.edit();
			editor.putBoolean("xgyh", false);
			editor.commit();
			return true;
		}
	}

	/**
	 * 
	 * Function: 修改用户 如果傳參 true 是修改 false 是讀取 返回false 是登陸
	 * 
	 * @return boolean
	 */
	private boolean isXgyh(boolean xgyh) {
		if (xgyh) {
			settings = this.getSharedPreferences(PREFS_NAME,
					Activity.MODE_PRIVATE);
			if (!settings.getBoolean("xgyh", false)) {
				editor = settings.edit();
				editor.putBoolean("xgyh", true);
				editor.commit();
				return false;
			} else {
				return true;
			}
		} else {
			settings = this.getSharedPreferences(PREFS_NAME,
					Activity.MODE_PRIVATE);
			if (!settings.getBoolean("xgyh", false)) {
				// 没有用户
				return true;
			} else {
				// 有用户
				return false;
			}
		}
	}

}
