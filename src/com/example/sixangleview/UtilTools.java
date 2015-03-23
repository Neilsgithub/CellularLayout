package com.example.sixangleview;

import android.content.Context;
import android.util.DisplayMetrics;

public class UtilTools {
	
	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}

	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}

	public static float getScreenDensity(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		return dm.density;
	}
	
	/**
	 * px to dp
	 */
	public static int getDimenT(int id,Context context) {
		try {
			return (int) (id * getScreenDensity(context));
		} catch (Exception e) {
			return 0;
		}
	}

}
