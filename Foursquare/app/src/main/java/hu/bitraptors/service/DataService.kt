package hu.bitraptors.service

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
import java.io.IOException

class DataService {

    private lateinit var searchListener : VenueSearchCallback
    private lateinit var detailsListener : VenueDetailsCallback
    private lateinit var okClient: OkHttpClient
    private var pictures: MutableList<Bitmap> = mutableListOf()

    interface VenueSearchCallback{
        fun getSearchResult(result : List<Venue>)
    }

    interface VenueDetailsCallback{
        fun getDetailsResult(result : hu.bitraptors.model.details.Venue)
        fun getDetailsImages(result: MutableList<Bitmap>)
    }

    fun getVenueDetails(listener: VenueDetailsCallback, venueId: String){
        detailsListener = listener

        RetrofitClient.venueService.getVenueDetails(id = venueId).enqueue(object : RetrofitClient.VenueCallback<DetailsResponse>{
            override fun onResponse(call: Call<DetailsResponse>, response: Response<DetailsResponse>) {
                super.onResponse(call, response)
                if(response.isSuccessful){
                    val venue = response.body()!!.response!!.venue!!
                    detailsListener.getDetailsResult(venue)
                }
            }
        })

    }

    fun getImages(
        listener: VenueDetailsCallback,
        photos: Photos
    ){
        val myRequest : Request
        myRequest = if(photos.count!! > 0) {
            val item = photos.groups!![0].items!![0]
            Request.Builder().url(item.prefix+"300x300"+item.suffix).build()
        } else{
            //Ha nincs kép egy placeholder képet tölt le
            Request.Builder().url("https://lh3.googleusercontent.com/81tvpT59weJbOGWT9jQ8_9RtcGXKCcVv59BU7Wl6PnS7okIgrS4iTCgwWpPQY2FRKw").build()
        }
            detailsListener = listener
            okClient = OkHttpClient()

            okClient.newCall(myRequest).enqueue(object : Callback {
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    Log.d("okhttp",e.message?:"")
                }

                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    val inputStream = response.body()?.byteStream()
                    val bitmap = BitmapFactory.decodeStream(inputStream)

                    //Hogy lehessen a kepek kozott scrollolni
                    pictures.add(bitmap)
                    pictures.add(bitmap)
                    detailsListener.getDetailsImages(pictures)
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