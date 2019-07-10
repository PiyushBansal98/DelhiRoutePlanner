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
import android.widget.Spinner;
import android.widget.TextView;

public class MetroStationDetail extends Activity  {
	
	Spinner _stations;
	Button _fetchDetail;
	Button _xxxxx;
	String StationSelected;
	TextView _LineStatus;
	EditText _showDetail;
	ArrayList<String> stationname= new ArrayList<String>();
	ArrayAdapter<String> adapt;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.metrostationdetail);
		initilizeComponents();
		try{
			getStations();
		}catch(Exception ae) {
	
		}

	}
	public void initilizeComponents(){
		_LineStatus = (TextView)findViewById(R.id.MSDtextviewlinestatus);
		_stations = (Spinner)findViewById(R.id.MSDspinnerstations);
		_stations.setOnItemSelectedListener(new OnItemSelectedListener() {
			
    		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    		{
    			StationSelected = parent.getItemAtPosition(pos).toString();
    			
    		}
    		public void onNothingSelected(AdapterView<?> parent){
    			
    		}
    	});
		
		_fetchDetail = (Button)findViewById(R.id.MSDbuttonfetchdetail);
		_fetchDetail.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				try{
					getStationsDetail();
				}catch(Exception ae){
					Log.w("","error"+ae.getMessage());
				}
				
			}
		});
		_showDetail = (EditText)findViewById(R.id.MSDedittextshowdetail);
		
		
	}

	public void getStations()throws SQLiteException, IOException{
		 DBHelperMetro db = new DBHelperMetro(this);
		   db.openDataBase();
		   Cursor cur=db.getStations();
			
		   if(cur.moveToFirst())
			{
				do
				{
					String str=cur.getString(1);
					stationname.add(str);
				}while(cur.moveToNext());
				adapt = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,stationname);
		    	_stations.setAdapter(adapt);
				
			}
	    }

	public void getStationsDetail()throws SQLiteException, IOException{
		 String line, nearby, Detail = ""; 
		DBHelperMetro db = new DBHelperMetro(this);
		   db.openDataBase();
		   Cursor c = db.getStationDetail(StationSelected);
		   if(c.moveToFirst())
			{
				do
				{
					line = c.getString(2);
					nearby = c.getString(3);
				}while(c.moveToNext());
				_LineStatus.setText("   "+line + "");
		    	Detail =" ****"+StationSelected+"***** \n\n"+
		    			"Nearby Stations : "+nearby+"\n";
		    	   
				_showDetail.setText(Detail);
			}
}

}
