package com.bank;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eivanchenko on 7/11/14.
 * Modified by ykuzub on 8/15/14.
 */
public class ProcessingCenter {
    private Map<String, Double> accounts = new HashMap();


    public Double getBalance(String account) {
        Double result = accounts.get(account);
        return result;
    }

    public void setBalance(String account, double balance) {
        accounts.put(account, balance);
    }

    public void increaseAccount(String account, double value) {
        Double currentBalance = getBalance(account);
        accounts.put(account, currentBalance + value);
    }

    public void decreaseAccount(String account, double value) {
        Double currentBalance = getBalance(account);
        accounts.put(account, currentBalance-value);
    }

    public void transfer(String fromAccount, String toAccount, double value) {
        Double fromAccountBalanceBeforeTransferring = getBalance(fromAccount);
        Double toAccountBalanceBeforeTransferring = getBalance(toAccount);

        try {
            increaseAccount(toAccount, value);
            decreaseAccount(fromAccount, value);
        } catch (Exception exception) {
            throw new RuntimeException("Transfer operation have been broken due to unknown exception" + exception.getMessage());
        } finally {
            setBalance(toAccount, toAccountBalanceBeforeTransferring);
            setBalance(fromAccount, fromAccountBalanceBeforeTransferring);
        }
    }

}