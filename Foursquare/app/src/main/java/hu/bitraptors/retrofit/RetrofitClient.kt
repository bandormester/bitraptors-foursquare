package hu.bitraptors.retrofit

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val ClientId = "UMXDQEHAZ3X2EHEJGUNUBO5JT5ADO5I3YJSZHJMOKGALLBXJ"
    val ClientSecret = "AFNHCFBVDC0SL4R43OONWAS4C1XOFNPPRLQEAB105NYYZ1WK"

    val venueService : FoursquareApi = Retrofit.Builder()
        .baseUrl("https://api.foursquare.com/v2/venues/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FoursquareApi::class.java)

    interface VenueCallback<T> : Callback<T>{
        override fun onFailure(call: Call<T>, t: Throwable) {
            Log.d("retrofit", t.message)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            Log.d("retrofit", response.code().toString())
            Log.d("retrofit", response.message())
        }
    }
}