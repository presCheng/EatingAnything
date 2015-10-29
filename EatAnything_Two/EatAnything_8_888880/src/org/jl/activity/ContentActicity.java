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
	// ��������
	Restaurant res;
	// �����ʶid
	int id;
	// ���Ƿ��˵�list
	List<MenuName> menuList;

	ListView listView;
	TotalFoodAdapter totalFoodAdapter;
	List<List<MenuName>> list01;
	TextView con_addr;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ý���û�б�����
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_content);
		id = (Integer) getIntent().getExtras().get("id");
		initResSqliteData();
		menuList = getMenuSqliteData();
		
		con_addr = (TextView)findViewById(R.id.con_addr);
		con_addr.setText("��ַ��" + res.getAddr());
		initView(savedInstanceState);

		// ���õ������¼�
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
	 * Function: ��ʼ���˵����ݿ�����
	 */
	private List<MenuName> getMenuSqliteData(String flag) {
		String sql = "select * from menuname where r_id =? and flag =?";
		// �͹�listҪ��������
		List<MenuName> list = SqliteUtils.getMenuDataBaseList(this, sql, id,
				flag);
		return list;
	}

	/**
	 * 
	 * Function: ��ʼ���˵����ݿ�����
	 */
	private List<MenuName> getMenuSqliteData() {
		String sql = "select * from menuname where r_id =?";
		// �͹�listҪ��������
		menuList = SqliteUtils.getMenuDataBaseList(this, sql, id);
		return menuList;
	}

	/**
	 * 
	 * Function: ��ʼ���������ݿ�����
	 */
	private void initResSqliteData() {
		res = SqliteUtils.getResDataBaseList(this, id);
	}

	/**
	 * 
	 * Function: ��ʼ����ͼ
	 */
	public void initView(Bundle savedInstanceState) {
		List<String> totalTitle = changeType();//��÷����������
		list01 = new ArrayList<List<MenuName>>();
		for (int i =0; i < totalTitle.size(); i++) {
			list01.add(getMenuSqliteData(totalTitle.get(i)));
		}
		totalFoodAdapter = new TotalFoodAdapter(ContentActicity.this,
				totalTitle, res,list01);
		listView = (ListView) findViewById(R.id.list_food_total);
		listView.setAdapter(totalFoodAdapter);
	}

	// �����������
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