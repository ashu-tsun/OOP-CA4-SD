package org.example.DTOs;

public class Income {

    private int incomeID;
    private String title;
    private Double amount;
    private String dateIncurred;

    public Income(int incomeID, String title, Double amount, String dateIncurred) {
        this.incomeID = incomeID;
        this.title = title;
        this.amount = amount;
        this.dateIncurred = dateIncurred;
    }

    public Income() {
    }

    public int getIncomeID() {
        return incomeID;
    }

    public void setIncomeID(int expenseID) {
        this.incomeID = expenseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDateIncurred() {
        return dateIncurred;
    }

    public void setDateIncurred(String dateIncurred) {
        this.dateIncurred = dateIncurred;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "incomeID=" + incomeID +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", dateIncurred='" + dateIncurred + '\'' +
                '}';
    }
}


