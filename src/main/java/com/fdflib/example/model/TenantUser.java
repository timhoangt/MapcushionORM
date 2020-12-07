package com.fdflib.example.model;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

public class TenantUser extends CommonState {

    public Long tenantId;
    public Long userId;
    public String color = "";

    public TenantUser() {
        super();
    }

}
