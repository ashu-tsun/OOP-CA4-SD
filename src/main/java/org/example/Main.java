package org.example;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ExpenseDAOInterface IExpenseDao = new MySqlExpensesDAO();
        IncomeDAOInterface IIncomeDao = new MySqlIncomeDAO();

        try {
            System.out.println("\nCall findAllExpenses()");
            List<Expense> expenses = IExpenseDao.findAllExpenses();     // call a method in the DAO

            if (expenses.isEmpty())
                System.out.println("There are no Expenses");
            else {
                for (Expense x : expenses)
                    System.out.println("Expense: " + x.toString());
            }

            System.out.println("\nCall findAllIncome()");
            List<Income> income = IIncomeDao.findAllIncome();     // call a method in the DAO

            if (income.isEmpty())
                System.out.println("There is no income");
            else {
                for (Income i : income)
                    System.out.println("Income: " + i.toString());
            }
        } catch (DAOException e) {
            // This code is executed when the DAO layer throws an exception.
            e.printStackTrace();
        }
    }
}