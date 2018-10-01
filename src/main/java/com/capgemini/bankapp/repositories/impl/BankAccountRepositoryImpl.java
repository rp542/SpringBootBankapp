package com.capgemini.bankapp.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.repositories.BankAccountRepository;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public double getBalance(long accountId) throws DataAccessException{
		try {
		double balance = jdbcTemplate.queryForObject("SELECT account_balance FROM bankaccount Where account_id=?",
				new Object[] { accountId }, Double.class);
		return balance;
	}
		catch (DataAccessException e) {
			e.initCause(new EmptyResultDataAccessException("Expected 1 actual 0", 1));
			throw e;
		}
	}
	
	@Override
	public double updateBalance(long accountId, double newBalance) throws DataAccessException{
		try{double balance = jdbcTemplate.update("update bankaccount set account_balance=? where account_id=?",
				new Object[] { newBalance, accountId });
		return balance;
	}
		catch (DataAccessException e) {
			e.initCause(new EmptyResultDataAccessException("Expected 1 actual 0", 1));
			throw e;
		}
	}

}