package com.example.osmas.walkesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class AddBookingActivity extends AppCompatActivity {


    EditText editDate, editStartTime, editStaffName, editAddress;
    RadioButton radioButtonDogWalk, radioButtonPetSit;
    Button btnAdd;
    Context context;
    MySQLiteHelper mySQLiteHelper;
    SQLiteDatabase mySQLiteDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        editDate = (EditText)findViewById(R.id.editTextDate);
        editStartTime = (EditText) findViewById(R.id.editTextStartTime);
        editStaffName = (EditText) findViewById(R.id.editTextStaffName);
        editAddress = (EditText)findViewById(R.id.editTextAddress);
        radioButtonDogWalk = (RadioButton)findViewById(R.id.radio_btn_dog_walk);
        radioButtonPetSit = (RadioButton)findViewById(R.id.radio_btn_pet_sit);
        btnAdd = (Button)findViewById(R.id.btnAddBooking);


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

    public void AddBooking(View view){

        String date = editDate.getText().toString();
        String start_time = editStartTime.getText().toString();
        String appointment_type = (radioButtonDogWalk.isChecked()) ? "Dog Walk" : "Pet Sit";
        String name = editStaffName.getText().toString();
        String address = editAddress.getText().toString();
        context=this;

        mySQLiteHelper = new MySQLiteHelper(context);
        mySQLiteDatabase = mySQLiteHelper.getWritableDatabase();

        boolean isInserted = mySQLiteHelper.addBookingData(date, start_time, appointment_type, name, address, mySQLiteDatabase);

        mySQLiteHelper.close();

        if (isInserted==true)
            Toast.makeText(getApplicationContext(),
                    "Data Added Successfully", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),
                    "Data Adding Error", Toast.LENGTH_LONG).show();
    }


}
