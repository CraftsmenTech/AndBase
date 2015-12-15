package com.andbase.demo.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.ab.activity.AbActivity;
import com.ab.fragment.AbAlertDialogFragment.AbDialogOnClickListener;
import com.ab.fragment.AbDialogFragment;
import com.ab.fragment.AbDialogFragment.AbDialogOnLoadListener;
import com.ab.fragment.AbLoadDialogFragment;
import com.ab.fragment.AbRefreshDialogFragment;
import com.ab.http.AbHttpListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.titlebar.AbTitleBar;
import com.andbase.R;
import com.andbase.global.MyApplication;
import com.andbase.web.NetworkWeb;

/**
 * 名称：DemoAbActivity 
 * 描述：AbActivity基本用法
 * 
 * @author 还如一梦中
 * @date 2011-12-13
 * @version
 */
public class DemoAbActivity extends AbActivity {

	private MyApplication application;
	private boolean isRefreshDialogShow = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setAbContentView(R.layout.demo_ab);

		AbTitleBar mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText(R.string.ab_name);
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		application = (MyApplication) abApplication;

		Button btn1 = (Button) this.findViewById(R.id.btn1);

		Button btn2 = (Button) this.findViewById(R.id.btn2);
		Button btn3 = (Button) this.findViewById(R.id.btn3);
		Button btn4 = (Button) this.findViewById(R.id.btn4);

		Button btn5 = (Button) this.findViewById(R.id.btn5);
		Button btn6 = (Button) this.findViewById(R.id.btn6);

		Button btn7 = (Button) this.findViewById(R.id.btn7);
		Button btn8 = (Button) this.findViewById(R.id.btn8);
		Button btn9 = (Button) this.findViewById(R.id.btn9);
		Button btn10 = (Button) this.findViewById(R.id.btn10);

