package org.example.DAOs;
import org.example.Exception.DAOException;
import org.example.DTOs.Expense;

import java.util.List;


public interface ExpenseDAOInterface {

    public List<Expense> findAllExpenses() throws DAOException;

    public Double findTotalExpenses() throws DAOException;

    public void addExpense(String title, String category, double amount, String date) throws DAOException;

    public void deleteExpense(int id) throws DAOException;
}
