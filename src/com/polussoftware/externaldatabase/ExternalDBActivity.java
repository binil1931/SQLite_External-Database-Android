package com.polussoftware.externaldatabase;

import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class ExternalDBActivity extends Activity {  
	 /** Called when the activity is first created. */  
	 DBHelper dbhelper;  
	 TextView name;
	 @Override  
	 public void onCreate(Bundle savedInstanceState) {  
	  super.onCreate(savedInstanceState);  
	  setContentView(R.layout.activity_main);  
	    
	  name = (TextView) findViewById(R.id.name);
	  
	  dbhelper = new DBHelper(this);  
	  try {  
	   dbhelper.createDataBase();  
	  } catch (IOException e) {  
	   // TODO Auto-generated catch block  
	   e.printStackTrace();  
	  }  
	  
	  Cursor cur = dbhelper.getData();  
	    
	  int noOfQuestion = cur.getCount();
	  Log.e("Tag", "count " + noOfQuestion);
		if (cur.moveToFirst())
		{
			do
			{
				name.append(cur.getString(3) + " ");
			}while (cur.moveToNext());
			
		}
	 }  
	}  