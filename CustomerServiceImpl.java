package com.greatlearning.crmapp.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.greatlearning.crmapp.dao.CustomerService;
import com.greatlearning.crmapp.entity.Customer;

@Repository
public class CustomerServiceImpl implements CustomerService {

	private SessionFactory sessionFactory;
	private Session session;

	public CustomerServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.session = this.sessionFactory.openSession();
	}

	public List<Customer> findAll() {

		Transaction tx = session.beginTransaction();
		List<Customer> customers = session.createQuery("from Customer", Customer.class).list();

		tx.commit();

		return customers;
	}

	public List<Customer> searchBy(String firstname, String email) {
		Transaction tx = session.beginTransaction();

		String query = "";

		if (firstname.length() != 0 && email.length() != 0) {
			query = "from Customer where firstname like '%" + firstname + "%' or email like '%" + email + "%'";
		} else if (firstname.length() != 0) {
			query = "from Customer where firstname like '%" + firstname + "%'";
		} else if (email.length() != 0) {
			query = "from Customer where email like '%" + email + "%'";
		} else {
			System.out.println("No records");
		}

		List<Customer> customers = session.createQuery(query, Customer.class).list();
		tx.commit();

		return customers;
	}

	public Customer findById(int id) {
		Transaction tx = session.beginTransaction();
		Customer customer = session.get(Customer.class, id);

		tx.commit();
		return customer;
	}

	public void save(Customer customer) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		tx.commit();

	}

	public void deleteById(int id) {
		Transaction tx = session.beginTransaction();

		try {
			Customer customer = session.get(Customer.class, id);
			session.delete(customer);
		} finally {
			tx.commit();
		}

	}

}
