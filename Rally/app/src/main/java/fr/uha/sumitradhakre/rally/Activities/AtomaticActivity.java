package fr.uha.sumitradhakre.rally.Activities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import fr.uha.sumitradhakre.rally.MapPointClasses.MapPoints;
import fr.uha.sumitradhakre.rally.MapPointClasses.MapPointsList;
import fr.uha.sumitradhakre.rally.MapPointClasses.ParcelableMapList;
import fr.uha.sumitradhakre.rally.R;

public class AtomaticActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap aMap;
    LatLng point = new LatLng(48.860834, 2.294308);
    private final String TAG = this.getClass().getSimpleName();
    int i = 0, size = 0;
    ArrayList<ParcelableMapList> parcelableMapLists;
    MapPointsList mapPointsList;
    ArrayList<MapPoints> points = new ArrayList<>();
    PolylineOptions polylineOptions = new PolylineOptions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atomatic);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapPointsList = new MapPointsList();
        parcelableMapLists = getIntent().getParcelableArrayListExtra("mapPointsList");
        if (parcelableMapLists == null) {
            System.out.print("hello");
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        aMap = googleMap;
        polylineOptions.color(Color.BLUE);
        polylineOptions.width(5);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//           ActivityCompat.requestPermissions(this,);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        aMap.setMyLocationEnabled(true);

        if (parcelableMapLists != null) {
            for (ParcelableMapList mapPoint : parcelableMapLists) {
                if (mapPoint == null) {
                    //Do nothing
                } else {
                    points.add(mapPoint.getMapPoints());
                }
            }

            size = points.size();
            for (int j = 0; j < size; j++) {
                MapPoints p1 = points.get(j);
                double a, b;
                a = p1.getLatitude();
                b = p1.getLongitude();
                LatLng p = new LatLng(a, b);
                polylineOptions.add(p);
                aMap.addPolyline(polylineOptions);
                if(j==0)
                aMap.addMarker(new MarkerOptions().position(p).title("Start position").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                else if(j==size-1)
                    aMap.addMarker(new MarkerOptions().position(p).title("Destination").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                else
                    aMap.addMarker(new MarkerOptions().position(p).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            }
        }


    }
}

