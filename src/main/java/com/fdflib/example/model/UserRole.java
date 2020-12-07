package com.fdflib.example.model;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

public class UserRole extends CommonState {

    public Long userId;
    public Role roleName;
    public Boolean isActive;

    public UserRole() {
        super();
    }

}
