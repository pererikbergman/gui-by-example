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
	private Spinner spinner;
	final ArrayList<String> spinnerArray = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner_activity);

		spinnerArray.add("A1");
		spinnerArray.add("A2");
		spinnerArray.add("A3");
		spinnerArray.add("A4");
		spinnerArray.add("A5");
		spinner = (Spinner) findViewById(R.id.spinner);

		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
				this, R.layout.spinner_item, spinnerArray) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				final LayoutInflater inflater = SpinnerActivity.this.getLayoutInflater();
				final View spinnerEntry = inflater.inflate(
						R.layout.spinner_item, null);
				final TextView contactName = (TextView) spinnerEntry
						.findViewById(R.id.text);
				final ImageView contactImage = (ImageView) spinnerEntry
						.findViewById(R.id.image);
				
				contactName.setText(spinnerArray.get(position));
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
				
				contactName.setText(spinnerArray.get(position));
				contactImage.setImageResource(R.drawable.ic_launcher);
				return spinnerEntry;
			}

		};
		spinner.setAdapter(spinnerArrayAdapter);
		
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
