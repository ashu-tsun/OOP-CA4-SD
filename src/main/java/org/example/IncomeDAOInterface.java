package org.example;

import java.util.List;

public interface IncomeDAOInterface {
    public List<Income> findAllIncome() throws DAOException;
}
