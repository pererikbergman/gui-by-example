package se.uncle.guibyexample.example.gridview;

import se.uncle.guibyexample.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;

public class FullImageActivity extends Activity {

	// Constants 
	private static final boolean AUTO_HIDE              = false;
	private static final int     AUTO_HIDE_DELAY_MILLIS = 3000;
	public  static final String  POSITION               = "position";

	// Components
	private ViewPager            mPager;
	private PagerAdapter         mPagerAdapter;

	// States
	private boolean              mVisible               = true;

	// Help classes
	private Handler              mHideHandler           = new Handler();
	private Runnable             mUIHider               = new UIHider();
	private OnTouchListener      mUIFlipper             = new UIFliper();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		setContentView(R.layout.fullscreen);

		// Get the start index
		int position = 0;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			position = extras.getInt(POSITION, 0);
		}
		
		// Setup the ViewPager.
		mPager = (ViewPager) findViewById(R.id.myfivepanelpager);
		mPagerAdapter = new ViewPagerAdapter(this);
		mPager.setAdapter(mPagerAdapter);
		mPager.setCurrentItem(position);

		// Add the UI Flipper.
		mPager.setOnTouchListener(mUIFlipper);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		if (AUTO_HIDE) {
			delayedHide(AUTO_HIDE_DELAY_MILLIS);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	private void toggle() {
		if (mVisible) {
			hide();
		} else {
			show();
		}
	}

	private void hide() {
		FullImageActivity.this.getActionBar().hide();
		mVisible = false;
	}

	private void show() {
		FullImageActivity.this.getActionBar().show();
		mVisible = true;

		if (AUTO_HIDE) {
			delayedHide(AUTO_HIDE_DELAY_MILLIS);
		}
	}

	private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mUIHider);
		mHideHandler.postDelayed(mUIHider, delayMillis);
	}

	/** Help Classes **/
	
	private final class UIFliper implements View.OnTouchListener {
		private float mOldX;

		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
				mOldX = motionEvent.getX();
			} else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
				if (Math.abs(mOldX - motionEvent.getX()) < 10) {
					toggle();
				}
			}
			return false;
		}
	}
	
	private final class UIHider implements Runnable {
		@Override
		public void run() {
			hide();
		}
	}
}