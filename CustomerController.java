package com.greatlearning.crmapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.crmapp.dao.CustomerService;
import com.greatlearning.crmapp.entity.Customer;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String listCustomers(Model theModel) {

		List<Customer> customers = customerService.findAll();
		theModel.addAttribute("customers", customers);
		return "list-customers"; // /WEB-INF/view/list-customers.jsp

	}

	@RequestMapping("/showFormForAdd")
	public String showFormforAdd(Model theModel) {
		Customer theCustomer = new Customer();

		theModel.addAttribute("Customer", theCustomer);

		return "Customer-form";

	}

	@RequestMapping("/showFormForUpdate")
	public String showFormforUpdate(@RequestParam("customerId") int id, Model theModel) {
		Customer theCustomer = customerService.findById(id);

		theModel.addAttribute("Customer", theCustomer);

		return "Customer-form";

	}

	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id, @RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname, @RequestParam("email") String email) {

		System.out.println(id);

		Customer theCustomer;

		if (id != 0) {
			theCustomer = customerService.findById(id);
			theCustomer.setfirstname(firstname);
			theCustomer.setlastname(lastname);
			theCustomer.setemail(email);
		} else
			theCustomer = new Customer(firstname, lastname, email);
		customerService.save(theCustomer);

		return "redirect:/customers/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {

		customerService.deleteById(theId);

		return "redirect:/customers/list";

	}
}
