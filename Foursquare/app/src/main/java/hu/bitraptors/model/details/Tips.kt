package hu.bitraptors.model.details

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("net.hexar.json2pojo")
class Tips {
    @SerializedName("count")
    var count: Long? = null
    @SerializedName("groups")
    var groups: List<Group>? = null

}