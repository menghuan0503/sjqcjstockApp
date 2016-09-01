package com.example.sjqcjstock.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.sjqcjstock.Activity.atmeActivity;
import com.example.sjqcjstock.Activity.personalnewsActivity;
import com.example.sjqcjstock.Activity.recivecommentActivity;
import com.example.sjqcjstock.Activity.sendedcommentActivity;
import com.example.sjqcjstock.Activity.systemMessageActivity;
import com.example.sjqcjstock.R;
import com.example.sjqcjstock.app.ExitApplication;

/**
 * 消息的主体页面
 */
public class ActivityMessage extends Activity implements OnClickListener{
	// 收到的评论
	private LinearLayout pickrecivecomment1;
	// 发出的评论
	private LinearLayout picksendcomment1;
	// 系统消息
	private LinearLayout systemMessage;
	// 提到我的
	private LinearLayout pickpersonalnews1;
	// 我的私信
	private LinearLayout pickatmecomment1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_message3);
		ExitApplication.getInstance().addActivity(this);
		initView();
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		pickrecivecomment1=(LinearLayout)findViewById(R.id.pickrecivecomment1);
		picksendcomment1=(LinearLayout)findViewById(R.id.picksendcomment1);
		pickpersonalnews1=(LinearLayout)findViewById(R.id.pickpersonalnews1);
		systemMessage=(LinearLayout)findViewById(R.id.system_message);
		pickatmecomment1=(LinearLayout)findViewById(R.id.pickatmecomment1);
		// 绑定点击事件
		pickrecivecomment1.setOnClickListener(this);
		picksendcomment1.setOnClickListener(this);
		systemMessage.setOnClickListener(this);
		pickpersonalnews1.setOnClickListener(this);
		pickatmecomment1.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()){
			case R.id.pickatmecomment1:
				intent =new Intent(this,atmeActivity.class);
				break;
			case R.id.pickpersonalnews1 :
				intent=new Intent(this,personalnewsActivity.class);
				break;
			case R.id.pickrecivecomment1:
				// 收到的评论
				intent=new Intent(this,recivecommentActivity.class);
				break;
			case R.id.picksendcomment1:
				//  发出的评论
				intent=new Intent(this,sendedcommentActivity.class);
				break;
			case R.id.system_message :
				intent=new Intent(this,systemMessageActivity.class);
				break;
		}
		if (intent != null){
			startActivity(intent);
		}
	}
}
