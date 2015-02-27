package com.example.testapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.entity.InputStreamEntity;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {


	private Button go;
	private EditText ed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Intent intent=new Intent(this,DisplayInfoActivity.class);
		setContentView(R.layout.activity_main);
		go=(Button)findViewById(R.id.go);
		ed=(EditText)findViewById(R.id.username);
		go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(new Runnable(){
					public void run(){
						
						URL url;
						try {
							url = new URL("http://codeforces.com/api/user.info?handles="+ed.getText().toString());
							try {
								URLConnection con=url.openConnection();
								BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
								String out="";
								String input=null;
								while((input=br.readLine())!=null){
									out+=input;
								}
								try {
									JSONObject jo=new JSONObject(out);
									String handle=null;
									String firstName=null;
									String lastName=null;
									String organization=null;
									String rank=null;
									int rating=-1;
									intent.putExtra("status",jo.getString("status"));
									handle=jo.getJSONArray("result").getJSONObject(0).getString("handle");
									//String handle="sai";
									firstName=jo.getJSONArray("result").getJSONObject(0).getString("firstName");
									lastName=jo.getJSONArray("result").getJSONObject(0).getString("lastName");
									organization=jo.getJSONArray("result").getJSONObject(0).getString("organization");
									rank=jo.getJSONArray("result").getJSONObject(0).getString("rank");
									rating=jo.getJSONArray("result").getJSONObject(0).getInt("rating");
										intent.putExtra("handle",handle);
										intent.putExtra("name",firstName+" "+lastName);
										intent.putExtra("organization",organization);
										intent.putExtra("rank",rank);
										intent.putExtra("rating",rating);
										
										
									
									startActivity(intent);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					
				}
				).start();
			/*	
			*/	
			}
		}
		);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
