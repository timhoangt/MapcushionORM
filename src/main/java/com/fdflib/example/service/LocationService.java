package com.fdflib.example.service;

import com.fdflib.example.model.Location;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;

public class LocationService extends FdfCommonServices {

    public Location saveLocation(Location location) {
        if(location != null) {
            return this.save(Location.class, location).current;
        }
        return null;
    }

    public Location getLocationById(long id) {
        return getLocationWithHistoryById(id).current;

    }

    public FdfEntity<Location> getLocationWithHistoryById(long id) {
        FdfEntity<Location> location = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            location = this.getEntityById(Location.class, id);
        }

        return location;
    }
}