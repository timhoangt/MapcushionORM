package com.fdflib.example.model;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

public class DestinationState extends CommonState {

    public Integer destinationId;
    public Integer stateId;
    public Boolean isActive;

    @FdfIgnore
    public DestinationState currentDriver = null;

    public DestinationState() {
        super();
    }

}
