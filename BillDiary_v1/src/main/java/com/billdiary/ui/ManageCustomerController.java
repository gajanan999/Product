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
    	ObservableList < Customer > ObcustomerList;
    	
    	ObcustomerList=customerTable.getSelectionModel().getSelectedItems();
    	System.out.println(event.getNewValue()+"kjjaf");
    	 address = (String) event.getNewValue();
    	 int row=event.getTablePosition().getRow();
    	 System.out.println(row+"*****");
    	 System.out.println(event.getTableColumn().getText());
    	System.out.println(data.get(2).getAddress());
    	data.get(2).setAddress(new SimpleStringProperty((String) event.getNewValue()));
    	System.out.println(data.get(2).getAddress());
    }

	@FXML 
	public void searchCustomer()
	{
		
	}

}
