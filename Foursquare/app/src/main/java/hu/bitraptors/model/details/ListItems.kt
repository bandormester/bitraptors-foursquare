package hu.bitraptors.model.details

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("net.hexar.json2pojo")
class ListItems {
    @SerializedName("count")
    var count: Long? = null
    @SerializedName("items")
    var items: List<Item>? = null

}