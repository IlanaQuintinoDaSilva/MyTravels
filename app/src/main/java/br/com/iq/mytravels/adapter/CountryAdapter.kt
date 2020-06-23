package br.com.iq.mytravels.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.iq.mytravels.R
import br.com.iq.mytravels.domain.Country
import br.com.iq.mytravels.domain.api.CountryService
import kotlinx.android.synthetic.main.adapter_country.view.*

class CountryAdapter(
    val countries: List<Country>,
    val onClick: (Country) -> Unit) : RecyclerView.Adapter<CountryAdapter.CountriesViewHolder>(){
    private var service = CountryService()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_country, parent, false)
        return CountriesViewHolder(view)
    }

    class CountriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        var country = countries[position]
        val itemView = holder.itemView

        with(itemView){
            tCountryId.text = country.id.toString()
            tName.text = country.name

        }
        holder.itemView.setOnClickListener{onClick(country)}
    }

    override fun getItemCount() = this.countries.size
}