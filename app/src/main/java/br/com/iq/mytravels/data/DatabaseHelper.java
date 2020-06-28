package br.com.iq.mytravels.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "mytravels.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_COUNTRY_CREATE=
            "CREATE TABLE " + MyTravelsContract.CountriesEntry.TABLE_NAME + " (" +
                    MyTravelsContract.CountriesEntry._ID + " INTEGER PRIMARY KEY, " +
                    MyTravelsContract.CountriesEntry.COLUMN_NAME + " TEXT " +
                    ")";
    private static final String TABLE_CITY_CREATE =
            "CREATE TABLE " + MyTravelsContract.CitiesEntry.TABLE_NAME + " (" +
                    MyTravelsContract.CitiesEntry._ID + " INTEGER PRIMARY KEY, " +
                    MyTravelsContract.CitiesEntry.COLUMN_NAME + " TEXT, " +
                    MyTravelsContract.CitiesEntry.COLUMN_COUNTRY + " TEXT , " +
                    MyTravelsContract.CitiesEntry.COLUMN_TRAVEL + " TEXT, " +
                    MyTravelsContract.CitiesEntry.COLUMN_DONE + " INTEGER default 0 " + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_COUNTRY_CREATE);
        db.execSQL(TABLE_CITY_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MyTravelsContract.CitiesEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MyTravelsContract.CountriesEntry.TABLE_NAME);
        onCreate(db);
    }
}
