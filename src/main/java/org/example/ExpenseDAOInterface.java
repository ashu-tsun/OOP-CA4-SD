package org.example;
import java.util.List;


public interface ExpenseDAOInterface {

    public List<Expense> findAllExpenses() throws DAOException;

    public Double findTotalExpenses() throws DAOException;

    public void addExpense(String title, String category, Double amount, String date) throws DAOException;

    public void deleteExpense(Integer id) throws DAOException;
}
