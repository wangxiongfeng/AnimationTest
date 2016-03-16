package com.example.animationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ActivityA extends Activity {

	private LinearLayout rl_activity;
	private ImageView search;
	private float y;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);
		init();
	}

	private void init() {
		rl_activity = (LinearLayout) findViewById(R.id.rl_activitya);//全局布局文件
		search = (ImageView)findViewById(R.id.search);	//搜索控件
//		ScaleAnimation 渐变动画
		search.setOnClickListener(new OnClickListener() { //
			@Override
			public void onClick(View v) {
				y = search.getY();
				TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -y);
				animation.setDuration(500);
				animation.setFillAfter(true);
				animation.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation) {
						
					}
					@Override
					public void onAnimationRepeat(Animation animation) {
						
					}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						Intent intent = new Intent();
						intent.setClass(getApplicationContext(), ActivityB.class);
						startActivityForResult(intent, 100);
						overridePendingTransition(R.anim.animation_2,R.anim.animation_1);
					}
				});
				rl_activity.startAnimation(animation);
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		TranslateAnimation animation = new TranslateAnimation(0, 0, -y, 0);
		animation.setDuration(500);
		animation.setFillAfter(true);
		rl_activity.startAnimation(animation);
		super.onActivityResult(requestCode, resultCode, data);
	}
}
