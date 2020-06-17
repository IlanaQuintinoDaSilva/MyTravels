package br.com.iq.mytravels.domain

import java.util.*

class City{
    var id: Long = 0
    var name: String = ""
    var country: String = ""


    override fun toString(): String{
        return "City(name='$name')"
    }
}