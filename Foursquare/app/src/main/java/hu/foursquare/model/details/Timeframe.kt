package hu.foursquare.model.details

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("net.hexar.json2pojo")
class Timeframe {
    @SerializedName("days")
    var days: String? = null
    @SerializedName("includesToday")
    var includesToday: Boolean? = null
    @SerializedName("open")
    var open: List<Open>? = null
    @SerializedName("segments")
    var segments: List<Any>? = null

}