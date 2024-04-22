package com.app.shuru.views.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.app.shuru.R
import com.app.shuru.viewmodel.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LocationFragment : BaseFragment<Any?>() {

    private val REQUEST_CODE_PERMISSIONS = 101
    private val requiredPermissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
    lateinit var locationRequest: com.google.android.gms.location.LocationRequest

    // Declare FusedLocationProviderClient variable
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.layout_location_weather_fragment, container, false)
        // Initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        setupViews(rootView)

        // Inflate the layout for this fragment
        return rootView
    }

    override fun setupViews(view: View) {
        // Perform setup for views in the fragment
        // For example, findViewById, set click listeners, etc.
        checkPermissions()

        viewModel.fetchWeather("Delhi", "d7b950541d7264a3b3df80a8b6f2cbf7")

        // Observe the movies LiveData in ViewModel
        viewModel.weather.observe(requireActivity(), Observer { weather ->
            // Handle success
            Log.e("weather", "temp: " + weather.main!!.temp.toString())
            Log.e("weather", "feelslike: " + weather.main!!.feelsLike.toString())
            Log.e("weather", "humidity: " + weather.main!!.humidity.toString())
        })

        viewModel.weatherCurrent.observe(requireActivity(), Observer { weather ->
            // Handle success
            Log.e("weatherCurrent", "temp: " + weather.main!!.temp.toString())
            Log.e("weatherCurrent", "feelslike: " + weather.main!!.feelsLike.toString())
            Log.e("weatherCurrent", "humidity: " + weather.main!!.humidity.toString())
        })
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                requiredPermissions,
                REQUEST_CODE_PERMISSIONS
            )
        } else {
            // Permissions already granted
            startLocationUpdates()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissions granted
                startLocationUpdates()
            } else {
                // Permissions denied
                // Handle the scenario when user denies the permission
                // I have not implemented rational permissions but this scenario can be handled using rational permissions
                showToast("Location Permission is mandatory. Please grant from ap settings")
            }
        }
    }

    // Call this method when you want to start receiving location updates
    private fun startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    location?.let {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        // Do something with the current location (latitude and longitude)
                        viewModel.fetchWeatherCurrentLoc(
                            latitude.toString(),
                            longitude.toString(),
                            "d7b950541d7264a3b3df80a8b6f2cbf7"
                        )
                    } ?: run {
                        // Handle the case where the last known location is null
                    }
                }
                .addOnFailureListener { e ->
                    // Handle failure to retrieve the last known location
                }
        }
    }

}




