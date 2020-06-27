package br.com.iq.mytravels.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import br.com.iq.mytravels.MyTravelsApplication
import br.com.iq.mytravels.R
import br.com.iq.mytravels.activity.country.AddCountryActivity
import br.com.iq.mytravels.activity.country.CountryActivity
import br.com.iq.mytravels.data.DatabaseHelper
import br.com.iq.mytravels.domain.api.CountryService
import br.com.iq.mytravels.extensions.addFragment
import br.com.iq.mytravels.fragment.AddCountryFragment
import br.com.iq.mytravels.fragment.CountryListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private var countryService = CountryService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedCountriesData()
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            addFragment(R.id.container_main, CountryListFragment())
        }

        btNew.setOnClickListener {
            val intent = Intent(context, AddCountryActivity::class.java)
            startActivity(intent)
        }

        mDrawerLayout = findViewById(R.id.drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true

            when (menuItem.itemId) {
/*                R.id.nav_travels ->{
                    if(savedInstanceState == null){
                        *//*setToolBarTitle(getString(R.string.actual_sprint))
                        addFragment(R.id.container, ToDoListFragment())*//*
                    }
                }*/
                R.id.nav_countries ->{
                    if(savedInstanceState == null){
                        addFragment(R.id.container_main, CountryListFragment())
                    }
                }
/*                R.id.nav_home_city_nearby ->{
                    *//*val intent = Intent(context, CategoryActivity::class.java)
                    startActivity(intent)*//*
                }*/
            }

            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }

    }

    private fun feedCountriesData(){
        val helper = DatabaseHelper(this)
        MyTravelsApplication.dbHelper = helper
        MyTravelsApplication.countries = countryService.getCountries(helper)

    }
}
