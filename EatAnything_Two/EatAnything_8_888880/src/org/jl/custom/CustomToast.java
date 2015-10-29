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
		// ��ȡLayoutInflater����
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// ��layout�ļ�����һ��View����
		View layout = inflater.inflate(R.layout.custom_toast, null);
		// ʵ����ImageView��TextView����
		TextView textView = (TextView) layout.findViewById(R.id.toast_tv);
		textView.setText(text);
		result.setView(layout);
		result.setGravity(Gravity.CENTER, Gravity.CENTER, Gravity.CENTER);
		result.setDuration(duration);

		return result;
	}

}