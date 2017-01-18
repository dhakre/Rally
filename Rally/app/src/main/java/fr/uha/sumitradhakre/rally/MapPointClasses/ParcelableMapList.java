package fr.uha.sumitradhakre.rally.MapPointClasses;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class ParcelableMapList implements Parcelable {
    private MapPoints mapPoints;

    public ParcelableMapList(MapPoints mpoint) {//constructor
        mapPoints = mpoint;
    }
    public MapPoints getMapPoints() {
        return mapPoints;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeDouble(mapPoints.getLatitude());
        out.writeDouble(mapPoints.getLongitude());
    }

    protected ParcelableMapList(Parcel in) {
        double lat = in.readDouble();
        double lon = in.readDouble();
        LatLng point =new LatLng(lat,lon);
        mapPoints = new MapPoints(point);
    }

    public static final Parcelable.Creator<ParcelableMapList> CREATOR = new Parcelable.Creator<ParcelableMapList>() {
        @Override
        public ParcelableMapList createFromParcel(Parcel in) {
            return new ParcelableMapList(in);
        }

        @Override
        public ParcelableMapList[] newArray(int size) {
            return new ParcelableMapList[size];
        }
    };


}
