package org.jl.custom;

import org.jl.domain.Restaurant;
import org.jl.utils.SqliteUtils;

import com.example.eatanything_1_0_1.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;

/**
 * 我的订单适配器
 * 
 * @author acer
 * 
 */
public class WdddAdapter extends BaseAdapter {
	private String[] list;
	private LayoutInflater inflater;
	private Context context;

	/**
	 * 
	 * @param context
	 *            上下文
	 * @param list
	 *            每一个项目的内容
	 */
	public WdddAdapter(Context context, String[] list) {

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
	public void updateListView(String[] list) {
		this.list = list;
		notifyDataSetChanged();
	}

	/**
	 * 获取数据集长度
	 */
	@Override
	public int getCount() {
		return list.length;
	}

	@Override
	public Object getItem(int position) {
		return list[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public String[] getList() {
		return list;
	}

	public void setList(String[] list) {
		this.list = list;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		String[] data = list[position].split("!");// 饭馆id和时间
		String sName = data[0];
		String sTime = data[1];
		final Restaurant res = SqliteUtils.getResDataBaseList(context,
				Integer.parseInt(sName));
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.simple_list_dd, null);
			viewHolder.name = (TextView) convertView.findViewById(R.id.dd_name);
			viewHolder.time = (TextView) convertView.findViewById(R.id.dd_time);
			viewHolder.pho = (ImageView) convertView
					.findViewById(R.id.dd_phone);
			viewHolder.pho.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					CustomDialog.Builder builder = new CustomDialog.Builder(
							context);
					builder.setTel1(res.getTel());
					builder.setTel2(res.getTel2());
					builder.setTel3(res.getTel3());
					builder.create().show();
				}
			});
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.name.setText(res.getName());
		viewHolder.time.setText(sTime);
		return convertView;
	}

	private final class ViewHolder {
		private TextView name;
		private TextView time;
		private ImageView pho;
	}
}
