package com.example.isiahlibor.finals;

import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public Double latitudefrom,latitudeto,longitudefrom,longitudeto;
    TextView txtDistance;
    EditText txtFromAddress;
EditText txtToAddress;
LocationManager lmanger;
Button btnDrawRoute;
private GoogleMap mMap;
public LatLng locationSimula = new LatLng(28.6139,77.2090);
public LatLng locationDestinasyon = new LatLng(30.7333,76.7794);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        txtFromAddress=(EditText) findViewById(R.id.editTextFrom);
        txtToAddress=(EditText) findViewById(R.id.editTextTo);
        btnDrawRoute =(Button)findViewById(R.id.buttonDrawRoute);

    }


        private void btnDrawRoutelistener() {

        btnDrawRoute.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        try {

        } catch (Exception e) {
        Toast.makeText(getBaseContext(), txtFromAddress.getText()+" not part of google Map Databaes",Toast.LENGTH_LONG).show();
        return;

        }
        try {
        latitudeto=getlatitudefrom(txtToAddress.getText().toString(),0);

        } catch (Exception e) {
        Toast.makeText(getBaseContext(), txtToAddress.getText()+" not part of google Map Databaes",Toast.LENGTH_LONG).show();
        return;
        }
        try {
        longitudefrom=getlatitudefrom(txtFromAddress.getText().toString(),1);
        } catch (Exception e) {
        return;
        }
        try {

        } catch (Exception e) {
        return;
        }

        locationSimula =
        locationDestinasyon
            mMap.clear();

        showMapMarker();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(locationSimula));

        txtDistance.setText("Distance between these two location is : "+distance +" miles");
        }
        });
        }

        private void showMapMarker() {

        Marker sourceMarker = mMap.addMarker(new MarkerOptions()
        .position(locationSimula)
        .title("Origin")
        .snippet(txtFromAddress.getText().toString())
        .icon(BitmapDescriptorFactory
        .fromResource(R.drawable.source)));

        sourceMarker.showInfoWindow();

        Marker destinationMarker = mMap.addMarker(new MarkerOptions()
        .position(locationDestinasyon)
        .title("Destination")
        .snippet(txtToAddress.getText().toString())
        .icon(BitmapDescriptorFactory
        .fromResource(R.drawable.destination)));
        destinationMarker.showInfoWindow();
        }

        private double getlatitudefrom(String addStr, int i) {

        // TODO Auto-generated method stub
        Geocoder coder = new Geocoder(this, Locale.ENGLISH);
        List<Address> address;

        try {
                  address = coder.getFromLocationName(addStr, 1);
                  if (address == null) {
                      return 28.6139;
                  }
                  Address location = address.get(0);
                  if (i==0) {

                  } else {

                  }

              } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              }

              return 0;
          }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
