package com.billdiary.ui;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.billdiary.config.SpringFxmlLoader;
import com.billdiary.model.Customer;
import com.billdiary.service.CustomerService;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


@Controller("AddCustomerController")
public class AddCustomerController implements Initializable{

	
	public String parentName;
	
	@Autowired
	private CustomerService customerService;
	@FXML
	TextField add_customerName;
	@FXML
	TextArea add_address;
	@FXML
	TextField add_mobileNo;
	@FXML
	ChoiceBox<?> add_city;
	@FXML
	TextField add_emailID;
	@FXML
	ChoiceBox<?> add_country;
	@FXML
	ChoiceBox<?> add_state;
	@FXML 
	TextArea add_additionalInfo;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println(this.getClass());
		System.out.println(this.getClass().getSuperclass());
	}

	
	@FXML
	public void addCustomer(ActionEvent event){
		String customerName=add_customerName.getText();
		String address=add_address.getText();
		String mobileNO=add_mobileNo.getText();
		String city=(String)add_city.getValue();
		String emailID=add_emailID.getText();
		String country=(String)add_country.getValue();
		if(customerName!=null && address!=null && mobileNO!=null && city!=null && emailID!=null && country!=null)
		{
			System.out.println(customerName+" "+address+" "+mobileNO+" "+city+" "+emailID+" "+country);
			Customer cust=new Customer();
			cust.setCustomerName(new SimpleStringProperty(customerName));
			cust.setAddress(new SimpleStringProperty(address));
			cust.setMobile_no(new SimpleStringProperty(mobileNO));
		    cust.setCity(new SimpleStringProperty(city));
		    cust.setEmailID(new SimpleStringProperty(emailID));
		    cust.setCountry(new SimpleStringProperty(country));
		    
			customerService.addCustomer(cust);
			
			((Node)(event.getSource())).getScene().getWindow().hide();
			
			System.out.println("ParentController of addcustomer : "+this.parentName);
			if(null!=this.parentName) {
			if(this.parentName.equals("CustomerController")) {
				ApplicationContext applicationContext=SpringFxmlLoader.getApplicationcontext();
				ManageCustomerController manageCustomer=(ManageCustomerController) applicationContext.getBean("ManageCustomerController");
				manageCustomer.getRefreshedTable();
			}else if(this.parentName.equals("InvoiceController")) {
				ApplicationContext applicationContext=SpringFxmlLoader.getApplicationcontext();
				ManageInvoiceController manageInvoiceController=(ManageInvoiceController) applicationContext.getBean("ManageInvoiceController");
				manageInvoiceController.refreshCustomerList();;
			}
			
			
			}
		
		}	
	}
	
	

	public String getParentName() {
		return parentName;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


}
