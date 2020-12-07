package com.fdflib.example.service;

import com.fdflib.example.model.User;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;

public class UserService extends FdfCommonServices {

    public User saveUser(User user) {
        if(user != null) {
            return this.save(User.class, user).current;
        }
        return null;
    }

    public User getUserById(long id) {
        return getUserWithHistoryById(id).current;

    }

    public FdfEntity<User> getUserWithHistoryById(long id) {
        FdfEntity<User> user = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            user = this.getEntityById(User.class, id);
        }

        return user;
    }
}