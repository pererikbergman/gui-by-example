package se.uncle.guibyexample.example.spinner;

import java.util.ArrayList;

import se.uncle.guibyexample.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

public class SpinnerActivity extends SherlockActivity {
	private Spinner mSpinner;
	private final ArrayList<String> mSpinnerArray = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner_activity);

		mSpinnerArray.add("A1");
		mSpinnerArray.add("A2");
		mSpinnerArray.add("A3");
		mSpinnerArray.add("A4");
		mSpinnerArray.add("A5");
		mSpinner = (Spinner) findViewById(R.id.spinner);

		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
				this, R.layout.spinner_item, mSpinnerArray) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				final LayoutInflater inflater = SpinnerActivity.this.getLayoutInflater();
				final View spinnerEntry = inflater.inflate(
						R.layout.spinner_item, null);
				final TextView contactName = (TextView) spinnerEntry
						.findViewById(R.id.text);
				final ImageView contactImage = (ImageView) spinnerEntry
						.findViewById(R.id.image);
				
				contactName.setText(mSpinnerArray.get(position));
				contactImage.setImageResource(R.drawable.ic_launcher);
				return spinnerEntry;
			}

			@Override
			public View getDropDownView(int position, View convertView,
					ViewGroup parent) {
				final LayoutInflater inflater = SpinnerActivity.this.getLayoutInflater();
				final View spinnerEntry = inflater.inflate(
						R.layout.spinner_item, null);
				final TextView contactName = (TextView) spinnerEntry
						.findViewById(R.id.text);
				final ImageView contactImage = (ImageView) spinnerEntry
						.findViewById(R.id.image);
				
				contactName.setText(mSpinnerArray.get(position));
				contactImage.setImageResource(R.drawable.ic_launcher);
				return spinnerEntry;
			}

		};
		mSpinner.setAdapter(spinnerArrayAdapter);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
