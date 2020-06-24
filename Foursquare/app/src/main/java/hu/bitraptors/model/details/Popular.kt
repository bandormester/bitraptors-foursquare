package hu.bitraptors.model.details

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("net.hexar.json2pojo")
class Popular {
    @SerializedName("isLocalHoliday")
    var isLocalHoliday: Boolean? = null
    @SerializedName("isOpen")
    var isOpen: Boolean? = null
    @SerializedName("richStatus")
    var richStatus: RichStatus? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("timeframes")
    var timeframes: List<Timeframe>? = null

}