package com.planner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashActivity extends Activity {
    ImageView animation;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        animation = (ImageView) findViewById(R.id.AnimInageview);
        Toast.makeText(getApplicationContext(), "Loading Please Wait ..", Toast.LENGTH_LONG).show();
        myAnimation();
        Thread splashThread = new Thread(){
          public void run(){
        		try{
        			sleep(3500);
        			
        			
        		}catch(Exception ae){
        			Toast.makeText(getApplicationContext(), "Error in Loading ..", Toast.LENGTH_SHORT).show();
        		}finally{
        			finish();
        			Intent goMain = new Intent(getApplicationContext(),MainScreen.class);
        			startActivity(goMain);
        			System.out.println("1...Control at Splash SCreen last Line " );
        		}
        	}
        };
        splashThread.start();
    }
    public void myAnimation(){
		      
    	TranslateAnimation sc=new TranslateAnimation( 0, 0, -200, 0) ;
		// sc.setInterpolator(new AccelerateDecelerateInterpolator());
		 //sc1.setInterpolator(new AccelerateDecelerateInterpolator());
		 sc.setDuration(3000);
		// sc1.setDuration(6000);
		 animation.startAnimation(sc);
		 //animation.startAnimation(sc1);
	
    }
}