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

public class CustomerCareMetro extends Activity{
	String Mesg;
	TextView detail;
	Button Police, Fire, Ambulance;
	TextView Num1, Num2;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customercaremetro);
        initComponents();
	}
	
	public void initComponents(){
		Mesg = "Metro Bhawan \n"+
		"Fire Brigade Lane, Barakhamba Road,\n"+
		"New Delhi - 110001, India \n"+
		"Board No. - 23417910/12 \n"+
		"Fax No. - 23417921 \n";
		
		detail = (TextView)findViewById(R.id.CCMTextViewDetail);
		detail.setText(Mesg);
		
		Police = (Button)findViewById(R.id.CCMButtonPolice);
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
		Fire = (Button)findViewById(R.id.CCMButtonFire);
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
		Ambulance = (Button)findViewById(R.id.CCMButtonAmbulance);
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
		Num1 = (TextView)findViewById(R.id.CCMTextViewnum1);
		Num1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try {       
					Intent intent = new Intent(Intent.ACTION_CALL);
					intent.setData(Uri.parse("tel: +91-11-155370"));
					Toast.makeText(getApplicationContext(), "Calling Metro 24Hr Helpline", Toast.LENGTH_SHORT).show();
					startActivity(intent);      
					} catch (Exception e) {
						Log.w("Error calling ", " "+e.getMessage());
						Toast.makeText(getApplicationContext(), "Problem calling number.", Toast.LENGTH_SHORT).show();
					} 
			}
		});
		Num2 = (TextView)findViewById(R.id.CCMTextViewnum2);
		Num2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try {       
					Intent intent = new Intent(Intent.ACTION_CALL);
					intent.setData(Uri.parse("tel: 011-155370"));
					Toast.makeText(getApplicationContext(), "Calling Mobile Helpline", Toast.LENGTH_SHORT).show();
					startActivity(intent);      
					} catch (Exception e) {
						Log.w("Error calling ", " "+e.getMessage());
						Toast.makeText(getApplicationContext(), "Problem calling number.", Toast.LENGTH_SHORT).show();
					} 
				}
		});	
	}

			
}


