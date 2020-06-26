package br.com.iq.mytravels.activity.country

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import br.com.iq.mytravels.MyTravelsApplication
import br.com.iq.mytravels.R
import br.com.iq.mytravels.data.DatabaseHelper
import br.com.iq.mytravels.domain.api.CountryService
import br.com.iq.mytravels.extensions.addFragment
import br.com.iq.mytravels.fragment.CountryListFragment

class CountryActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private var country: String = ""
    private var service = CountryService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedInitialData()

        setContentView(R.layout.activity_country)

        mDrawerLayout = findViewById(R.id.drawer_layout_country)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true

            when (menuItem.itemId) {
                R.id.nav_travels ->{
                    if(savedInstanceState == null){
                        /*setToolBarTitle(getString(R.string.actual_sprint))
                        addFragment(R.id.container, ToDoListFragment())*/
                    }
                }
                R.id.nav_countries ->{
                    if(savedInstanceState == null){
                        addFragment(R.id.container, CountryListFragment())
                    }
                }
                R.id.nav_cities ->{
                    /*val intent = Intent(context, CategoryActivity::class.java)
                    startActivity(intent)*/
                }
                R.id.nav_places ->{
                    /*val intent = Intent(context, CloudBackup::class.java)
                    startActivity(intent)*/
                }
                R.id.nav_backup ->{

                }
            }

            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }

        addFragment(R.id.container, CountryListFragment())
    }

    private fun feedInitialData(){
        val helper = DatabaseHelper(this)
        MyTravelsApplication.dbHelper = helper
        MyTravelsApplication.countries = service.getCountries(helper)

    }
}
