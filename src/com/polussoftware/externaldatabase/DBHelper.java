package com.polussoftware.externaldatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {  
	private static final int DATABASE_VERSION = 4; 
	 private static String DB_NAME = "testsql";  
	 private static final String TABLE_NAME = "table_name1"; 
	 private SQLiteDatabase db;  
	 private final Context context;  
	 private String DB_PATH;  
	  
	 public DBHelper(Context context) {  
	  super(context, DB_NAME, null, DATABASE_VERSION);  
	  this.context = context;  
	  DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";  
	 }  
	  
	 public void createDataBase() throws IOException {  
	  
	  boolean dbExist = checkDataBase();  
	  if (dbExist) {  
	  
  } else {  
	   this.getReadableDatabase();  
	   try {  
	    copyDataBase();  
	   } catch (IOException e) {  
	    throw new Error("Error copying database");  
	   }  
	  }  
	 }  
	  
	 private boolean checkDataBase() {  
	  File dbFile = new File(DB_PATH + DB_NAME);  
	  return dbFile.exists();  
	 }  
	  
	 private void copyDataBase() throws IOException {  
	  
	  InputStream myInput = context.getAssets().open(DB_NAME);  
	  String outFileName = DB_PATH + DB_NAME;  
	  OutputStream myOutput = new FileOutputStream(outFileName);  
	  byte[] buffer = new byte[1024];  
	  int length;  
	  while ((length = myInput.read(buffer)) > 0) {  
	   myOutput.write(buffer, 0, length);  
	  }  
	  
	  // Close the streams  
	  myOutput.flush();  
	  myOutput.close();  
	  myInput.close();  
	  
	 }  
	  
	 public Cursor getData() {  
	  String myPath = DB_PATH + DB_NAME;  
	  db = SQLiteDatabase.openDatabase(myPath, null,  
	    SQLiteDatabase.OPEN_READONLY);  
	  Cursor c = db.rawQuery("SELECT * FROM table_name1", null);  
	   // Note: Master is the one table in External db. Here we trying to access the records of table from external db.  
	  return c;  
	 }  
	  
	 @Override  
	 public void onCreate(SQLiteDatabase arg0) {  
	  // TODO Auto-generated method stub  
	 }  
	  
	 @Override  
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
	  // TODO Auto-generated method stub  
		 
		 db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);  
		   
	        // Create tables again  
	        onCreate(db);  
	 }  
	}  
