package hu.foursquare.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import hu.foursquare.R
import hu.foursquare.adapter.VenueAdapter
import hu.foursquare.model.search.Venue
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(), VenueAdapter.OnVenueClickedListener {
    private lateinit var mainActivity: MainActivity
    private lateinit var venueAdapter: VenueAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainActivity = activity as MainActivity
        venueAdapter = VenueAdapter()

        recyclerView.layoutManager = LinearLayoutManager(mainActivity)
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        btSwitchMap.setOnClickListener {
            mainActivity.toMapFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        venueAdapter.addVenues(mainActivity.nearVenues)
        venueAdapter.listener = this
        recyclerView.adapter = venueAdapter
    }

    override fun onVenueClicked(venue: Venue) {
        Toast.makeText(mainActivity, venue.id, Toast.LENGTH_SHORT).show()
        mainActivity.openVenueDetails(venue.id!!)
    }

}