package br.com.iq.mytravels.domain

import java.util.*

class City{
    var id: Long = 0
    var name: String = ""
    var country: String = ""
    var travel: String = ""
    var done: Int = 0


    override fun toString(): String{
        return "City(name='$name')"
    }
}