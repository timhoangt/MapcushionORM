package com.fdflib.example.service;

import com.fdflib.example.model.TenantRole;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;

public class TenantRoleService extends FdfCommonServices {

    public TenantRole saveTenantRole(TenantRole tenantrole) {
        if(tenantrole != null) {
            return this.save(TenantRole.class, tenantrole).current;
        }
        return null;
    }

    public TenantRole getTenantRoleById(long id) {
        return getTenantRoleWithHistoryById(id).current;

    }

    public FdfEntity<TenantRole> getTenantRoleWithHistoryById(long id) {
        FdfEntity<TenantRole> tenantrole = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            tenantrole = this.getEntityById(TenantRole.class, id);
        }

        return tenantrole;
    }
}