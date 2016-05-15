package com.example.osmas.walkesapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class ListBookingActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase mySQLiteDatabase;
    MySQLiteHelper mySQLiteHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.row_layout);

        listView = (ListView)findViewById(R.id.listViewBookings);
        mySQLiteHelper = new MySQLiteHelper(getApplicationContext());
        mySQLiteDatabase = mySQLiteHelper.getReadableDatabase();
        cursor = mySQLiteHelper.getBookings(mySQLiteDatabase);
        listView.setAdapter(listDataAdapter);

        if(cursor.moveToFirst())
        {
            do {
                    String date, start_time, appointment_type, name, address;
                    date = cursor.getString(0);
                    start_time = cursor.getString(1);
                    appointment_type = cursor.getString(2);
                    name = cursor.getString(3);
                    address = cursor.getString(4);

                DataProvider dataProvider = new DataProvider(date,start_time,appointment_type,name,address);
                listDataAdapter.add(dataProvider);
            }while(cursor.moveToNext());
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}


