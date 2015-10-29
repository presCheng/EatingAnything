package org.jl.custom;

import org.jl.domain.MenuName;
import org.jl.domain.Restaurant;
import org.jl.utils.UserUtils;

import com.example.eatanything_1_0_1.R;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class TotalFoodAdapter extends BaseAdapter {
	// 设置配置信息
	String PREFS_NAME = "eatAnythingU";
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	private List<String> list;
	private LayoutInflater inflater;
	private Context context;
	private List<List<MenuName>> menuList;
	private Restaurant res;

	/**
	 * 
	 * @param context
	 *            上下文
	 * @param list
	 *            每一个项目的内容
	 */
	public TotalFoodAdapter(Context context, List<String> list, Restaurant res,
			List<List<MenuName>> menuList) {

		this.context = context;
		this.list = list;
		this.res = res;
		this.menuList = menuList;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * 当ListView数据发生变化时,调用此方法来更新ListView
	 * 
	 * @param list
	 */
	public void updateListView(List<String> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	/**
	 * 获取数据集长度
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		String name = list.get(position);// 饭类型
		name = UserUtils.tabChange(name);
		List<MenuName> menu = menuList.get(position);// 饭内容
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.simple_list_con, null);
			viewHolder.textTitle = (TextView) convertView
					.findViewById(R.id.textTitle);
			viewHolder.photo = (ImageView) convertView.findViewById(R.id.phone);
			viewHolder.photo.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// 如果点击的电话，就记录在我的订单里面
					SaveWddd();
					CustomDialog.Builder builder = new CustomDialog.Builder(
							context);
					builder.setTel1(res.getTel());
					builder.setTel2(res.getTel2());
					builder.setTel3(res.getTel3());
					builder.create().show();
				}
			});
			for (int i = 0; i < menu.size(); i++) {
				TextView ts = new TextView(context);
				ts.setText(menu.get(i).getName() + " " + (7 + i % 3) + " 元 ");
				ts.setPadding(10, 10, 10, 10);
				ts.setPadding(10, 5, 0, 5);
				ts.setTextSize(20);
				((ViewGroup) convertView).addView(ts);
				if (i != menu.size() - 1) {
					LinearLayout v = new LinearLayout(context);
					LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
							LayoutParams.MATCH_PARENT, 2);
					textParams.setMargins(10, 0, 0, 0);
					v.setBackgroundColor(context.getResources().getColor(
							R.color.gray));
					((ViewGroup) convertView).addView(v, textParams);
				}

			}
			viewHolder.textTitle.setText(name);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	/**
	 * 保存我的订单
	 */
	private void SaveWddd() {
		settings = context.getSharedPreferences(PREFS_NAME,
				Activity.MODE_PRIVATE);
		Set<String> siteno = new HashSet<String>();
		siteno = settings.getStringSet("dd", siteno);
		if (siteno.size() == 0) {
			editor = settings.edit();
			siteno.add(res.getId()+"!"+UserUtils.getTimeMinute(0));
			editor.putStringSet("dd", siteno);
			editor.commit();
		} else {
			String[] data = (String[]) siteno
					.toArray(new String[siteno.size()]);
			siteno.clear();
			for (int i = 0; i < data.length; i++) {
				siteno.add(data[i]);
			}
			siteno.add(res.getId()+"!"+UserUtils.getTimeMinute(0));
			editor = settings.edit();
			editor.putStringSet("dd", siteno);
			editor.commit();
		}
	}

	private final class ViewHolder {
		private TextView textTitle;
		private ImageView photo;
	}
}
