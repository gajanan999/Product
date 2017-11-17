package com.billdiary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billdiary.DAOUtility.Mapper;
import com.billdiary.dao.CustomerDAO;
import com.billdiary.entities.CustomerEntity;
import com.billdiary.model.Customer;

@Service
public class CustomerService {

	@Autowired
	CustomerDAO customerDAO;
	
	public List<Customer> fetchCustomers()
	{
		List<Customer> customerList=new ArrayList<>();
		List<CustomerEntity> customerEntityList=new ArrayList<>();
		try {
		customerEntityList=customerDAO.fetchCustomers();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		Mapper m=new Mapper();
		customerList=m.getCustomerModels(customerEntityList);
		
		return customerList;
	}
	
	
	public boolean deleteCustomer(long id)
	{
		boolean customerDeleted=false;
		customerDAO.deleteCustomer(id);
		customerDeleted=true;
		return customerDeleted;
	}
	
}
