package hu.bitraptors.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import hu.bitraptors.R
import hu.bitraptors.model.response.DetailsResponse
import hu.bitraptors.model.response.SearchResponse
import hu.bitraptors.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitClient.venueService.findVenues().enqueue(object : RetrofitClient.VenueCallback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                Log.d("retrofit", response.code().toString())
                if(response.isSuccessful){
                    Log.d("retrofit", response.body()!!.response.venues[0].location.lat.toString())
                }
            }
        })

        RetrofitClient.venueService.getVenueDetails().enqueue(object : RetrofitClient.VenueCallback<DetailsResponse> {
            override fun onResponse(call: Call<DetailsResponse>, response: Response<DetailsResponse>) {
                Log.d("retrofit", response.code().toString())
                if(response.isSuccessful){
                    Log.d("retrofit", response.body()!!.response.venue.location.lat.toString())
                }
            }
        })
    }
}
