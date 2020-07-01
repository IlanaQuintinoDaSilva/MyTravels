package br.com.iq.mytravels.activity.ui.city

import android.content.Intent

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import br.com.iq.mytravels.MyTravelsApplication
import br.com.iq.mytravels.R
import br.com.iq.mytravels.activity.BaseActivity
import br.com.iq.mytravels.activity.MainActivity
import br.com.iq.mytravels.activity.NavMainActivity
import br.com.iq.mytravels.data.DatabaseHelper
import br.com.iq.mytravels.domain.Country
import br.com.iq.mytravels.domain.api.CityService
import kotlinx.android.synthetic.main.activity_add_city.*

class AddCityActivity : BaseActivity() {
    private var country: String = ""
    private var countries: List<Country> = ArrayList()
    private var city: String = ""
    private var cityService = CityService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_city)

        val spinner: Spinner = findViewById(R.id.planets_spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                country = parent.getItemAtPosition(pos).toString()


            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }


        countries = MyTravelsApplication.countries
        var arraySize =  countries.count()


        var myStrings = arrayOfNulls<String>(arraySize)
        countries = MyTravelsApplication.countries


        var i = 0
        for (country in MyTravelsApplication.countries){
            myStrings[i] = country.name
            i++
        }

        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, myStrings)


        btAddCity.setOnClickListener{
            city = etCity.text.toString()
            addCity(city, country,"")
            val intent = Intent(context, NavMainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addCity(city: String,country: String, travel: String){
        val helper = DatabaseHelper(this)
        cityService.addCity(city, country, travel, helper)
    }
}
