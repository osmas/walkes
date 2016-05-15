package com.example.osmas.walkesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imageButtonAddBooking  = null;
    ImageButton imageButtonListBooking  = null;
    ImageButton imageButtonSearch = null;
    ImageButton imageButtonPoochPic  = null;
    SharedPreferences mSharedPreferences = null;
    TextView textViewGreeting = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textViewGreeting = (TextView) findViewById(R.id.textViewWelcome);

        imageButtonAddBooking = (ImageButton)findViewById(R.id.imageButtonAddBooking);
        imageButtonAddBooking.setOnClickListener(this);

        imageButtonListBooking = (ImageButton)findViewById(R.id.imageButtonListBooking);
        imageButtonListBooking.setOnClickListener(this);

        imageButtonSearch = (ImageButton)findViewById(R.id.imageButtonSearch);
        imageButtonSearch.setOnClickListener(this);

        imageButtonPoochPic = (ImageButton)findViewById(R.id.imageButtonPoochPic);
        imageButtonPoochPic.setOnClickListener(this);

        // Access default preferences location
        mSharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String defaultUserName = getResources().getString(R.string.user_name_default);
        String userName;
        String key = getString(R.string.key_user_name);
        userName = mSharedPreferences.getString(key, defaultUserName);
        textViewGreeting.setText("Welcome, " + userName);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Snackbar.make(view, "Replace Mr O with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent myIntent = new Intent(view.getContext(), AddBookingActivity.class);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v){
        int id = v.getId();

        if(id==R.id.imageButtonAddBooking){
            Intent myIntent = new Intent(v.getContext(), AddBookingActivity.class);
            startActivity(myIntent);
        }
        if(id==R.id.imageButtonListBooking){
            Intent myIntent = new Intent(v.getContext(), ListBookingActivity.class);
            startActivity(myIntent);

        }
        if(id==R.id.imageButtonSearch){
            Intent myIntent = new Intent(v.getContext(), SearchActivity.class);
            startActivity(myIntent);
        }
        if(id==R.id.imageButtonPoochPic){
            takePicture();
        }
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void takePicture(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


}
