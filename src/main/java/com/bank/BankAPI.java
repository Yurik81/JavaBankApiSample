package com.bank;

import com.exceptions.BankApiException;

/**
 * Created by eivanchenko on 7/11/14.
 * Modified by ykuzub on 8/15/14.
 */
public class BankAPI {

    private ProcessingCenter processingCenter;

    public void setProcessingCenter(ProcessingCenter processingCenter) {
        this.processingCenter = processingCenter;
    }

    public Double getBalance(String account) throws BankApiException {
        VerifyAccount(account);

        Double result = processingCenter.getBalance(account);
        if ((result) == null) {
            throw new BankApiException("Account doesn't exist.");
        }
        return result;
    }

    public void setBalance(String account, Double value) throws BankApiException {
        VerifyAccount(account);
        if ((value <0) || (value >Double.MAX_VALUE)) {
            throw new BankApiException("You can't set this balance for this account. " + value);
        }
        processingCenter.setBalance(account, value);
    }

    public void decrease(String account, Double value) throws BankApiException {
        Double currentBalance = getBalance(account);
        Double result = currentBalance - value;
        if (result < 0) {
            throw new IllegalArgumentException("New exception have been raised due to illegal argument.");
        }
        processingCenter.decreaseAccount(account, value);
    }

    public void increase(String account, Double value) throws BankApiException {
        Double currentBalance = getBalance(account);
        if (Double.MAX_VALUE - currentBalance - value < 0) {
            throw new IllegalArgumentException("Too much result after increasing operation");
        }
        processingCenter.increaseAccount(account, value);
    }

    public void transfer(String fromAccount, String toAccount, double value) throws BankApiException {
        Double fromAccountBalance = getBalance(fromAccount);
        Double toAccountBalance = getBalance(toAccount);

        if ((fromAccountBalance < value) && ((Double.MAX_VALUE - toAccountBalance - value) < 0)) {
            throw new RuntimeException("New exception have been raised due to illegal argument.");
        }
        processingCenter.transfer(fromAccount, toAccount, value);
    }
    private void VerifyAccount(String account) {
        String pattern = "^[a-zA-Z0-9]+$";
        if (!account.matches(pattern)) {
            throw new RuntimeException("Not valid account!");
        }
    }
}