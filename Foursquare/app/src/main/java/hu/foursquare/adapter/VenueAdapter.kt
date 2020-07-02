package hu.foursquare.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.foursquare.R
import hu.foursquare.model.search.Venue
import kotlinx.android.synthetic.main.row_venue.view.*

class VenueAdapter : RecyclerView.Adapter<VenueAdapter.VenueHolder>(){

    private val venues = mutableListOf<Venue>()
    var listener : OnVenueClickedListener? = null

    interface OnVenueClickedListener{
        fun onVenueClicked(venue : Venue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueAdapter.VenueHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_venue, parent, false)
        return VenueHolder(view)
    }

    override fun getItemCount() = venues.size

    override fun onBindViewHolder(holder: VenueAdapter.VenueHolder, position: Int) {
        holder.venue = venues[position]
        holder.tvRowName.text = holder.venue!!.name
    }

    fun addVenues(newVenues : List<Venue>){
        val size = itemCount
        venues.clear()
        venues.addAll(newVenues)
        notifyItemRangeChanged(size, itemCount)
    }

    inner class VenueHolder(venueView : View) : RecyclerView.ViewHolder(venueView){
        var venue : Venue? = null
        var tvRowName: TextView = venueView.tvRowName

        init{
            venueView.setOnClickListener {
                listener?.onVenueClicked(venue!!)
            }
        }
    }


}