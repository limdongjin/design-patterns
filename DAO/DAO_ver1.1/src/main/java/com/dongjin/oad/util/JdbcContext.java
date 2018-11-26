package com.dongjin.oad.util;

import org.jetbrains.annotations.NotNull;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JdbcContext {
    private final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * @return Connection
     * @throws SQLException, if connect fail
     */
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * @param query ( ex, "select * from products" )
     * @param action for manipulate resultSet
     * @return ArrayList
     */
    @NotNull
    public ArrayList executeSql(final String query, @NotNull Action action) throws SQLException {
        return processStatement(connection -> connection.prepareStatement(query), action);
    }

    /**
     * @param query
     * @return
     * @throws SQLException
     */
    public boolean executeSql(final String query) throws SQLException{
        return processStatement(connection -> connection.prepareStatement(query));
    }

    /**
     * @return result
     * @throws SQLException
     */
    @NotNull
    private ArrayList processStatement(@NotNull StatementStrategy statementStrategy, Action action) throws SQLException {
        ResultSet resultSet;

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = statementStrategy.makeStatement(connection)) {

            resultSet = preparedStatement.executeQuery();
            return action.run(resultSet);
        }
    }

    /**
     * @param statementStrategy
     * @return
     * @throws SQLException
     */
    private boolean processStatement(@NotNull StatementStrategy statementStrategy) throws SQLException {
        int result;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = statementStrategy.makeStatement(connection)) {

            result = preparedStatement.executeUpdate();
            return result != 0;
        }
    }

}
