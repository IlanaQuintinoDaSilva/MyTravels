package br.com.iq.mytravels.domain.api

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log


import br.com.iq.mytravels.MyTravelsApplication
import br.com.iq.mytravels.data.DatabaseHelper
import br.com.iq.mytravels.data.MyTravelsContract
import br.com.iq.mytravels.domain.City
import br.com.iq.mytravels.domain.Country


class CityService : ContentProvider(){

    private var helper: DatabaseHelper? = null
    private var country: String = ""

    override fun onCreate(): Boolean {
        helper = DatabaseHelper(context)
        return true
    }

    override fun insert(uri: Uri?, values: ContentValues?): Uri? {
        return null
    }

    override fun query(uri: Uri?, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(uri: Uri?, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(uri: Uri?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun addCity(city: String, country: String, travel: String, helper: DatabaseHelper){
        var db = helper.writableDatabase
        var values = ContentValues().apply {
            put(MyTravelsContract.CitiesEntry.COLUMN_NAME, city)
            put(MyTravelsContract.CitiesEntry.COLUMN_COUNTRY, country)
            put(MyTravelsContract.CitiesEntry.COLUMN_TRAVEL, travel)
            put(MyTravelsContract.CitiesEntry.COLUMN_DONE, 0)

        }

        val newRowId = db?.insert(MyTravelsContract.CitiesEntry.TABLE_NAME, null, values)

        db?.close()

    }

    fun getCities(helper: DatabaseHelper, isDone: String):List<City> {
        val cities = mutableListOf<City>()
        val db = helper.readableDatabase
        val projection = arrayOf(MyTravelsContract.CitiesEntry.COLUMN_NAME, MyTravelsContract.CitiesEntry.COLUMN_COUNTRY,
            MyTravelsContract.CitiesEntry.COLUMN_TRAVEL, MyTravelsContract.CitiesEntry.COLUMN_DONE)
        val selection = MyTravelsContract.CitiesEntry.COLUMN_DONE + " = ?"
        val selectionArgs = arrayOf(isDone)
        val c = db.query(MyTravelsContract.CitiesEntry.TABLE_NAME,
            projection, null, null, null, null, null)
        val i = c.count
        Log.d("Cities Count", i.toString())
        while (c.moveToNext()) {
            var ct = City()
            ct.name = c.getString(0)
            ct.country = c.getString(1)
            ct.travel = c.getString(2)
            ct.done = c.getInt(3)
            cities.add(ct)
        }
        c.close()
        return cities
    }

    fun updateDone(id: Long) {
        helper = MyTravelsApplication.dbHelper
        var db = helper?.writableDatabase
        var values = ContentValues().apply {
            put(MyTravelsContract.CitiesEntry.COLUMN_DONE, 1)
        }
        val selectionArgs = arrayOf(id.toString())
        var numRows = db?.update(MyTravelsContract.CitiesEntry.TABLE_NAME,values,
            MyTravelsContract.CitiesEntry._ID + "=?", selectionArgs)
    }

}