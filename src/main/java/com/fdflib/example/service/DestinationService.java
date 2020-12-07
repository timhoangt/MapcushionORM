package com.fdflib.example.service;

import com.fdflib.example.model.Destination;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;

public class DestinationService extends FdfCommonServices {

    public Destination saveDestination(Destination destination) {
        if(destination != null) {
            return this.save(Destination.class, destination).current;
        }
        return null;
    }

    public Destination getDestinationById(long id) {
        return getDestinationWithHistoryById(id).current;

    }

    public FdfEntity<Destination> getDestinationWithHistoryById(long id) {
        FdfEntity<Destination> destination = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            destination = this.getEntityById(Destination.class, id);
        }

        return destination;
    }
}