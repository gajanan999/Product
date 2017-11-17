package com.billdiary.ui;

import java.net.URL;

import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.billdiary.model.Customer;
import com.billdiary.model.ProductDetails;
import com.billdiary.service.CustomerService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


@Controller("ManageCustomerController")
public class ManageCustomerController implements Initializable {
	
	@Autowired
	private CustomerService customerService;
	
	List<Customer> customerList=new ArrayList<>();
	
	@FXML
	TextField customerName;
	
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


	@FXML 
	public void searchCustomer()
	{
		
	}

}
