package org.example.DAOs;

import org.example.Exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MySqlDAO {

        public Connection getConnection() throws DAOException
        {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/budget";
            String username = "root";
            String password = "";
            Connection connection = null;

            try
            {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            }
            catch (ClassNotFoundException e)
            {
                System.out.println("Failed to find driver class " + e.getMessage());
                System.exit(1);
            }
            catch (SQLException e)
            {
                System.out.println("Connection failed " + e.getMessage());
                System.exit(2);
            }
            return connection;
        }

        public void freeConnection(Connection connection) throws DAOException
        {
            try
            {
                if (connection != null)
                {
                    connection.close();
                    connection = null;
                }
            }
            catch (SQLException e)
            {
                System.out.println("Failed to free connection: " + e.getMessage());
                System.exit(1);
            }
        }


}
