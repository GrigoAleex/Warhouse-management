package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Log;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Order;

public class LogDAO extends ModelDAO<Log> {
    @Override
    protected String tableName () {
        return "logs";
    }

    @Override
    protected Class<Log> model () {
        return Log.class;
    }
}
