package com.billdiary.model;





import org.springframework.stereotype.Component;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Hyperlink;


@Component
public class ProductDetails {
	
	private SimpleIntegerProperty productId;
	
	private  SimpleStringProperty name;
	
	private SimpleDoubleProperty wholesalePrice;
	
	private SimpleDoubleProperty retailPrice;
	
	private SimpleStringProperty description;
	
	private SimpleIntegerProperty stock;
	
	private SimpleDoubleProperty discount;
	
	private Hyperlink Delete; 
	
	public ProductDetails()
	{
	
	}
	public ProductDetails(int prodId, String nameOfProduct,
			double wholesale_Price, double retail_Price, String descriptionOfProduct,
			int stockOfProduct, double Discount, Hyperlink delete) {
		
		this.productId = new SimpleIntegerProperty(prodId);
		this.name = new SimpleStringProperty(nameOfProduct);
		this.wholesalePrice =new SimpleDoubleProperty( wholesale_Price);
		this.retailPrice = new SimpleDoubleProperty(retail_Price);
		this.description = new SimpleStringProperty(descriptionOfProduct);
		this.stock = new SimpleIntegerProperty(stockOfProduct);
		this.discount =new SimpleDoubleProperty (Discount);
		this.Delete = new Hyperlink("Delete");
	}
	public int getProductId() {
		return productId.get();
	}
	public void setProductId(SimpleIntegerProperty prodId) {
		this.productId=prodId;
	}
	public String getName() {
		return name.get();
	}
	public void setName(SimpleStringProperty nameOfProduct) {
		this.name=nameOfProduct;
	}
	public double getWholesalePrice() {
		return wholesalePrice.get();
	}
	public void setWholesalePrice(SimpleDoubleProperty wholesale_Price) {
		this.wholesalePrice=wholesale_Price;
	}
	public double getRetailPrice() {
		return retailPrice.get();
	}
	public void setRetailPrice(SimpleDoubleProperty retail_Price) {
		this.retailPrice=retail_Price;
	}
	public String getDescription() {
		return description.get();
	}
	public void setDescription(SimpleStringProperty descriptionOfProduct) {
		this.description=descriptionOfProduct;
	}
	public int getStock() {
		return stock.get();
	}
	public void setStock(SimpleIntegerProperty stockOfProduct) {
		this.stock=stockOfProduct;
	}
	public double getDiscount() {
		return discount.get();
	}
	public void setDiscount(SimpleDoubleProperty Discount) {
		this.discount=Discount;
	}
	public Hyperlink getDelete() {
		return Delete;
	}
	public void setDelete(Hyperlink delete) {
		Delete = delete;
	}
	
}
