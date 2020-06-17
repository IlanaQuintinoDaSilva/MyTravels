package br.com.iq.mytravels.data;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import static br.com.iq.mytravels.data.MyTravelsContract.CONTENT_AUTHORITY;
import static br.com.iq.mytravels.data.MyTravelsContract.PATH_CITY;
import static br.com.iq.mytravels.data.MyTravelsContract.PATH_COUNTRY;

public class MyTravelsProvider extends ContentProvider{
    //constants for the operation
    private static final int CITY = 1;
    private static final int CITY_ID = 2;
    private static final int COUNTRY = 3;
    private static final int COUNTRY_ID = 4;

    //urimatcher
    private static final UriMatcher uriMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_COUNTRY, COUNTRY);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_COUNTRY + "/#", COUNTRY_ID);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CITY, CITY);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CITY + "/#", CITY_ID);
    }

    private DatabaseHelper helper;
    @Override
    public boolean onCreate() {
        helper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String orderBy) {
        //get db
        SQLiteDatabase db = helper.getReadableDatabase();
        //cursor
        Cursor cursor;
        //our integer
        int match = uriMatcher.match(uri);
        //intables
        String inTables = MyTravelsContract.CitiesEntry.TABLE_NAME
                + " inner join "
                + MyTravelsContract.CountriesEntry.TABLE_NAME
                + " on " + MyTravelsContract.CitiesEntry.COLUMN_COUNTRY + " = "
                + MyTravelsContract.CountriesEntry.TABLE_NAME + "." + MyTravelsContract.CountriesEntry._ID;
        SQLiteQueryBuilder builder;
        switch (match) {
            case CITY:
                builder = new SQLiteQueryBuilder();
                builder.setTables(inTables);
                cursor = builder.query(db, projection, selection, selectionArgs,
                        null, null, orderBy);
                break;
            case CITY_ID:
                builder = new SQLiteQueryBuilder();
                builder.setTables(inTables);
                selection = MyTravelsContract.CitiesEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = builder.query(db, projection, selection, selectionArgs, null, null, orderBy);
                break;
            case COUNTRY:
                cursor = db.query(MyTravelsContract.CountriesEntry.TABLE_NAME,
                        projection, null, null, null, null, orderBy);
                break;
            case COUNTRY_ID:
                selection = MyTravelsContract.CountriesEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(MyTravelsContract.CountriesEntry.TABLE_NAME,
                        projection, selection, selectionArgs, null, null, orderBy);
                break;
            default:
                throw new IllegalArgumentException("Query unknown URI: " + uri);

        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case CITY:
                return insertRecord(uri, contentValues, MyTravelsContract.CitiesEntry.TABLE_NAME);
            case COUNTRY:
                return insertRecord(uri, contentValues, MyTravelsContract.CountriesEntry.TABLE_NAME);
            default:
                throw new IllegalArgumentException("Insert unknown URI: " + uri);
        }
    }

    private Uri insertRecord(Uri uri, ContentValues values, String table) {
        //this time we need a writable database
        SQLiteDatabase db = helper.getWritableDatabase();
        long id = db.insert(table, null, values);
        if (id == -1) {
            Log.e("Error", "insert error for URI " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case CITY:
                return deleteRecord(uri, null, null, MyTravelsContract.CitiesEntry.TABLE_NAME);
            case CITY_ID:
                return deleteRecord(uri, selection, selectionArgs, MyTravelsContract.CitiesEntry.TABLE_NAME);
            case COUNTRY:
                return deleteRecord(uri, null, null, MyTravelsContract.CountriesEntry.TABLE_NAME);
            case COUNTRY_ID:
                long id = ContentUris.parseId(uri);
                selection = MyTravelsContract.CountriesEntry._ID + "=?";
                String[] sel = new String[1];
                sel[0] = String.valueOf(id);
                return deleteRecord(uri, selection, sel,
                        MyTravelsContract.CountriesEntry.TABLE_NAME);

            default:
                throw new IllegalArgumentException("Insert unknown URI: " + uri);
        }
    }

    private int deleteRecord(Uri uri, String selection, String[] selectionArgs, String tableName) {
        //this time we need a writable database
        SQLiteDatabase db = helper.getWritableDatabase();
        int id = db.delete(tableName, selection, selectionArgs);
        if (id == -1) {
            Log.e("Error", "delete unknown URI " + uri);
            return -1;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return id;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case CITY:
                return updateRecord(uri, values, selection, selectionArgs, MyTravelsContract.CitiesEntry.TABLE_NAME);
            case COUNTRY:
                return updateRecord(uri, values, selection, selectionArgs, MyTravelsContract.CountriesEntry.TABLE_NAME);
            default:
                throw new IllegalArgumentException("Update unknown URI: " + uri);
        }
    }

    private int updateRecord(Uri uri, ContentValues values, String selection, String[] selectionArgs, String tableName) {
        //this time we need a writable database
        SQLiteDatabase db = helper.getWritableDatabase();
        int id = db.update(tableName, values, selection, selectionArgs);
        if (id == 0) {
            Log.e("Error", "update error for URI " + uri);
            return -1;
        }
        return id;
    }
}

