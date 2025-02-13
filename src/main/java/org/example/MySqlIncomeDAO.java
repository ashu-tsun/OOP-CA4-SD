package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlIncomeDAO extends MySqlDAO implements IncomeDAOInterface{
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
                int incomeId = resultSet.getInt("incomeId");
                String title = resultSet.getString("title");
                double amount = resultSet.getDouble("amount");
                String dateIncurred = resultSet.getString("dateIncurred");
                Income u = new Income(incomeId, title, amount, dateIncurred);
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
    public Double findTotalIncome() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Double total = null;

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


}
