package com.planner;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class BusRouteDetail extends Activity 
{
	ImageView _back;
	Spinner _RouteSpinner;
	Button _fetchDetail, _toMainMenu;
	EditText _showDetail;
	String selectedRoute;
	ArrayAdapter<String> adapt;
	ArrayList<String> BusRoutes = new ArrayList<String>();
	
public void onCreate(Bundle bun){
		super.onCreate(bun);
		setContentView(R.layout.busroutedetail);
		populateFrom();
		try{
			fillSpinner();
		}catch(Exception ae){
			Log.w("BusRouteDetail",""+ae.getMessage());
		}
}
	
public void populateFrom(){
	_showDetail =(EditText)findViewById(R.id.DRDeditboxshowdetail);
	_showDetail.setFocusable(false);
	_showDetail.setEnabled(false);
	
//	_back = (ImageView)findViewById(R.id.DRDimageviewback);
//	_back.setOnClickListener(this);
	
	_RouteSpinner = (Spinner)findViewById(R.id.DRDspinnerroutes);
	_RouteSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long arg3) {
			selectedRoute = parent.getItemAtPosition(pos).toString();
			
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	
	});
	
	_fetchDetail = (Button)findViewById(R.id.DRDbuttonfetchroutedetail);
	_fetchDetail.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			try{
				getRouteDetail();
			}catch(Exception ae){
				Log.w("Error in Fetching Route Detail",""+ae.getMessage());
			}
			
		}
	});
//	_toMainMenu = (Button)findViewById(R.id.DRDbuttonmainmenu);
	//_toMainMenu.setOnClickListener(this);

}


private void fillSpinner()throws SQLiteException, IOException {
	
	  DBHelperBusRouteDetail db = new DBHelperBusRouteDetail(this);
	   db.openDataBase();
	   Cursor cur=db.getRoutesName();
		
	   if(cur.moveToFirst())
		{
			do
			{
				String str=cur.getString(0);
				BusRoutes.add(str);
			}while(cur.moveToNext());
			adapt = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,BusRoutes);
	    	_RouteSpinner.setAdapter(adapt);
			
		}
	   cur.close();
}
private void getRouteDetail() throws SQLiteException, IOException{
	   String Starts, Ends,  MiddleStops = "No Stops", Length,  Detail = "";
	   
	   DBHelperBusRouteDetail db = new DBHelperBusRouteDetail(this);
	   db.openDataBase();
	   Cursor cur=db.getRouteDetail(selectedRoute);
	  
	   if(cur.moveToFirst())
		{
			do
			{
				Starts = cur.getString(1);
				Ends = cur.getString(2);
				MiddleStops = cur.getString(3);
				Length = cur.getString(4);
				//Toast.makeText(getApplicationContext(), "Working in loop", Toast.LENGTH_SHORT).show();
			}while(cur.moveToNext());
	    	
	    	
	    	Detail =" ****** Route No: "+selectedRoute+"***** \n"
	    			+"Route Length: "+Length+" KM \n"+
	    			"Starts At:   "+Starts+"\n"
	    	        + "Ends At:   "+Ends+"\n"
	    	        +"Middle Stops Are : \n"+MiddleStops+"\n \n \n";
	    	        
	    	//Toast.makeText(getApplicationContext(), "Working after Detail Specified", 100).show();	    	
	    	_showDetail.setText(Detail);
	    	
	    	
		}
	   cur.close();
	   
}
}