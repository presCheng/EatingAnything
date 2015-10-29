package org.jl.custom;

import java.util.List;

import org.jl.activity.MenuActivity;
import org.jl.activity.WelcomeActivity;
import org.jl.domain.ListInfo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.eatanything_1_0_1.R;

/**
 * 
 * Class Name: RoomAdapter.java Function: 自定义的listview适配器，可以实现listview里面的控件监听
 * Modifications:
 * 
 * @author 徐志国 DateTime 2014-4-8 上午8:26:05
 * @version 1.0
 */
public class RoomAdapter extends BaseAdapter implements SectionIndexer {
	private List<ListInfo> list;
	private LayoutInflater inflater;
	private Context context;
	ViewHolder holder = null;
	
	int imgs [] = new int []{R.drawable.logo,R.drawable.cqxc,R.drawable.cuyb,R.drawable.cwrj,
			R.drawable.logo,R.drawable.logo,R.drawable.js,R.drawable.jshc,
			R.drawable.ll,R.drawable.lsx,R.drawable.ltx,R.drawable.mj,
			R.drawable.sh,R.drawable.sx,R.drawable.wmy,R.drawable.yj,
			R.drawable.ym,R.drawable.zjh,R.drawable.zs,R.drawable.logo,
			R.drawable.sh,R.drawable.sx,R.drawable.wmy,R.drawable.yj,
			R.drawable.ym,R.drawable.zjh,R.drawable.zs,R.drawable.logo,
			R.drawable.sh,R.drawable.sx,R.drawable.wmy,R.drawable.yj,
			R.drawable.ym,R.drawable.zjh,R.drawable.zs,R.drawable.logo,
			R.drawable.sh,R.drawable.sx,R.drawable.wmy,R.drawable.yj,
			R.drawable.ym,R.drawable.zjh,R.drawable.zs,R.drawable.logo,
			R.drawable.hbg,R.drawable.hxy,R.drawable.js,R.drawable.jshc,
			R.drawable.ll,R.drawable.lsx,R.drawable.ltx,R.drawable.mj,
			R.drawable.sh,R.drawable.sx,R.drawable.wmy,R.drawable.yj,
			R.drawable.ym,R.drawable.zjh,R.drawable.zs,R.drawable.logo,
			R.drawable.sh,R.drawable.sx,R.drawable.wmy,R.drawable.yj,
			R.drawable.hbg,R.drawable.hxy,R.drawable.js,R.drawable.jshc,
			R.drawable.ll,R.drawable.lsx,R.drawable.ltx,R.drawable.mj,
			R.drawable.sh,R.drawable.sx,R.drawable.wmy,R.drawable.yj,
			R.drawable.ym,R.drawable.zjh,R.drawable.zs,R.drawable.logo,
			R.drawable.sh,R.drawable.sx,R.drawable.wmy,R.drawable.yj,
			R.drawable.hbg,R.drawable.hxy,R.drawable.js,R.drawable.jshc
			};
	
	//判断是否点击
	Boolean onclick = false;
	
