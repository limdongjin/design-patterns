package com.dongjin.oad.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Action {
    ArrayList run(ResultSet resultSet) throws SQLException, IllegalAccessException;
}
