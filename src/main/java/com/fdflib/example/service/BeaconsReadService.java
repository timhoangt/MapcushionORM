package com.fdflib.example.service;

import com.fdflib.example.model.BeaconsRead;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;

public class BeaconsReadService extends FdfCommonServices {

    public BeaconsRead saveBeaconsRead(BeaconsRead beaconsread) {
        if(beaconsread != null) {
            return this.save(BeaconsRead.class, beaconsread).current;
        }
        return null;
    }

    public BeaconsRead getBeaconsReadById(long id) {
        return getBeaconsReadWithHistoryById(id).current;

    }

    public FdfEntity<BeaconsRead> getBeaconsReadWithHistoryById(long id) {
        FdfEntity<BeaconsRead> beaconsread = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            beaconsread = this.getEntityById(BeaconsRead.class, id);
        }

        return beaconsread;
    }
}