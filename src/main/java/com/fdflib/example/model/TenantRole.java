package com.fdflib.example.model;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

public class TenantRole extends CommonState {

    public Long tenantId;
    public Role roleName;
    public Boolean isActive;

    public TenantRole() {
        super();
    }

}
