package com.capgemini.bankapp.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.entities.Customer;
import com.capgemini.bankapp.repositories.CustomerRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Customer authenticate(Customer customer) throws DataAccessException{
		try {
			Customer customer2 = jdbcTemplate.queryForObject(
					"select * from customers inner join bankaccount on bankaccount.customer_id=customers.customer_id where customers.customer_id=? and customers.customer_password=?",
					new Object[] { customer.getCustomerId(), customer.getPassword() }, new CustomerRowMapper());

			return customer2;
		}

		catch (DataAccessException e) {

			e.initCause(new EmptyResultDataAccessException("Expected 1 actual 0 ", 1));

			throw e;
		}

	}

	@Override
	public Customer updateProfile(Customer customer) {
		int count = jdbcTemplate.update(
				"update customers set customer_name=?,customer_address=?,customer_email=? where customer_id=?",
				new Object[] { customer.getCustomerName(), customer.getAddress(), customer.getEmail(),
						customer.getCustomerId() });
		if (count != 0) {
			return customer;
		} else {
			return getCustomer(customer.getCustomerId());
		}
	}

	public Customer getCustomer(int customerId) {

		return jdbcTemplate.queryForObject(
				"select * from customers inner join bankaccount on bankaccount.customer_id=customers.customer_id where customers.customer_id=?",
				new Object[] { customerId }, new CustomerRowMapper());

	}

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) {
		int count = jdbcTemplate.update("update customers set customer_password=? where customer_id=?",
				new Object[] { newPassword, customer.getCustomerId() });
		if (count != 0) {
			return true;
		} else {
			return false;
		}
	}

	class CustomerRowMapper implements RowMapper<Customer> {
		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

			Customer customer = new Customer();

			customer.setCustomerId(rs.getInt(1));
			customer.setCustomerName(rs.getString(2));
			customer.setPassword(rs.getString(3));
			customer.setEmail(rs.getString(4));
			customer.setAddress(rs.getString(5));
			customer.setDateOfBirth(rs.getDate(6).toLocalDate());

			BankAccount account = new BankAccount(rs.getLong(7), rs.getString(8), rs.getDouble(9));
			customer.setAccount(account);
			return customer;
		}

	}
}