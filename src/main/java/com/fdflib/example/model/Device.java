package com.fdflib.example.model;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

public class Device extends CommonState {

    public Long userId;
    public Long destinationId; 

    public Device() {
        super();
    }

}
