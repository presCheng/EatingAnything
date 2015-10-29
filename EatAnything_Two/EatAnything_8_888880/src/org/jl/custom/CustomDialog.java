package org.jl.custom;

import com.example.eatanything_1_0_1.R;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * Class Name: CustomDialog.java Function: 自定义弹窗框 这个主要是推出框 Modifications:
 * 
 * @author 徐志国 DateTime 2013-12-11 下午7:06:57
 * @version 1.0
 */
public class CustomDialog extends Dialog {
	/**
	 * 
	 * @param context
	 */
	public CustomDialog(Context context) {
		super(context);
	}

	/**
	 * 
	 * @param context
	 * @param theme
	 */
	public CustomDialog(Context context, int theme) {
		super(context, theme);
	}

	/**
	 * 
	 * Class Name: CustomDialog.java Function: 内部类 创建弹出框 Modifications:
	 * 
	 * @author 徐志国 DateTime 2013-12-11 下午7:07:42
	 * @version 1.0
	 */
	@SuppressLint("WrongViewCast")
	public static class Builder {
		private Context context;
		private String tel1;
		private String tel2;
		private String tel3;
		private View contentView;

		public Builder(Context context) {
			this.context = context;
		}

		public Builder setTel1(String tel1) {
			this.tel1 = tel1;
			return this;
		}

		public Builder setTel2(String tel2) {
			this.tel2 = tel2;
			return this;
		}

		public Builder setTel3(String tel3) {
			this.tel3 = tel3;
			return this;
		}

		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		/**
		 * 
		 * Function: 创建CustomDialog实例
		 * 
		 * @return
		 */
		@SuppressLint("WrongViewCast")
		public CustomDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// 实例化自定义主题的对话框
			final CustomDialog dialog = new CustomDialog(context,
					R.style.Dialog);
			View layout = inflater.inflate(R.layout.dialog_normal_layout, null);
			dialog.addContentView(layout, new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

			if (tel1 != null) {
				((TextView) layout.findViewById(R.id.tel1)).setText(tel1);
			} else if (contentView != null) {
				((LinearLayout) layout.findViewById(R.id.tel1))
						.removeAllViews();
				((LinearLayout) layout.findViewById(R.id.tel1)).addView(
						contentView, new LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT));
			}
			if (tel2 != null) {
				((TextView) layout.findViewById(R.id.tel2)).setText(tel2);
			} else if (contentView != null) {
				((LinearLayout) layout.findViewById(R.id.tel2))
						.removeAllViews();
				((LinearLayout) layout.findViewById(R.id.tel2)).addView(
						contentView, new LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT));
			}
			if (tel3 != null) {
				((TextView) layout.findViewById(R.id.tel3)).setText(tel3);
			} else if (contentView != null) {
				((LinearLayout) layout.findViewById(R.id.tel3))
						.removeAllViews();
				((LinearLayout) layout.findViewById(R.id.tel3)).addView(
						contentView, new LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT));
			}
			((TextView) layout.findViewById(R.id.cancel))
					.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();

						}
					});
			dialog.setContentView(layout);
			return dialog;
		}
	}
}
