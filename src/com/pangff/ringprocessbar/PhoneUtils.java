package com.pangff.ringprocessbar;

import android.content.Context;

/**
 * 系统相关工具
 * 
 * @author pangff
 */
public class PhoneUtils {

	public static int dipToPixels(float dip, Context context) {
		final float SCALE = context.getResources().getDisplayMetrics().density;
		float valueDips = dip;
		int valuePixels = (int) (valueDips * SCALE + 0.5f);
		return valuePixels;
	}

}
