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
import android.widget.TextView;
import android.widget.Toast;

public class MetroLineDetail extends Activity implements OnClickListener{
	ImageView _backtomain;
	Button _fetchLineDetail, _viewonmap;
	Spinner _linenames;
	TextView _lineColor;
	EditText _showdetail;
	ArrayAdapter <String> adapt;
	String str, LineSelected;
	ArrayList<String> stationname =  new ArrayList<String>();
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.metrolinedetail);
		initilizeComponents();
		try{
			fillFormSpinner();
		}catch(Exception ae){
			Log.e("MetroLineDetail", "Error in getting database value");
		}
	
	}
	public void initilizeComponents(){
		_lineColor = (TextView)findViewById(R.id.textView12);
		
		_backtomain = (ImageView)findViewById(R.id.MLDimageviewback);
		_backtomain.setOnClickListener(this);
		
		_fetchLineDetail = (Button)findViewById(R.id.MLDbuttonfetchlinedetail);
		_fetchLineDetail.setOnClickListener(this);
		
		_viewonmap = (Button)findViewById(R.id.MLDbuttonviewonmap);
		_viewonmap.setOnClickListener(this);
		
		_linenames = (Spinner)findViewById(R.id.MLDspinnerline);
		
		_showdetail = (EditText)findViewById(R.id.MLDeditboxshowdetail);
		_showdetail.setOnClickListener(this);
		_showdetail.setFocusable(false);
		_showdetail.setEnabled(false);
		//_showdetail.setTextColor(R.color.green);
		
	
		_linenames.setOnItemSelectedListener(new OnItemSelectedListener() {
		
    		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    		{
    			LineSelected = parent.getItemAtPosition(pos).toString();
    			
    		}
    		public void onNothingSelected(AdapterView<?> parent){
    			
    		}
    	});
    	
		
		
		_fetchLineDetail.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try{
					getLineDetail();
					 
				}catch(Exception ae){
					Log.e("MetroLineDetail", "Error :"+ae.getMessage());
				}
				
			}
		});
	}

	private void fillFormSpinner() throws SQLiteException, IOException {
		
		  DBHelperMetro db = new DBHelperMetro(this);
		   db.openDataBase();
		   Cursor cur=db.getLine();
			
		   if(cur.moveToFirst())
			{
				do
				{
					String str=cur.getString(1);
					stationname.add(str);
				}while(cur.moveToNext());
					adapt = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,stationname);
					_linenames.setAdapter(adapt);
				
			}
	    }
	private void getLineDetail() throws SQLiteException, IOException{
		   String Detail, NoOfInterchange,Interchange, Startat, Endsat ="amit detail here";
		   
		   DBHelperMetro db = new DBHelperMetro(this);
		   db.openDataBase();
		   Cursor cur=db.getLineDetail(LineSelected);
		   if(cur.moveToFirst())
			{
				do
				{
					NoOfInterchange = cur.getString(2);
					Interchange = cur.getString(3);
					Startat = cur.getString(4);
					Endsat = cur.getString(5);
				}while(cur.moveToNext());
	
				
		    	Detail =" ******* "+LineSelected+" Line"+"******* \n\n"+
		    			"No Of Interchange : "+NoOfInterchange+"\n"
		    	        + "Interchange Stations : "+Interchange+"\n"
		    	        +"Line Starts at : "+Startat+"\n"
		    	        +"Line Ends at : "+Endsat+"\n";
		    	
		    	Toast.makeText(getApplicationContext(), "Working in metro in ", Toast.LENGTH_SHORT).show();
		    	
				 		
		    	_showdetail.setText(Detail);
			}
		   
	   }
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
