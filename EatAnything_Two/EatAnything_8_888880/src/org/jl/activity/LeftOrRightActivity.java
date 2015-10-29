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
	// ����������Ϣ
	String PREFS_NAME = "eatAnythingU";
	SharedPreferences settings;
	SharedPreferences.Editor editor;

	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			// �޸�ͷ�������
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
				tv.setText("��Ե��");
				// -----------�޸Ĳ����
				ImageView ivv = (ImageView) findViewById(R.id.s_tx);
				TextView tvv = (TextView) findViewById(R.id.s_qdl);
				tvv.setText("��Ե��");
				ivv.setBackground(drawable);
				isXgyh(true);// ͷ�����������
				break;
			// ������ӳ
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
		// ���ý���û�б�����
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_grzx);
		LinearLayout layout = (LinearLayout) this
				.findViewById(R.id.grzx_layout);
		// ---�ж��Ƿ��¼
		if (!isXgyh(false)) {
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.logintx);
			bitmap = UserTools.toRoundBitmap(bitmap);
			Drawable drawable = UserTools.bitmap2Drawable(bitmap);
			ImageView iV = (ImageView) layout.findViewById(R.id.grzx_tx);
			iV.setBackground(drawable);
			TextView tv = (TextView) layout.findViewById(R.id.grzx_qdl);
			TextView tv_dlh = (TextView) findViewById(R.id.grzx_dlh);
			tv_dlh.setText("���ѵ�¼����ȥ���Ͱ�");
			tv.setText("��Ե��");
		} else {
			// ���û��¼ ���˵�¼
			layout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					Intent intent = new Intent(LeftOrRightActivity.this,
							LoginActivity.class);
					startActivityForResult(intent, 0);
				}
			});
		}
		setNavigation_zhuCeAndZhuXiao("��ҳ");
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
	 * ��������
	 */
	@SuppressLint("NewApi")
	private void setGrzx() {
		setContentView(R.layout.activity_grzx);
		LinearLayout layout = (LinearLayout) this
				.findViewById(R.id.grzx_layout);
		// ---�ж��Ƿ��¼
		if (!isXgyh(false)) {
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.logintx);
			bitmap = UserTools.toRoundBitmap(bitmap);
			Drawable drawable = UserTools.bitmap2Drawable(bitmap);
			ImageView iV = (ImageView) layout.findViewById(R.id.grzx_tx);
			iV.setBackground(drawable);
			TextView tv = (TextView) layout.findViewById(R.id.grzx_qdl);
			TextView tv_dlh = (TextView) findViewById(R.id.grzx_dlh);
			tv_dlh.setText("���ѵ�¼����ȥ���Ͱ�");
			tv.setText("��Ե��");
		} else {
			// ���û��¼ ���˵�¼
			layout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					Intent intent = new Intent(LeftOrRightActivity.this,
							LeftOrRightActivity.class);
					startActivityForResult(intent, 0);
				}
			});
		}
		setNavigation_zhuCeAndZhuXiao("��������");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0 && requestCode == 0 && null != data) {
			// Bundle dataBundle =data.getExtras();
			// String result =dataBundle.getString("city");
			// //��¼ͨ��,�޸�ͷ��
			handler.sendEmptyMessage(1);
		}
	}

	/**
	 * �����û�ͷ�������
	 */
	private void setUserTxAndName() {
		// ˵����¼���û�
		if (!isXgyh(false)) {
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.logintx);
			bitmap = UserTools.toRoundBitmap(bitmap);
			Drawable drawable = UserTools.bitmap2Drawable(bitmap);
			// -----------�޸Ĳ����
			ImageView ivv = (ImageView) findViewById(R.id.s_tx);
			TextView tvv = (TextView) findViewById(R.id.s_qdl);
			tvv.setText("��Ե��");
			ivv.setBackground(drawable);
		}
	}

	/**
	 * ��Ҫ����
	 */
	private void setWydc() {
		Intent intent = new Intent(LeftOrRightActivity.this, MenuActivity.class);
		startActivity(intent);
	}

	/**
	 * �ҵĶ���
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
		setNavigation_wddd("�ҵĶ���");
	}

	/**
	 * ������
	 */
	private void setJcgc() {
		UmengUpdateAgent.forceUpdate(LeftOrRightActivity.this);
		// setContentView(R.layout.ww);
		Toast.makeText(getApplicationContext(), "���ڼ�����°汾", Toast.LENGTH_SHORT)
				.show();
		// setSliding();
	}

	/**
	 * ��������
	 */
	private void setGywm() {
		setContentView(R.layout.activity_about_us);
		setNavigation("��������");
		setGywmWel();
		setGywmQlhc();
		setGywmYjfk();
	}

	/**
	 * ��������-��ӭҳ
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
	 * ��������-������
	 */
	private void setGywmQlhc() {
		LinearLayout layout_text = (LinearLayout) this
				.findViewById(R.id.about_text);
		TextView qlhc = (TextView) layout_text
				.findViewById(R.id.about_text_qlhc);
		qlhc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				CustomToast.makeText(getApplicationContext(), "����ɹ�",
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	/**
	 * ��������-�������
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
	 * ���ò����
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
	 * ���õ������¼�
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
	 * ���ø������ĵĵ������¼�
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
					CustomToast.makeText(getApplicationContext(), "ע�N�ɹ�",
							CustomToast.LENGTH_SHORT).show();
					setGrzx();
					setUserTxAndName();
				} else {
					Toast.makeText(getApplicationContext(), "δ���",
							CustomToast.LENGTH_SHORT).show();
				}
			}
		});
		tittle.setText(name);
	}

	/**
	 * �����ҵĶ����ĵ������¼�
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
				//�������
				editor = settings.edit();
				editor.putStringSet("dd", null);
				editor.commit();
				//ˢ��ҳ��
				setWddd();
				CustomToast.makeText(getApplicationContext(), "����ɹ�",
						Toast.LENGTH_SHORT).show();
			}
		});
		tittle.setText(name);
	}

	/**
	 * �˳�����
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�����",
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
	 * m ע�N���
	 * 
	 * @return
	 */
	private boolean zxDl() {
		settings = this.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
		if (!settings.getBoolean("xgyh", false)) {
			// �h��߀δ���
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
	 * Function: �޸��û� ������� true ���޸� false ���xȡ ����false �ǵ��
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
				// û���û�
				return true;
			} else {
				// ���û�
				return false;
			}
		}
	}

}
