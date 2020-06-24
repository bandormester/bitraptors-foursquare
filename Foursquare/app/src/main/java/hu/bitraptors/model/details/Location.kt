package hu.bitraptors.model.details

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("net.hexar.json2pojo")
class Location {
    @SerializedName("cc")
    var cc: String? = null
    @SerializedName("country")
    var country: String? = null
    @SerializedName("formattedAddress")
    var formattedAddress: List<String>? = null
    @SerializedName("labeledLatLngs")
    var labeledLatLngs: List<LabeledLatLng>? =
        null
    @SerializedName("lat")
    var lat: Double? = null
    @SerializedName("lng")
    var lng: Double? = null

}