package com.fdflib.example.model;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

public class DestinationQueue extends CommonState {

    public Integer queueId;
    public Integer destinationId;

    public DestinationQueue() {
        super();
    }

}
