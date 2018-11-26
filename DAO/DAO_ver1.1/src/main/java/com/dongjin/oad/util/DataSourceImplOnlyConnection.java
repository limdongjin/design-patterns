package com.dongjin.oad.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Logger;

public class DataSourceImplOnlyConnection implements DataSource {
    /**
     * <p>Attempts to establish a connection with the data source that
     * this {@code DataSource} object represents.
     */
    private Connection connection;
    @NotNull
    private final String url;
    @Nullable
    private final String id;
    @Nullable
    private final String pw;

    public DataSourceImplOnlyConnection(@NotNull String url, String id, String pw) throws SQLException{
        this.url = url;
        this.id = id;
        this.pw = pw;

        setConnection(url, id, pw);
    }

    public DataSourceImplOnlyConnection(@NotNull String url) throws SQLException {
        this.url = url;
        this.id = null;
        this.pw = null;

        setConnection(url);
    }


    private void setConnection(@NotNull String url, String id, String pw) throws SQLException {
        try {
            connection = DriverManager.getConnection(url, id, pw);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    private void setConnection() throws SQLException {
        // re connection
        if(this.id == null){
            setConnection(this.url);
        }else{
            setConnection(this.url, this.id, this.pw);
        }
    }

    private void setConnection(@NotNull String url) throws SQLException {
        try {
            connection = DriverManager.getConnection(url);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if(connection.isClosed()){
            // re connect
            setConnection();
        }
        return connection;
    }

    /**
     * <p>Attempts to establish a connection with the data source that
     * this {@code DataSource} object represents.
     *
     * @param username the database user on whose behalf the connection is
     *                 being made
     * @param password the user's password
     * @return a connection to the data source
     * @since 1.4
     */
    @Nullable
    @Override
    public Connection getConnection(String username, String password) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.4
     */
    @Nullable
    @Override
    public PrintWriter getLogWriter() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param out
     * @since 1.4
     */
    @Override
    public void setLogWriter(PrintWriter out) {

    }

    /**
     * {@inheritDoc}
     *
     * @param seconds
     * @since 1.4
     */
    @Override
    public void setLoginTimeout(int seconds) {

    }

    /**
     * {@inheritDoc}
     *
     * @since 1.4
     */
    @Override
    public int getLoginTimeout() {
        return 0;
    }

    /**
     * Returns an object that implements the given interface to allow access to
     * non-standard methods, or standard methods not exposed by the proxy.
     * <p>
     * If the receiver implements the interface then the result is the receiver
     * or a proxy for the receiver. If the receiver is a wrapper
     * and the wrapped object implements the interface then the result is the
     * wrapped object or a proxy for the wrapped object. Otherwise return the
     * the result of calling <code>unwrap</code> recursively on the wrapped object
     * or a proxy for that result. If the receiver is not a
     * wrapper and does not implement the interface, then an <code>SQLException</code> is thrown.
     *
     * @param iface A Class defining an interface that the result must implement.
     * @return an object that implements the interface. May be a proxy for the actual implementing object.
     * @since 1.6
     */
    @Nullable
    @Override
    public <T> T unwrap(Class<T> iface) {
        return null;
    }

    /**
     * Returns true if this either implements the interface argument or is directly or indirectly a wrapper
     * for an object that does. Returns false otherwise. If this implements the interface then return true,
     * else if this is a wrapper then return the result of recursively calling <code>isWrapperFor</code> on the wrapped
     * object. If this does not implement the interface and is not a wrapper, return false.
     * This method should be implemented as a low-cost operation compared to <code>unwrap</code> so that
     * callers can use this method to avoid expensive <code>unwrap</code> calls that may fail. If this method
     * returns true then calling <code>unwrap</code> with the same argument should succeed.
     *
     * @param iface a Class defining an interface.
     * @return true if this implements the interface or directly or indirectly wraps an object that does.
     * @since 1.6
     */
    @Override
    public boolean isWrapperFor(Class<?> iface) {
        return false;
    }

    /**
     * Return the parent Logger of all the Loggers used by this data source. This
     * should be the Logger farthest from the root Logger that is
     * still an ancestor of all of the Loggers used by this data source. Configuring
     * this Logger will affect all of the log messages generated by the data source.
     * In the worst case, this may be the root Logger.
     *
     * @return the parent Logger for this data source
     * @since 1.7
     */
    @Nullable
    @Override
    public Logger getParentLogger() {
        return null;
    }
}
