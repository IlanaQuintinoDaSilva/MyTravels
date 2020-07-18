package br.com.iq.mytravels.activity.ui.country

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.iq.mytravels.MyTravelsApplication
import br.com.iq.mytravels.R
import br.com.iq.mytravels.adapter.CountryAdapter
import br.com.iq.mytravels.data.DatabaseHelper
import br.com.iq.mytravels.domain.Country
import br.com.iq.mytravels.domain.api.CountryService
import br.com.iq.mytravels.fragment.BaseFragment

import java.util.*


class CountryListFragment : BaseFragment() {

    private var countries: List<Country> = ArrayList()
    var rvCountry: RecyclerView? = null
    private var countryService = CountryService()
    private lateinit var helper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        helper = DatabaseHelper(activity)
        getCountries()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_country_list, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvCountry = view?.findViewById(R.id.rvCountry)
        rvCountry?.layoutManager = LinearLayoutManager(activity)
        rvCountry?.itemAnimator = DefaultItemAnimator()
        rvCountry?.setHasFixedSize(true)
    }

    override fun onResume(){
        super.onResume()
        setupAdapter(countries)
        MyTravelsApplication.pageSelection = 0
    }

    private fun setupAdapter(list: List<Country>){
        rvCountry?.adapter = CountryAdapter(countries) { onClickItem(it) }
    }

    private fun onClickItem(country: Country) {

    }


    private fun getCountries(){
        MyTravelsApplication.dbHelper = helper
        MyTravelsApplication.countries = countryService.getCountries(helper)
        countries = MyTravelsApplication.countries
    }


}
