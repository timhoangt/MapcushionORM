package com.fdflib.example.model;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

public class Floor extends CommonState {

    public Long userId;
    public Long locationId;
    public Long tenantId;
    public String name = "";
    public String description = "";
    public Float maxLat;
    public Float maxLong;
    public Float maxAlt;
    public Float minLat;
    public Float minLong;
    public Float minAlt;

    public Floor() {
        super();
    }

}
