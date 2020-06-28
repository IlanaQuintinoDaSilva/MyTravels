package br.com.iq.mytravels

import java.util.ArrayList



import br.com.iq.mytravels.data.DatabaseHelper
import br.com.iq.mytravels.domain.Country
import br.com.iq.mytravels.domain.City


object MyTravelsApplication {
    var cities: List<City> = ArrayList()
    var countries: List<Country> = ArrayList()
    var launched: Boolean = false
    var dbHelper: DatabaseHelper? = null
    var pageSelection: Int = 0
}