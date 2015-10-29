package org.jl.custom;

import org.jl.activity.LeftOrRightActivity;

import com.example.eatanything_1_0_1.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
/**
 * 引导界面的fragment
 * @author acer
 *
 */
public class GuideFragment extends Fragment {
	private static final String KEY_CONTENT = "TestFragment:Content";
	private static final String KEY_ISLASTPIC = "TestFragment:IsLastPic";
	private int mContent;
	/**
	 * 最后一张图片
	 */
	private boolean mIsLastPic;

	public static GuideFragment newInstance(int content, boolean isLastPic) {
		GuideFragment fragment = new GuideFragment();

		fragment.mContent = content;
		fragment.mIsLastPic = isLastPic;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if ((savedInstanceState != null)
				&& savedInstanceState.containsKey(KEY_CONTENT)) {
			mContent = savedInstanceState.getInt(KEY_CONTENT);
			mIsLastPic = savedInstanceState.getBoolean(KEY_ISLASTPIC);
		}
		View root = inflater
				.inflate(R.layout.fragment_layout, container, false);
		ImageView iv = (ImageView) root.findViewById(R.id.iv);
		iv.setImageResource(mContent);
		Button btn = (Button) root.findViewById(R.id.experbt);
		btn.setOnClickListener(new OnClickListener() {
			//点击体验跳转页面
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),
						LeftOrRightActivity.class);
				getActivity().startActivity(intent);
				getActivity().finish();
			}
		});
		//按钮在最后一张图片上显示
		if (mIsLastPic)
			btn.setVisibility(View.VISIBLE);
		else
			btn.setVisibility(View.GONE);
		return root;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_CONTENT, mContent);
		outState.putBoolean(KEY_ISLASTPIC, mIsLastPic);
	}
}
