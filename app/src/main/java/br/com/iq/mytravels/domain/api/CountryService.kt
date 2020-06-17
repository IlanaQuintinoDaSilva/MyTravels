package br.com.iq.mytravels.domain.api

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log


import br.com.iq.mytravels.MyTravelsApplication
import br.com.iq.mytravels.data.DatabaseHelper
import br.com.iq.mytravels.data.MyTravelsContract
import br.com.iq.mytravels.domain.Country


class CountryService : ContentProvider(){

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

    fun addCountry(country: String, helper: DatabaseHelper){
        var db = helper.writableDatabase
        var values = ContentValues().apply {
            put(MyTravelsContract.CountriesEntry.COLUMN_NAME, country)
        }

        val newRowId = db?.insert(MyTravelsContract.CountriesEntry.TABLE_NAME, null, values)

        db?.close()

    }

    fun getCountries(helper: DatabaseHelper):List<Country> {
        val countries = mutableListOf<Country>()
        //val helper = DatabaseHelper(context)
        val db = helper.readableDatabase
        val projection = arrayOf(MyTravelsContract.CountriesEntry.COLUMN_NAME)
        val selection = MyTravelsContract.CitiesEntry.COLUMN_COUNTRY + " = ?"
        val selectionArgs = arrayOf("1")
        val c = db.query(MyTravelsContract.CountriesEntry.TABLE_NAME,
            projection, null, null, null, null, null)
        val i = c.count
        Log.d("Countries Count", i.toString())
        var rowContent = ""
        while (c.moveToNext()) {
            var ct = Country()
            ct.name = c.getString(0)
            countries.add(ct)
        }
        c.close()
        return countries
    }

}