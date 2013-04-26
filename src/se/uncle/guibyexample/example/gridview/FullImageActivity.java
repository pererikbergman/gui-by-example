package se.uncle.guibyexample.example.gridview;

import java.util.Timer;
import java.util.TimerTask;

import se.uncle.guibyexample.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;

public class FullImageActivity extends Activity {

	public static final String POSITION = "position";

	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	private View mTopbar;
	private View mBottombar;
	private boolean mGUIIsShowing = false;

	private Timer mTimer = new Timer();
	private HideGUITimerTask mTimerTask;
	
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

		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		int position = extras.getInt(POSITION);

		mPager = (ViewPager) findViewById(R.id.myfivepanelpager);
		mPagerAdapter = new ViewPagerAdapter(this, mThumbIds);
		mPager.setAdapter(mPagerAdapter);
		mPager.setCurrentItem(position);

		mPager.setOnTouchListener(new ResetGUITimer());
		
		Button back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				resetTimer();
				int idx = (mPager.getCurrentItem() - 1) % mThumbIds.length;
				mPager.setCurrentItem(idx);
			}
		});

		Button next = (Button) findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				resetTimer();
				int idx = (mPager.getCurrentItem() + 1) % mThumbIds.length;
				mPager.setCurrentItem(idx);
			}
		});

		Button home = (Button) findViewById(R.id.home);
		home.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 FullImageActivity.this.finish();
			}
		});

		mTopbar = (LinearLayout) findViewById(R.id.topbar);
		mBottombar = (LinearLayout) findViewById(R.id.bottombar);
		showGUI();
		
		startTimer();
	}

	/** Timer functions **/
	
	private void startTimer() {
		mTimerTask = new HideGUITimerTask();
		mTimer.schedule(mTimerTask, 3000);
	}
	
	private void resetTimer() {
		mTimerTask.cancel();
		startTimer();
	}
	
	/** Show & Hide GUI **/
	
	private void showGUI() {
		if ( mGUIIsShowing ){
			return;
		}
		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setDuration(500);
		mTopbar.startAnimation(fadeIn);
		mTopbar.setVisibility(View.VISIBLE);
		
		fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setDuration(500);
		mBottombar.startAnimation(fadeIn);
		mBottombar.setVisibility(View.VISIBLE);
		mGUIIsShowing = true;
	}

	private void hideGUI() {
		if ( !mGUIIsShowing ){
			return;
		}
		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setDuration(500);
		mTopbar.startAnimation(fadeOut);
		mTopbar.setVisibility(View.INVISIBLE);
		
		mBottombar.startAnimation(fadeOut);
		mBottombar.setVisibility(View.INVISIBLE);
		mGUIIsShowing = false;
	}
	
	/** HELP CLASSES **/
	
	private class HideGUITimerTask extends TimerTask {
		@Override
		public void run() {
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					hideGUI();
				}
			});
			
		}
	}
	
	private class ResetGUITimer implements OnTouchListener {
		private static final float THRESHOLD = 5f;
		
	    private float mLastX = 0;

	    @Override
	    public boolean onTouch(View v, MotionEvent event) {
	        switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	            mLastX = event.getX();
	            break;

	        case MotionEvent.ACTION_UP:
	            float newX = event.getX();
	            if (Math.abs(mLastX - newX) < THRESHOLD) {
	                showGUI();
	                resetTimer();
	                return true;
	            }
	            mLastX = 0;
	            break;
	        }
	        return false;
	    }
	}
}