package com.dataiku.millennium.cli.db;

import com.dataiku.millennium.cli.util.PathResolver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnector {
    private static final Logger LOG = Logger.getLogger(DatabaseConnector.class.getName());
    private static final String JDBC_PREFIX = "jdbc:sqlite:";

    public static Connection connectToDatabase(String dbPath, String referencePath) throws SQLException {
        String resolvedPath = PathResolver.resolveDatabasePath(dbPath, referencePath);
        String url = JDBC_PREFIX + resolvedPath;
        Connection connection = DriverManager.getConnection(url);
        LOG.info("Connection to database successful: " +  url);
        return connection;
    }
}
