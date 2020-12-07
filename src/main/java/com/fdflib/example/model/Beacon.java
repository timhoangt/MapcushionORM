package com.fdflib.example.model;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

public class Beacon extends CommonState {

    public Integer UUID;
    public Integer majorId;
    public Integer minorId;
    public Long tenantId;
    public Integer TXPower;
    public Double Accuracy;

    public Beacon() {
        super();
    }

}
