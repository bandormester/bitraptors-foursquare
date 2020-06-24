package hu.bitraptors.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bitraptors.R
import hu.bitraptors.model.search.Venue

class VenueAdapter : RecyclerView.Adapter<VenueAdapter.VenueHolder>(){

    val venues = mutableListOf<Venue>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueAdapter.VenueHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_venue, parent, false)
        return VenueHolder(view)
    }

    override fun getItemCount() = venues.size

    override fun onBindViewHolder(holder: VenueAdapter.VenueHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class VenueHolder(venueView : View) : RecyclerView.ViewHolder(venueView){

    }


}