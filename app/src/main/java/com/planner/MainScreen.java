package com.planner;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainScreen extends Activity implements OnClickListener{
	Button btnDtc , btnMetro ,btnUtilities, btnmaps;
	ImageView exitImg;
	TextView contactDeveloper;
	
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreenlayout);
        System.out.println("2 ..... Control at Mainscreen First Line " );
        btnMetro = (Button)findViewById(R.id.buttonMetro);
        btnMetro.setOnClickListener(this);
    	btnDtc = (Button)findViewById(R.id.buttonDtc);
    	btnDtc.setOnClickListener(this);
    	
        exitImg = (ImageView)findViewById(R.id.Mainexitimageview);
        exitImg.setOnClickListener(this);
              
  }

	public void onClick(View itemclicked) {
		try{	
			switch(itemclicked.getId()){
			case R.id.buttonDtc:
				Intent godtcscreen = new Intent(getApplicationContext(), DtcServiceScreen.class);
				startActivity(godtcscreen);
				break;
			case R.id.buttonMetro:
				Intent gometroservices = new Intent(getApplicationContext(), MetroServiceScreen.class);
				startActivity(gometroservices);
				break;
			
			case R.id.Mainexitimageview:
				Toast.makeText(getApplicationContext(), "App Will Exit.. Thanks for using", Toast.LENGTH_SHORT).show();
				finish();
				break;
			}
	}catch(Exception ae){
		Toast.makeText(getApplicationContext(), "Error :"+ae.getMessage(), Toast.LENGTH_SHORT).show();
		Intent gomain = new Intent(getApplicationContext(),SplashActivity.class);
		finish();
		startActivity(gomain);
		}
	}
	
	/*public void myAnimationText(){
	      
    	 TranslateAnimation sc=new TranslateAnimation( 0, 0, 50, 0) ;
    	 sc.setInterpolator(new AccelerateDecelerateInterpolator());
		 sc.setDuration(1000);
		 exitImg.startAnimation(sc);
		
	
    }
	

	public void DTCAnimation(){
		TranslateAnimation sc=new TranslateAnimation( 0, 0, -100, 0) ;
		sc.setInterpolator(new AccelerateDecelerateInterpolator());
		sc.setDuration(1500);
		btnDtc.startAnimation(sc);
		
	}
	
	public void MetroAnimation(){
		TranslateAnimation sc=new TranslateAnimation( 0, 0, -100, 0) ;
		sc.setInterpolator(new AccelerateDecelerateInterpolator());
		sc.setDuration(1500);
		btnMetro.startAnimation(sc);
	}
	
	public void TaxiAnimation(){
		TranslateAnimation sc=new TranslateAnimation( 0, 0, -100, 0) ;
		sc.setInterpolator(new AccelerateDecelerateInterpolator());
		sc.setDuration(1500);
		btnUtilities.startAnimation(sc);
	}
	public void LocationAnimation(){
		TranslateAnimation sc=new TranslateAnimation( 0, 0, -100, 0) ;
		sc.setInterpolator(new AccelerateDecelerateInterpolator());
		sc.setDuration(1500);
		btnmaps.startAnimation(sc);
	}
	*/
}
