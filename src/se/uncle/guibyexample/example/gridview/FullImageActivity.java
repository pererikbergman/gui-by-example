package se.uncle.guibyexample.example.gridview;

import se.uncle.guibyexample.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class FullImageActivity extends Activity {

	public static final String POSITION = "position";
	
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
		setContentView(R.layout.full_image);

		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		int position = extras.getInt(POSITION);
		
        mPager = (ViewPager) findViewById(R.id.myfivepanelpager);
        mPagerAdapter = new ViewPagerAdapter(this, mThumbIds);
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(position);
	}
}