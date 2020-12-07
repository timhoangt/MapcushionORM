package com.fdflib.example.model;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

public class History extends CommonState {

    public Integer userId;
    public String description = "";

    public History() {
        super();
    }

}
