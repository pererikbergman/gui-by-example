package se.uncle.guibyexample.example.gridview;

import se.uncle.guibyexample.R;
import se.uncle.guibyexample.example.gridview.util.SystemUiHider;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FullImageActivity extends Activity {

	private static final boolean AUTO_HIDE = true;
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
	private static final boolean TOGGLE_ON_CLICK = true;
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	public static final String POSITION = "position";

	private SystemUiHider mSystemUiHider;

	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;

	public int[] mThumbIds = { R.drawable.pic_1, R.drawable.pic_2,
			R.drawable.pic_3, R.drawable.pic_4, R.drawable.pic_5,
			R.drawable.pic_6, R.drawable.pic_7, R.drawable.pic_8,
			R.drawable.pic_9, R.drawable.pic_10, R.drawable.pic_11,
			R.drawable.pic_12, R.drawable.pic_13, R.drawable.pic_14,
			R.drawable.pic_15 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fullscreen);

		// Get the starting position.
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		int position = extras.getInt(POSITION);

		// Set up the pager view.
		mPager = (ViewPager) findViewById(R.id.myfivepanelpager);
		mPagerAdapter = new ViewPagerAdapter(this, mThumbIds);
		mPager.setAdapter(mPagerAdapter);
		mPager.setCurrentItem(position);

		// Setting up the 'back' button.
		Button back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int idx = (mPager.getCurrentItem() - 1) % mThumbIds.length;
				mPager.setCurrentItem(idx);
			}
		});
		back.setOnTouchListener(mDelayHideTouchListener);

		// Setting up the 'next' button.
		Button next = (Button) findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int idx = (mPager.getCurrentItem() + 1) % mThumbIds.length;
				mPager.setCurrentItem(idx);
			}
		});
		next.setOnTouchListener(mDelayHideTouchListener);

		// Setting up the 'home' button.
		Button home = (Button) findViewById(R.id.home);
		home.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FullImageActivity.this.finish();
			}
		});
		home.setOnTouchListener(mDelayHideTouchListener);

		final View controlsView = findViewById(R.id.fullscreen_content_controls);
		mSystemUiHider = SystemUiHider.getInstance(this, mPager, HIDER_FLAGS);
		mSystemUiHider.setup();
		mSystemUiHider.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
					@Override
					public void onVisibilityChange(boolean visible) {
						controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
						if (visible && AUTO_HIDE) {
							delayedHide(AUTO_HIDE_DELAY_MILLIS);
						}
					}
				});

		mPager.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (TOGGLE_ON_CLICK) {
					mSystemUiHider.toggle();
				} else {
					mSystemUiHider.show();
				}
			}
		});
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		delayedHide(100);
	}

	View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (AUTO_HIDE) {
				delayedHide(AUTO_HIDE_DELAY_MILLIS);
			}
			return false;
		}
	};

	Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
			mSystemUiHider.hide();
		}
	};

	private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}
}