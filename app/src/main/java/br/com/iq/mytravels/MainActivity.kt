package br.com.iq.mytravels

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
    }
}
