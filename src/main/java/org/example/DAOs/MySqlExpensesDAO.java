package org.example.DAOs;

import org.example.Exception.DAOException;
import org.example.DTOs.Expense;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlExpensesDAO extends MySqlDAO implements ExpenseDAOInterface {
        /**
         * Will access and return a List of all expenses in the Expense table
         * @return List of expenses
         * @throws DAOException
         */
        @Override
        public List<Expense> findAllExpenses() throws DAOException {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            List<Expense> expenseList = new ArrayList<>();

            try {
                //Get connection object using the getConnection() method inherited
                // from the super class (MySqlDao.java)
                connection = this.getConnection();

                //Prepare query
                String query = "SELECT * FROM expenses";

                preparedStatement = connection.prepareStatement(query);
                //Using a PreparedStatement to execute SQL...
                resultSet = preparedStatement.executeQuery();
                //Taking in the data and assigning it to an Expense, then placing it in a list
                while (resultSet.next()) {
                    int expenseId = resultSet.getInt("expenseId");
                    String title = resultSet.getString("title");
                    String category = resultSet.getString("category");
                    double amount = resultSet.getDouble("amount");
                    String dateIncurred = resultSet.getString("dateIncurred");
                    Expense u = new Expense(expenseId, title, category, amount, dateIncurred);
                    expenseList.add(u);
                }
            //Exception handling
            } catch (SQLException e) {
                throw new DAOException("findAllExpensesResultSet() " + e.getMessage());
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
                    throw new DAOException("FindAllExpenses() " + e.getMessage());
                }
            }

            return expenseList;     // return the list
        }

    /**
     * Will access and return a total of all expenses
     *
     * @return total of amounts
     * @throws DAOException
     */
    @Override
    public double findTotalExpenses() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        double total = 0.0;

        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            //Getting a total from database
            String query = "SELECT SUM(amount) AS Total FROM expenses";

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
     * Will access and add an expense
     *
     * @return void
     * @throws DAOException
     */
    @Override
    public void addExpense(String title, String category, double amount, String date) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            //Creating structure for adding expense
            String query = "INSERT INTO expenses VALUES (null,?,?,?,?)";

            preparedStatement = connection.prepareStatement(query);

            //Setting the statement parameters to the values given in method
            preparedStatement.setString(1, title);
            preparedStatement.setString(2,category);
            preparedStatement.setDouble(3,amount);
            preparedStatement.setDate(4, Date.valueOf(date));
            //Updating database
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new DAOException("addExpense() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e){
                throw new DAOException("addExpense() " + e.getMessage());
            }
        }
    }

    /**
     * Will access and delete an expense
     *
     * @return void
     * @throws DAOException
     */
    @Override
    public void deleteExpense(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            //Preparing delete statement
            String query = "DELETE FROM expenses WHERE expenseId = ?";

            preparedStatement = connection.prepareStatement(query);
            //Setting the id in prepared query to the id given
            preparedStatement.setInt(1, id);
            //Updating the database
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new DAOException("deleteExpense() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e){
                throw new DAOException("deleteExpense() " + e.getMessage());
            }
        }
    }

    /**
     * Will access and retrieve total expenses for specified month
     *
     * @return total of expenses
     * @throws DAOException
     */
    @Override
    public double findAllExpensesMonth(int month) throws DAOException {
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
            String query = "SELECT SUM(amount) AS Total FROM expenses WHERE MONTH(dateIncurred) = ?";

            preparedStatement = connection.prepareStatement(query);
            //Inserting specified month
            preparedStatement.setInt(1, month);

            resultSet = preparedStatement.executeQuery();
            //Accessing the total
            while (resultSet.next()) {
                total = resultSet.getDouble("Total");
            }


        } catch (SQLException e) {
            throw new DAOException("findAllExpensesMonth() " + e.getMessage());
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
                throw new DAOException("findAllExpensesMonth() " + e.getMessage());
            }
        }

        return total;
    }


}



