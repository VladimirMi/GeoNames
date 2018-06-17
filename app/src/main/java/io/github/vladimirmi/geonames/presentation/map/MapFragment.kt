package io.github.vladimirmi.geonames.presentation.map

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.github.vladimirmi.geonames.R
import kotlinx.android.synthetic.main.fragment_map.*

/**
 * Created by Vladimir Mikhalev 17.06.2018.
 */

private const val MAP_BUNDLE_KEY = "MapBundleKey"

class MapFragment : Fragment(), OnMapReadyCallback {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapBundle = savedInstanceState?.getBundle(MAP_BUNDLE_KEY)
        mapView.onCreate(mapBundle)
        mapView.getMapAsync(this)
    }

    override fun onSaveInstanceState(out: Bundle) {
        super.onSaveInstanceState(out)

        val mapBundle = out.getBundle(MAP_BUNDLE_KEY)
                ?: Bundle().also { out.putBundle(MAP_BUNDLE_KEY, it) }

        mapView.onSaveInstanceState(mapBundle)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapReady(map: GoogleMap) {
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
