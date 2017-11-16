package com.billdiary.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.billdiary.entities.ProductEntity;
import com.billdiary.model.User;
import com.billdiary.utility.Constants;
@Repository
public class ProductDAO extends AbstractJpaDAO< ProductEntity >{

	@PersistenceContext
	EntityManager entityManager;
	
	public ProductDAO()
	{
		setClazz(ProductEntity.class );
	}
	public List<ProductEntity> fetchProducts()
	{
		//Query q=entityManager.createNativeQuery("select * from product");
		//setClazz(Product.class);
		
		List<ProductEntity> p=findAll();
		return p;
		//Object[] o=(Object[]) q.getSingleResult();
}
	public void deleteProduct(int productId) throws ClassNotFoundException, SQLException
	{
		List<ProductEntity> p=findAll();
		for(ProductEntity pr:p)
		{
			if(pr.getId()==productId)
			{
				boolean dbCreated=false;
				Class.forName(Constants.DB_DRIVER);
				Connection connection = DriverManager.getConnection(Constants.DB_URL,Constants.DB_USERNAME,Constants.DB_PASSWORD);
				 PreparedStatement st = connection.prepareStatement("delete"+" "+productId+" "+"from product");
				 connection.createStatement().executeQuery("shutdown");
				 st.close();
				connection.close();
				
			}
		}
	}
	
	
	
}