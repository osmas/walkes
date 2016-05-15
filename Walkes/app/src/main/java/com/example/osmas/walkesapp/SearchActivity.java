package com.example.osmas.walkesapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends AppCompatActivity {

    EditText editSearch;
    ListView listViewSearchResults;
    SQLiteDatabase mySQLiteDatabase;
    MySQLiteHelper mySQLiteHelper;
    Cursor cursor;
    ListDataAdapter searchDataAdapter;
    String search_term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editSearch = (EditText)findViewById(R.id.editTextSearch);

        listViewSearchResults = (ListView)findViewById(R.id.listViewSearchResults);


    }

    public void SearchBooking(View view)
    {
        search_term = editSearch.getText().toString();
        searchDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.row_layout);
        mySQLiteHelper = new MySQLiteHelper(getApplicationContext());
        mySQLiteDatabase = mySQLiteHelper.getReadableDatabase();
        cursor = mySQLiteHelper.getSearch(search_term,mySQLiteDatabase);
        listViewSearchResults.setAdapter(searchDataAdapter);

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
                searchDataAdapter.add(dataProvider);
            }while(cursor.moveToNext());
        }

    }
}
