package fr.uha.sumitradhakre.rally.MapPointClasses;


import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class MapPoints implements Serializable {
    private LatLng latLng;
    double latitude;
    double longitude;
    //boolean reached;

    public MapPoints(@NonNull LatLng point) {
        this.latLng = point;
        this.latitude = point.latitude;
        this.longitude = point.longitude;
    }


    public LatLng getLatLng()
    {
        return latLng;
    }
    public double getLatitude()
    {
        return latitude;
    }
    public double getLongitude()
    {
        return longitude;
    }


}
