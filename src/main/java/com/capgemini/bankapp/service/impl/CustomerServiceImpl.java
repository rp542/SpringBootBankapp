package com.capgemini.bankapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.bankapp.entities.Customer;
import com.capgemini.bankapp.exceptions.ChangePasswordFailedException;
import com.capgemini.bankapp.exceptions.UserNotFoundException;
import com.capgemini.bankapp.repositories.CustomerRepository;
import com.capgemini.bankapp.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer authenticate(Customer customer) throws UserNotFoundException {
		try {
			return customerRepository.authenticate(customer);
		} catch (DataAccessException e) {
			UserNotFoundException u = new UserNotFoundException("Customer Not Found");
			u.initCause(e);
			throw u;
		}
	}

	@Override
	public Customer updateProfile(Customer customer) {

		return customerRepository.updateProfile(customer);
	}

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) {
		try {
			return customerRepository.updatePassword(customer, oldPassword, newPassword);
		}

		catch (DataAccessException e) {
			ChangePasswordFailedException changePasswordFailedException = new ChangePasswordFailedException(
					"Password Change unsuccessfull");
			changePasswordFailedException.initCause(e);
			throw e;
		}
	}
}