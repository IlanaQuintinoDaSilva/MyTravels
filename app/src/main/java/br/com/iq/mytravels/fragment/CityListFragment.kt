package br.com.iq.mytravels.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.iq.mytravels.MyTravelsApplication
import br.com.iq.mytravels.R
import br.com.iq.mytravels.activity.country.AddCountryActivity
import br.com.iq.mytravels.adapter.CityAdapter
import br.com.iq.mytravels.adapter.CountryAdapter
import br.com.iq.mytravels.domain.City
import br.com.iq.mytravels.domain.Country

import br.com.iq.mytravels.fragment.dummy.DummyContent
import br.com.iq.mytravels.fragment.dummy.DummyContent.DummyItem
import kotlinx.android.synthetic.main.fragment_country_list.*
import java.util.*


class CityListFragment : BaseFragment() {

    private var cities: List<City> = ArrayList()
    var rvCity: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getCities()

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_city_list, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        cities = MyTravelsApplication.cities
    }


}
