package se.uncle.guibyexample.example.slideout;

import se.uncle.guibyexample.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

public class SampleActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample);

//		getActionBar().hide();

		findViewById(R.id.sample_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int width = (int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 40, getResources()
										.getDisplayMetrics());
						SlideoutActivity.prepare(SampleActivity.this,
								R.id.inner_content, width);
						startActivity(new Intent(SampleActivity.this,
								MenuActivity.class));
						overridePendingTransition(0, 0);
					}
				});
	}

}