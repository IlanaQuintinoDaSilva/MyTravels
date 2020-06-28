package br.com.iq.mytravels.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class MyTravelsContract {
    //URI section
    public static final String CONTENT_AUTHORITY = "br.com.iq.mytravels.mytravelsprovider";
    public static final String PATH_COUNTRY="countries";
    public static final String PATH_STATE="states";
    public static final String PATH_CITY="cities";
    public static final String PATH_PLACE="places";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public String concatContent(String path){
        return "content://" + path;
    }

    public static final class CountriesEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_COUNTRY);

        // Table name
        public static final String TABLE_NAME = "country";
        //column names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
    }


    public static final class CitiesEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CITY);

        // Table name
        public static final String TABLE_NAME = "city";
        //column (field) names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_COUNTRY = "country";
        public static final String COLUMN_TRAVEL = "travel";
        public static final String COLUMN_DONE = "done";

    }

}

