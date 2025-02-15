package org.example;

import org.example.DAOs.ExpenseDAOInterface;
import org.example.DAOs.IncomeDAOInterface;
import org.example.DAOs.MySqlExpensesDAO;
import org.example.DAOs.MySqlIncomeDAO;
import org.example.DTOs.Expense;
import org.example.DTOs.Income;
import org.example.Exception.DAOException;

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
            IExpenseDao.addExpense(title,category,amount,date);
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
            IExpenseDao.deleteExpense(id);
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
            System.out.println("Total Income: " + totalIncome);


            //Part Five
            System.out.println("\nCall addIncome()");
            String title2 ="Job";
            double amount2 = 520.50;
            String date2 = "2025-02-16";
            IIncomeDao.addIncome(title2,amount2,date2);
            System.out.println("\nAdded income (" + title2 + ", " + amount2 + ", " + date2 +")");

            //Showing Part Five Worked
            System.out.println("\nCall findAllIncome() to show update");
            income = IIncomeDao.findAllIncome();     // call a method in the DAO

            if (income.isEmpty())
                System.out.println("There is no income");
            else {
                for (Income i : income)
                    System.out.println("Income: " + i.toString());
            }

            //Part Six
            int id2 =2;
            IIncomeDao.deleteIncome(id);
            System.out.println("\nDeleted income with id " + id2);

            //Showing Part Six Worked
            System.out.println("\nCall findAllIncome() to show update");
            income = IIncomeDao.findAllIncome();     // call a method in the DAO

            if (income.isEmpty())
                System.out.println("There is no income");
            else {
                for (Income i : income)
                    System.out.println("Income: " + i.toString());
            }

            //Part Seven
            int month = 1;
            double balance = findBalance(month);
            System.out.printf("Your balance for month %d is %.2f",month,balance);



        } catch (DAOException e) {
            // This code is executed when the DAO layer throws an exception.
            e.printStackTrace();
        }
    }

    public static double findBalance(int month) throws DAOException {
        ExpenseDAOInterface IExpenseDao = new MySqlExpensesDAO();
        IncomeDAOInterface IIncomeDao = new MySqlIncomeDAO();
        double totalExpenses = 0;
        double totalIncome = 0;

        //Gathering monthly expenses from other DAO's and storing the totals
        totalExpenses = IExpenseDao.findAllExpensesMonth(month);
        totalIncome = IIncomeDao.findAllIncomeMonth(month);

        System.out.println("\nTotal Income = " + totalIncome);
        System.out.println("Total Expenses: " + totalExpenses);
        //Returning what money is left
        return totalIncome-totalExpenses;
    }
}