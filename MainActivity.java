package com.example.keegan.project;

import java.util.List;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ListActivity;
import android.os.Bundle;
 import android.view.View;
 import android.widget.ListView;
 import android.widget.Toast;


public class MainActivity extends ListActivity {  

    @Override 
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        // Get running processes 
        ActivityManager manager = (ActivityManager)
                getSystemService(ACTIVITY_SERVICE); 
        List<RunningAppProcessInfo> runningProcesses = manager.getRunningAppProcesses(); 
        if (runningProcesses != null && runningProcesses.size() > 0)
        { // Set data to the list adapter 
             setListAdapter(new ListAdapter(this, runningProcesses)); 
        } else {
         
            //In case there are no processes running (not a chance :)) 
            Toast.makeText(getApplicationContext(), "Nothing is running!",
                    Toast.LENGTH_LONG).show(); 
        } 
}  
    @Override protected void onListItemClick(ListView l, View v, int position, long id)
    { 
        int pid = ((RunningAppProcessInfo)getListAdapter().getItem(position)).pid; 
        android.os.Process.killProcess(pid); 
        Toast.makeText(getApplicationContext(), "Critical process\nCannot kill\n",
                Toast.LENGTH_LONG).show(); 
    }
    @Override     public void onBackPressed() { 
        android.os.Process.killProcess(android.os.Process.myPid()); 
    }
     }