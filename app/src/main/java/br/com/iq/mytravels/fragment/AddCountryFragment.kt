package br.com.iq.mytravels.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.iq.mytravels.R
import br.com.iq.mytravels.activity.MainActivity
import br.com.iq.mytravels.data.DatabaseHelper
import br.com.iq.mytravels.domain.api.CountryService
import kotlinx.android.synthetic.main.activity_add_country.*
import kotlinx.android.synthetic.main.fragment_add_country.*


class AddCountryFragment : BaseFragment() {
    private var country: String = ""
    private var countryService = CountryService()
    private lateinit var helper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        helper = DatabaseHelper(activity)
        btAddCountry2.setOnClickListener{
            country = etCountry2.text.toString()
            addCountry(country)
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_country, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume(){
        super.onResume()
    }


    private fun addCountry(country: String){
        countryService.addCountry(country, helper)
    }
}