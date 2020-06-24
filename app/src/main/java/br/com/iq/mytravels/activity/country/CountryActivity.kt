package br.com.iq.mytravels.activity.country

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.iq.mytravels.MyTravelsApplication
import br.com.iq.mytravels.R
import br.com.iq.mytravels.data.DatabaseHelper
import br.com.iq.mytravels.domain.api.CountryService
import br.com.iq.mytravels.extensions.addFragment
import br.com.iq.mytravels.fragment.CountryListFragment

class CountryActivity : AppCompatActivity() {

    private var service = CountryService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        feedInitialData()
        addFragment(R.id.container, CountryListFragment())
    }

    private fun feedInitialData(){
        val helper = DatabaseHelper(this)
        MyTravelsApplication.dbHelper = helper
        MyTravelsApplication.countries = service.getCountries(helper)

    }
}
