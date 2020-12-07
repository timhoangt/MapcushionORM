package com.fdflib.example.model;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

public class BeaconsRead extends CommonState {

    public Long deviceId;
    public Integer UUID;
    public Integer majorId;
    public Integer minorId;
    public Integer RSSI;
    public Integer remote_time;

    public BeaconsRead() {
        super();
    }

}
