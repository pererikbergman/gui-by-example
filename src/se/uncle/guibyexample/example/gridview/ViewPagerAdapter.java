package se.uncle.guibyexample.example.gridview;

import se.uncle.guibyexample.R;
import android.app.Activity;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ViewPagerAdapter extends PagerAdapter {
	
	private int imageArray[] = { R.drawable.pic_1, R.drawable.pic_2,
			R.drawable.pic_3, R.drawable.pic_4, R.drawable.pic_5,
			R.drawable.pic_6, R.drawable.pic_7, R.drawable.pic_8,
			R.drawable.pic_9, R.drawable.pic_10, R.drawable.pic_11,
			R.drawable.pic_12, R.drawable.pic_13, R.drawable.pic_14,
			R.drawable.pic_15 };

	private Activity mActivity;
	
	public ViewPagerAdapter(Activity act) {
		mActivity = act;
	}

	public int getCount() {
		return imageArray.length;
	}

	public Object instantiateItem(View collection, int position) {
		ImageView view = new ImageView(mActivity);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		view.setScaleType(ScaleType.FIT_XY);
		view.setBackgroundResource(imageArray[position]);
		((ViewPager) collection).addView(view, 0);
		return view;
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView((View) arg2);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == ((View) arg1);
	}

	@Override
	public Parcelable saveState() {
		return null;
	}
}