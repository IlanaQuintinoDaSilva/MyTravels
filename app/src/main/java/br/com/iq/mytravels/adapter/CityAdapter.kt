package br.com.iq.mytravels.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.iq.mytravels.R
import br.com.iq.mytravels.domain.City
import br.com.iq.mytravels.domain.Country
import br.com.iq.mytravels.domain.api.CityService
import br.com.iq.mytravels.domain.api.CountryService
import kotlinx.android.synthetic.main.adapter_city.view.*
import kotlinx.android.synthetic.main.adapter_country.view.*

class CityAdapter(
    val cites: List<City>,
    val onClick: (City) -> Unit) : RecyclerView.Adapter<CityAdapter.CitiesViewHolder>(){
    private var service = CityService()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_city, parent, false)
        return CitiesViewHolder(view)
    }

    class CitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        var city = cites[position]
        val itemView = holder.itemView

        with(itemView){
            tCityId.text = city.id.toString()
            tCityName.text = city.name
            tCtCountry.text = city.country

        }
        holder.itemView.setOnClickListener{onClick(city)}
    }

    override fun getItemCount() = this.cites.size
}