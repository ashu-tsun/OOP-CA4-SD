package org.example;
import java.util.List;


public interface ExpenseDAOInterface {

    public List<Expense> findAllExpenses() throws DAOException;

}
