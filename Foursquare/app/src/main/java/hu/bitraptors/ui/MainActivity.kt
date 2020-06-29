package hu.bitraptors.ui

import android.Manifest
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import hu.bitraptors.R
import hu.bitraptors.service.DataService
import hu.bitraptors.model.search.Venue
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DataService.VenueSearchCallback {

    lateinit var nearVenues : List<Venue>
    lateinit var myLocation : LatLng
    lateinit var dataService: DataService
    private val REQUEST_LOCATION = 100

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationCallback = MyLocationCallback()
        dataService = DataService()

        accessLocation()
    }

    inner class MyLocationCallback : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            if(locationResult == null){
                getLastLocation()
                return
            }
            val location = locationResult.locations[0]
            Log.d("map","onLocationResult Callback")
            Log.d("map", "Lat: ${location.latitude}  Lon: ${location.longitude}")

            myLocation = LatLng(location.latitude, location.longitude)
            getVenuesFromApi()
        }
    }

    fun getLastLocation(){
        fusedLocationClient.lastLocation
            .addOnSuccessListener {
                Log.d("map","Legutobbi location")
                Log.d("map","Lat: ${it.latitude}  Lon: ${it.longitude}")

                myLocation = LatLng(it.latitude, it.longitude)
                getVenuesFromApi()
            }
            .addOnFailureListener{
                Toast.makeText(this, "Unable to get Location data", Toast.LENGTH_LONG).show()
            }
    }

    private fun getVenuesFromApi(){
        val location = "${myLocation.latitude},${myLocation.longitude}"
        dataService.searchVenues(this, location)

       // RetrofitClient.venueService.findVenues(ll = location).enqueue(object : RetrofitClient.VenueCallback<SearchResponse> {
       //     override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
       //         super.onResponse(call, response)
       //         if(response.isSuccessful){
       //             nearVenues = response.body()!!.response!!.venues?: emptyList()
       //             if(nearVenues.isEmpty()) Toast.makeText(this@MainActivity, "No near Venues found!", Toast.LENGTH_LONG).show()
       //             supportFragmentManager.beginTransaction()
       //                 .replace(R.id.flFragment, ListFragment()).commit()
       //
       //         }
       //     }
       // })
    }

    private fun accessLocation(){
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            sendLocationRequest()
        } else {
            if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(this,"Permission is needed to show Venues",Toast.LENGTH_LONG).show()
            }

            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == REQUEST_LOCATION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                sendLocationRequest()
            }else{
                Toast.makeText(this, "Permission was not granted", Toast.LENGTH_LONG).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun sendLocationRequest() {
        val locationRequest = LocationRequest.create()?.apply {
            interval = 1000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            numUpdates = 1
        }

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest!!)

        val client: SettingsClient = LocationServices.getSettingsClient(this)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
        }

        task.addOnFailureListener { exception ->
            getLastLocation()

            if (exception is ResolvableApiException){
                Log.d("map","hibas beallitas")
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(this@MainActivity,
                        1)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    fun toMapFragment(){
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            .replace(R.id.flFragment, MapsFragment()).commit()
    }

    fun toListFragment(){
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.sliden_in_left, R.anim.slide_out_right)
            .replace(R.id.flFragment, ListFragment()).commit()
    }

    fun openVenueDetails(venueId : String){
        val intent = Intent(this,DetailsActivity::class.java)
        intent.putExtra("venueId", venueId)
        startActivity(intent)
    }

    override fun getSearchResult(result: List<Venue>) {
        nearVenues = result
        if(nearVenues.isEmpty()) Toast.makeText(this@MainActivity, "No near Venues found!", Toast.LENGTH_LONG).show()
        ivSplash.visibility = View.GONE
        supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, ListFragment()).commit()
    }
}
