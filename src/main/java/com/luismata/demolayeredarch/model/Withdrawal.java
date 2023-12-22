package com.luismata.demolayeredarch.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Withdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int withdrawalId;
    @NotNull
    private int accountId;
    private long oldBalance;
    @NotNull
    private long withdrawalAmount;
    private long newBalance;
    @CreationTimestamp
    private Date transactionDate;

    // Could be useful to add a customerId as well, for feature of multiple account owners


    public Withdrawal(int accountId, long withdrawalAmount) {
        this.accountId = accountId;
        this.withdrawalAmount = withdrawalAmount;
    }

    public Withdrawal(int accountId, long oldBalance, long withdrawalAmount, long newBalance) {
        this.accountId = accountId;
        this.oldBalance = oldBalance;
        this.withdrawalAmount = withdrawalAmount;
        this.newBalance = newBalance;
    }

    public int getWithdrawalId() {
        return withdrawalId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public long getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(long oldBalance) {
        this.oldBalance = oldBalance;
    }

    public long getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(long withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public long getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(long newBalance) {
        this.newBalance = newBalance;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
