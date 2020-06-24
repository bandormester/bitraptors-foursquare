package hu.bitraptors.model.search

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("net.hexar.json2pojo")
class Response {
    @SerializedName("confident")
    var confident: Boolean? = null
    @SerializedName("venues")
    var venues: List<Venue>? = null

}