		Button btn11 = (Button) this.findViewById(R.id.btn11);
		Button btn12 = (Button) this.findViewById(R.id.btn12);
		Button btn13 = (Button) this.findViewById(R.id.btn13);

		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DemoAbActivity.this,
						TitleBarActivity.class);
				startActivity(intent);
			}
		});
		
		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				View mView = mInflater.inflate(R.layout.dialog_location_view,
						null);
				AbDialogUtil.showFragment(mView);
			}
		});


		btn3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				View mView = mInflater.inflate(R.layout.dialog_sample_view,
						null);
				// AbDialogUtil.showDialog(mView);
				AbDialogUtil.showDialog(mView,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {
								AbToastUtil.showToast(DemoAbActivity.this,
										"弹出框被取消");
							}

						});
			}
		});

		btn4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				View mView = mInflater.inflate(R.layout.dialog_sample_view,
						null);
				// AbDialogUtil.showPanel(mView);
				AbDialogUtil.showPanel(mView,
						new DialogInterface.OnCancelListener() {

							@Override
							public void onCancel(DialogInterface dialog) {
								AbToastUtil.showToast(DemoAbActivity.this,
										"弹出框被取消");
							}

				  });
			}
		});

		btn5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				// 显示有背景层的加载的弹出框
				showLoadDialog();
			}
		});

		btn6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 显示无背景层的加载的弹出框
				showLoadPanel();
			}
		});

		btn7.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 显示有背景层的刷新的弹出框
				showRefreshDialog();
			}
		});

		btn8.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 显示无背景层的刷新的弹出框
				showRefreshPanel();
			}
		});

		btn9.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AbDialogUtil.showAlertDialog(DemoAbActivity.this,
						android.R.drawable.ic_dialog_alert, "这里是标题", "这里写一些描述",
						new AbDialogOnClickListener() {

							@Override
							public void onPositiveClick() {
								AbToastUtil.showToast(DemoAbActivity.this,
										"点击了确认");

							}

							@Override
							public void onNegativeClick() {
								AbToastUtil.showToast(DemoAbActivity.this,
										"点击了取消");

							}

						});
			}
		});

		btn10.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AbDialogUtil.showAlertDialog(DemoAbActivity.this,
						android.R.drawable.ic_dialog_alert, "这里是标题", "这里写一些描述", null);
			}
		});

		btn11.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				View mView = mInflater.inflate(R.layout.dialog_sample_view,
						null);
				AbDialogUtil.showAlertDialog(mView);
			}
		});

		//ProgressDialog
		btn12.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AbDialogUtil.showProgressDialog(DemoAbActivity.this,0, "查询中...");
				// AbDialogUtil.removeDialog(DemoAbActivity.this);
			}
		});

		btn13.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AbToastUtil.showToast(DemoAbActivity.this, "Toast提示框");
			}
		});

	}

	/**
	 * 下载数据
	 * 
	 * @param mDialogFragment
	 */
	public void downRss(final AbDialogFragment mDialogFragment) {
		// 下载网络数据
		NetworkWeb web = NetworkWeb.newInstance(DemoAbActivity.this);
		web.testHttpGet("test1", "test1", new AbHttpListener(){

			@Override
			public void onSuccess(String content) {
				mDialogFragment.loadFinish();
				AbDialogUtil.showAlertDialog(DemoAbActivity.this,
						android.R.drawable.ic_dialog_alert, "返回结果", content,
						new AbDialogOnClickListener() {

							@Override
							public void onPositiveClick() {
								AbToastUtil.showToast(DemoAbActivity.this,
										"点击了确认");

							}

							@Override
							public void onNegativeClick() {
								AbToastUtil.showToast(DemoAbActivity.this,
										"点击了取消");

							}

						});
			}

			@Override
			public void onFailure(String content) {
				mDialogFragment.loadStop();
				
				//模拟用，真是开发中需要直接调用run内的内容
				new Handler().postDelayed(new Runnable(){

					@Override
					public void run() {
						//显示重试的框
						showRefreshDialog();
					}
					
				}, 3000);
				
				//错误提示
				AbToastUtil.showToast(DemoAbActivity.this, content);
				
			}
			
		});
	}

	/**
	 * 显示刷新弹出框有背景层
	 */
	public void showLoadDialog() {

		final AbLoadDialogFragment mDialogFragment = AbDialogUtil
				.showLoadDialog(this, R.drawable.ic_load, "正在查询,请稍候");
		mDialogFragment.setAbDialogOnLoadListener(new AbDialogOnLoadListener() {

			@Override
			public void onLoad() {
				// 下载网络数据
				downRss(mDialogFragment);
			}

		});
		//取消的监听
		mDialogFragment.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				AbToastUtil.showToast(DemoAbActivity.this, "Load框被取消");
			}

		});
	}

	/**
	 * 显示加载弹出框无背景层
	 */
	public void showLoadPanel() {

		final AbLoadDialogFragment mDialogFragment = AbDialogUtil
				.showLoadPanel(this, R.drawable.ic_load, "正在查询,请稍候");
		mDialogFragment.setAbDialogOnLoadListener(new AbDialogOnLoadListener() {

			@Override
			public void onLoad() {
				// 下载网络数据
				downRss(mDialogFragment);
			}

		});
		//取消的监听
		mDialogFragment.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				AbToastUtil.showToast(DemoAbActivity.this, "Load框被取消");
			}

		});
	}

	/**
	 * 显示刷新弹出框有背景层
	 */
	public void showRefreshDialog() {
		if(isRefreshDialogShow){
			return;
		}
		
		// 显示重新刷新的框
		final AbRefreshDialogFragment mDialogFragment = AbDialogUtil
				.showRefreshDialog(this, R.drawable.ic_refresh, "请求出错，请重试");
		mDialogFragment.setAbDialogOnLoadListener(new AbDialogOnLoadListener() {

					@Override
					public void onLoad() {
						// 下载网络数据
						downRss(mDialogFragment);
					}

		});
		isRefreshDialogShow = true;
        //取消的监听
		mDialogFragment.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				AbToastUtil.showToast(DemoAbActivity.this, "load框被取消");
				isRefreshDialogShow = false;
			}

		});
	}

	/**
	 * 显示刷新弹出框无背景层
	 */
	public void showRefreshPanel() {
		if(isRefreshDialogShow){
			return;
		}
		// 显示重新刷新的框
		final AbRefreshDialogFragment mDialogFragment = AbDialogUtil
				.showRefreshPanel(this, R.drawable.ic_refresh, "请求出错，请重试");
		mDialogFragment.setAbDialogOnLoadListener(new AbDialogOnLoadListener() {

					@Override
					public void onLoad() {
						// 下载网络数据
						downRss(mDialogFragment);
					}

		});
		isRefreshDialogShow = true;

		mDialogFragment.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				AbToastUtil.showToast(DemoAbActivity.this, "load框被取消");
				isRefreshDialogShow = false;
			}

		});
	}

}
