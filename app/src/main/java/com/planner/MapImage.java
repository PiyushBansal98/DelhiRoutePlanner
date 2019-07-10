package com.planner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;

public class MapImage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.mapimage);
		
		
		WebView full=(WebView)findViewById(R.id.webView1);
		full.loadUrl("file:///android_asset/metromap.jpg");
		full.getSettings().setBuiltInZoomControls(true);
		
		
		
		
		
		
		
		
	
	}
	
	
	

}
