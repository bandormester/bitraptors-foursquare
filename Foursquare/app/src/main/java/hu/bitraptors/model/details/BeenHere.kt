package hu.bitraptors.model.details

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("net.hexar.json2pojo")
class BeenHere {
    @SerializedName("count")
    var count: Long? = null
    @SerializedName("lastCheckinExpiredAt")
    var lastCheckinExpiredAt: Long? = null
    @SerializedName("marked")
    var marked: Boolean? = null
    @SerializedName("unconfirmedCount")
    var unconfirmedCount: Long? = null

}