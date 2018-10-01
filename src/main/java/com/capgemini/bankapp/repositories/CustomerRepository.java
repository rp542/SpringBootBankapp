package com.capgemini.bankapp.repositories;

import org.springframework.dao.DataAccessException;

import com.capgemini.bankapp.entities.Customer;

public interface CustomerRepository {
	public Customer authenticate(Customer customer) throws DataAccessException;

	public Customer updateProfile(Customer customer);

	public boolean updatePassword(Customer customer, String oldPassword, String newPassword);

}