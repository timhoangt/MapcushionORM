package com.fdflib.example.service;

import com.fdflib.example.model.Floor;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;

public class FloorService extends FdfCommonServices {

    public Floor saveFloor(Floor floor) {
        if(floor != null) {
            return this.save(Floor.class, floor).current;
        }
        return null;
    }

    public Floor getFloorById(long id) {
        return getFloorWithHistoryById(id).current;

    }

    public FdfEntity<Floor> getFloorWithHistoryById(long id) {
        FdfEntity<Floor> floor = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            floor = this.getEntityById(Floor.class, id);
        }

        return floor;
    }
}