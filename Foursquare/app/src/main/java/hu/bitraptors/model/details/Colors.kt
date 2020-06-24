package hu.bitraptors.model.details

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("net.hexar.json2pojo")
class Colors {
    @SerializedName("algoVersion")
    var algoVersion: Long? = null
    @SerializedName("highlightColor")
    var highlightColor: HighlightColor? = null
    @SerializedName("highlightTextColor")
    var highlightTextColor: HighlightTextColor? = null

}