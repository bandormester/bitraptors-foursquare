package hu.foursquare.model.details

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("net.hexar.json2pojo")
class Venue {
    @SerializedName("attributes")
    var attributes: Attributes? = null
    @SerializedName("beenHere")
    var beenHere: BeenHere? = null
    @SerializedName("bestPhoto")
    var bestPhoto: BestPhoto? = null
    @SerializedName("canonicalUrl")
    var canonicalUrl: String? = null
    @SerializedName("categories")
    var categories: List<Category>? = null
    @SerializedName("colors")
    var colors: Colors? = null
    @SerializedName("contact")
    var contact: Contact? = null
    @SerializedName("createdAt")
    var createdAt: Long? = null
    @SerializedName("dislike")
    var dislike: Boolean? = null
    @SerializedName("hereNow")
    var hereNow: HereNow? = null
    @SerializedName("id")
    var id: String? = null
    @SerializedName("inbox")
    var inbox: Inbox? = null
    @SerializedName("likes")
    var likes: Likes? = null
    @SerializedName("listed")
    var listed: Listed? = null
    @SerializedName("location")
    var location: Location? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("ok")
    var ok: Boolean? = null
    @SerializedName("pageUpdates")
    var pageUpdates: PageUpdates? = null
    @SerializedName("photos")
    var photos: Photos? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("popular")
    var popular: Popular? = null
    @SerializedName("reasons")
    var reasons: Reasons? = null
    @SerializedName("seasonalHours")
    var seasonalHours: List<Any>? = null
    @SerializedName("shortUrl")
    var shortUrl: String? = null
    @SerializedName("specials")
    var specials: Specials? = null
    @SerializedName("stats")
    var stats: Stats? = null
    @SerializedName("timeZone")
    var timeZone: String? = null
    @SerializedName("tips")
    var tips: Tips? = null
    @SerializedName("venueRatingBlacklisted")
    var venueRatingBlacklisted: Boolean? = null
    @SerializedName("verified")
    var verified: Boolean? = null

}