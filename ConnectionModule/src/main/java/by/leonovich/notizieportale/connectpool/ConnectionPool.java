package by.leonovich.notizieportale.connectpool;

import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alexanderleonovich on 30.04.15.
 * Connection pool class
 */
public class ConnectionPool {

    //private InitialContext ic;

    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

   /* @Resource(name = "jdbc/mysitetonight", description = "DBConnection",
            authenticationType = Resource.AuthenticationType.CONTAINER,
            type = javax.sql.DataSource.class,
            mappedName = "jdbc/mysitetonight") */    // JNDI reference to DataSource
    private DataSource dataSource;

    public ConnectionPool() {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://localhost:3306/mysitetonight");
        p.setDriverClassName("com.mysql.jdbc.Driver");
        p.setUsername("root");
        p.setPassword("21021991");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        dataSource = new DataSource();
        dataSource.setPoolProperties(p);
    }

    /**
     * Method for get Connection from pool
     *
     * @return Connection connection
     */
   /* public Connection getConnection() {
        try {
            ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/mysitetonight");
            return dataSource.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }*/

    public Connection getConnection() {
        try {
            Connection connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            if (!connection.isClosed()) {
                return connection;
            } else {
                logger.error("Error getting connection! Connection is not open!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method for put connection in pool
     * @param connection connection, what put in pool
     */
    public static void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    /**
     * Method for put connection in pool
     * @param resultSet ResultSet object for close
     * @param connection connection, what put in pool
     */
    public static void releaseConnection(Connection connection, ResultSet resultSet) {
        try {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
        } finally {
            if (connection != null) {
                try {
                    connection.close();} catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
    }

}
