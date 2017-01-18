package fr.uha.sumitradhakre.rally.MapPointClasses;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.uha.sumitradhakre.rally.MapPointClasses.MapPoints;

public class MapPointsList implements Serializable {
    private List<MapPoints> mapPointsList;

    public List<MapPoints> getMapPointsList() {
       // System.out.println("hello");
        if (mapPointsList == null) mapPointsList = new ArrayList<>();
        if(mapPointsList!=null) return mapPointsList;
        return mapPointsList;
    }
    public void addPointsToList(MapPoints point)
    {
        //mapPointsList.add(MapPoints point);
    }

    public void populate() {
        LatLng p = new LatLng(48.860834, 2.294308);
        getMapPointsList().add(new MapPoints(p));
    }


}
