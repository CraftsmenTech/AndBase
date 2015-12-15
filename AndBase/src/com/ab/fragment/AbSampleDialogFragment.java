package com.ab.fragment;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * © 2012 amsoft.cn
 * 名称：AbDialogFragment.java 
 * 描述：弹出框
 *
 * @author 还如一梦中
 * @version v1.0
 * @date：2014-07-29 上午9:00:52
 */
public class AbSampleDialogFragment extends DialogFragment {
	
	private int mTheme;
	private int mStyle;
	private View mContentView;
	private DialogInterface.OnCancelListener mOnCancelListener = null;
	private DialogInterface.OnDismissListener mOnDismissListener = null;

	/**
	 * Create a new instance of AbDialogFragment, providing "style" as an
	 * argument.
	 */
	public static AbSampleDialogFragment newInstance(int style, int theme) {
		AbSampleDialogFragment f = new AbSampleDialogFragment();

		// Supply style input as an argument.
		Bundle args = new Bundle();
		args.putInt("style", style);
		args.putInt("theme", theme);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mStyle = getArguments().getInt("style");
		mTheme = getArguments().getInt("theme");
		setStyle(mStyle, mTheme);
	}
	
	@Override
	public void onCancel(DialogInterface dialog) {
		// 用户中断
		if(mOnCancelListener != null){
			mOnCancelListener.onCancel(dialog);
		}
		
		super.onCancel(dialog);
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		// 用户隐藏
		if(mOnDismissListener != null){
		    mOnDismissListener.onDismiss(dialog);
		}
		super.onDismiss(dialog);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return mContentView;
	}

	public View getContentView() {
		return mContentView;
	}

	public void setContentView(View mContentView) {
		this.mContentView = mContentView;
	}

	public DialogInterface.OnCancelListener getOnCancelListener() {
		return mOnCancelListener;
	}

	public void setOnCancelListener(
			DialogInterface.OnCancelListener onCancelListener) {
		this.mOnCancelListener = onCancelListener;
	}

	public DialogInterface.OnDismissListener getOnDismissListener() {
		return mOnDismissListener;
	}

	public void setOnDismissListener(
			DialogInterface.OnDismissListener onDismissListener) {
		this.mOnDismissListener = onDismissListener;
	}
	
}
