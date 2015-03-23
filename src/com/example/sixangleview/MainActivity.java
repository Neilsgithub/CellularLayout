package com.example.sixangleview;

import com.example.sexangleview.R;
import com.example.view.SixangleImageView;
import com.example.view.SixangleScrollView;
import com.example.view.SixangleImageView.OnSixangleImageClickListener;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private SixangleScrollView sexangleViewGroup;
	private ViewBean viewBean;
	private SixangleImageView imageViews;
	private static final int ID = 0x10000;
	public static int screenWidth  ;  // ÆÁÄ»¿í¶È
	public static int scrrenHeight ;  //ÆÁÄ»¸ß¶È

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		screenWidth = metric.widthPixels ;
		scrrenHeight = metric.heightPixels;	
		sexangleViewGroup = (SixangleScrollView) findViewById(R.id.sexangleView);
		initView();
	}

	@SuppressLint("ResourceAsColor")
	public void initView() {
		int id = 0;
		for (int i = 0; i < 30; i++) {
			viewBean = new ViewBean();
			viewBean.setHome(i);
			viewBean.setColor(R.color.black);
			viewBean.setTextsize(40);
			viewBean.setTexts(" ");
			imageViews = new SixangleImageView(this, viewBean);
			if (i%5==0||i%5==3) {
				imageViews.setId(id++);
				viewBean.setTexts("TEST");
				imageViews.setOnSexangleImageClick(listener);
				
			}
			
			sexangleViewGroup.addView(imageViews);
		}
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) (screenWidth*(1.5)), LayoutParams.MATCH_PARENT);
		sexangleViewGroup.setLayoutParams(layoutParams);
	}
	
	OnSixangleImageClickListener listener = new OnSixangleImageClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case 0:
				Toast.makeText(MainActivity.this, view.getId()+"",
						Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(MainActivity.this, view.getId()+"",
						Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(MainActivity.this, view.getId()+"",
						Toast.LENGTH_SHORT).show();
				break;
			case 3:
				Toast.makeText(MainActivity.this, view.getId()+"",
						Toast.LENGTH_SHORT).show();
				break;
			case 4:
				Toast.makeText(MainActivity.this, view.getId()+"",
						Toast.LENGTH_SHORT).show();
				break;

			}

		}
	};

	public String setName(int i) {
		return "Test";
	}

	public class ViewBean {
		private int color;
		private float textsize;
		private int home;
		private String texts;

		public int getColor() {
			return color;
		}

		public void setColor(int color) {
			this.color = color;
		}

		public float getTextsize() {
			return textsize;
		}

		public void setTextsize(float textsize) {
			this.textsize = textsize;
		}

		public int getHome() {
			return home;
		}

		public void setHome(int home) {
			this.home = home;
		}

		public String getTexts() {
			return texts;
		}

		public void setTexts(String texts) {
			this.texts = texts;
		}
	}

}
