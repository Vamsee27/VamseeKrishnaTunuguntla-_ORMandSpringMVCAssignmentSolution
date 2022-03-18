package com.greatlearning.crmapp.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.crmapp.entity.Customer;

@Service
public interface CustomerService {

	public List<Customer> findAll();

	public List<Customer> searchBy(String name, String author);

	public Customer findById(int id);

	public void save(Customer Customer); // save or update

	public void deleteById(int id);

}
