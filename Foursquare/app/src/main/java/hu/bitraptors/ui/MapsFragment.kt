package hu.bitraptors.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import hu.bitraptors.R
import hu.bitraptors.model.search.Venue
import kotlinx.android.synthetic.main.fragment_maps.*

class MapsFragment : Fragment(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null
    private lateinit var mainActivity: MainActivity
    private lateinit var lastMarker : Marker

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        btSwitchList.setOnClickListener {
            mainActivity.toListFragment()
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(mainActivity.myLocation,16.0f))

        addVenueMarkers((activity as MainActivity).nearVenues)

        mMap!!.setOnInfoWindowClickListener { marker ->
            Toast.makeText(mainActivity, marker.tag.toString(), Toast.LENGTH_SHORT).show()
            mainActivity.openVenueDetails(marker.tag.toString())
            lastMarker = marker
        }

        mMap!!.setOnMarkerClickListener {
            hideButton()
            false
        }

        mMap!!.setOnMapClickListener { showButton() }

    }

    private fun addVenueMarkers(venues : List<Venue>){
        for(venue in venues){
            venue.location?.let{
                val position = LatLng(it.lat!!, it.lng!!)
                mMap!!.addMarker(MarkerOptions().position(position).title(venue.name)).tag=venue.id
            }
        }
    }

    //A gomb miatt nem latszodik a maptoolbar, ha markerre klikkelunk
    private fun hideButton(){
        if(btSwitchList.isFocusable){
            val fade = AlphaAnimation(1.0f, 0.5f)
            fade.duration = 500

            val slide = TranslateAnimation(0.0f,-220.0f,0.0f,0.0f)
            slide.duration = 250

            val set = AnimationSet(false)
            set.addAnimation(fade)
            set.addAnimation(slide)
            set.fillAfter = true

            btSwitchList.isFocusable = false
            btSwitchList.isClickable = false

            btSwitchList.startAnimation(set)
        }

    }

    private fun showButton(){
        if(!btSwitchList.isFocusable){
            val fade = AlphaAnimation(0.5f, 1.0f)
            fade.duration = 500

            val slide = TranslateAnimation(-220.0f,0.0f,0.0f,0.0f)
            slide.duration = 250

            val set = AnimationSet(false)
            set.addAnimation(fade)
            set.addAnimation(slide)
            set.fillAfter = true

            btSwitchList.isFocusable = true
            btSwitchList.isClickable = true

            btSwitchList.startAnimation(set)
        }

    }
}
