package br.com.rafaeldias.gym.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Toast
import br.com.rafaeldias.gym.R
import br.com.rafaeldias.gym.utils.Permissao

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    val permissoesLocalizacao = listOf(Manifest.permission.ACCESS_FINE_LOCATION)

    private lateinit var locationListener: LocationListener

    private lateinit  var enderecoGym: String
    private var latitudeGym: Double = 0.0
    private var longitudeGym: Double = 0.0
    private lateinit  var titleGym: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        Permissao.validaPermissao(permissoesLocalizacao.toTypedArray(),this,1)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        enderecoGym = intent!!.getStringExtra("endereco")
        latitudeGym = intent!!.getDoubleExtra("latitude",0.0)
        longitudeGym = intent!!.getDoubleExtra("longitude", 0.0)
        titleGym = intent!!.getStringExtra("title")
    }

    private fun initLocationListener() {
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {

            }

            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

            }

            override fun onProviderEnabled(p0: String?) {

            }

            override fun onProviderDisabled(p0: String?) {

            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for(resposta in grantResults) {
            if (resposta == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(applicationContext, getString(R.string.sem_permissao_acesso_mapa), Toast.LENGTH_LONG).show()
            } else {
                requestLocationUpdates()
            }
        }
    }

    private fun requestLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0.1f, locationListener)
        }
    }

    private fun addMarcador(latLng: LatLng, titulo: String, endereco: String) {
        mMap.addMarker(MarkerOptions().position(latLng).title(titulo).snippet(endereco)).showInfoWindow()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        initLocationListener()
        requestLocationUpdates()

        addMarcador(LatLng(latitudeGym,longitudeGym), titleGym, enderecoGym)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitudeGym,longitudeGym),16f))

        val circulo = CircleOptions()
        circulo.center(LatLng(latitudeGym,longitudeGym))
        circulo.radius(100.0)
        circulo.fillColor(Color.argb(128,0,51,102))
        circulo.strokeWidth(10f)
        circulo.strokeColor(Color.argb(128,0,51,102))

        mMap.addCircle(circulo)
    }
}
