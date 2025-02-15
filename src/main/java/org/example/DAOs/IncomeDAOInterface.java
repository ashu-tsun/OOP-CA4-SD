package org.example.DAOs;

import org.example.Exception.DAOException;
import org.example.DTOs.Income;

import java.util.List;

public interface IncomeDAOInterface {
    public List<Income> findAllIncome() throws DAOException;

    public double findTotalIncome() throws DAOException;

    public void addIncome(String title, double amount, String date) throws DAOException;

    public void deleteIncome(int id) throws DAOException;

    public double findAllIncomeMonth(int month) throws DAOException;
}