	// 设置配置信息
		String PREFS_NAME = "eatAnything";
		SharedPreferences settings;
		SharedPreferences.Editor editor;
	/**
	 * 
	 * @param context
	 *            上下文
	 * @param list
	 *            每一个项目的内容
	 */
	public RoomAdapter(Context context, List<ListInfo> list) {
		this.context = context;
		this.list = list;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * 当ListView数据发生变化时,调用此方法来更新ListView
	 * 
	 * @param list
	 */
	public void updateListView(List<ListInfo> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	/**
	 * 获取数据集长度
	 */
	@Override
	public int getCount() {
		return list.size();
	}

	/**
	 * 根据位置获取当前数据
	 */
	@Override
	public Object getItem(int position) {
		
		return list.get(position);
	}

	/**
	 * 获取位置
	 */
	@Override
	public long getItemId(int position) {
		// 这个是根据食品的id返回
		return list.get(position).getId();
		// return position;
	}

	public List<ListInfo> getList() {
		return list;
	}

	public void setList(List<ListInfo> list) {
		this.list = list;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ListInfo mContent = list.get(position);
		if (convertView == null) {
			// 获取布局文件
			convertView = inflater.inflate(R.layout.simple_list, null);
			// new一个对象，存放每条item的窗体
			holder = new ViewHolder();
			holder.img_home = (ImageView) convertView
					.findViewById(R.id.list_img_home);
			holder.img_home.setBackgroundResource(imgs[position]
					);
			// holder.img_car = (ImageView) convertView
			// .findViewById(R.id.list_img_car);
			// holder.img_car.setBackgroundResource(list.get(position)
			// .getImg_car());
			// holder.img_phone = (ImageView) convertView
			// .findViewById(R.id.list_img_phone);
			// holder.img_phone.setBackgroundResource(list.get(position)
			// .getImg_phone());
			holder.linear_aixing = (LinearLayout) convertView.findViewById(R.id.list_linear_aixing);
			holder.restaurant = (TextView) convertView
					.findViewById(R.id.list_name);
			holder.tvLetter = (TextView) convertView.findViewById(R.id.catalog);
			convertView.setTag(holder);
			holder.tvLetter_lLayout = (LinearLayout) convertView
					.findViewById(R.id.catalog_bottom);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final ImageView img_shou = (ImageView)convertView.findViewById(R.id.list_img_shou);
		final ImageView img_aixin = (ImageView)convertView.findViewById(R.id.list_img_aixing);
		img_aixin.setBackgroundResource(R.drawable.kongxing);
		 settings = context.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
		if(settings.getBoolean(position+"", false)){
			img_shou.setBackgroundResource(R.drawable.img_shou);
			img_aixin.setBackgroundResource(R.drawable.shixing);
		}else {
			img_aixin.setBackgroundResource(R.drawable.kongxing);
			img_shou.setBackgroundResource(R.drawable.kongbai);
		}
		// 设置爱心监听事件
		 holder.linear_aixing.setOnClickListener(new OnClickListener() {
		 @Override
		 public void onClick(View v) {
//		 CustomDialog.Builder builder = new CustomDialog.Builder(context);
//		 builder.setTel1(list.get(position).getTelNum1());
//		 builder.setTel2(list.get(position).getTelNum2());
//		 builder.setTel3(list.get(position).getTelNum3());
//		 builder.create().show();
		//设置点击改变图片  但下次再进入 就会显示原来初始的图片
		 
		 //getBoolean(position + "", false) 获得position+""的值，如果是空则为false
		 if(settings.getBoolean(position + "", false)){
			 img_shou.setBackgroundResource(R.drawable.kongbai);
			 img_aixin.setBackgroundResource(R.drawable.kongxing);
			 editor = settings.edit();
			 
			 //将position+""  变成false
			 editor.putBoolean(position+"", false);
			 editor.commit();
		 }
		 else {
			 img_shou.setBackgroundResource(R.drawable.img_shou);
			 img_aixin.setBackgroundResource(R.drawable.shixing);
			 editor = settings.edit();
			//将position+""  变成true
			 editor.putBoolean(position+"", true);
			 editor.commit();
		 }
			 
		
		
		
		 }
		 });
		// 设置item名字
		// 设置餐厅名
		holder.restaurant.setText(list.get(position).getRestaurant());
		// 根据position获取分类的首字母的Char ascii值
		int section = getSectionForPosition(position);

		// 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
		if (position == getPositionForSection(section)) {
			holder.tvLetter.setVisibility(View.VISIBLE);
			holder.tvLetter_lLayout.setVisibility(View.VISIBLE);
			holder.tvLetter.setText(mContent.getSortLetters());
		} else {
			holder.tvLetter.setVisibility(View.GONE);
			holder.tvLetter_lLayout.setVisibility(View.GONE);
		}

		return convertView;
	}
	

	/**
	 * 
	 * Class Name: RoomAdapter.java Function: 自定义视图持有人 Modifications:
	 * 
	 * @author 徐志国 DateTime 2014-4-3 下午5:02:46
	 * @version 1.0
	 */
	private final class ViewHolder {
		private ImageView img_home;
		// private ImageView img_car;
		private TextView restaurant;
		private TextView tvLetter;
		private LinearLayout tvLetter_lLayout;
		private LinearLayout linear_aixing;
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	@Override
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 根据ListView的当前位置获取分类的首字母的Char ascii值
	 */
	@Override
	public int getSectionForPosition(int position) {
		return list.get(position).getSortLetters().charAt(0);
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 提取英文的首字母，非英文字母用#代替。
	 * 
	 * @param str
	 * @return
	 */
	private String getAlpha(String str) {
		String sortStr = str.trim().substring(0, 1).toUpperCase();
		// 正则表达式，判断首字母是否是英文字母
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

}
