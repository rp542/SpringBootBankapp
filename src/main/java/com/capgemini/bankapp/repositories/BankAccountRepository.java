package com.capgemini.bankapp.repositories;

import org.springframework.dao.DataAccessException;

public interface BankAccountRepository {
	public double getBalance(long accountId);

	public double updateBalance(long accountId, double newBalance) throws DataAccessException;

}