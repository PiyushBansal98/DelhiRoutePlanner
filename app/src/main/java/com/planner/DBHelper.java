package com.planner;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	private static String DB_NAME = "metroFare";
	private static String DB_PATH = "/data/data/com.planner/databases/";
	private SQLiteDatabase dbTcr;
	private final Context myContext;
	private static final int DATABASE_VERSION = 1;
	
	// default Constructer to call SQLiteOpenHelper
	
	public DBHelper(Context context){
		super(context, DB_NAME, null, DATABASE_VERSION);
		this.myContext = context;
	}
	public boolean checkDatabase(){
		SQLiteDatabase checkDB = null;
		try{
			String mypath = DB_PATH +DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
			Log.e("Test 1", "Table Opened ");
		}catch(Exception ae){
			Log.e("Test 1", "Error : Unable to open database"+"."+ae.toString());
		}
		if(checkDB!=null){
			checkDB.close();
		}
		return checkDB !=null ? true:false;
	}
	
	public void createDatabase(){
		boolean dbExist = checkDatabase();
		if(dbExist)	{
			Log.e("test","createDatabase[] - db exists");
			this.getWritableDatabase();
		}else{
			Log.e("test","createDatabase[] - db not exists");
			this.getReadableDatabase();
	        try {
	        	copyDatabase();
	        	Log.e("test","createDatabase[] - copied db");
	        }catch (IOException e){
	        	Log.e("test","createDatabase[] - copied not db");
	        }
	        SQLiteDatabase checkDB = null;
	        String myPath = DB_PATH + DB_NAME;
	        checkDB = SQLiteDatabase.openDatabase(myPath, null,SQLiteDatabase.OPEN_READWRITE);
	        
	       
		}
	}
	
	public void copyDatabase() throws IOException, SQLiteException {
		Log.e("tofu","copy database");
		InputStream myInput = myContext.getAssets().open(DB_NAME);

	    String outFileName = DB_PATH + DB_NAME;
	    OutputStream myOutput = new FileOutputStream(outFileName);

	    //transfer bytes from the inputfile to the outputfile
	    byte[] buffer = new byte[1024];
	    int length;
	    while ((length = myInput.read(buffer))>0){
	    	myOutput.write(buffer, 0, length);
        }
	    myOutput.flush();
	    myOutput.close();
	    myInput.close();
	}

	public void openDataBase() throws SQLiteException, IOException{
		this.createDatabase();
		try { 
		    String myPath = DB_PATH + DB_NAME;
		    dbTcr = SQLiteDatabase.openDatabase(myPath, null,SQLiteDatabase.OPEN_READONLY);
		}catch(SQLException sqle){
			throw sqle;
		}
    }

@Override
public void onCreate(SQLiteDatabase db) {
	
	
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
	Log.e("dbUpgrade","OLD Version = "+oldVersion+"");
	Log.e("dbUpgrade","New Version = "+newVersion+"");
	myContext.deleteDatabase(DB_NAME);
	Log.e("dbUpgrade","deleted"+DB_NAME);
}

public Cursor displayFare(String str2, String str1)	{
	String str="select " +str2+" from metro where stations='"+str1+"'";
	return dbTcr.rawQuery(str, null);
}
public Cursor getStation()	{
return this.dbTcr.query("metro",new String[]{"stations"},null, null, null, null,null);	
}	
}


