package com.billdiary.dao;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.billdiary.entities.ProductEntity;

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
		List<ProductEntity> p=findAll();
		return p;
	}
	@Transactional
	public boolean deleteProduct(int productId)
	{
		boolean productDeleted=false;
		//deleteById(id);
		int i=(int)productId;
		ProductEntity c=entityManager.find(ProductEntity.class, i);
		entityManager.remove(c);
		productDeleted=true;
		return productDeleted;
	}
	
	
	
}