package com.dongjin.oad.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JdbcContext {
    DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * @return connection
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    /**
     * @param query ( ex, "select * from products" )
     * @param action for manipulate resultSet
     * @return ArrayList
     * @throws Exception
     */
    public ArrayList executeSql(final String query, Action action) throws Exception{
        return processStatement(connection -> connection.prepareStatement(query), action);
    }

    /**
     * @param statementStrategyImpl
     * @return result
     * @throws SQLException
     */
    private ArrayList processStatement(StatementStrategy statementStrategy, Action action) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            resultSet = preparedStatement.executeQuery();
            return action.run(resultSet);
        }catch (SQLException e){
            throw e;
        }finally {
            if(preparedStatement != null) {  try{ preparedStatement.close(); }catch (SQLException e) {} }
            if(connection != null) {  try{ connection.close(); }catch (SQLException e) {} }
        }
    }


}
