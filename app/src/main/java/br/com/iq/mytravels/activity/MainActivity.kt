package br.com.iq.mytravels.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import br.com.iq.mytravels.MyTravelsApplication
import br.com.iq.mytravels.R
import br.com.iq.mytravels.activity.city.AddCityActivity
import br.com.iq.mytravels.activity.country.AddCountryActivity
import br.com.iq.mytravels.activity.country.CountryActivity
import br.com.iq.mytravels.data.DatabaseHelper
import br.com.iq.mytravels.domain.api.CityService
import br.com.iq.mytravels.domain.api.CountryService
import br.com.iq.mytravels.extensions.addFragment
import br.com.iq.mytravels.fragment.AddCountryFragment
import br.com.iq.mytravels.fragment.CityListFragment
import br.com.iq.mytravels.fragment.CountryListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private var countryService = CountryService()
    private var cityService = CityService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getCountries()
        getCities()
        setContentView(R.layout.activity_main)

//        if(savedInstanceState == null) {

        if (MyTravelsApplication.pageSelection == 0){
            addFragment(R.id.container_main, CountryListFragment())
        }

        if (MyTravelsApplication.pageSelection == 1){
            addFragment(R.id.container_main, CityListFragment())
        }

//        }

        btNew.setOnClickListener {
            if (MyTravelsApplication.pageSelection == 0){
                val intent = Intent(context, AddCountryActivity::class.java)
                startActivity(intent)
            }
            if (MyTravelsApplication.pageSelection == 1){
                val intent = Intent(context, AddCityActivity::class.java)
                startActivity(intent)
            }

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
                        MyTravelsApplication.pageSelection = 0
                        reloadActivity()
//                        addFragment(R.id.container_main, CountryListFragment())
                    }
                }
                R.id.nav_cities ->{
                    if(savedInstanceState == null){
                      MyTravelsApplication.pageSelection = 1
//                        addFragment(R.id.container_main, CityListFragment())

                        reloadActivity()
/*                        val intent = Intent(context, AddCityActivity::class.java)
                        startActivity(intent)*/
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

    private fun getCountries(){
        val helper = DatabaseHelper(this)
        MyTravelsApplication.dbHelper = helper
        MyTravelsApplication.countries = countryService.getCountries(helper)

    }

    private fun getCities(){
        val helper = DatabaseHelper(this)
        MyTravelsApplication.dbHelper = helper
        MyTravelsApplication.cities = cityService.getCities(helper,"")

    }

    private fun reloadActivity(){
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }
}
