package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.User;

public class UserDAO extends ModelDAO<User> {

    @Override
    protected String tableName () {
        return "users";
    }

    @Override
    protected Class<User> model () {
        return User.class;
    }

}
