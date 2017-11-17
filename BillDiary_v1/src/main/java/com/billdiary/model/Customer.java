package com.billdiary.model;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.billdiary.entities.CustomerEntity;
import com.billdiary.utility.GeneralUitilies;
import com.billdiary.utility.URLS;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;


public class Customer {
	
	
	
	@Autowired
	GeneralUitilies generalUtilities;
	
	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}


	/**
	 * This fields are only for searching purpose
	 */
	private StringProperty name = new SimpleStringProperty();
	private StringProperty ID = new SimpleStringProperty();
	
	public StringProperty getID() {
		return ID;
	}
	
	public void setID(final String ID)
	{
		this.name.set(ID);
	}
	
	public void setName(final String custName)
	{
		this.name.set(custName);
	}

	public StringProperty getName() {
		return name;
	}


	/**
	 * These fields are only for tableview purpose
	 * 
	 */
	private SimpleStringProperty customerName;
	private SimpleIntegerProperty customerID;
	private SimpleStringProperty address;
	private SimpleStringProperty mobile_no;
	private SimpleStringProperty city;
	private SimpleStringProperty country;
	private Hyperlink deleteHyperlink;
	private Hyperlink saveHyperlink;
	private List<Hyperlink> hyperlinks =new ArrayList<>();

	public List<Hyperlink> getHyperlinks() {
		
		if(hyperlinks.isEmpty())
		{
			
			hyperlinks.add(getDeleteHyperlink());
			hyperlinks.add(getSaveHyperlink());
		}
		return hyperlinks;
	}

	public void setHyperlinks(List<Hyperlink> hyperlinks) {
		this.hyperlinks = hyperlinks;
	}

	public Hyperlink getSaveHyperlink() {
		if(saveHyperlink==null)
		{
			saveHyperlink=new Hyperlink("Save");
			this.saveHyperlink.setStyle("-fx-text-fill: #606060;");
		}
		return saveHyperlink;
	}

	public void setSaveHyperlink(Hyperlink saveHyperlink) {
		this.saveHyperlink = saveHyperlink;
		
	}

	public Hyperlink getDeleteHyperlink() {
		if(deleteHyperlink==null)
		{
			deleteHyperlink=new Hyperlink("Delete");
			this.deleteHyperlink.setStyle("-fx-text-fill: #606060;");
		}
		return deleteHyperlink;
	}

	public void setDeleteHyperlink(Hyperlink deleteHyperlink) {
		this.deleteHyperlink = deleteHyperlink;
	}

	public String getAddress() {
		return address.get();
	}

	public String getMobile_no() {
		return mobile_no.get();
	}

	public String getCity() {
		return city.get();
	}

	public String getCountry() {
		return country.get();
	}
	
	public String getCustomerName() {
		return customerName.get();
	}

	public Integer getCustomerID() {
		return customerID.get();
	}


	
	public void setCustomerName(SimpleStringProperty customerName) {
		this.customerName = customerName;
	}

	public void setCustomerID(SimpleIntegerProperty customerID) {
		this.customerID = customerID;
	}

	public void setAddress(SimpleStringProperty address) {
		this.address = address;
	}

	public void setMobile_no(SimpleStringProperty mobile_no) {
		this.mobile_no = mobile_no;
	}

	public void setCity(SimpleStringProperty city) {
		this.city = city;
	}

	public void setCountry(SimpleStringProperty country) {
		this.country = country;
	}

	public Customer(CustomerEntity customerEnitity)
	{
		this.customerID=new SimpleIntegerProperty(customerEnitity.getCustomerID());
		this.customerName=new SimpleStringProperty(customerEnitity.getCustomerName());
		this.address=new SimpleStringProperty(customerEnitity.getAddress());
		this.city=new SimpleStringProperty(customerEnitity.getCity());
		this.country=new SimpleStringProperty(customerEnitity.getCountry());
		this.mobile_no=new SimpleStringProperty(customerEnitity.getMobile_no());
		this.image=new Image(generalUtilities.getFileAsInputStream (URLS.SAVE_IMAGE));
		this.deleteHyperlink=new Hyperlink("Delete");
		this.deleteHyperlink.setStyle("-fx-text-fill: #606060;");
		this.saveHyperlink=new Hyperlink("Save");
		this.saveHyperlink.setStyle("-fx-text-fill: #606060;");
		this.hyperlinks=new ArrayList<>();
		this.hyperlinks.add(getDeleteHyperlink());
		this.hyperlinks.add(getSaveHyperlink());
		
		
	}
	
	public Customer(final int customerID,final String customerName)
	{
		this.customerID=new SimpleIntegerProperty(customerID);
		this.customerName=new SimpleStringProperty(customerName);
	}
	public Customer()
	{
		
	}
	
}
