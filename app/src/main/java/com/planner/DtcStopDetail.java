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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class DtcStopDetail extends Activity {//implements AdapterView.OnItemClickListener  {
	Spinner _dtcStopsSpinner;
	Button _fetchDetail, _viewOnMap;
	ImageView _back;
	EditText showResult;
	ArrayAdapter <String> adapt;
	String  StopSelected;
	ArrayList<String> stopsname =  new ArrayList<String>();
	
	  
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dtcstopdetail);
        InitilizeComponents();
        try{
            fillFormSpinner();
        }catch(Exception ae){
            Log.e("Error :", ""+ae.getMessage());
        }
	}
	public void InitilizeComponents(){

		//_viewOnMap = (Button)findViewById(R.id.DSDbuttonviewonmap);
		//_back =(ImageView)findViewById(R.id.DSDimageviewback);
		showResult = (EditText)findViewById(R.id.DSDeditboxshowdetail);
    	_dtcStopsSpinner = (Spinner)findViewById(R.id.DSDspinnerstops);
    	_fetchDetail = (Button)findViewById(R.id.DSDbuttonfetchstopdetail);
    	_fetchDetail.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try{
		           // Toast.makeText(getApplicationContext(), "Working Before getStopDetail", Toast.LENGTH_SHORT).show();
					getStopDetail();
		        }catch(Exception ae){
		            Log.e("Error :", ""+ae.getMessage());
		        }
				
			}
		});	
    	_dtcStopsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		
    		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    		{
    			StopSelected = parent.getItemAtPosition(pos).toString();
    			 Toast.makeText(getApplicationContext(), "stopSelected"+StopSelected, Toast.LENGTH_SHORT).show();
    			
    		}
    		public void onNothingSelected(AdapterView<?> parent){
    			
    		}
    	});
    	
    	
	}
	private void fillFormSpinner() throws SQLiteException, IOException {
		
		  DBHelperBusStops db = new DBHelperBusStops(this);
		   db.openDataBase();
		   Cursor cur=db.displayStops();
			
		   if(cur.moveToFirst())
			{
				do
				{
					String str=cur.getString(1);
					stopsname.add(str);
				}while(cur.moveToNext());
				adapt = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,stopsname);
		    	_dtcStopsSpinner.setAdapter(adapt);
				
			}
		   db.close();
	    }
	  private void getStopDetail() throws SQLiteException, IOException{
		   String Towards = "Null Values", Location = "Null Values", Zone= "Null Values",  Detail = "NULL VALUES";
		   
		   DBHelperBusStops db = new DBHelperBusStops(this);
		   db.openDataBase();
		   Cursor cur=db.displayStopDetail(StopSelected);
		   Toast.makeText(getApplicationContext(), "stopSelected"+StopSelected, Toast.LENGTH_SHORT).show();
		   if(cur.moveToFirst())
			{
				do
				{
					Towards = cur.getString(2);
					Location = cur.getString(3);
					Zone = cur.getString(4);
			}while(cur.moveToNext());
				//showResult.setText("Hello this is working");
		    	Detail =" ********* "+StopSelected+"********* \n\n"+
		    			"Towards : "+Towards+"\n"
		    	        + "Nearby : "+Location+"\n"
		    	        +"Zone : "+Zone+"\n";
				
		    	showResult.setText(Detail);
			}
		   
}
}
