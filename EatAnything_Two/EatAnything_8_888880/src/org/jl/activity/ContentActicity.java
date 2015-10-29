package org.jl.activity;

import org.jl.custom.TotalFoodAdapter;
import org.jl.domain.MenuName;
import org.jl.domain.Restaurant;
import org.jl.utils.SqliteUtils;
import org.jl.utils.UserTools;
import org.jl.utils.UserUtils;

import com.example.eatanything_1_0_1.R;

import java.util.ArrayList;
import java.util.List;



import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ContentActicity extends Activity {
	// 餐厅对象
	Restaurant res;
	// 传入标识id
	int id;
	// 这是饭菜的list
	List<MenuName> menuList;

	ListView listView;
	TotalFoodAdapter totalFoodAdapter;
	List<List<MenuName>> list01;
	TextView con_addr;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置界面没有标题栏
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_content);
		id = (Integer) getIntent().getExtras().get("id");
		initResSqliteData();
		menuList = getMenuSqliteData();
		
		con_addr = (TextView)findViewById(R.id.con_addr);
		con_addr.setText("地址：" + res.getAddr());
		initView(savedInstanceState);

		// 设置导航栏事件
		LinearLayout layout = (LinearLayout) this
				.findViewById(R.id.includde_cont);
		layout.findViewById(R.id.img_l).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				});
		TextView tittle = (TextView) layout.findViewById(R.id.room_m);
		tittle.setText(res.getName());
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	/**
	 * 
	 * Function: 初始化菜单数据库数据
	 */
	private List<MenuName> getMenuSqliteData(String flag) {
		String sql = "select * from menuname where r_id =? and flag =?";
		// 餐馆list要保存下来
		List<MenuName> list = SqliteUtils.getMenuDataBaseList(this, sql, id,
				flag);
		return list;
	}

	/**
	 * 
	 * Function: 初始化菜单数据库数据
	 */
	private List<MenuName> getMenuSqliteData() {
		String sql = "select * from menuname where r_id =?";
		// 餐馆list要保存下来
		menuList = SqliteUtils.getMenuDataBaseList(this, sql, id);
		return menuList;
	}

	/**
	 * 
	 * Function: 初始化餐厅数据库数据
	 */
	private void initResSqliteData() {
		res = SqliteUtils.getResDataBaseList(this, id);
	}

	/**
	 * 
	 * Function: 初始化视图
	 */
	public void initView(Bundle savedInstanceState) {
		List<String> totalTitle = changeType();//获得饭菜类的数字
		list01 = new ArrayList<List<MenuName>>();
		for (int i =0; i < totalTitle.size(); i++) {
			list01.add(getMenuSqliteData(totalTitle.get(i)));
		}
		totalFoodAdapter = new TotalFoodAdapter(ContentActicity.this,
				totalTitle, res,list01);
		listView = (ListView) findViewById(R.id.list_food_total);
		listView.setAdapter(totalFoodAdapter);
	}

	// 返回飯菜有种类
	public List<String> changeType() {
		List<String> copyList = new ArrayList<String>();
		for (int i = 0; i < menuList.size(); i++) {
			String flag = menuList.get(i).getFlag();
			if (!copyList.contains(flag)) {
				copyList.add(flag);
			}
		}

		return copyList;
	}
}