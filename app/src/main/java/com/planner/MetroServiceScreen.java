package com.planner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MetroServiceScreen extends Activity implements OnClickListener{
	ImageView _backimage;
	Button _planRoute, _lineDetail, _stationDetail, _care;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metroservicescreen);
        initilizeComponents();
    
    _backimage.setOnClickListener(new OnClickListener() {
		
		
		public void onClick(View v) {
			Toast.makeText(getApplicationContext(), "Choose Your Option", Toast.LENGTH_SHORT).show();
			Intent gomain = new Intent(getApplicationContext(),MainScreen.class);
			startActivity(gomain);
			finish();
		}
	});
  
	}
	public void initilizeComponents(){
		_backimage = (ImageView)findViewById(R.id.MSimageviewgoback);    
		_backimage.setOnClickListener(this);
	    _planRoute =(Button)findViewById(R.id.MSbuttonplanroute);
	    _planRoute.setOnClickListener(this);
	    _lineDetail = (Button)findViewById(R.id.MSbuttonlinedetail);
	    _lineDetail.setOnClickListener(this);
	    _stationDetail = (Button)findViewById(R.id.MSbuttonstationdetail);
	    _stationDetail.setOnClickListener(this);
	    _care = (Button)findViewById(R.id.MSbuttoncustomercare);
	    _care.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		
		switch(v.getId()){
		
		case R.id.MSbuttonstationdetail:
			Intent gostationdetail = new Intent(getApplicationContext(), MetroStationDetail.class);
			startActivity(gostationdetail);
		break;
		
		case R.id.MSbuttonlinedetail:
			Intent golinedetail = new Intent(getApplicationContext(), MetroLineDetail.class);
			startActivity(golinedetail);
		break;
		
		case R.id.MSbuttonplanroute:
			Intent gometroplanroute = new Intent(getApplicationContext(),MetroPlanRoute.class);
			startActivity(gometroplanroute);
		break;
		
		case R.id.MSbuttoncustomercare:
			Intent goccare = new Intent(getApplicationContext(), CustomerCareMetro.class);
			startActivity(goccare);
		break;
		
		case R.id.MSimageviewgoback:
			// Toast.makeText(getApplicationContext(), "Choose Your Option", Toast.LENGTH_SHORT).show();
			Intent gomain = new Intent(getApplicationContext(),MainScreen.class);
			startActivity(gomain);
			finish();
		break;
		}
	}
	
	
}
