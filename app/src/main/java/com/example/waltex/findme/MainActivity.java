package com.example.waltex.findme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    GoogleMap mGoogleMap;
    GoogleApiClient mGoogleApiClient;
    Button btnGo;
    Button btnPlace;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (googleServicesAvailable()) {
            Toast.makeText(this, "Perfect!!!", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_main);
            initMap();



        } else {
            //No google maps layout
        }

        btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    geoLocate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        btnPlace = (Button) findViewById(R.id.btnPlacePicker);

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment();
            }
        });




    }

    private void openFragment() {
        Intent intent = new Intent(this, PlaceActivity.class);
         startActivity(intent);
    }


    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    public boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Cant connect to play services", Toast.LENGTH_LONG).show();
        }

        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        //array of inserted markers
        LatLng sydney = new LatLng(5.692173, -0.037003);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Crime Hotspot!.Robbery sector"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //
        LatLng wa = new LatLng(10.047117, -2.508767);
        googleMap.addMarker(new MarkerOptions().position(wa)
                .title("Crime Hotspot!.Stone to death"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(wa));
        //
        LatLng axim = new LatLng(4.868883, -2.233175);
        googleMap.addMarker(new MarkerOptions().position(axim)
                .title("Crime Hotspot!.Flooding Sector"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(axim));
        //
        LatLng ksi = new LatLng(6.676100, -1.563237);
        googleMap.addMarker(new MarkerOptions().position(ksi)
                .title("Crime Hotspot!.Robbery sector"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ksi));
        //
        LatLng el = new LatLng(5.727515, 0.006204);
        googleMap.addMarker(new MarkerOptions().position(el)
                .title("Crime Hotspot!!Kwashey"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(el));
        //
        LatLng laKo = new LatLng(5.125063, -1.215803);
        googleMap.addMarker(new MarkerOptions().position(laKo)
                .title("Crime Hotspot!!Fire burns"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(laKo));
        //
        LatLng la = new LatLng(5.624101, -0.274708);
        googleMap.addMarker(new MarkerOptions().position(la)
                .title("Crime Hotspot!Armed Robbers here"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(la));
        //
        LatLng sowutuom = new LatLng(5.624101, -0.274708);
        googleMap.addMarker(new MarkerOptions().position(sowutuom)
                .title("Crime Hotspot!Armed attackers here"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sowutuom));
        //
        LatLng koforidua = new LatLng(6.056245, -0.267464);
        googleMap.addMarker(new MarkerOptions().position(koforidua)
                .title("Crime Hotspot!Voilence"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(koforidua));
        //
        LatLng Cape = new LatLng(5.114003, -1.235201);
        googleMap.addMarker(new MarkerOptions().position(Cape)
                .title("Crime Hotspot!Civil war"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Cape));
        //
        LatLng adowso = new LatLng(6.714647, -0.511315);
        googleMap.addMarker(new MarkerOptions().position(adowso)
                .title("Crime Hotspot!Cheif-tancy Voilence"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(adowso));
        //
        LatLng akosombo = new LatLng(6.264195, 0.045104);
        googleMap.addMarker(new MarkerOptions().position(akosombo)
                .title("Crime Hotspot!killing"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(akosombo));
        //
        LatLng kpong = new LatLng(6.159627, 0.057077);
        googleMap.addMarker(new MarkerOptions().position(kpong)
                .title("Crime Hotspot!Scolding"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(kpong));
        //
        LatLng winnie = new LatLng(5.349129, -0.613669);
        googleMap.addMarker(new MarkerOptions().position(winnie)
                .title("Crime Hotspot!killing&Robbery"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(winnie));
        //
        LatLng oda = new LatLng(5.926184, -0.973191);
        googleMap.addMarker(new MarkerOptions().position(oda)
                .title("Crime Hotspot!fighting"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(oda));
        //
        LatLng nkawkaw = new LatLng(6.543664, -0.763593);
        googleMap.addMarker(new MarkerOptions().position(nkawkaw)
                .title("Crime Hotspot!Gallamsey"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(nkawkaw));
        //
        LatLng apedwa = new LatLng(6.123699, -0.492711);
        googleMap.addMarker(new MarkerOptions().position(apedwa)
                .title("Crime Hotspot!spot killing"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(apedwa));
        //
        LatLng adeiso = new LatLng(5.795337, -0.483098);
        googleMap.addMarker(new MarkerOptions().position(adeiso)
                .title("Crime Hotspot!curfew"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(adeiso));
        //
        LatLng amanase = new LatLng(5.994448, -0.421218);
        googleMap.addMarker(new MarkerOptions().position(amanase)
                .title("Crime Hotspot!witch camps"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(amanase));
        //
        LatLng nsawam = new LatLng(5.809405, -0.346391);
        googleMap.addMarker(new MarkerOptions().position(nsawam)
                .title("Crime Hotspot!Road tacks"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(nsawam));
        //


        if (mGoogleMap != null){

            mGoogleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    MainActivity.this.setMarker("local", latLng.latitude, latLng.longitude);
                }
            });


            mGoogleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(Marker marker) {

                }

                @Override
                public void onMarkerDrag(Marker marker) {

                }

                @Override
                public void onMarkerDragEnd(Marker marker) {

                    //geolocation plugin
                    Geocoder gc = new Geocoder(MainActivity.this);
                    LatLng ll = marker.getPosition();
                    double lat = ll.latitude;
                    double lng = ll.longitude;
                    List<Address> list = null;
                    try {
                        list = gc.getFromLocation(lat, lng, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Address add = list.get(0);
                    marker.setTitle(add.getLocality());
                    marker.showInfoWindow();
                }
            });

            mGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View v = getLayoutInflater().inflate(R.layout.info_window, null);

                    //converts layout items to realted elements
                    TextView tvLocality = v.findViewById(R.id.tv_locality);
                    TextView tvLat = v.findViewById(R.id.tv_lat);
                    TextView tvLng = v.findViewById(R.id.tv_lng);
                    TextView tvSnippet = v.findViewById(R.id.tv_snippet);

                    LatLng ll = marker.getPosition();
                    tvLocality.setText(marker.getTitle());
                    tvLat.setText("Latitude: " + ll.latitude);
                    tvLng.setText("Longitude: " + ll.longitude);
                    tvSnippet.setText(marker.getSnippet());

                    return v;
                }
            });
        }

            //go to location n with the ability to zoom in
         goToLocationZoom(5.626631,-0.2726412 , 15);


        //USER location find

        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        //    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        //    return;
        //   }
        //   }
        //  mGoogleMap.setMyLocationEnabled(true);

  //      mGoogleApiClient = new GoogleApiClient.Builder(this)
   //             .addApi(LocationServices.API)
   //             .addConnectionCallbacks(this)
   //             .addOnConnectionFailedListener(this)
    //            .build();

    //    mGoogleApiClient.connect();
    }

    private void goToLocation(double lat, double lng) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        mGoogleMap.moveCamera(update);
    }

    private void goToLocationZoom(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = (CameraUpdate) CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMap.moveCamera(update);


    }

    //marker class
    Marker marker;

    public void geoLocate() throws IOException {
        EditText et = findViewById(R.id.editLocation);
        String location = et.getText().toString();

        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(location, 1);
        Address address = list.get(0);
        String locality = address.getLocality();

        Toast.makeText(this, locality, Toast.LENGTH_LONG).show();

        double lat = address.getLatitude();
        double lng = address.getLongitude();
        goToLocationZoom(lat, lng, 15);
        setMarker(locality, lat, lng);

    }

  Circle circle;
    Marker marker1;
    Marker marker2;
    Polyline line;



    private void setMarker(String locality, double lat, double lng) {
        //marker options

      if (marker != null){
           removeEverything();
        }


        MarkerOptions options = new MarkerOptions()
                                .title(locality)
                                .draggable(true)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                                .position(new LatLng(lat, lng))
                                .snippet("Save zone!!");





        marker = mGoogleMap.addMarker(options);

        //marker lines
        if (marker1 == null){
            marker1 = mGoogleMap.addMarker(options);
        }else if (marker2 == null){
            marker2 = mGoogleMap.addMarker(options);
            drawLine();
        } else {
            removeEverything();
            marker1 = mGoogleMap.addMarker(options);
        }

     circle = drawCircle(new LatLng(lat, lng));
    }


    private void drawLine() {
        PolylineOptions options = new PolylineOptions()
                        .add(marker1.getPosition())
                        .add(marker2.getPosition())
                        .color(Color.CYAN)
                        .width(3);

        line = mGoogleMap.addPolyline(options);
    }

       private Circle drawCircle(LatLng latLng) {

        CircleOptions options = new CircleOptions()
                                .center(latLng)
                                .radius(1000)
                                .fillColor(0x3380a0bb)
                                .strokeColor(Color.BLUE)
                                .strokeWidth(3);

        return mGoogleMap.addCircle(options);
    }

    private void removeEverything(){
//        marker1.remove();
//       marker1 = null;
//        marker2.remove();
//        marker2 = null;
//        line.remove();
//        circle.remove();
//        circle = null;
    }

    //menu methods

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            //menu for types of maps
            case R.id.mapTypeNone:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;

            case R.id.mapTypeNormal:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;

            case R.id.mapTypeSatellite:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;

            case R.id.mapTypeTerrain:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;

            case R.id.mapTypeHybrid:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    LocationRequest mLocationRequest;

    @Override
    public void onConnected(@Nullable Bundle bundle) {
            //get location every sec from phone gps
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);


            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }


        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onLocationChanged(Location location) {
        //for zoom map movement
        if (location == null){
            Toast.makeText(this, "Cant get current location", Toast.LENGTH_LONG).show();
        }else {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, 15);
            mGoogleMap.animateCamera(update);
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
