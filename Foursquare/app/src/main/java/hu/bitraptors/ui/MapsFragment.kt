package hu.bitraptors.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import hu.bitraptors.R
import hu.bitraptors.model.search.Venue
import kotlinx.android.synthetic.main.fragment_maps.*

class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //TODO

        btSwitchList.setOnClickListener {
            addVenueMarkers((activity as MainActivity).nearVenues)
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        Log.d("map","lefut")

        val budapest = LatLng(47.5, 19.0)    //TODO
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(budapest,16.0f))
        mMap.setOnInfoWindowClickListener {marker -> Log.d("map", marker.tag.toString()) }

    }

    private fun addVenueMarkers(venues : List<Venue>){
        for(venue in venues){
            venue.location?.let{
                val position = LatLng(it.lat!!, it.lng!!)
                mMap.addMarker(MarkerOptions().position(position).title(venue.name)).tag=venue.id
            }
        }
    }
}
