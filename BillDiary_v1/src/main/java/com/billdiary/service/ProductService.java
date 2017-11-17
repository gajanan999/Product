package com.billdiary.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.billdiary.DAOUtility.Mapper;
import com.billdiary.dao.ProductDAO;
import com.billdiary.entities.ProductEntity;
import com.billdiary.model.ProductDetails;

@Service
public class ProductService {
	@Autowired
	ProductDAO productDAO;
	
	public List<ProductDetails> fetchProducts()
	{
		List<ProductDetails> productList=new ArrayList<>();
		List<ProductEntity> productEntityList=new ArrayList<>();
		try {
			productEntityList=productDAO.fetchProducts();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		Mapper m=new Mapper();
		productList=m.getProductModels(productEntityList);
		
		return productList;
	}
	public boolean deleteProduct(int productId)
	{
		boolean productDeleted=false;
		try {
			productDeleted=productDAO.deleteProduct(productId);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return productDeleted;
	}
	
}
