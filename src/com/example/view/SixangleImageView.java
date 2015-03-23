package com.example.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.sexangleview.R;
import com.example.sixangleview.MainActivity.ViewBean;

public class SixangleImageView extends ImageView {

	private static final String TAG = "SexangleImageView";
	
	private static final float DEFAULT_TEXT_SIZE = 24;
	private static final int DEFAULT_IMAGE_BG_COLOR = 0xfff;
	private static final String DEFAULT_TEXT = "";
	
	private int color = DEFAULT_IMAGE_BG_COLOR;
	private float textsize = DEFAULT_TEXT_SIZE;
	private String texts = DEFAULT_TEXT;
	
	private int mWidth;
	private int mHeight;
	private int mLenght;
	private int centreX;
	private int centreY;
	private Paint bgPaint;
	private Paint textPaint;  
	
	private OnSixangleImageClickListener listener;
	
	public SixangleImageView(Context context) {
		super(context);
	}
	public SixangleImageView(Context context,ViewBean bean) {
		super(context);
		init();
		setCustomAttributes(bean);
	}

	private void setCustomAttributes(ViewBean bean) {
		textsize = bean.getTextsize();
		texts = bean.getTexts();
		color=colors[0];
	}
	
	@SuppressLint("Recycle")
	public SixangleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.sexangleImageView);
		color = typedArray.getInt(R.styleable.sexangleImageView_backcolor, 0);
		textsize = typedArray.getDimension(R.styleable.sexangleImageView_textSize, 24);
		texts = typedArray.getString(R.styleable.sexangleImageView_texts);
	}
	
	private int[] colors = { getResources().getColor(R.color.brown)
	};

	private void init() {
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setAntiAlias(true);
		textPaint.setTextSize( 35);  
		textPaint.setColor( Color.BLACK);
		textPaint.setTextAlign(Paint.Align.CENTER);
		  
		bgPaint = new Paint();
		bgPaint.setAntiAlias(true);
		bgPaint.setStyle(Style.STROKE);// FILL
		bgPaint.setColor(Color.parseColor("#A2A2A2"));
		bgPaint.setAlpha(200);
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		mWidth = getWidth();
		mHeight = getHeight();
		mLenght = mWidth / 2;
		centreX = mWidth / 2;
		centreY = mHeight / 2;
		double radian30 = 30 * Math.PI / 180;
		float a = (float) (mLenght * Math.sin(radian30));
		float b = (float) (mLenght * Math.cos(radian30));
		float c = (mHeight - 2 * b) / 2;

		Path path = new Path();
		path.moveTo(getWidth(), getHeight() / 2);
		path.lineTo(getWidth() - a, getHeight() - c);
		path.lineTo(getWidth() - a - mLenght, getHeight() - c);
		path.lineTo(0, getHeight() / 2);
		path.lineTo(a, c);
		path.lineTo(getWidth() - a, c);
		path.close();
		
		canvas.drawPath(path, bgPaint);
		
		FontMetrics fontMetrics = textPaint.getFontMetrics();

		float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;

		float offY = fontTotalHeight / 2 - fontMetrics.bottom;

		float newY = centreX + offY;

		canvas.drawText(texts, centreY, newY, textPaint);

		//canvas.drawText(texts, centreX, centreY, textPaint);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(mWidth, mHeight);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		float start = 1.0f;
		float end = 0.94f;
		Animation scaleAnimation = new ScaleAnimation(start, end, start, end,
		Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		Animation endAnimation = new ScaleAnimation(end, start, end, start,
		Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		scaleAnimation.setDuration(100);
		scaleAnimation.setFillAfter(true);
		endAnimation.setDuration(100);
		endAnimation.setFillAfter(true);
			
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			this.startAnimation(scaleAnimation);
			float edgeLength = ((float) getWidth()) / 2;
			float radiusSquare = edgeLength * edgeLength * 3 / 4;
			float dist = (event.getX() - getWidth() / 2)
					* (event.getX() - getWidth() / 2)
					+ (event.getY() - getHeight() / 2)
					* (event.getY() - getHeight() / 2);
			if (dist <= radiusSquare) {
				bgPaint.setColor(Color.BLUE);
				bgPaint.setAlpha(50);
				invalidate();
			}

			break;

		case MotionEvent.ACTION_UP:
			this.startAnimation(endAnimation);
			bgPaint.setColor(Color.BLACK);
			bgPaint.setAlpha(60);
			if(listener!=null){
				listener.onClick(this);
			}
			invalidate();

			break;
		case MotionEvent.ACTION_CANCEL:
			this.startAnimation(endAnimation);
			bgPaint.setColor(Color.BLACK);
			bgPaint.setAlpha(60);

			invalidate();
			break;
		}
		return true;
	}

	public void setOnSexangleImageClick(OnSixangleImageClickListener listener ){
		this.listener=listener;
	}
	
	public interface  OnSixangleImageClickListener {
		public void onClick(View view);
	}
}

