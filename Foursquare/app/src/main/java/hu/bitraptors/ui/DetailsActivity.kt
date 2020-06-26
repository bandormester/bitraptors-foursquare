package hu.bitraptors.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import hu.bitraptors.R
import hu.bitraptors.model.details.DetailsResponse
import hu.bitraptors.model.details.Venue
import hu.bitraptors.model.search.SearchResponse
import hu.bitraptors.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {

    private lateinit var venue : Venue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val venueId = intent.getStringExtra("venueId")
        if(venueId!=null){
            getVenueFromApi(venueId)
        }else{
            Toast.makeText(this, "Venue ID not found", Toast.LENGTH_LONG).show()
        }
    }

    private fun getVenueFromApi(venueId: String){
        RetrofitClient.venueService.getVenueDetails(id = venueId).enqueue(object : RetrofitClient.VenueCallback<DetailsResponse>{
            override fun onResponse(
                call: Call<DetailsResponse>,
                response: Response<DetailsResponse>
            ) {
                super.onResponse(call, response)
                if(response.isSuccessful){
                    venue = response.body()!!.response!!.venue!!
                    Log.d("retrofit",venue.name)
                    setupView()
                }
            }
        })
    }

    fun setupView(){
        //TODO
    }
}
