package com.fdflib.example.model;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

public class Destination extends CommonState {

    public Long historyId;
    public Long tenantId;
    public State state;
    public Float lat;
    public Float lon;
    public Float alt;
    public String time;

    public Destination() {
        super();
    }

}
