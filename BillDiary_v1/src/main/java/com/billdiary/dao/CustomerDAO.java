package com.billdiary.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.billdiary.entities.CustomerEntity;

@Repository
public class CustomerDAO extends AbstractJpaDAO< CustomerEntity >{

	@PersistenceContext
	EntityManager entityManager;
	
	public CustomerDAO()
	{
		setClazz(CustomerEntity.class);
	}
	public List<CustomerEntity> fetchCustomers()
	{
		List<CustomerEntity> customerEntityList =findAll();
		return customerEntityList;
	}
	
	@Transactional
	public boolean deleteCustomer(long id)
	{

		boolean customerDeleted=false;
		
		//deleteById(id);
		int i=(int)id;
		CustomerEntity c=entityManager.find(CustomerEntity.class, i);
		
		entityManager.remove(c);
		
		customerDeleted=true;
		return customerDeleted;
	}
	
	@Transactional
	public boolean saveCustomer(long id)
	{

		boolean customersaved=false;
		
		//deleteById(id);
		int i=(int)id;
		CustomerEntity c=entityManager.find(CustomerEntity.class, i);
		
		entityManager.merge(c);
		
		customersaved=true;
		return customersaved;
	}
}
