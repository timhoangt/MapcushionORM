package com.fdflib.example.model;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

public class Queue extends CommonState {

    public Long userId;
    public Integer size;

    public Queue() {
        super();
    }

}
