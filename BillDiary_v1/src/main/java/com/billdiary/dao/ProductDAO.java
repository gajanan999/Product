package com.billdiary.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.billdiary.entities.Product;
import com.billdiary.model.User;
@Repository
public class ProductDAO extends AbstractJpaDAO< Product >{

	@PersistenceContext
	EntityManager entityManager;
	
	public ProductDAO()
	{
		setClazz(Product.class );
	}
	public List<Product> fetchProducts()
	{
		//Query q=entityManager.createNativeQuery("select * from product");
		//setClazz(Product.class);
		
		List<Product> p=findAll();
		return p;
		//Object[] o=(Object[]) q.getSingleResult();
		
		
	}
	
}