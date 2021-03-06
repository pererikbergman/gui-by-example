package se.uncle.guibyexample.example.slideout;

import se.uncle.guibyexample.R;
import se.uncle.guibyexample.example.slideout.help.BaseActivity;
import se.uncle.guibyexample.example.slideout.help.SampleListFragment;
import se.uncle.guibyexample.example.slideout.help.TestFragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;

public class SlideInMenuActivity extends BaseActivity {

	public SlideInMenuActivity() {
		super(R.string.title_bar_slide);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// set the Above View
		setContentView(R.layout.slidein_content_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, new TestFragment())
		.commit();
		
		setSlidingActionBarEnabled(true);
	}

	@Override
	public ListFragment getSlideInMenu() {
		return new SampleListFragment();
	}

}