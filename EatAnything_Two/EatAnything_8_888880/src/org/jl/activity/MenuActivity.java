package org.jl.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jl.custom.ClearEditText;
import org.jl.custom.RoomAdapter;
//import org.jl.custom.SideBar;
//import org.jl.custom.SideBar.OnTouchingLetterChangedListener;
import org.jl.domain.ListInfo;
import org.jl.domain.Restaurant;
import org.jl.exception.CompleteException;
import org.jl.utils.CharacterParser;
import org.jl.utils.HttpUtils;
import org.jl.utils.JsonUtils;
import org.jl.utils.PinyinComparator;
import org.jl.utils.SqliteUtils;

import com.example.eatanything_1_0_1.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * Class Name: MenuActivity.java Function: �˵����� Modifications:
 * 
 * @author ��־�� DateTime 2014-4-8 ����8:24:45
 * @version 1.0
 */
public class MenuActivity extends Activity {
	private ListView listView;
	private PullToRefreshListView mPullToRefreshListView;
	// ʱ���ǩ
	private String label;
	//private SideBar sideBar;
	private TextView dialog;
	private RoomAdapter adapter;
	private ClearEditText mClearEditText;
	/**
	 * ����ת����ƴ������
	 */
	private CharacterParser characterParser;
	private List<ListInfo> SourceDateList;
	/**
	 * ����ƴ��������ListView�����������
	 */
	private PinyinComparator pinyinComparator;

