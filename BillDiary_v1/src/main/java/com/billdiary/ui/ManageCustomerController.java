package com.billdiary.ui;

import java.net.URL;

import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.billdiary.entities.CustomerEntity;
import com.billdiary.model.Customer;
import com.billdiary.model.ProductDetails;
import com.billdiary.service.CustomerService;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


@Controller("ManageCustomerController")
public class ManageCustomerController implements Initializable {
	
	@Autowired
	private CustomerService customerService;
	
	List<Customer> customerList=new ArrayList<>();
	
	@FXML
	TextField customerName;
	String address;
	@FXML
	private TableView < Customer > customerTable;
	
    private ObservableList < Customer > data = FXCollections.observableArrayList();
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//this.customerName.textProperty().bind(this.customer.getName());
		
		customerTable.setItems(data);
		populate(retrieveData());
		
	}
	
	
	private List<Customer> retrieveData(){
		try {
			if(customerList.isEmpty())
			{
				customerList=customerService.fetchCustomers();
			}
		return customerList;
				
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return new ArrayList<Customer>();
	}
	
    private void populate(final List < Customer > customers) {

    	if(data.isEmpty())
    	{
	        for(Customer cust:customers)
	        {
	        	data.add(cust);
	        	int custID=cust.getCustomerID();
	        	cust.getDeleteHyperlink().setOnAction(e->deleteButtonClickedThroughHyperlink(custID));
	        }
    	}

    }
    
    public void deleteButtonClickedThroughHyperlink(int customerId)
    {
    		System.out.println(customerId);
    		customerService.deleteCustomer(customerId);
    		System.out.println(customerId+ "Customer deleted");
    		customerList.clear();
    		data.clear();
    		customerTable.setItems(data);
    		populate(retrieveData());
    	
    }
    
    
    @FXML public void deleteCustomer()
    {
    	ObservableList < Customer > ObcustomerList;
    	
    	ObcustomerList=customerTable.getSelectionModel().getSelectedItems();
    	
    	if(ObcustomerList!=null)
    	{
    		System.out.println(ObcustomerList.get(0).getCustomerID());
    		customerService.deleteCustomer(ObcustomerList.get(0).getCustomerID());
    		System.out.println(ObcustomerList.get(0).getCustomerID()+ "Customer deleted");
    		customerList.clear();
    		data.clear();
    		customerTable.setItems(data);
    		populate(retrieveData());
    		
    	}
    	
    }
    
    @FXML public void saveCustomer()
    {
    	ObservableList < Customer > ObcustomerList;
    	
    	ObcustomerList=customerTable.getSelectionModel().getSelectedItems();
    	System.out.println(ObcustomerList.get(0).getAddress());
    	if(ObcustomerList!=null)
    	{
    			customerService.saveCustomer(ObcustomerList);
    		System.out.println(ObcustomerList.get(0).getCustomerID());
    		List<Customer> custList = customerService.saveCustomer(ObcustomerList);
    		System.out.println(ObcustomerList.get(0).getCustomerID()+ "Customer deleted");
    		customerList.clear();
    		data.clear();
    		customerTable.setItems(data);
    		populate(retrieveData());
    		
    	}
    	
    }

    
    @FXML private <T>void setEditedValue(CellEditEvent<Customer,T> event)
    {
    	if("customerName".equals(event.getTableColumn().getId())) {
    		String customerName=event.getNewValue().toString();
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setCustomerName(new SimpleStringProperty(customerName));
    	}
    	if("address".equals(event.getTableColumn().getId())) {
    		String address=event.getNewValue().toString();
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setAddress(new SimpleStringProperty(address));
    	}
    	if("mobileNO".equals(event.getTableColumn().getId())) {
    		String mobileNO=event.getNewValue().toString();
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setMobile_no(new SimpleStringProperty(mobileNO));
    	}
    	if("city".equals(event.getTableColumn().getId())) {
    		String city=event.getNewValue().toString();
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setCity(new SimpleStringProperty(city));
    	}
    	if("country".equals(event.getTableColumn().getId())) {
    		String country=event.getNewValue().toString();
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setCountry(new SimpleStringProperty(country));
    	}
    	/*
    	if("joiningDate".equals(event.getTableColumn().getId())) {
    		String joiningDate=event.getNewValue().toString();
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setJoiningDate(new SimpleStringProperty(joiningDate));
    	}
    	*/
    	
    }

	@FXML 
	public void searchCustomer()
	{
		
	}

}
