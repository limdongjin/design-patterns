package com.dongjin.oad.util;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface Action {
    ArrayList run(ResultSet resultSet) throws Exception;
}
