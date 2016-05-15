package com.example.osmas.walkesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by osmas on 06/01/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BookingsDB";

    //Static constants to use in CRUD operations

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
         Log.e("Database Operations","Success Database created or opened ...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // SQL statement to create book table
        String CREATE_BOOKINGS_TABLE = "CREATE TABLE " + Booking.NewBooking.TABLE_NAME +"(" +
                Booking.NewBooking.KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
               Booking.NewBooking.KEY_DATE + " TEXT,"+ Booking.NewBooking.KEY_START_TIME+" TEXT,"+
                Booking.NewBooking.KEY_APPOINTMENT_TYPE+" TEXT,"+Booking.NewBooking.KEY_NAME+ " TEXT," +
                Booking.NewBooking.KEY_ADDRESS+" TEXT );";

        // create books table
        db.execSQL(CREATE_BOOKINGS_TABLE);

        Log.e("Database Operations", "Success table created  ...");

    }

    public boolean addBookingData(String date, String start_time, String appointment_type, String name, String address, SQLiteDatabase db ){

        ContentValues values = new ContentValues();
        values.put(Booking.NewBooking.KEY_DATE,date); // get date
        values.put(Booking.NewBooking.KEY_START_TIME,start_time); // get time
        values.put(Booking.NewBooking.KEY_APPOINTMENT_TYPE,appointment_type); // get type
        values.put(Booking.NewBooking.KEY_NAME,name); // get name
        values.put(Booking.NewBooking.KEY_ADDRESS,address); // get address
        // 3. insert
        long result = db.insert(Booking.NewBooking.TABLE_NAME, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
        db.close();// 4. close
        Log.e("Database Operations", "One row of data added  ...");

        if(result == -1)
            return false;
        else
            return true;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS bookings_table");

        // create fresh books table
        this.onCreate(db);
    }

    //CRUD operations on the Data

    public Cursor getBookings(SQLiteDatabase db){

        Cursor cursor;
        String[] columns = {Booking.NewBooking.KEY_DATE,Booking.NewBooking.KEY_START_TIME,
                Booking.NewBooking.KEY_APPOINTMENT_TYPE,Booking.NewBooking.KEY_NAME,
                Booking.NewBooking.KEY_ADDRESS};

        cursor = db.query(Booking.NewBooking.TABLE_NAME,columns,null,null,null,null,null);
        return cursor;
    }

    public Cursor getSearch(String search_term, SQLiteDatabase db)
    {

        String[] columns = {Booking.NewBooking.KEY_DATE,Booking.NewBooking.KEY_START_TIME,
                Booking.NewBooking.KEY_APPOINTMENT_TYPE,Booking.NewBooking.KEY_NAME,
                Booking.NewBooking.KEY_ADDRESS};

        String selection = Booking.NewBooking.KEY_NAME+" LIKE ?";

        String[] selection_args = {search_term};

        Cursor cursor = db.query(Booking.NewBooking.TABLE_NAME,columns,selection,selection_args,null,null,null);

        return cursor;
    }



}
