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
 * Class Name: RoomAdapter.java Function: �Զ����listview������������ʵ��listview����Ŀؼ�����
 * Modifications:
 * 
 * @author ��־�� DateTime 2014-4-8 ����8:26:05
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
	
	//�ж��Ƿ���
	Boolean onclick = false;
	
	// ����������Ϣ
		String PREFS_NAME = "eatAnything";
		SharedPreferences settings;
		SharedPreferences.Editor editor;
	/**
	 * 
	 * @param context
	 *            ������
	 * @param list
	 *            ÿһ����Ŀ������
	 */
	public RoomAdapter(Context context, List<ListInfo> list) {
		this.context = context;
		this.list = list;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * ��ListView���ݷ����仯ʱ,���ô˷���������ListView
	 * 
	 * @param list
	 */
	public void updateListView(List<ListInfo> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	/**
	 * ��ȡ���ݼ�����
	 */
	@Override
	public int getCount() {
		return list.size();
	}

	/**
	 * ����λ�û�ȡ��ǰ����
	 */
	@Override
	public Object getItem(int position) {
		
		return list.get(position);
	}

	/**
	 * ��ȡλ��
	 */
	@Override
	public long getItemId(int position) {
		// ����Ǹ���ʳƷ��id����
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
			// ��ȡ�����ļ�
			convertView = inflater.inflate(R.layout.simple_list, null);
			// newһ�����󣬴��ÿ��item�Ĵ���
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
		// ���ð��ļ����¼�
		 holder.linear_aixing.setOnClickListener(new OnClickListener() {
		 @Override
		 public void onClick(View v) {
//		 CustomDialog.Builder builder = new CustomDialog.Builder(context);
//		 builder.setTel1(list.get(position).getTelNum1());
//		 builder.setTel2(list.get(position).getTelNum2());
//		 builder.setTel3(list.get(position).getTelNum3());
//		 builder.create().show();
		//���õ���ı�ͼƬ  ���´��ٽ��� �ͻ���ʾԭ����ʼ��ͼƬ
		 
		 //getBoolean(position + "", false) ���position+""��ֵ������ǿ���Ϊfalse
		 if(settings.getBoolean(position + "", false)){
			 img_shou.setBackgroundResource(R.drawable.kongbai);
			 img_aixin.setBackgroundResource(R.drawable.kongxing);
			 editor = settings.edit();
			 
			 //��position+""  ���false
			 editor.putBoolean(position+"", false);
			 editor.commit();
		 }
		 else {
			 img_shou.setBackgroundResource(R.drawable.img_shou);
			 img_aixin.setBackgroundResource(R.drawable.shixing);
			 editor = settings.edit();
			//��position+""  ���true
			 editor.putBoolean(position+"", true);
			 editor.commit();
		 }
			 
		
		
		
		 }
		 });
		// ����item����
		// ���ò�����
		holder.restaurant.setText(list.get(position).getRestaurant());
		// ����position��ȡ���������ĸ��Char asciiֵ
		int section = getSectionForPosition(position);

		// �����ǰλ�õ��ڸ÷�������ĸ��Char��λ�� ������Ϊ�ǵ�һ�γ���
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
	 * Class Name: RoomAdapter.java Function: �Զ�����ͼ������ Modifications:
	 * 
	 * @author ��־�� DateTime 2014-4-3 ����5:02:46
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
	 * ���ݷ��������ĸ��Char asciiֵ��ȡ���һ�γ��ָ�����ĸ��λ��
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
	 * ����ListView�ĵ�ǰλ�û�ȡ���������ĸ��Char asciiֵ
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
	 * ��ȡӢ�ĵ�����ĸ����Ӣ����ĸ��#���档
	 * 
	 * @param str
	 * @return
	 */
	private String getAlpha(String str) {
		String sortStr = str.trim().substring(0, 1).toUpperCase();
		// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

}
