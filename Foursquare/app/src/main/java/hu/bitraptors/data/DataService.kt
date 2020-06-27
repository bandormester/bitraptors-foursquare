package hu.bitraptors.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import hu.bitraptors.model.details.DetailsResponse
import hu.bitraptors.model.details.Photos
import hu.bitraptors.model.search.SearchResponse
import hu.bitraptors.model.search.Venue
import hu.bitraptors.retrofit.RetrofitClient
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

class DataService {

    private lateinit var searchListener : VenueSearchCallback
    private lateinit var detailsListener : VenueDetailsCallback
    private lateinit var okClient: OkHttpClient
    private lateinit var okRequest: Request

    public interface VenueSearchCallback{
        fun getSearchResult(result : List<Venue>)
    }

    public interface VenueDetailsCallback{
        fun getDetailsResult(result : hu.bitraptors.model.details.Venue)
        fun getDetailsImages(result: Array<Bitmap>)
    }

    fun getVenueDetails(listener: VenueDetailsCallback, venueId: String){
        detailsListener = listener

        RetrofitClient.venueService.getVenueDetails(id = venueId).enqueue(object : RetrofitClient.VenueCallback<DetailsResponse>{
            override fun onResponse(call: Call<DetailsResponse>, response: Response<DetailsResponse>) {
                super.onResponse(call, response)
                if(response.isSuccessful){
                    val venue = response.body()!!.response!!.venue!!
                    Log.d("retrofit",venue.name)
                    detailsListener.getDetailsResult(venue)
                }
            }
        })

    }

    fun getImages(listener: VenueDetailsCallback){
        detailsListener = listener
        okClient = OkHttpClient()
        okRequest = Request.Builder().url("https://igx.4sqi.net/img/general/300x300/5163668_xXFcZo7sU8aa1ZMhiQ2kIP7NllD48m7qsSwr1mJnFj4.jpg").build()

        okClient.newCall(okRequest).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("okhttp",e.message?:"")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val inputStream = response.body()?.byteStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)
                detailsListener.getDetailsImages(arrayOf(bitmap))
            }
        })
    }

    fun searchVenues(listener: VenueSearchCallback, location: String){
        searchListener = listener

        RetrofitClient.venueService.findVenues(ll = location).enqueue(object : RetrofitClient.VenueCallback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                super.onResponse(call, response)
                if(response.isSuccessful){
                    val nearVenues = response.body()!!.response!!.venues?: emptyList()
                    searchListener.getSearchResult(nearVenues)
                }
            }
        })
    }

}