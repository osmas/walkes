package com.example.osmas.walkesapp;

/**
 * Created by osmas on 06/01/2016.
 */
public class Booking {

    public static abstract class NewBooking{

        //Static constants to use in CRUD operations
        // Bookings table name
        public static final String TABLE_NAME = "bookings_table";

        // Books Table Columns names
        public static final String KEY_ID = "id";
        public static final String KEY_DATE = "date";
        public static final String KEY_START_TIME = "start_time";
        public static final String KEY_APPOINTMENT_TYPE = "appointment_type";
        public static final String KEY_NAME = "name";
        public static final String KEY_ADDRESS = "address";
    }
}
