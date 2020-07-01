package br.com.iq.mytravels.activity.ui.country

import android.content.Intent

import android.os.Bundle
import br.com.iq.mytravels.R
import br.com.iq.mytravels.activity.BaseActivity
import br.com.iq.mytravels.activity.MainActivity
import br.com.iq.mytravels.activity.NavMainActivity
import br.com.iq.mytravels.data.DatabaseHelper
import br.com.iq.mytravels.domain.api.CountryService
import kotlinx.android.synthetic.main.activity_add_country.*

class AddCountryActivity : BaseActivity() {
    private var country: String = ""
    private var countryService = CountryService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_country)
        btAddCountry.setOnClickListener{
            country = etCountry.text.toString()
            addCountry(country)
            val intent = Intent(context, NavMainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addCountry(country: String){
        val helper = DatabaseHelper(this)
        countryService.addCountry(country, helper)
    }
}
