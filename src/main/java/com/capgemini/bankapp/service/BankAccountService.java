package com.capgemini.bankapp.service;

import com.capgemini.bankapp.exceptions.LowBalanceException;

public interface BankAccountService {
	public double getBalance(long accountId);

	public double withdraw(long accountId, double amount) throws LowBalanceException;

	public double deposit(long accountId, double amount);

	public boolean fundTransfer(long fromAcc, long toAcc, double amount);

}