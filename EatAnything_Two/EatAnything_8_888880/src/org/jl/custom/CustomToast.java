package org.jl.custom;


import com.example.eatanything_1_0_1.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast extends Toast {

	public CustomToast(Context context) {
		super(context);
	}

	public static Toast makeText(Context context, CharSequence text,
			int duration) {
		Toast result = new Toast(context);
		// 获取LayoutInflater对象
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// 由layout文件创建一个View对象
		View layout = inflater.inflate(R.layout.custom_toast, null);
		// 实例化ImageView和TextView对象
		TextView textView = (TextView) layout.findViewById(R.id.toast_tv);
		textView.setText(text);
		result.setView(layout);
		result.setGravity(Gravity.CENTER, Gravity.CENTER, Gravity.CENTER);
		result.setDuration(duration);

		return result;
	}

}