package hu.foursquare.model.details

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("net.hexar.json2pojo")
class Group {
    @SerializedName("count")
    var count: Long? = null
    @SerializedName("items")
    var items: List<Item>? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("type")
    var type: String? = null

}