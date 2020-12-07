package com.fdflib.example.model;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

public class Location extends CommonState {

    public Long userId;
    public Long tenantId;
    public String name = "";
    public String description = "";
    public Float maxLat;
    public Float maxLong;
    public Float minLat;
    public Float minLong;

    public Location() {
        super();
    }

}
