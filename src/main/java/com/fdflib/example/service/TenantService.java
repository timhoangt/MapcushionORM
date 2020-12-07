package com.fdflib.example.service;

import com.fdflib.example.model.Tenant;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;

public class TenantService extends FdfCommonServices {

    public Tenant saveTenant(Tenant tenant) {
        if(tenant != null) {
            return this.save(Tenant.class, tenant).current;
        }
        return null;
    }

    public Tenant getTenantById(long id) {
        return getTenantWithHistoryById(id).current;

    }

    public FdfEntity<Tenant> getTenantWithHistoryById(long id) {
        FdfEntity<Tenant> tenant = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            tenant = this.getEntityById(Tenant.class, id);
        }

        return tenant;
    }
}