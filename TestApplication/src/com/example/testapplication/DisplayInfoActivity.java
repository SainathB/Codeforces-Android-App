package com.example.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayInfoActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent in=getIntent();
		String status=in.getStringExtra("status");
		if(status.equals("OK")){
			setContentView(R.layout.display_info);
			String total=in.getStringExtra("total");
			String handle=in.getStringExtra("handle");
			String name=in.getStringExtra("name");
			String organization=in.getStringExtra("organization");
			String rank=in.getStringExtra("rank");
			int rating=in.getIntExtra("rating",0);
			
			TextView h=(TextView)findViewById(R.id.handle);
			h.setText(handle);
			TextView nm=(TextView)findViewById(R.id.name);
			nm.setText(name);
			TextView org=(TextView)findViewById(R.id.organization);
			org.setText(organization);
			TextView rnk=(TextView)findViewById(R.id.rank);
			rnk.setText(rank);
			TextView rat=(TextView)findViewById(R.id.rating);
			Integer rr=new Integer(rating);
			rat.setText(rr.toString());

		}
		else{
			setContentView(R.layout.failure);
		}
				
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_info, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
