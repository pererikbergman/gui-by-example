package se.uncle.guibyexample.example.spinner;

import java.util.ArrayList;

import se.uncle.guibyexample.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerActivity extends Activity {
	private Spinner spinner;
	ArrayList<String> spinnerArray = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner);
		
		spinnerArray.add("A1");
		spinnerArray.add("A2");
		spinnerArray.add("A3");
		spinnerArray.add("A4");
		spinnerArray.add("A5");
		spinner = (Spinner) findViewById(R.id.spinner1);
		
		ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
		        android.R.layout.simple_spinner_dropdown_item,
		            spinnerArray);
		    spinner.setAdapter(spinnerArrayAdapter);
	}
}
