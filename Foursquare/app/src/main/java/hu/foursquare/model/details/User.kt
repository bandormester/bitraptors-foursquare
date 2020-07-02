package hu.foursquare.model.details

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("net.hexar.json2pojo")
class User {
    @SerializedName("firstName")
    var firstName: String? = null
    @SerializedName("id")
    var id: String? = null
    @SerializedName("photo")
    var photo: Photo? = null
    @SerializedName("type")
    var type: String? = null

}