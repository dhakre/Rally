package fr.uha.sumitradhakre.rally.Activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import fr.uha.sumitradhakre.rally.MapPointClasses.MapPoints;
import fr.uha.sumitradhakre.rally.MapPointClasses.MapPointsList;
import fr.uha.sumitradhakre.rally.MapPointClasses.ParcelableMapList;
import fr.uha.sumitradhakre.rally.R;

public class AdminActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap aMap;
    private final String TAG = this.getClass().getSimpleName();
    int count = 0, i = 0, size = 0;
    private SupportMapFragment mapFragment;
    MapPointsList mapPointsList = new MapPointsList(); //intionalising the list
    PolylineOptions polylineOptions = new PolylineOptions();
    LatLng start, destination;
    ArrayList<ParcelableMapList> pointsExtra;
    MapPoints Mp;
    boolean status;
    private List<MapPoints> mapPoints = new ArrayList<MapPoints>(); //new list of MapPoints
    boolean check;
    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        doneButton=(Button)findViewById(R.id.button5);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if ((mapPointsList != null) && (pointsExtra != null)) {
                    Intent intent = new Intent(AdminActivity.this,AtomaticActivity.class);
                    Log.v("Admin",pointsExtra+":");
                    intent.putParcelableArrayListExtra("mapPointsList", pointsExtra);
                    startActivity(intent);
                }
                if ((mapPointsList != null) && (pointsExtra != null)) {
                    Intent i = new Intent(AdminActivity.this,MapsActivity.class);
                    Log.v("Admin",pointsExtra+":");
                    i.putParcelableArrayListExtra("mapPointsList", pointsExtra);
                    startActivity(i);
                }
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        aMap = googleMap;
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
        aMap.setMyLocationEnabled(true);
        aMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(@NonNull LatLng point) {
                Log.d(TAG, point.toString());
                polylineOptions.color(Color.BLUE);
                polylineOptions.width(5);
                Mp = new MapPoints(point);
                mapPointsList.getMapPointsList().add(new MapPoints(point));
                mapPoints.add(Mp); // add points to list
                // pointsExtra.add(new ParcelableMapList(Mp)); //put data in parcalable
                if (check == true) {
                    destination = point;
                }
                polylineOptions.add(point);
                if (count == 1) {
                    start = point;
                }
                aMap.addPolyline(polylineOptions);
                //logic for changing marker colours
                if (count == 0) {
                    aMap.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    count++;
                } else if (count > 5) {
                    aMap.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                    count++;
                } else {
                    aMap.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
                    count++;
                }
                pointsExtra = new ArrayList<ParcelableMapList>(); // creating list for list transfer
                for (MapPoints point1 : mapPoints) {
                    pointsExtra.add(new ParcelableMapList(point1));
                }

            }

        });

    }
}
