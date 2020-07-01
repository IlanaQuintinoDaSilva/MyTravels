package br.com.iq.mytravels.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import br.com.iq.mytravels.MyTravelsApplication
import br.com.iq.mytravels.R
import br.com.iq.mytravels.activity.country.AddCountryActivity
import br.com.iq.mytravels.activity.ui.city.AddCityActivity
import br.com.iq.mytravels.data.DatabaseHelper
import br.com.iq.mytravels.domain.api.CountryService

class NavMainActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var countryService = CountryService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            if (MyTravelsApplication.pageSelection == 0){
                val intent = Intent(context, AddCountryActivity::class.java)
                startActivity(intent)
            }
            if (MyTravelsApplication.pageSelection == 1){
                val intent = Intent(context, AddCityActivity::class.java)
                startActivity(intent)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_country, R.id.nav_city
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.nav_main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}