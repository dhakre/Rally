package fr.uha.sumitradhakre.rally.Activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import fr.uha.sumitradhakre.rally.MapPointClasses.ParcelableMapList;
import fr.uha.sumitradhakre.rally.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button showAdminScreen, showAtomaticScreen, showCusstomScreen;
    ArrayList<ParcelableMapList> parcelableMapLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //recieve data from admin
        parcelableMapLists = getIntent().getParcelableArrayListExtra("mapPointsList");
        showAdminScreen = (Button) findViewById(R.id.button3);
        showAdminScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        showAtomaticScreen = (Button) findViewById(R.id.button4);
        showAtomaticScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, AtomaticActivity.class);
                intent.putParcelableArrayListExtra("mapPointsList", parcelableMapLists);
                startActivity(intent);
            }
        });

        showCusstomScreen = (Button) findViewById(R.id.button2);
        showCusstomScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, CustomActivity.class);
                intent.putParcelableArrayListExtra("mapPointsList", parcelableMapLists);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Add marker to paris
        LatLng pari = new LatLng(48.860834, 2.294308);
        mMap.addMarker(new MarkerOptions().position(pari).title("Marker in paris"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pari, 10));
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
        mMap.setMyLocationEnabled(true); // show user location

           mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

                @Override
                public void onMapClick(LatLng point) {
                    mMap.addMarker(new MarkerOptions().position(point));
                }


            });

    }
}
