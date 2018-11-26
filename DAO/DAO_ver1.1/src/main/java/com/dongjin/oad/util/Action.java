package com.dongjin.oad.util;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Action {
    @NotNull ArrayList run(ResultSet resultSet) throws SQLException;
}
