package com.planner;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
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

public class MetroPlanRoute extends Activity {
	static ArrayList<String>stations = new ArrayList<String>();	 ;
	static String fair;	
	static String str,str1;
	int x=0;
	Button _getRoute, _viewonmap;
	TextView _showfare;
	Spinner _spinSource;
	Spinner _spinDestination;
	EditText _showDetail;
	String Source, Destination = "NO VALUE";
	ImageView _back;
	String SourceLine, DestinationLine = "NO VALUE";
	String mesg, Detail = "No Value";
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.metroplanroute);
		initilizeComponents();
	
	}
	public void initilizeComponents(){
		_showDetail = (EditText)findViewById(R.id.MPReditboxresult);
		_back = (ImageView)findViewById(R.id.MPRimageviewback);
		_viewonmap = (Button)findViewById(R.id.MPRbuttonviewonmap);
		_getRoute = (Button)findViewById(R.id.MPRbuttonfetchroute);
		_showfare = (TextView)findViewById(R.id.MPRtextviewfare);
		_spinSource = (Spinner)findViewById(R.id.MPRspinnersource);
		_spinDestination = (Spinner)findViewById(R.id.MPRspinnerdestination);
		
		getStations();
		
		//ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,stations);
		ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,stations);
		_spinSource.setAdapter(adapt);
		_spinDestination.setAdapter(adapt);
		
		_back.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				Intent goMetroService = new Intent(getApplicationContext(), MetroServiceScreen.class);
				startActivity(goMetroService);
				finish();
			}
		});
		
		_viewonmap.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				Intent seemap = new Intent(getApplicationContext(), MapImage.class);
				startActivity(seemap);
				
			}
		});
		_getRoute.setOnClickListener(new OnClickListener() {
			
		
			public void onClick(View v) {
			 
					mesg = "";
					Detail = "";
				_showDetail.setText("");
				
				startRoutePlan();
				
			}
		});
		
		_spinSource.setOnItemSelectedListener(new OnItemSelectedListener() {

			
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Source = arg0.getItemAtPosition(arg2).toString();
				str=stations.get(arg2).replace(' ', '_');
					Log.d("fhdsfshgfsh",str);
				
			}

			
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		
		});
		_spinDestination.setOnItemSelectedListener(new OnItemSelectedListener() {

			
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				Destination = arg0.getItemAtPosition(arg2).toString();
				str1=stations.get(arg2);
				
				Log.d("lllllllllllllll",str1);
				showfair(str,str1);
		}

			
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		
		});
	  }
	  
	  public void getStations(){
		  DBHelper db=new DBHelper(MetroPlanRoute.this);
			
			try {
				db.openDataBase();
				Cursor cur=db.getStation();
				if(cur.moveToFirst()){
					do{
					String station=cur.getString(0);
					stations.add(station);
					System.out.println(stations);
					}while(cur.moveToNext());
					
					}
				db.close();
				
			} catch (SQLiteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	  public void showfair(String str2, String str12) {
			
			DBHelper db=new DBHelper(MetroPlanRoute.this);
			try {
				db.openDataBase();
				String stat1,stat2;
				if(str2.contains(" ")){
					stat1=str2.replace(' ',  '_');
				}else{
					stat1 = str2;
				}
	/*			if(str12.contains(" ")){
					stat2=str12.replace(' ', '_');
					//stat2 = str12;
				}else{
					stat2 = str12;
				}
				
	*/			stat2 = str12;
				System.out.println("station "+str2+"   station2 "+str12);
					
				Cursor cur=db.displayFare(stat1,stat2);
				
				while(cur.moveToNext()){
					fair=cur.getString(0);
					_showfare.setText(fair);
					Log.d("fairrrrrrrrrrrrrrrrr", fair);
				}
				db.close();
				
			} catch (SQLiteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
 
public void searchSourceLineColor(){
	 DBHelperMetro db=new DBHelperMetro(MetroPlanRoute.this);
		try {
			db.openDataBase();
			Cursor cur=db.getSourceLineDetail(Source);
			if(cur.moveToFirst()){
				do{
				SourceLine =cur.getString(2);
				}while(cur.moveToNext());
				
				}
			db.close();
			
		} catch (SQLiteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
}
public void searchDestinationLineColor(){
	DBHelperMetro db=new DBHelperMetro(MetroPlanRoute.this);
	try {
		db.openDataBase();
		Cursor cur=db.getDestinationLineDetail(Destination);
		if(cur.moveToFirst()){
			do{
			DestinationLine=cur.getString(2);
			}while(cur.moveToNext());
			
			}
		db.close();
		
	} catch (SQLiteException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public void startRoutePlan(){
	searchSourceLineColor();
	searchDestinationLineColor();
	showRoutes(SourceLine, DestinationLine);
	Toast.makeText(getApplicationContext(), "Source Line : "+SourceLine+",  Destination Line "+DestinationLine, Toast.LENGTH_SHORT).show();
}
public void showRoutes(String src , String dest){
	Detail ="Source: "+Source+", "+src+" \n"
	+" Destination: "+Destination+", "+dest+" \n";
		
	if(src.equals("Blue Line") || dest.equals("Blue Line") ){
		 mesg= "Continue To "+src+" , You dont Need to Change the Metro"+ "\n HAPPY JOURNEY";
		_showDetail.setText("For VAISHALI Change from YAMUNA BANK \n "+Detail + mesg);
		
	}else if((src.equals("Blue Line") &&  dest.equals("Yellow Line")) ||( src.equals("Yellow Line") && dest.equals("Blue Line"))){
		mesg = "Change for Yellow Line : Rajiv Chowk \n"+"follow the Blue Line Route \n"+" \n HAPPY JOURNEY";
		_showDetail.setText("For VAISHALI Change from YAMUNA BANK \n "+Detail + mesg);
	
	}else if((src.equals("Blue Line") && dest.equals("Red Line")) || (src.equals("Red Line") || dest.equals("Blue Line"))){
				
			if(src.equals("Red Line")) {
					mesg ="Change for Yellow Line at : Kashmere Gate  \n"
					+"Change for Blue Line at : Rajiv Chowk \n"
					+"follow the Destination Route \n"
					+" \n HAPPY JOURNEY";
				}else {
					mesg ="Change for Yellow Line at : Rajiv Chowk \n"
					    +"Change for Red Line at : Kashmere Gate \n"
						+"follow the Destination Route \n"
					   +" \n HAPPY JOURNEY";
					_showDetail.setText("For VAISHALI Change from YAMUNA BANK \n "+Detail + mesg);
				}
				_showDetail.setText(Detail + mesg);
		
	}else if((src.equals("Blue Line") && dest.equals("Violet Line")) || (src.equals("Violet Line") && dest.equals("Blue Line"))){
		if(src.equals("Violet Line")) {
				mesg = "Change for Yellow Line at : Central Secretariate  \n"
					+"Change for Blue Line at : Rajiv Chowk \n"
					+"follow the Destination Route \n"
					+" \n HAPPY JOURNEY";
		}else {
			mesg =  "Change for Yellow Line at : Rajiv Chowk \n"
		        +"Change for Violet Line at : Central Secretariate \n"
		     	+"follow the Destination Route \n"
		        +" \n HAPPY JOURNEY";
		}
		_showDetail.setText("For VAISHALI Change from YAMUNA BANK \n "+Detail + mesg);
		
	}else if((src.equals("Blue Line") && dest.equals("Orange Line")) || (src.equals("Orange Line") && dest.equals("Blue Line"))) {
			// 5.  Blue to Orange and Orange to Blue 
			if(src.equals("Orange Line")) {
				mesg ="ORANGE : AIRPORT LINE \n" 
				+"Change for Yellow Line at : New Delhi  \n"
				+"Change for Blue Line at : Rajiv Chowk \n"
				+"follow the Destination Route \n"
				+" \n HAPPY JOURNEY";
			}else {
				mesg ="ORANGE : AIRPORT LINE \n"  
				+"Change for Yellow Line at : Rajiv Chowk \n"
				+"Change for Orange Line at : New Delhi \n"
				+"follow the Destination Route \n"
				+" \n HAPPY JOURNEY";
			}
			_showDetail.setText("For VAISHALI Change from YAMUNA BANK \n "+Detail + mesg);
	}else if((src.equals("Blue Line") && dest.equals("Green Line")) || (src.equals("Green Line") && dest.equals("Blue Line")) ){
		// 6. Blue to Green and Green to Blue
		if(src.equals("Green Line")) {
			mesg = "Change for Red Line at : Inderlok  \n"
				+"Change for Yellow Line at : Kashmere Gate \n"
				+"Change for Blue Line at : Rajeev Chowk \n"
				+"follow the Destination Route \n"
				+" \n HAPPY JOURNEY";
		}else {
			mesg = "Change for Yellow Line at : Rajeev Chowk  \n"
			+"Change for Red Line at : Kashmere Gate \n"
			+"Change for Green Line at : Inderlok \n"
			+"follow the Destination Route \n"
			+" \n HAPPY JOURNEY";
		}
		_showDetail.setText(Detail + mesg);
	}else if((src.equals("Yellow Line") && dest.equals("Yellow Line"))){
		mesg = "Follow the same Line, U dont need to Change Metro \n"+ "HAPPY JOURNEY";
		_showDetail.setText(Detail);
		
	}else if((src.equals("Yellow Line") && dest.equals("Red Line")) || (src.equals("Red Line") && dest.equals("Yellow Line"))){
				// 1 . yellow line to blue done, 2 yellow to yellow done
				// 3. yellow to red line
				mesg = "Change at : kashmere Gate  \n"
				+"follow the Destination Route \n"
				+" \n HAPPY JOURNEY";
				_showDetail.setText(Detail + mesg);
	
	}else if((src.equals("Yellow Line") && dest.equals("Orange Line")) || (src.equals("Orange Line") && dest.equals("Yellow Line"))){
			// yellow to Orange  and orange to yellow
			mesg = "Change at : New Delhi  \n"
			+"follow the Destination Route \n"
			+" \n HAPPY JOURNEY";
	
	}else if((src.equals("Yellow Line") && dest.equals("Green Line")) || (src.equals("Green Line") || dest.equals("Yellow Line"))){
		// 5. yellow to green & green  to yellow 
		if(src.equals("Green Line")) {
			mesg = "Change for Red Line at : Inderlok  \n"
				+"Change for Yellow Line at : Kashmere Gate \n"
				+"follow the Destination Route \n"
				+" \n HAPPY JOURNEY";
		}else {
			mesg = "Change for Red Line at : Kashmere Gate \n"
			+"Change for Green Line at : Inderlok \n"
			+"follow the Destination Route \n"
			+" \n HAPPY JOURNEY";
		}
		_showDetail.setText(Detail + mesg);
	}else if ((src.equals("Yellow Line") && dest.equals("Violet Line")) || (src.equals("Violet Line") && dest.equals("Yellow Line"))){
		// 6. yellow line to violet line
		mesg = "Change at : Central Secretariate  \n"
			+"follow the Destination Route \n"
			+" \n HAPPY JOURNEY";
			_showDetail.setText(Detail + mesg);
	
	}else if(src.equals("Red Line") && dest.equals("Red Line")){
		// 1. red to red
		mesg = "Follow the same Line, U dont need to Change Metro \n"+ "HAPPY JOURNEY";
		_showDetail.setText(Detail + mesg);
	
	}else if((src.equals("Red Line") && dest.equals("Orange Line")) || (src.equals("Orange Line") && dest.equals("Red Line"))){
		
		if(src.equals("Orange Line")) {
			mesg ="ORANGE : AIRPORT LINE\n" 
				+"Change for Yellow Line at : New Delhi  \n"
				+"Change for Red Line at : Kashmere Gate \n"
				+"follow the Destination Route \n"
				+" \n HAPPY JOURNEY";
		}else {
			mesg ="ORANGE : AIRPORT LINE\n" 
				+"Change for Yellow Line at : Kashmere Gate \n"
			+"Change for Orange Line at : New Delhi \n"
			+"follow the Destination Route \n"
			+" \n HAPPY JOURNEY";
		}
		_showDetail.setText(Detail + mesg);
	
	}else if((src.equals("Red Line") && dest.equals("Green Line")) || (src.equals("Green Line") && dest.equals("Red Line"))){
		mesg = "Change at : Inderlok  \n"
			+"follow the Destination Route \n"
			+" \n HAPPY JOURNEY";
			_showDetail.setText(Detail + mesg);
			
	}else if((src.equals("Red Line") && dest.equals("Violet Line")) || (src.equals("Violet Line") && dest.equals("Red Line"))){
		if(src.equals("Violet Line")) {
			mesg ="Change for Yellow Line at : New Delhi  \n"
				+"Change for Red Line at : Kashmere Gate \n"
				+"follow the Destination Route \n"
				+" \n HAPPY JOURNEY";
		}else {
			mesg ="Change for Yellow Line at : Kashmere Gate  \n"
			+"Change for Violet Line at :  Central Secretariate  \n"
			+"follow the Destination Route \n"
			+" \n HAPPY JOURNEY";
		}
		_showDetail.setText(Detail + mesg);
	}else if(src.equals("Orange Line") && dest.equals("Orange Line")){
		mesg = "Follow the same Line, U dont need to Change Metro \n"+ "HAPPY JOURNEY";
		_showDetail.setText(Detail + mesg);
		
	}else if((src.equals("Orange Line") && dest.equals("Green Line")) || (src.equals("Green Line") && dest.equals("Orange Line"))){
		//orange to green  now 4.
		if(src.equals("Green Line")) {
			mesg ="ORANGE : AIRPORT LINE\n" 
				+"Change for Red Line at : Inderlok  \n"
				+"Change for Yellow Line at : Kashmere Gate \n"
				+"Change for Orange Line at : New Delhi \n"
				+"follow the Destination Route \n"
				+" \n HAPPY JOURNEY";
		}else {
			mesg ="ORANGE : AIRPORT LINE\n" 
				+"Change for Yellow Line at : New Delhi \n"
				+"Change for Red Line at : Kashmere Gate \n"
				+"Change for Green Line at : Inderlok \n"
				+"follow the Destination Route \n"
				+" \n HAPPY JOURNEY";
		}
		_showDetail.setText(Detail + mesg);
		
	}else if((src.equals("Orange Line") && dest.equals("Violet Line")) || (src.equals("Violet Line") && dest.equals("Orange Line"))){
		
		if(src.equals("Violet Line")) {
			mesg ="ORANGE : AIRPORT LINE\n" 
				+"Change for Yellow Line at : Central Secretariate \n"
				+"Change for Orange Line at : New Delhi \n"
				+"follow the Destination Route \n"
				+" \n HAPPY JOURNEY";
		}else {
			mesg ="ORANGE : AIRPORT LINE\n" 
				+"Change for Yellow Line at : New Delhi \n"
				+"Change for Violet Line at : Central Secretariate \n"
				+"follow the Destination Route \n"
				+" \n HAPPY JOURNEY";
		}
		_showDetail.setText(Detail + mesg);
		
	}else if(src.equals("Green Line") && dest.equals("Green Line")){
		mesg = "Follow the same Line, \n You dont need to Change Metro \n"+ "HAPPY JOURNEY";
		_showDetail.setText(Detail + mesg);
	}else if(src.equals("Green Line") || dest.equals("Violet Line") && src.equals("Violet Line") || dest.equals("Green Line")){
		
		if(src.equals("Violet Line")) {
			mesg = "Change for Yellow Line at : Central Secretariate "
				+"Change for Red Line at : Kashmere Gate  \n"
				+"Change for Green Line at : Inderlok \n"
				+"follow the Destination Route \n"
				+" \n HAPPY JOURNEY";
		}else {
			mesg = "Change for Red Line at : Inderlok \n"
			+"Change for Yellow Line at : Kashmere Gate \n"
			+"Change for Violet Line at : Central Secretariate \n"
			+"follow the Destination Route \n"
			+" \n HAPPY JOURNEY";
		}
		_showDetail.setText(Detail + mesg);
		
	}else if(src.equals("Violet Line") || dest.equals("Violet Line")){
		mesg = "Follow the same Line, \n You dont need to Change Metro \n"+ "HAPPY JOURNEY";
		_showDetail.setText(Detail + mesg);
	}
	
}
}