	/**
	 * toast UI �任
	 */
	private static Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			MenuActivity ma = (MenuActivity) msg.obj;
			switch (msg.what) {
			case 1:
				Toast.makeText(ma.getApplicationContext(), "���ݻ�ȡ�쳣,������",
						Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(ma.getApplicationContext(), "���������쳣,�������绷��",
						Toast.LENGTH_SHORT).show();
				break;
			case 3:
				Toast.makeText(ma.getApplicationContext(), "����ˢ��ȡ�У������ĵȴ�",
						Toast.LENGTH_SHORT).show();
				break;
			case 4:
				Toast.makeText(ma.getApplicationContext(), "��ǰΪ��������",
						Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	};

	private List<Restaurant> resList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initSqliteData();
		init();
		initView();
		setListener();
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	/**
	 * 
	 * Function: ��ʼ�����ݿ�����
	 */
	private void initSqliteData() {
		resList = SqliteUtils.getResDataBaseList(this);
	}

	/**
	 * 
	 * Function: ��ʼ����Ϣ
	 */
	private void init() {
		// ���ý���û�б�����
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menu);
		// ����top�������¼�
		LinearLayout layout = (LinearLayout) this
				.findViewById(R.id.includde_menu);
		layout.findViewById(R.id.img_l).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
					}
				});
		TextView tittle = (TextView) layout.findViewById(R.id.room_m);
		tittle.setText("����������ѧԺ");
	}

	/**
	 * 
	 * Function: ��ʼ����ͼ
	 */
	private void initView() {
		// ����activityʱ���Զ����������
		// getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		// ---------------------------------------
		// ʵ��������תƴ����
		characterParser = CharacterParser.getInstance();
		pinyinComparator = new PinyinComparator();
		//sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		//sideBar.setTextView(dialog);
		// �����Ҳഥ������
		//sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
//			@Override
//			public void onTouchingLetterChanged(String s) {
//				// ����ĸ�״γ��ֵ�λ��
//				int position = adapter.getPositionForSection(s.charAt(0));
//				if (position != -1) {
//					listView.setSelection(position);
//				}
//			}
//		});
		// --------------------------------------------
		mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.room_list);
		mPullToRefreshListView.setMode(Mode.PULL_FROM_START);
		mPullToRefreshListView.setScrollingWhileRefreshingEnabled(true);
		listView = mPullToRefreshListView.getRefreshableView();
		SourceDateList = filledData(ecsoList());
		// ����a-z��������Դ����
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new RoomAdapter(this, SourceDateList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int id,
					long ids) {
				Log.i("TAG", "tiaozhuan");
				Intent intent = new Intent(MenuActivity.this,
						ContentActicity.class);
				// ��������
				int flag = (int) ids;
				intent.putExtra("id", flag);
				startActivity(intent);
			}
		});
		// -------------------------------------------
		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

		// �������������ֵ�ĸı�����������
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// ������������ֵΪ�գ�����Ϊԭ�����б�����Ϊ���������б�
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	/**
	 * ��װ����
	 * 
	 * @return List<ListInfo>
	 */
	private List<ListInfo> ecsoList() {
		List<ListInfo> list = new ArrayList<ListInfo>(resList.size());
		for (int i = 0; i < resList.size(); i++) {
			int img_home = getResources().getIdentifier("list_1", "drawable",
					"com.example.eatanything_1_0_1");
			int img_car = getResources().getIdentifier("list_2", "drawable",
					"com.example.eatanything_1_0_1");
			int img_phone = getResources().getIdentifier("list_3", "drawable",
					"com.example.eatanything_1_0_1");
			int id = Integer.parseInt(resList.get(i).getId());
			String restaurant = resList.get(i).getName();
			String telNum1 = resList.get(i).getTel();
			String telNum2 = resList.get(i).getTel2();
			String telNum3 = resList.get(i).getTel3();
			ListInfo item = new ListInfo(id, img_home, img_car, img_phone,
					telNum1, telNum2, telNum3, restaurant);
			list.add(item);
		}
		return list;
	}

	/**
	 * 
	 * Function: ����mPullToRefreshListView����
	 * 
	 * @author ��־�� DateTime 2014-4-8 ����8:21:53
	 */
	private void setListener() {
		mPullToRefreshListView
				.setOnRefreshListener(new OnRefreshListener<ListView>() {
					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {
						label = DateUtils.formatDateTime(
								getApplicationContext(),
								System.currentTimeMillis(),
								DateUtils.FORMAT_SHOW_TIME
										| DateUtils.FORMAT_SHOW_DATE
										| DateUtils.FORMAT_ABBREV_ALL);
						mPullToRefreshListView.getLoadingLayoutProxy()
								.setRefreshingLabel("����ˢ��");
						mPullToRefreshListView.getLoadingLayoutProxy()
								.setPullLabel("����ˢ��");
						mPullToRefreshListView.getLoadingLayoutProxy()
								.setReleaseLabel("�ͷſ�ʼˢ��");
						refreshView.getLoadingLayoutProxy()
								.setLastUpdatedLabel("������ʱ��:" + label);
						// ��ȡ����
						try {
							getDate();
						} catch (RuntimeException e) {

						}
					}
				});
	}
	/**
	 * 
	 * Function: �������
	 */
	private void getDate() {
		new GetDataTask().execute();
	}
	/**
	 * 
	 * Class Name: MenuActivity.java Function: ������ݵ� AsyncTask ������handler��������
	 * Modifications:
	 * 
	 * @author ��־�� DateTime 2014-4-8 ����8:22:46
	 * @version 1.0
	 */
	private class GetDataTask extends
			AsyncTask<String, List<String>, List<Restaurant>> {

		/**
		 * ���ɸ���Ķ��󣬲�����execute����֮�� ����ִ�е���onProExecute���� ���ִ��doInBackgroup����
		 * 
		 */
		/**
		 * �����String������ӦAsyncTask�еĵ�һ������ �����String����ֵ��ӦAsyncTask�ĵ���������
		 * �÷�������������UI�̵߳��У���Ҫ�����첽�����������ڸ÷����в��ܶ�UI���еĿռ�������ú��޸�
		 * ���ǿ��Ե���publishProgress��������onProgressUpdate��UI���в���
		 */
		@Override
		protected List<Restaurant> doInBackground(String... params)
				throws CompleteException {
			List<String> urlList;
			// ���ݻ�ȡ
			List<Restaurant> list;
			try {
				urlList = getUrlData();// ����
				String resData = urlList.get(0);
				String menuData = urlList.get(1);
				saveToBase(resData, menuData);
				list = JsonUtils.getRestaurant("Restaurant", resData);
			} catch (CompleteException e) {
				Message msg = new Message();
				msg.what = 4;
				msg.obj = MenuActivity.this;
				mHandler.sendMessage(msg);
				return null;
			} catch (RuntimeException e) {
				return resList;
			}
			return list;
		}

		/**
		 * �������
		 * 
		 * @return
		 */
		private List<String> getUrlData() {
			int length = 2;
			List<String> urlList = new ArrayList<String>(length);
			String resData = null;
			String menuData = null;
			try {
				String path = "http://chishenme521.duapp.com/SaeServlet/andoridServlet.jsp?flag=restaurant";
				resData = HttpUtils.getJsonContent(MenuActivity.this, path);
				path = "http://chishenme521.duapp.com/SaeServlet/andoridServlet.jsp?flag=menuname";
				menuData = HttpUtils.getJsonContent(MenuActivity.this, path);
			} catch (RuntimeException e) {
				Message msg = new Message();
				msg.what = 1;
				msg.obj = MenuActivity.this;
				mHandler.sendMessage(msg);
				throw new RuntimeException();
			} catch (Exception e) {
				Message msg = new Message();
				msg.what = 2;
				msg.obj = MenuActivity.this;
				mHandler.sendMessage(msg);
				throw new RuntimeException();
			}
			urlList.add(resData);
			urlList.add(menuData);
			return urlList;
		}

		/**
		 * �����String������ӦAsyncTask�еĵ�����������Ҳ���ǽ���doInBackground�ķ���ֵ��
		 * ��doInBackground����ִ�н���֮�������У�����������UI�̵߳��� ���Զ�UI�ռ��������
		 */
		@Override
		protected void onPostExecute(List<Restaurant> result) {
			if (!"".equals(result) && result != null) {
				resList = result;
				// UserUtils.initListView(MenuActivity.this, listView, result);
				SourceDateList = filledData(ecsoList());
				// ����a-z��������Դ����
				Collections.sort(SourceDateList, pinyinComparator);
				adapter.setList(SourceDateList);
				adapter.notifyDataSetChanged();
			}
			// ˢ�½��������ؿ�
			mPullToRefreshListView.onRefreshComplete();
		}

		@Override
		protected void onPreExecute() {
			Message msg = new Message();
			msg.what = 3;
			msg.obj = MenuActivity.this;
			mHandler.sendMessage(msg);
		}
	}

	/**
	 * 
	 * Function: �������ݽ��մ����ݽ�sqllite
	 */
	private void saveToBase(String resData, String menuData) {
		SqliteUtils.saveResDataBaseList(this, resData);
		SqliteUtils.saveMenuDataBaseList(this, menuData);
	}

	/**
	 * ΪListView�������
	 * 
	 * @param date
	 * @return
	 */
	private List<ListInfo> filledData(List<ListInfo> date) {
		List<ListInfo> mListInfos = new ArrayList<ListInfo>();

		for (int i = 0; i < date.size(); i++) {
			// ����ת����ƴ��
			String pinyin = characterParser.getSelling(date.get(i)
					.getRestaurant());
			String sortString = pinyin.substring(0, 1).toUpperCase();
			// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
			if (sortString.matches("[A-Z]")) {
				date.get(i).setSortLetters(sortString.toUpperCase());
			} else {
				date.get(i).setSortLetters("#");
			}
			mListInfos.add(date.get(i));
		}
		return mListInfos;

	}

	/**
	 * ����������е�ֵ���������ݲ�����ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<ListInfo> filterDateList = new ArrayList<ListInfo>();
		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = SourceDateList;
		} else {
			filterDateList.clear();
			for (ListInfo sortModel : SourceDateList) {
				String name = sortModel.getRestaurant();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// ����a-z��������
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}

}
