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

public class DBHelperMetro extends SQLiteOpenHelper{
	
	private static String DB_PATH ="/data/data/com.planner/databases/";
	private static String DB_NAME ="MetroDB";
	private SQLiteDatabase dbTcr;
	private final Context myContext;
	private static final int DATABASE_VERSION = 1;
	
	public DBHelperMetro(Context context){
		super(context, DB_NAME, null, DATABASE_VERSION);
		this.myContext = context;
	}
	public void createDataBase(){
		boolean dbExist = checkDataBase();
		if(dbExist)	{
			Log.e("test","createDatabase[] - db exists");
			this.getWritableDatabase();
		}else{
			Log.e("test","createDatabase[] - db not exists");
			this.getReadableDatabase();
	        try {
	        	copyDataBase();
	        	Log.e("test","createDatabase[] - copied db");
	        }catch (IOException e){
	        	Log.e("test","createDatabase[] - copied not db");
	        }
	        SQLiteDatabase checkDB = null;
	        String myPath = DB_PATH + DB_NAME;
	        checkDB = SQLiteDatabase.openDatabase(myPath, null,SQLiteDatabase.OPEN_READWRITE);
	              
		}
	}
	
private boolean checkDataBase()	{
		SQLiteDatabase checkDB = null;
		try{
	    	String myPath = DB_PATH + DB_NAME;
	        checkDB = SQLiteDatabase.openDatabase(myPath, null,SQLiteDatabase.OPEN_READWRITE);
	        Log.e("test","opened table");
	    }catch(SQLiteException e){
	    	Log.e("test","didn't open table - not exists");
		}
		if(checkDB != null){
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}
	
private void copyDataBase() throws IOException,SQLiteException{
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
		this.createDataBase();
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

public Cursor getLine()
{	
	String str="select * from MetroLineDetail";
	return dbTcr.rawQuery(str, null);
}

public Cursor getLineDetail(String linecolor)
{
	String str = "select * from MetroLineDetail where Line_color = '"+linecolor+"'";
	return dbTcr.rawQuery(str, null);
}
public  Cursor getStations()
{
	String str="select * from MetroStationDetail";
	return dbTcr.rawQuery(str, null);
}

public Cursor getStationDetail(String stationname)
{
	String str = "select * from MetroStationDetail where Station_name = '"+stationname+"'";
	return dbTcr.rawQuery(str, null);
}

public Cursor getSourceLineDetail(String source)
{
	String str = "select * from MetroStationDetail where Station_name = '"+source+"'";
	return dbTcr.rawQuery(str, null);
}

public Cursor getDestinationLineDetail(String destination)
{
	String str = "select * from MetroStationDetail where Station_name = '"+destination+"'";
	return dbTcr.rawQuery(str, null);
}
}
