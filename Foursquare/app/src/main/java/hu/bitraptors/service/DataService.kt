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
    private lateinit var okRequest: Request
    private var pictures: MutableList<Bitmap> = mutableListOf()
    private var pictureCount: Int = 0

    public interface VenueSearchCallback{
        fun getSearchResult(result : List<Venue>)
    }

    public interface VenueDetailsCallback{
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
                    Log.d("retrofit",venue.name)
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
                    Log.d("okhttp","valasz")
                    Log.d("okhttp", "pic count "+pictureCount.toString())
                    Log.d("okhttp", "pic size "+pictures.size.toString())
                    pictures.add(bitmap)
                    detailsListener.getDetailsImages(pictures)
                }
            })


        //for(group in photos.groups!!){
        //    Log.d("okhttp","group for")
        //    Log.d("okhttp",photos.groups!!.size.toString())
        //    for(item in group.items!!){
        //        Log.d("okhttp",group.items!!.size.toString())
        //        Log.d("okhttp","item for")
        //        val myRequest = Request.Builder().url(item.prefix+"300x300"+item.suffix).build()
//
        //        okClient.newCall(myRequest).enqueue(object : Callback {
        //            override fun onFailure(call: okhttp3.Call, e: IOException) {
        //                Log.d("okhttp",e.message?:"")
        //            }
//
        //            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
        //                val inputStream = response.body()?.byteStream()
        //                val bitmap = BitmapFactory.decodeStream(inputStream)
        //                Log.d("okhttp","valasz")
        //                Log.d("okhttp", "pic count "+pictureCount.toString())
        //                Log.d("okhttp", "pic size "+pictures.size.toString())
        //                pictures.add(bitmap)
        //                if(pictures.size == pictureCount)
        //                detailsListener.getDetailsImages(pictures)
        //            }
        //        })
//
        //    }
        //}



      // okRequest = Request.Builder().url("https://igx.4sqi.net/img/general/300x300/5163668_xXFcZo7sU8aa1ZMhiQ2kIP7NllD48m7qsSwr1mJnFj4.jpg").build()

      // okClient.newCall(okRequest).enqueue(object : Callback {
      //     override fun onFailure(call: okhttp3.Call, e: IOException) {
      //         Log.d("okhttp",e.message?:"")
      //     }

      //     override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
      //         val inputStream = response.body()?.byteStream()
      //         val bitmap = BitmapFactory.decodeStream(inputStream)
      //         detailsListener.getDetailsImages(arrayOf(bitmap))
      //     }
      // })
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