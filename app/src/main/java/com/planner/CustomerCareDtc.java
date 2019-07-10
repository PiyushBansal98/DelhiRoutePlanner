package com.planner;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
	
public class CustomerCareDtc extends Activity{
	String Mesg;
	TextView detail;
	Button Police, Fire, Ambulance;
	TextView Num1, Num2;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customercaredtc);
	
        initComponents();
	}
	public void initComponents(){
		Mesg = "DTC Bhawan \n"+
		"ITO, IP Estate\n"+
		"New Delhi - 110002, India \n"+
		"Contact No. - 011-233370236 \n"+
		"\t \t -  011-23315085\n";
		
		detail = (TextView)findViewById(R.id.CCDTextViewDetail);
		detail.setText(Mesg);
		
		Police = (Button)findViewById(R.id.CCDButtonPolice);
		Police.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try {       
					Intent intent = new Intent(Intent.ACTION_CALL);
					intent.setData(Uri.parse("tel: +9111-100"));
					Toast.makeText(getApplicationContext(), "Calling Delhi Police HeadQuator", 200).show();
					startActivity(intent);      
					} catch (Exception e) {
						Log.w("Error calling ", " "+e.getMessage());
						Toast.makeText(getApplicationContext(), "Problem calling number.", Toast.LENGTH_LONG).show();
					} 
				
			}
		});
		Fire = (Button)findViewById(R.id.CCDButtonFire);
		Fire.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try {       
					Intent intent = new Intent(Intent.ACTION_CALL);
					intent.setData(Uri.parse("tel: +9111-101"));
					Toast.makeText(getApplicationContext(), "Calling Delhi Fire Brigade", 200).show();
					startActivity(intent);      
					} catch (Exception e) {
						Log.w("Error calling ", " "+e.getMessage());
						Toast.makeText(getApplicationContext(), "Problem calling number.", Toast.LENGTH_SHORT).show();
					} 
			}
		});
		Ambulance = (Button)findViewById(R.id.CCDButtonAmbulance);
		Ambulance.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try {       
					Intent intent = new Intent(Intent.ACTION_CALL);
					intent.setData(Uri.parse("tel: +9111-102"));
					Toast.makeText(getApplicationContext(), "Calling Ambulance Service", Toast.LENGTH_SHORT).show();
					startActivity(intent);      
					} catch (Exception e) {
						Log.w("Error calling ", " "+e.getMessage());
						Toast.makeText(getApplicationContext(), "Problem calling number.", Toast.LENGTH_SHORT).show();
					} 
			}
		});
		Num1 = (TextView)findViewById(R.id.CCDTextViewCallNum1);
		Num1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try {       
					Intent intent = new Intent(Intent.ACTION_CALL);
					intent.setData(Uri.parse("tel: +91-11-41400400"));
					Toast.makeText(getApplicationContext(), "Calling DTC 24Hr Helpline", Toast.LENGTH_SHORT).show();
					startActivity(intent);      
					} catch (Exception e) {
						Log.w("Error calling ", " "+e.getMessage());
						Toast.makeText(getApplicationContext(), "Problem calling number.", Toast.LENGTH_SHORT).show();
					} 
			}
		});
		Num2 = (TextView)findViewById(R.id.CCDTextViewCallNum2);
		Num2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try {       
					Intent intent = new Intent(Intent.ACTION_CALL);
					intent.setData(Uri.parse("tel: +91-11-23370236"));
					Toast.makeText(getApplicationContext(), "Calling DTC 24Hr Helpline", Toast.LENGTH_SHORT).show();
					startActivity(intent);      
					} catch (Exception e) {
						Log.w("Error calling ", " "+e.getMessage());
						Toast.makeText(getApplicationContext(), "Problem calling number.", Toast.LENGTH_SHORT).show();
					} 
				}
		});	
	}
}
