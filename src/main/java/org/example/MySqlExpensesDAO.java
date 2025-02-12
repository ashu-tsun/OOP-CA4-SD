package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlExpensesDAO extends MySqlDAO implements ExpenseDAOInterface {
        /**
         * Will access and return a List of all users in User database table
         * @return List of task objects
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


                String query = "SELECT * FROM expenses";
                preparedStatement = connection.prepareStatement(query);

                //Using a PreparedStatement to execute SQL...
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int expenseId = resultSet.getInt("expenseId");
                    String title = resultSet.getString("title");
                    String category = resultSet.getString("category");
                    Double amount = resultSet.getDouble("amount");
                    String dateIncurred = resultSet.getString("dateIncurred");
                    Expense u = new Expense(expenseId, title, category, amount, dateIncurred);
                    expenseList.add(u);
                }
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
            return expenseList;     // may be empty
        }


    }



