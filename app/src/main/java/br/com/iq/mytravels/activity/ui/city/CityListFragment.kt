package br.com.iq.mytravels.activity.ui.city

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.iq.mytravels.MyTravelsApplication
import br.com.iq.mytravels.R
import br.com.iq.mytravels.adapter.CityAdapter
import br.com.iq.mytravels.data.DatabaseHelper
import br.com.iq.mytravels.domain.City
import br.com.iq.mytravels.domain.api.CityService
import br.com.iq.mytravels.fragment.BaseFragment

import java.util.*


class CityListFragment : BaseFragment() {

    private var cities: List<City> = ArrayList()
    private var cityService = CityService()
    var rvCity: RecyclerView? = null
    private lateinit var helper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        helper = DatabaseHelper(activity)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_city_list, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MyTravelsApplication.pageSelection = 1
        getCities()
        rvCity = view?.findViewById(R.id.rvCity)
        rvCity?.layoutManager = LinearLayoutManager(activity)
        rvCity?.itemAnimator = DefaultItemAnimator()
        rvCity?.setHasFixedSize(true)
    }

    override fun onResume(){
        super.onResume()
        setupAdapter(cities)
    }

    private fun setupAdapter(list: List<City>){
        rvCity?.adapter = CityAdapter(cities) { onClickItem(it) }
    }

    private fun onClickItem(city: City) {

    }


    private fun getCities(){
        MyTravelsApplication.dbHelper = helper
        MyTravelsApplication.cities = cityService.getCities(helper,"0")
        cities = MyTravelsApplication.cities
    }


}
