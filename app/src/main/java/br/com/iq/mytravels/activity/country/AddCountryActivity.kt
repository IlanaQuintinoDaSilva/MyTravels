package br.com.iq.mytravels.activity.country

import android.content.Intent

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import br.com.iq.mytravels.R
import br.com.iq.mytravels.activity.BaseActivity
import br.com.iq.mytravels.data.DatabaseHelper
import br.com.iq.mytravels.domain.api.CountryService
import br.com.iq.mytravels.extensions.addFragment
import android.support.design.widget.NavigationView
import kotlinx.android.synthetic.main.activity_add_country.*

class AddCountryActivity : BaseActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private var country: String = ""
    private var countryService = CountryService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_country)

        mDrawerLayout = findViewById(R.id.drawer_layout)

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
                        /*val intent = Intent(context, BacklogActivity::class.java)
                        startActivity(intent)*/
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

        btAddCountry.setOnClickListener{
            country = etCountry.text.toString()
            addCountry(country)
            val intent = Intent(context, CountryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addCountry(country: String){
        val helper = DatabaseHelper(this)
        countryService.addCountry(country, helper)
    }
}
