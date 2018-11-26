package com.dongjin.oad.util;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

interface StatementStrategy {
    @NotNull PreparedStatement makeStatement(Connection connection) throws SQLException;
}