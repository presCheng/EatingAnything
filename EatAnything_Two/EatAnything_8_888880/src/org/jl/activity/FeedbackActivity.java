package org.jl.activity;

import org.jl.utils.HttpUtils;
import org.jl.utils.UserUtils;

import com.example.eatanything_1_0_1.R;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 意见反馈界面
 * 
 * @author acer
 * 
 */
public class FeedbackActivity extends Activity {
	private Button submit;
	private EditText submitContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置界面没有标题栏
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_about_feedback);
		initview();
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	public void initview() {
		// 设置导航栏事件
		LinearLayout layout = (LinearLayout) this
				.findViewById(R.id.include_about);
		layout.findViewById(R.id.img_l).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				});
		TextView tittle = (TextView) layout.findViewById(R.id.room_m);
		tittle.setText("意见反馈");
		submit = (Button) this.findViewById(R.id.submit);
		submitContent = (EditText) this.findViewById(R.id.submitcontent);
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// 插到数据库
				String content = submitContent.getText().toString();
				String flag = saveDataForInternet(content);
				if (content == null || content.isEmpty()) {
					Toast.makeText(
							FeedbackActivity.this.getApplicationContext(),
							"请输入您的意见", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(
							FeedbackActivity.this.getApplicationContext(),
							"感谢您的反馈", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	/**
	 * 保存数据到数据库
	 * 
	 * @param content
	 * @return
	 */
	public String saveDataForInternet(String content) {
		String name = UserUtils.getPhoneNum(FeedbackActivity.this);
		String date = "name=" + name + "&content=" + content;
		String url = "http://chishenme521.duapp.com/SaeServlet/InsertServletFeedback";
		String returnFlag = HttpUtils.sendPost(url, date);
		return returnFlag;
	}
}
