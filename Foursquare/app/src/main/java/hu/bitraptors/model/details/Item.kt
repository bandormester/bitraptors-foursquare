package hu.bitraptors.model.details

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("net.hexar.json2pojo")
class Item {
    @SerializedName("canonicalUrl")
    var canonicalUrl: String? = null
    @SerializedName("collaborative")
    var collaborative: Boolean? = null
    @SerializedName("createdAt")
    var createdAt: Long? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("editable")
    var editable: Boolean? = null
    @SerializedName("firstName")
    var firstName: String? = null
    @SerializedName("followers")
    var followers: Followers? = null
    @SerializedName("height")
    var height: Long? = null
    @SerializedName("id")
    var id: String? = null
    @SerializedName("lastName")
    var lastName: String? = null
    @SerializedName("listItems")
    var listItems: ListItems? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("photo")
    var photo: Photo? = null
    @SerializedName("prefix")
    var prefix: String? = null
    @SerializedName("public")
    var public: Boolean? = null
        private set
    @SerializedName("suffix")
    var suffix: String? = null
    @SerializedName("type")
    var type: String? = null
    @SerializedName("updatedAt")
    var updatedAt: Long? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("user")
    var user: User? = null
    @SerializedName("visibility")
    var visibility: String? = null
    @SerializedName("width")
    var width: Long? = null
}