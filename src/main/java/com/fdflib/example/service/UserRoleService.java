package com.fdflib.example.service;

import com.fdflib.example.model.UserRole;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;

public class UserRoleService extends FdfCommonServices {

    public UserRole saveUserRole(UserRole userrole) {
        if(userrole != null) {
            return this.save(UserRole.class, userrole).current;
        }
        return null;
    }

    public UserRole getUserRoleById(long id) {
        return getUserRoleWithHistoryById(id).current;

    }

    public FdfEntity<UserRole> getUserRoleWithHistoryById(long id) {
        FdfEntity<UserRole> userrole = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            userrole = this.getEntityById(UserRole.class, id);
        }

        return userrole;
    }
}