package com.pangff.ringprocessbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * 公共关注或追踪btn
 * @author pangff
 */
public class AttBtnView extends FrameLayout{
	
	TextView attText;//关注文字
	
	ProgressBar progressBar;
	
	boolean isTrack;//是否追踪

	public AttBtnView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.common_attr_btn, this, true);
		attText = (TextView) findViewById(R.id.commontAttText);
		progressBar = (ProgressBar) LayoutInflater.from(context).inflate(R.layout.common_attr_progressbar, null);
		//this.setBackgroundResource(R.drawable.btn_uatt_normal);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp.gravity = Gravity.CENTER;
		this.addView(progressBar,lp);
		progressBar.setVisibility(View.INVISIBLE);
	}
	
	public void setIsTrack(boolean isTrack){
		this.isTrack  = isTrack;
	}
	
	/**
	 * 设置正在请求状态
	 */
	public void setOnRequest(){
		progressBar.setVisibility(View.VISIBLE);
		attText.setVisibility(View.INVISIBLE);
	}
	
	public boolean getState(){
		return isAtted;
	}
	/**
	 * 设置正在请求状态
	 */
	public void setOnUnRequest(){
		progressBar.setVisibility(View.INVISIBLE);
		attText.setVisibility(View.VISIBLE);
	}
	
	/**
	 * 设置未关注的默认状态 显示关注两个字
	 */
	private void setUnAttState(){
		attText.setVisibility(View.VISIBLE);
		attText.setText("关注");
		attText.setBackgroundResource(R.drawable.common_uatt_btn_selector);
		attText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.uatt_heart_selector, 0, 0, 0);
		this.setBackgroundResource(R.drawable.btn_uatt_normal);
		progressBar.setVisibility(View.INVISIBLE);
	}
	
	/**
	 * 设置已经关注的状态，显示取消两个字
	 */
	private void setHasAttState(){
		attText.setVisibility(View.VISIBLE);
		if(!this.isTrack){
			attText.setText("取消");
			attText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.att_heart_selector, 0, 0, 0);
		}else{
			attText.setText("取消追踪");
		}
		attText.setBackgroundResource(R.drawable.common_att_btn_selector);
		this.setBackgroundResource(R.drawable.btn_att_normal);
		progressBar.setVisibility(View.INVISIBLE);
	}
	
	boolean isAtted;
	/**
	 * 设置状态
	 */
	public void setAttState(boolean isAtted){
		this.isAtted = isAtted;
		if(isAtted){
			setHasAttState();
		}else{
			setUnAttState();
		}
	}
}
