package hu.bitraptors.ui

import android.graphics.Bitmap
import androidx. appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import hu.bitraptors.R
import hu.bitraptors.adapter.ImageAdapter
import hu.bitraptors.service.DataService
import hu.bitraptors.model.details.Venue
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), DataService.VenueDetailsCallback {

    private lateinit var venue : Venue
    private lateinit var dataService: DataService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        dataService = DataService()

        val venueId = intent.getStringExtra("venueId")
        if(venueId!=null){
            getVenueFromApi(venueId)
        }else{
            Toast.makeText(this, "Venue ID not found", Toast.LENGTH_LONG).show()
        }





    }

    private fun getVenueFromApi(venueId: String){
        dataService.getVenueDetails(this, venueId)
    }

    private fun setupView(){
        tvDetailsName.text = venue.name

        if(venue.location?.formattedAddress!=null){
            tvAddress.text = venue.location?.formattedAddress?.get(0)
        }else tvAddress.text = getString(R.string.no_address)

        if(!venue.categories.isNullOrEmpty()){
            for(cat in venue.categories!!){
                if(cat.primary == true){
                    tvCategory.text = getString(R.string.details_category, cat.name)
                    break;
                }
            }
        } else tvCategory.text = getString(R.string.no_category)

        if(venue.shortUrl != null){
            tvUrl.text = venue.shortUrl
        } else tvUrl.text = getString(R.string.no_website)

        if(venue.description != null){
            tvDescription.text = venue.description
        }else tvDescription.text = getString(R.string.no_description)


    }

    override fun getDetailsResult(result: Venue) {
        venue = result
        setupView()

        venue.photos?.let {
            dataService.getImages(this, it)
        }

    }

    override fun getDetailsImages(result: MutableList<Bitmap>) {
        runOnUiThread {
            val array = result.toTypedArray()
            val imageAdapter = ImageAdapter(this)
            imageAdapter.addImages(array)
            viewPager.adapter = imageAdapter
            Log.d("okhttp", "lefutott")
        }
    }
}
