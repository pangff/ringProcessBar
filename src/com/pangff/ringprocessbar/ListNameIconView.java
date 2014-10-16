package com.pangff.ringprocessbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * @author pangff
 * 实现textview后面紧跟着图标，
 * textview和图标们作为统一布局，图标位置随textview内容结束位置变化
 */
public class ListNameIconView extends LinearLayout {

	int minWidth = 0;
	TextView textView;
	ImageView icon1, icon2;

	public ListNameIconView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(
				R.layout.icon_after_text, this, true);
		textView = (TextView) findViewById(R.id.textView);
		icon1 = (ImageView) findViewById(R.id.icon1);
		icon2 = (ImageView) findViewById(R.id.icon2);
	}


	public void setIconState(String name, boolean showFirst, boolean showSecond) {
		minWidth = 0;
		if (showFirst) {
			icon1.setVisibility(View.VISIBLE);
			minWidth += PhoneUtils.dipToPixels(20, this.getContext())+PhoneUtils.dipToPixels(9, this.getContext());// 这里的长度为后面跟着的图标宽度,+横向margin数值
		}else{
			icon1.setVisibility(View.GONE);
		}
		if (showSecond) {
			icon2.setVisibility(View.VISIBLE);
			minWidth += PhoneUtils.dipToPixels(20, this.getContext())+PhoneUtils.dipToPixels(9, this.getContext());;// 这里的长度为后面跟着的图标宽度+横向margin数值
		}else{
			icon2.setVisibility(View.GONE);
		}
		textView.setText(name);
		textView.setPadding(0, 0, minWidth, 0);
	}

}
