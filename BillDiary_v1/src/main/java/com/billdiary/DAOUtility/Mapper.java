package com.billdiary.DAOUtility;

import java.util.ArrayList;
import java.util.List;

import com.billdiary.entities.CustomerEntity;
import com.billdiary.entities.ProductEntity;
import com.billdiary.model.Customer;
import com.billdiary.model.ProductDetails;
import com.billdiary.model.User;
import com.billdiary.model.User1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Hyperlink;

public class Mapper {
	
	
	
	public static User getUserEntity(User1 user)
	{
		User userEntity=new User();
		if(null!=user)
		{
			userEntity.setId(user.getId());
			userEntity.setUserName(user.getUserName());
			userEntity.setPassword(user.getPassword());
			userEntity.setRole(user.getRole());
		}
		
		return userEntity;
	}

	public List<Customer> getCustomerModels(List<CustomerEntity> customerEntityList) {
		List<Customer> customerList=new ArrayList<>();
		
		for(CustomerEntity customerEntity:customerEntityList)
		{
			Customer cust=new Customer();
			cust.setCustomerID(new SimpleIntegerProperty(customerEntity.getCustomerID()));
			//cust.setCustomerID(new SimpleIntegerProperty(customerEntity.getCustomerID()));
			cust.setCustomerName(new SimpleStringProperty(customerEntity.getCustomerName()));
			cust.setAddress(new SimpleStringProperty(customerEntity.getAddress()));
			cust.setCity(new SimpleStringProperty(customerEntity.getCity()));
			cust.setCountry(new SimpleStringProperty(customerEntity.getCountry()));
			cust.setMobile_no(new SimpleStringProperty(customerEntity.getMobile_no()));
			customerList.add(cust);
		}
		
		return customerList;
	}
	public List<ProductDetails> getProductModels(List<ProductEntity> productEntityList) {
		List<ProductDetails> productList=new ArrayList<>();
		
		for(ProductEntity productEntity:productEntityList)
		{
			ProductDetails prod=new ProductDetails();
			prod.setProductId(new SimpleIntegerProperty(productEntity.getId()));
			prod.setDescription(new SimpleStringProperty(productEntity.getDescription()));
			prod.setDiscount(new SimpleDoubleProperty(productEntity.getDiscount()));
			prod.setName(new SimpleStringProperty(productEntity.getName()));
			prod.setRetailPrice(new SimpleDoubleProperty(productEntity.getRetail_price()));
			prod.setWholesalePrice(new SimpleDoubleProperty(productEntity.getWholesale_price()));
			prod.setStock(new SimpleIntegerProperty(productEntity.getStock()));
			prod.setDelete(new Hyperlink("Delete"));
			productList.add(prod);
			
			
		}
		
		return productList;
	}
}
