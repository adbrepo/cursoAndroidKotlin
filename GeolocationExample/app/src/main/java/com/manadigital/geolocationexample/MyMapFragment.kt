package com.manadigital.geolocationexample

import android.content.ContentValues.TAG
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.manadigital.geolocationexample.databinding.FragmentMyMapBinding
import com.manadigital.geolocationexample.R


class MyMapFragment : Fragment() {

    private var mapsSupported: Boolean = true

    private var _binding: FragmentMyMapBinding? = null
    private val binding get() = _binding!!

    private var myPosition = LatLng(-4.0, -5.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding.mapView.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMyMapBinding.inflate(layoutInflater)

        binding.mapView.onCreate(savedInstanceState)
        setupMap(myPosition)
        binding.btnAddress.setOnClickListener {
            getLocationByAddress(binding.edtAddress.editText!!.text.toString())
        }

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        try {
            MapsInitializer.initialize(activity)

            // Initialize the SDK
            Places.initialize(requireContext(), getString(R.string.places_api_key))

            // Create a new PlacesClient instance
            val placesClient = Places.createClient(requireContext())

            // Initialize the AutocompleteSupportFragment.
            val autocompleteFragment = childFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment

            // Specify the types of place data to return.
            autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG))

            // Set up a PlaceSelectionListener to handle the response.
            autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
                override fun onPlaceSelected(place: Place) {
                    // TODO: Get info about the selected place.
                    Log.i(TAG, "Place: ${place.name}, ${place.id}, ${place.latLng}")
                    setupMap(place.latLng!!)
                }

                override fun onError(status: Status) {
                    // TODO: Handle the error.
                    Log.i(TAG, "An error occurred: $status")
                }
            })

        } catch (e: GooglePlayServicesNotAvailableException) {
            mapsSupported = false
        }

    }

    private fun setupMap(latLng: LatLng) {
        if(mapsSupported) {
            binding.mapView.getMapAsync { map: GoogleMap ->
                //map.isMyLocationEnabled = true
                map.mapType = GoogleMap.MAP_TYPE_NORMAL
                map.clear()

                val googlePlex = CameraPosition.builder()
                    .target(latLng)
                    .zoom(15f)
                    .bearing(0f)
                    .tilt(0f)
                    .build()

                map.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 50, null)

                val markerOptions = MarkerOptions()
                    .position(latLng)
                    .title("Tu direccion")
                    .snippet("Subtitulo")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                val marker = map.addMarker(markerOptions)
                marker.showInfoWindow()

                binding.mapView.onResume()
            }
        } else {
            binding.mapView.visibility = View.GONE
        }
    }

    private fun getLocationByAddress(address: String) {
        Log.w("MyMapFragment", "Search address: $address")

        try {
            val geocoder = Geocoder(context)
            val addresses = geocoder.getFromLocationName(address, 5)
            addresses.forEach {
                Log.w(
                    "MyMapFragment:",
                    "Address: ${it.adminArea},${it.locality},${it.subLocality}${it.countryName}"
                )
            }
            setupMap(LatLng(addresses[0].latitude, addresses[0].longitude))
        } catch (e: Exception) {
            Log.w("MyMapFragment", e)
        }
    }
}