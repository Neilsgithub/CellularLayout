package com.example.view;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class SixangleScrollView extends FrameLayout {

	private static String TAG = "SexangleScrollView";
	private static final int RADIU_COUNT = 20; 
	private static final double H_MARGIN_RATIO = 0.8 ; 
	private static final double V_MARGIN = 20.0 ; 

	private int childRadius;
	private int childWidth;
	private int childHeight;
	private int mChildCount;
	private int centerX, centerY;

	public SixangleScrollView(Context context) {
		super(context);
	}

	public SixangleScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SixangleScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		final int count = getChildCount();

		int wSize = MeasureSpec.getSize(widthMeasureSpec);
		int hSize = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(wSize, hSize);

		centerX = wSize / 2;
		centerY = hSize / 2;
		childRadius = (wSize) / RADIU_COUNT;
		childWidth = childRadius * 2;
		childHeight = childRadius * 2;

		if (mChildCount != count) {
			mChildCount = count;
		}
		computerPoint(0 + childRadius * 3, centerY, childWidth ,30,getHeight());
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			child.measure(childWidth, childWidth);
		}
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		final int count = getChildCount();
		int childLeft = 0, childTop;
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			childLeft = (int) (centers.get(i).x - childRadius);
			childTop = (int) (centers.get(i).y - childHeight / 2);
			child.layout(childLeft, childTop, childLeft + childWidth, childTop
					+ childHeight);
		}

	}
	
	public void setOnItemClick(SixangleImageView.OnClickListener l) {
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			((SixangleImageView) getChildAt(i)).setOnClickListener(l);
		}
	}
	
	private CircleCenteter computerXY(double a, double b, double h ,int n , int dislpayHeight) {
		
		Double tempX = 0.0;
		Double tempy = 0.0;
		if ((n % 5) == 3 || (n % 5) == 4 ) {
			tempX = ((1 / 2) * h)
					+ ((1 / 2) * h + H_MARGIN_RATIO * h )* (n / 5)*2 + H_MARGIN_RATIO * h + 0.5*h;
		} else if ((n % 5) == 0) {
			tempX = ((1 / 2) * h)
					+ ((1 / 2) * h + H_MARGIN_RATIO * h )* ((n / 5)-1)*2 + H_MARGIN_RATIO * h + 0.5*h;
		} else {
			tempX = ((1 / 2) * h) + ((1 / 2) * h + H_MARGIN_RATIO * h )* (n / 5)*2  + 0.5*h;
		}
		
		switch ((n % 5)) {
		
		case 1:
			tempy = (dislpayHeight / 2) - 0.5*h - (-10) ;
			
			break;
		case 2:
			tempy = (double) ((dislpayHeight / 2) + 0.5*h+(-10)) ;
			break;
		case 3:
			tempy = (double) ((dislpayHeight / 2) - h-(-V_MARGIN)) ;
			break;
		case 4: 
			tempy = (double) ((dislpayHeight / 2)) ;
			break;
		case 0: 
			tempy = (double) ((dislpayHeight / 2) + h + (-V_MARGIN)) ;
			break;	
		default:
			break;
		}
		
		return new CircleCenteter(tempX, tempy);
	}
	
	private void computerPoint(double a, double b, double h ,int n , int dislpayHeight) {
		centers.clear();
		for (int i = 1; i <= n; i++) {
			int x = (int) computerXY( a,  b,  h , i ,  dislpayHeight).x;
			int y =  (int) computerXY( a,  b,  h , i , dislpayHeight).y;
			CircleCenteter c = new CircleCenteter(x,y);
			centers.add(c);
		}
	}

	private ArrayList<CircleCenteter> centers = new ArrayList<CircleCenteter>(1);

	class CircleCenteter {
		double x, y;

		public CircleCenteter(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
}
