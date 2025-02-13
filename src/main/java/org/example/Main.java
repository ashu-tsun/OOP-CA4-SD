package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ExpenseDAOInterface IExpenseDao = new MySqlExpensesDAO();
        IncomeDAOInterface IIncomeDao = new MySqlIncomeDAO();

        try {
            //Part One
            System.out.println("\nCall findAllExpenses()");
            List<Expense> expenses = IExpenseDao.findAllExpenses();     // call a method in the DAO

            if (expenses.isEmpty())
                System.out.println("There are no Expenses");
            else {
                for (Expense x : expenses)
                    System.out.println("Expense: " + x.toString());
            }
            //Part One Total
            System.out.println("\nCall findTotalExpenses()");
            double totalExpenses = IExpenseDao.findTotalExpenses();
            System.out.println("Total Expenses: " + totalExpenses);

            //Part Two
            System.out.println("\nAdd an expense");
            String title ="Concert";
            String category = "Entertainment";
            double amount = 250.00;
            String date = "2025-02-27";
            //IExpenseDao.addExpense(title,category,amount,date);
            System.out.println("\nAdded expense (" + title + ", " + category + ", " + amount + ", " + date +")");

            //Showing Part Two Worked
            System.out.println("\nCall findAllExpenses() to show update");
            expenses = IExpenseDao.findAllExpenses();     // call a method in the DAO

            if (expenses.isEmpty())
                System.out.println("There are no Expenses");
            else {
                for (Expense x : expenses)
                    System.out.println("Expense: " + x.toString());
            }

            //Part Three
            System.out.println("\nCall deleteExpense()");
            int id =2;
            //IExpenseDao.deleteExpense(id);
            System.out.println("\nDeleted expense with id " + id);

            //Showing Part Three Worked
            System.out.println("\nCall findAllExpenses() to show update");
            expenses = IExpenseDao.findAllExpenses();     // call a method in the DAO

            if (expenses.isEmpty())
                System.out.println("There are no Expenses");
            else {
                for (Expense x : expenses)
                    System.out.println("Expense: " + x.toString());
            }

            //Part Four
            System.out.println("\nCall findAllIncome()");
            List<Income> income = IIncomeDao.findAllIncome();     // call a method in the DAO

            if (income.isEmpty())
                System.out.println("There is no income");
            else {
                for (Income i : income)
                    System.out.println("Income: " + i.toString());
            }

            //Part Four Total
            System.out.println("\nCall findTotalIncome()");
            double totalIncome = IIncomeDao.findTotalIncome();
            System.out.println("Total Expenses: " + totalIncome);

        } catch (DAOException e) {
            // This code is executed when the DAO layer throws an exception.
            e.printStackTrace();
        }
    }
}