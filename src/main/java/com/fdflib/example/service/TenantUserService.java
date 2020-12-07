package com.fdflib.example.service;

import com.fdflib.example.model.TenantUser;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;

public class TenantUserService extends FdfCommonServices {

    public TenantUser saveTenantUser(TenantUser tenantuser) {
        if(tenantuser != null) {
            return this.save(TenantUser.class, tenantuser).current;
        }
        return null;
    }

    public TenantUser getTenantUserById(long id) {
        return getTenantUserWithHistoryById(id).current;

    }

    public FdfEntity<TenantUser> getTenantUserWithHistoryById(long id) {
        FdfEntity<TenantUser> tenantuser = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            tenantuser = this.getEntityById(TenantUser.class, id);
        }

        return tenantuser;
    }
}