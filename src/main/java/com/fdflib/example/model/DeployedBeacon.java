package com.fdflib.example.model;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

public class DeployedBeacon extends CommonState {

    public Long floorId;
    public Integer UUID;
    public Integer majorId;
    public Integer minorId;
    public Float lat;
    public Float lon;
    public Float alt;

    public DeployedBeacon() {
        super();
    }

}
