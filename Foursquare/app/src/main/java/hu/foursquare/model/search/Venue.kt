package hu.foursquare.model.search

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("net.hexar.json2pojo")
class Venue {
    @SerializedName("categories")
    var categories: List<Category>? = null
    @SerializedName("hasPerk")
    var hasPerk: Boolean? = null
    @SerializedName("id")
    var id: String? = null
    @SerializedName("location")
    var location: Location? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("referralId")
    var referralId: String? = null

}