package com.planner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class DtcServiceScreen extends Activity {
	
	Button _planRoute, _stopDetail, _RouteDetail, _Customercare;
	//ImageView _goBack;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dtcservicescreen);
		
		//_goBack = (ImageView)findViewById(R.id.DSImageviewback);
		//_planRoute = (Button)findViewById(R.id.DSbuttonplanroute);
		_stopDetail = (Button)findViewById(R.id.DSbuttonstopdetail);
		_RouteDetail =(Button)findViewById(R.id.DSbuttonroutedetail);
		_Customercare = (Button)findViewById(R.id.DSbuttoncustomercare);
		
		/*_goBack.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent gobacktomain = new Intent(getApplicationContext(),MainScreen.class);
				startActivity(gobacktomain);
				
			}
		});*/
		
		
		_stopDetail.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent gostopdetail = new Intent(getApplicationContext(), DtcStopDetail.class);
				startActivity(gostopdetail);
				
			}
		});
		
		_RouteDetail.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent goRoute = new Intent(getApplicationContext(), BusRouteDetail.class);	
				startActivity(goRoute);
			}
		});
		
		_Customercare.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent gocare = new Intent(getApplicationContext(), CustomerCareDtc.class);
				startActivity(gocare);
				
			}
		});
		
	}
}
