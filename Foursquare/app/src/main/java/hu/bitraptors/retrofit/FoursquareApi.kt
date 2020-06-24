package hu.bitraptors.retrofit

import hu.bitraptors.model.response.DetailsResponse
import hu.bitraptors.model.response.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public interface FoursquareApi {

    @GET("search")
    fun findVenues(@Query("client_id") client_id : String = RetrofitClient.ClientId,
                   @Query("client_secret") client_secret : String = RetrofitClient.ClientSecret,
                   @Query("v") v : String = "20200624",
                   @Query("ll") ll : String = "47.5,19") : Call<SearchResponse>

    @GET("{id}")
    fun getVenueDetails(@Path("id") id : String = "50389c0fe4b04a184c86b7e8",
                        @Query("client_id") client_id : String = RetrofitClient.ClientId,
                        @Query("client_secret") client_secret : String = RetrofitClient.ClientSecret,
                        @Query("v") v : String = "20200624") : Call<DetailsResponse>
}