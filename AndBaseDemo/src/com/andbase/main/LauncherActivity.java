package com.andbase.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.andbase.R;


public class LauncherActivity extends Activity {
	
	private RelativeLayout mLaunchLayout;
	private Animation mFadeIn;
	private Animation mFadeInScale;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.launcher);
		mLaunchLayout = (RelativeLayout) findViewById(R.id.launch);
		init();
		setListener();
	}

	private void setListener() {
		
		mFadeIn.setAnimationListener(new AnimationListener() {

			public void onAnimationStart(Animation animation) {

			}

			public void onAnimationRepeat(Animation animation) {

			}

			public void onAnimationEnd(Animation animation) {
				mLaunchLayout.startAnimation(mFadeInScale);
			}
		});
		
		mFadeInScale.setAnimationListener(new AnimationListener() {

			public void onAnimationStart(Animation animation) {

			}

			public void onAnimationRepeat(Animation animation) {

			}

			public void onAnimationEnd(Animation animation) {
				Intent intent = new Intent();
				intent.setClass(LauncherActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});

	}

	private void init() {
		initAnim();
		mLaunchLayout.startAnimation(mFadeIn);
	}

	private void initAnim() {
		mFadeIn = AnimationUtils.loadAnimation(LauncherActivity.this,
				R.anim.welcome_fade_in);
		mFadeIn.setDuration(500);
		mFadeIn.setFillAfter(true);
		mFadeInScale = AnimationUtils.loadAnimation(LauncherActivity.this,
				R.anim.welcome_fade_in_scale);
		mFadeInScale.setDuration(3000);
		mFadeInScale.setFillAfter(true);
	}

}
