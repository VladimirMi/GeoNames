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
import io.github.vladimirmi.geonames.ServiceLocator
import io.github.vladimirmi.geonames.data.repository.MBTilesProvider
import kotlinx.android.synthetic.main.fragment_map.*
import java.io.File

/**
 * Created by Vladimir Mikhalev 17.06.2018.
 */

private const val MAP_BUNDLE_KEY = "MapBundleKey"

class MapExportFragment : Fragment(), OnMapReadyCallback {

    val database = ServiceLocator.instance.appDatabase

    val tileProvider by lazy { MBTilesProvider(File(context?.filesDir, "tiles")) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
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
        map.setMinZoomPreference(10f)
        map.setMaxZoomPreference(15f)
        val sydney = LatLng(51.28, 37.54)
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))


        map.setOnCameraIdleListener {
            val bounds = map.projection.visibleRegion.latLngBounds
            val names = database.geoNameDao().findAll(bounds.northeast.latitude, bounds.southwest.latitude,
                    bounds.northeast.longitude, bounds.southwest.longitude)

            map.clear()
            names.forEach {
                val position = LatLng(it.latitude.toDouble(), it.longitude.toDouble())
                map.addMarker(MarkerOptions().position(position).title(it.name))
            }
        }
    }
}
