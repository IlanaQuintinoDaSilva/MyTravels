package br.com.iq.mytravels.fragment

import android.content.Context
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
import br.com.iq.mytravels.adapter.CountryAdapter
import br.com.iq.mytravels.domain.Country

import br.com.iq.mytravels.fragment.dummy.DummyContent
import br.com.iq.mytravels.fragment.dummy.DummyContent.DummyItem
import java.util.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [CountryListFragment.OnListFragmentInteractionListener] interface.
 */
class CountryListFragment : BaseFragment() {

    private var countries: List<Country> = ArrayList()
    var rvCountry: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
    }

    private fun setupAdapter(list: List<Country>){
        rvCountry?.adapter = CountryAdapter(countries) { onClickItem(it) }
    }

    private fun onClickItem(country: Country) {

    }


    private fun getCountries(){
        countries = MyTravelsApplication.countries
    }


}
