package org.example.DAOs;
import org.example.Exception.DAOException;
import org.example.DTOs.Income;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlIncomeDAO extends MySqlDAO implements IncomeDAOInterface {
    /**
     * Will access and return a List of all users in User database table
     * @return List of task objects
     * @throws DAOException
     */
    @Override
    public List<Income> findAllIncome() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Income> incomeList = new ArrayList<>();

        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();


            String query = "SELECT * FROM income";
            preparedStatement = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int incomeID = resultSet.getInt("incomeID");
                String title = resultSet.getString("title");
                double amount = resultSet.getDouble("amount");
                String dateIncurred = resultSet.getString("dateIncurred");
                Income u = new Income(incomeID, title, amount, dateIncurred);
                incomeList.add(u);
            }
        } catch (SQLException e) {
            throw new DAOException("findAllIncomeResultSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e){
                throw new DAOException("FindAllIncome() " + e.getMessage());
            }
        }
        return incomeList;     // may be empty
    }


    /**
     * Will access and return a total of all income
     *
     * @return total of amounts
     * @throws DAOException
     */
    @Override
    public double findTotalIncome() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        double total = 0.0;

        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            //Getting a total from database
            String query = "SELECT SUM(amount) AS Total FROM income";

            //Returning a total
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            //Accessing the total
            while (resultSet.next()) {
                total = resultSet.getDouble("Total");
            }



        } catch (SQLException e) {
            throw new DAOException("findTotal() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e){
                throw new DAOException("FindTotal() " + e.getMessage());
            }
        }

        return total;
    }

    /**
     * Will access and add an income
     *
     * @return void
     * @throws DAOException
     */
    @Override
    public void addIncome(String title, double amount, String date) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            //Creating structure for added income
            String query = "INSERT INTO income VALUES (null,?,?,?,?)";

            preparedStatement = connection.prepareStatement(query);

            //Setting the statement parameters to the values given in method
            preparedStatement.setString(1, title);
            preparedStatement.setDouble(3,amount);
            preparedStatement.setDate(4, Date.valueOf(date));
            //Updating database
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new DAOException("addIncome() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e){
                throw new DAOException("addIncome() " + e.getMessage());
            }
        }
    }

    /**
     * Will access and delete an income
     *
     * @return void
     * @throws DAOException
     */
    @Override
    public void deleteIncome(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            //Preparing delete statement
            String query = "DELETE FROM income WHERE incomeId = ?";

            preparedStatement = connection.prepareStatement(query);
            //Setting the id in prepared query to the id given
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new DAOException("deleteIncome() " + e.getMessage());
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e){
                throw new DAOException("deleteIncome() " + e.getMessage());
            }
        }
    }

    /**
     * Will access and retrieve total income for specified month
     *
     * @return total
     * @throws DAOException
     */
    @Override
    public double findAllIncomeMonth(int month) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        double total = 0.0;

        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            //Preparing query
            //Getting a total from database for the month specified
            String query = "SELECT SUM(amount) AS Total FROM income WHERE MONTH(dateIncurred) = ?";

            preparedStatement = connection.prepareStatement(query);
            //Inserting month
            preparedStatement.setInt(1, month);

            resultSet = preparedStatement.executeQuery();
            //Accessing the total
            while (resultSet.next()) {
                total = resultSet.getDouble("Total");
            }


        } catch (SQLException e) {
            throw new DAOException("findAllIncomeMonth() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e){
                throw new DAOException("findAllIncomeMonth() " + e.getMessage());
            }
        }

        return total;
    }

}
