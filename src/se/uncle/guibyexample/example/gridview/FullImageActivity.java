package se.uncle.guibyexample.example.gridview;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import se.uncle.guibyexample.R;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;

public class FullImageActivity extends SherlockActivity {

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
		setContentView(R.layout.full_image_activity);

		// Get the start index
		int position = 0;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			position = extras.getInt(POSITION, 0);
		}
		
		// Setup the ViewPager.
		mPager = (ViewPager) findViewById(R.id.imagepager);
		mPagerAdapter = new ViewPagerAdapter(this);
		mPager.setAdapter(mPagerAdapter);
		mPager.setCurrentItem(position);

		// Add the UI Flipper.
		mPager.setOnTouchListener(mUIFlipper);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#99000000")));
		actionBar.setSplitBackgroundDrawable(new ColorDrawable(Color.parseColor("#99000000")));
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
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void toggle() {
		if (mVisible) {
			hide();
		} else {
			show();
		}
	}

	private void hide() {
		FullImageActivity.this.getSupportActionBar().hide();
		mVisible = false;
	}

	private void show() {
		FullImageActivity.this.getSupportActionBar().show();
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