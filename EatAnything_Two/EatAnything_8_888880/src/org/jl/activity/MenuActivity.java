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
 * Class Name: MenuActivity.java Function: 菜单界面 Modifications:
 * 
 * @author 徐志国 DateTime 2014-4-8 上午8:24:45
 * @version 1.0
 */
public class MenuActivity extends Activity {
	private ListView listView;
	private PullToRefreshListView mPullToRefreshListView;
	// 时间标签
	private String label;
	//private SideBar sideBar;
	private TextView dialog;
	private RoomAdapter adapter;
	private ClearEditText mClearEditText;
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<ListInfo> SourceDateList;
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;

	/**
	 * toast UI 变换
	 */
	private static Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			MenuActivity ma = (MenuActivity) msg.obj;
			switch (msg.what) {
			case 1:
				Toast.makeText(ma.getApplicationContext(), "数据获取异常,请重试",
						Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(ma.getApplicationContext(), "网络连接异常,请检查网络环境",
						Toast.LENGTH_SHORT).show();
				break;
			case 3:
				Toast.makeText(ma.getApplicationContext(), "数据刷获取中，请耐心等待",
						Toast.LENGTH_SHORT).show();
				break;
			case 4:
				Toast.makeText(ma.getApplicationContext(), "当前为最新数据",
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
	 * Function: 初始化数据库数据
	 */
	private void initSqliteData() {
		resList = SqliteUtils.getResDataBaseList(this);
	}

	/**
	 * 
	 * Function: 初始化信息
	 */
	private void init() {
		// 设置界面没有标题栏
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menu);
		// 设置top导航栏事件
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
		tittle.setText("黑龙江工程学院");
	}

	/**
	 * 
	 * Function: 初始化视图
	 */
	private void initView() {
		// 启动activity时不自动弹出软键盘
		// getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		// ---------------------------------------
		// 实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();
		pinyinComparator = new PinyinComparator();
		//sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		//sideBar.setTextView(dialog);
		// 设置右侧触摸监听
		//sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
//			@Override
//			public void onTouchingLetterChanged(String s) {
//				// 该字母首次出现的位置
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
		// 根据a-z进行排序源数据
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
				// 传入数据
				int flag = (int) ids;
				intent.putExtra("id", flag);
				startActivity(intent);
			}
		});
		// -------------------------------------------
		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

		// 根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
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
	 * 封装数据
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
	 * Function: 设置mPullToRefreshListView监听
	 * 
	 * @author 徐志国 DateTime 2014-4-8 上午8:21:53
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
								.setRefreshingLabel("正在刷新");
						mPullToRefreshListView.getLoadingLayoutProxy()
								.setPullLabel("下拉刷新");
						mPullToRefreshListView.getLoadingLayoutProxy()
								.setReleaseLabel("释放开始刷新");
						refreshView.getLoadingLayoutProxy()
								.setLastUpdatedLabel("最后更新时间:" + label);
						// 获取数据
						try {
							getDate();
						} catch (RuntimeException e) {

						}
					}
				});
	}
	/**
	 * 
	 * Function: 获得数据
	 */
	private void getDate() {
		new GetDataTask().execute();
	}
	/**
	 * 
	 * Class Name: MenuActivity.java Function: 获得数据的 AsyncTask 类似与handler的轻量级
	 * Modifications:
	 * 
	 * @author 徐志国 DateTime 2014-4-8 上午8:22:46
	 * @version 1.0
	 */
	private class GetDataTask extends
			AsyncTask<String, List<String>, List<Restaurant>> {

		/**
		 * 生成该类的对象，并调用execute方法之后 首先执行的是onProExecute方法 其次执行doInBackgroup方法
		 * 
		 */
		/**
		 * 这里的String参数对应AsyncTask中的第一个参数 这里的String返回值对应AsyncTask的第三个参数
		 * 该方法并不运行在UI线程当中，主要用于异步操作，所有在该方法中不能对UI当中的空间进行设置和修改
		 * 但是可以调用publishProgress方法触发onProgressUpdate对UI进行操作
		 */
		@Override
		protected List<Restaurant> doInBackground(String... params)
				throws CompleteException {
			List<String> urlList;
			// 数据获取
			List<Restaurant> list;
			try {
				urlList = getUrlData();// 接收
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
		 * 获得数据
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
		 * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
		 * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI空间进行设置
		 */
		@Override
		protected void onPostExecute(List<Restaurant> result) {
			if (!"".equals(result) && result != null) {
				resList = result;
				// UserUtils.initListView(MenuActivity.this, listView, result);
				SourceDateList = filledData(ecsoList());
				// 根据a-z进行排序源数据
				Collections.sort(SourceDateList, pinyinComparator);
				adapter.setList(SourceDateList);
				adapter.notifyDataSetChanged();
			}
			// 刷新结束，隐藏框
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
	 * Function: 网络数据接收存数据进sqllite
	 */
	private void saveToBase(String resData, String menuData) {
		SqliteUtils.saveResDataBaseList(this, resData);
		SqliteUtils.saveMenuDataBaseList(this, menuData);
	}

	/**
	 * 为ListView填充数据
	 * 
	 * @param date
	 * @return
	 */
	private List<ListInfo> filledData(List<ListInfo> date) {
		List<ListInfo> mListInfos = new ArrayList<ListInfo>();

		for (int i = 0; i < date.size(); i++) {
			// 汉字转换成拼音
			String pinyin = characterParser.getSelling(date.get(i)
					.getRestaurant());
			String sortString = pinyin.substring(0, 1).toUpperCase();
			// 正则表达式，判断首字母是否是英文字母
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
	 * 根据输入框中的值来过滤数据并更新ListView
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

		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}

}
