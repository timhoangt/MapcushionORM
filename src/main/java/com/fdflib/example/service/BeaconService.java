package com.fdflib.example.service;

import com.fdflib.example.model.Beacon;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;

public class BeaconService extends FdfCommonServices {

    public Beacon saveBeacon(Beacon beacon) {
        if(beacon != null) {
            return this.save(Beacon.class, beacon).current;
        }
        return null;
    }

    public Beacon getBeaconById(long id) {
        return getBeaconWithHistoryById(id).current;

    }

    public FdfEntity<Beacon> getBeaconWithHistoryById(long id) {
        FdfEntity<Beacon> beacon = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            beacon = this.getEntityById(Beacon.class, id);
        }

        return beacon;
    }
}