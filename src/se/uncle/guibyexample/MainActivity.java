package se.uncle.guibyexample;

import se.uncle.guibyexample.example.gridview.GridViewActivity;
import se.uncle.guibyexample.example.slideout.SampleActivity;
import se.uncle.guibyexample.example.spinner.SpinnerActivity;
import android.os.Bundle;


public class MainActivity extends ActivityListStarter {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		addItem("Spinner", SpinnerActivity.class);
		addItem("Grid", GridViewActivity.class);
		addItem("SlideOut", SampleActivity.class);
		super.onCreate(savedInstanceState);
	}

}
