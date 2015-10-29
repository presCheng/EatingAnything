package org.jl.custom;

import java.util.List;

import org.jl.activity.ContentActicity;
import org.jl.domain.Image;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatanything_1_0_1.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

public class WaterfallAdapter extends BaseAdapter {

	List<Image> list;
	Context context;
	private Drawable drawable;

	public WaterfallAdapter(List<Image> image, Context context) {
		this.list = image;
		this.context = context;
		drawable = context.getResources().getDrawable(R.drawable.load_default);
	}

	@Override
	public int getCount() {
		return list != null ? list.size() : 0;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	public void refresh(List<Image> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(final int position, View view, ViewGroup group) {
		final Holder holder;
		// 得到View
		if (view == null) {
			holder = new Holder();
			LayoutInflater inflater = LayoutInflater.from(context);
			view = inflater.inflate(R.layout.image_item_recom, null);
			holder.ivIcon = (ImageView) view.findViewById(R.id.row_icon);
			holder.pbLoad = (ProgressBar) view.findViewById(R.id.pb_load);
			holder.tvTtile = (TextView) view.findViewById(R.id.bottom_tittle);
			view.setTag(holder);
		} else {
			holder = (Holder) view.getTag();
		}

		String url = list.get(position).getUrl();
		ImageLoader.getInstance().displayImage(url, holder.ivIcon,
				new ImageLoadingListener() {
					@Override
					public void onLoadingStarted(String imageUri, View view) {
						// //这儿初先初始化出来image所占的位置的大小，先把瀑布流固定住，这样瀑布流就不会因为图片加载出来后大小变化了
						// LayoutParams lp = (LayoutParams)
						// holder.ivIcon.getLayoutParams();
						// //多屏幕适配
						// int dWidth = 480;
						// int dHeight = 800;
						// float wscale = dWidth / 480.0f;
						// float hscale = dHeight / 800.0f;
						// lp.height = (int) (800 * hscale);
						// lp.width = (int) (600 * wscale);
						// holder.ivIcon.setLayoutParams(lp);

						holder.ivIcon.setImageDrawable(drawable);
						holder.pbLoad.setVisibility(View.VISIBLE);
					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
						String message = null;
						switch (failReason.getType()) {
						case IO_ERROR:
							message = "Input/Output error";
							break;
						case DECODING_ERROR:
							message = "can not be decoding";
							break;
						case NETWORK_DENIED:
							message = "Downloads are denied";
							break;
						case OUT_OF_MEMORY:
							message = "内存不足";
							Toast.makeText(context, message, Toast.LENGTH_SHORT)
									.show();
							break;
						case UNKNOWN:
							message = "Unknown error";
							Toast.makeText(context, message, Toast.LENGTH_SHORT)
									.show();
							break;
						}
						holder.pbLoad.setVisibility(View.GONE);
					}

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						holder.pbLoad.setVisibility(View.GONE);
					}

					@Override
					public void onLoadingCancelled(String paramString,
							View paramView) {
					}
				});
		holder.tvTtile.setText(list.get(position).getDescript());
		holder.ivIcon.setTag(list.get(position).getR_id());
		holder.ivIcon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, ContentActicity.class);
				int flag = Integer.valueOf((String) view.getTag());
				intent.putExtra("id", flag);
				context.startActivity(intent);
			}
		});

		return view;
	}

}

class Holder {
	public ImageView ivIcon;
	public ProgressBar pbLoad;
	public TextView tvTtile;
}