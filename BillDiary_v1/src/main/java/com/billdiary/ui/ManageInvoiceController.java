package com.billdiary.ui;

import java.net.URL;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.table.TableFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.billdiary.config.SpringFxmlLoader;
import com.billdiary.model.Customer;
import com.billdiary.model.Product;
import com.billdiary.service.CustomerService; 
import com.billdiary.utility.Constants;
import com.billdiary.utility.URLS;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

@Controller("ManageInvoiceController")
public class ManageInvoiceController implements Initializable  {
	
	
	@Autowired
	public LayoutController layoutController;
	
	
	@FXML 
	TextArea invAddress;
	@FXML
	TextField invMobileNo;
	@FXML 
	Text invNO;
	@FXML
	Text invDate;
	@FXML
	DatePicker invDueDate;
	
	/**  All table related **/
	@FXML 
	TableView<Product> productTable;
	private ObservableList < Product > data = FXCollections.observableArrayList();
	@FXML TableColumn<Product,Integer>productQuantity;
	@FXML TableColumn<Product,Double>productPrice;
	@FXML TableColumn<Product,Double>productDiscount;
	@FXML TableColumn<Product,Integer>productID;
	
	
	
	
	Customer selectedCustomer=null;
	
	@FXML
	ChoiceBox<String> selCustChoicebox;
	ObservableList<String> options;
	
	@Autowired
	private CustomerService customerService;
	List<String> customerNameList=new ArrayList<>();
	List<Customer> custList=new ArrayList<>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		refreshCustomerChoicebox();
		LocalDate localDate = LocalDate.now();
		invDate.setText(localDate.toString()+ " (YYYY-DD-MM)");
		int invoiceNO=calculateInvoiceNO();
		invNO.setText(Integer.toString(invoiceNO));
		/*final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
		     public DateCell call(final DatePicker datePicker) {
		         return new DateCell() {
		             @Override public void updateItem(LocalDate item, boolean empty) {
		                 super.updateItem(item, empty);
		                 if (empty || item == null) {
		                     setText(null);
		                     setGraphic(null);
		                 } else {
		                     setText(item.toString());
		                 }

		                 if (MonthDay.from(item).equals(MonthDay.of(9, 25))) {
		                     setTooltip(new Tooltip("Happy Birthday!"));
		                     setStyle("-fx-background-color: #ff4444;");
		                 }
		                 if (item.equals(LocalDate.now().plusDays(1))) {
		                     // Tomorrow is too soon.
		                     setDisable(true);
		                 }
		             }
		         };
		     }
		 };

		invDueDate.setDayCellFactory(dayCellFactory);*/
		invDueDate.setValue(localDate);
		
		
		
		
		productQuantity.setCellFactory(TextFieldTableCell.<Product,Integer>forTableColumn(new IntegerStringConverter()));
		productPrice.setCellFactory(TextFieldTableCell.<Product,Double>forTableColumn(new DoubleStringConverter()));
		productDiscount.setCellFactory(TextFieldTableCell.<Product,Double>forTableColumn(new DoubleStringConverter()));
		productID.setCellFactory(TextFieldTableCell.<Product,Integer>forTableColumn(new IntegerStringConverter()));
		

		
		
	}
	
	
	
	private int calculateInvoiceNO() {
		// TODO Auto-generated method stub0
		int invoiceNO=0;
		
		return 1;
	}



	public void refreshCustomerChoicebox()
	{
		
		if(null!=custList)
		custList.clear();
		if(null!=customerNameList)
		customerNameList.clear();
		if(null!=options)
		options.clear();
		if(null!=selCustChoicebox)
		selCustChoicebox.getItems().clear();
		
		custList=customerService.fetchCustomers();
		for(Customer cust:custList){
			customerNameList.add(cust.getCustomerName()+" "+cust.getMobile_no());
		}
		options =FXCollections.observableArrayList(
			        "Add New Customer"    
			    );
		options.addAll(customerNameList);
		selCustChoicebox.getItems().addAll(options);
		selCustChoicebox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				
				
				System.out.println(options.get(newValue.intValue()));
				selectCustomer(options.get(newValue.intValue()));
				
			}
		});
		selCustChoicebox.setTooltip(new Tooltip("Select a Customer"));
		
	}
	
	public void selectCustomer(String choiceboxValue)
	{
		if("Add New Customer".equals(choiceboxValue)){
			SpringFxmlLoader loader=SpringFxmlLoader.getInstance();
			//ResourceBundle bundle = ResourceBundle.getBundle("resources.UIResources");
			StackPane addCustomer=(StackPane) loader.load(URLS.ADD_CUSTOMER);
			ApplicationContext applicationContext=SpringFxmlLoader.getApplicationcontext();
			AddCustomerController addCustomerController=(AddCustomerController) applicationContext.getBean("AddCustomerController");
			addCustomerController.setParentName("InvoiceController");
			BorderPane root = new BorderPane();
			root.setCenter(addCustomer);
			layoutController.loadWindow(root,"Add Customer Details",Constants.POPUP_WINDOW_WIDTH,Constants.POPUP_WINDOW_HEIGHT);
		}
		else {
			
			for(Customer cust:custList){
				String matchString=cust.getCustomerName()+" "+cust.getMobile_no();
				if(matchString.equals(choiceboxValue)) {
					selectedCustomer=cust;
				}
			}
			if(null!=selectedCustomer) {
			invAddress.setText(selectedCustomer.getAddress());
			invMobileNo.setText(selectedCustomer.getMobile_no());
			}
			
		}
		
	}
	
	@FXML 
	public void handleKeyAction(KeyEvent event){
		
		System.out.println("+++++++");
		
		if((KeyCode) event.getCode()==KeyCode.ADD)
		{
			try {
			Product prd=getEmptyProuct();
			
			data.add(prd);
			productTable.setItems(data);
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	private Product getEmptyProuct() {
		// TODO Auto-generated method stub
		Product prd=new Product();
		prd.setProductId(new SimpleIntegerProperty(0));
		prd.setName(new SimpleStringProperty(""));
		prd.setDescription(new SimpleStringProperty(""));
		prd.setQuantity(new SimpleIntegerProperty(0));
		prd.setRetailPrice(new SimpleDoubleProperty(0.0));
		prd.setDiscount(new SimpleDoubleProperty(0.0));
		
		
		return prd;
	}



	@FXML
	public void addNewCustomer()
	{
	
		SpringFxmlLoader loader=SpringFxmlLoader.getInstance();
		StackPane addCustomer=(StackPane)loader.load(URLS.ADD_CUSTOMER);
		
		BorderPane root = new BorderPane();
		root.setCenter(addCustomer);
		layoutController.loadWindow(root,"Add New Customer",Constants.SEARCH_CUSTOMER_WIDTH,Constants.SEARCH_CUSTOMER_HEIGHT);
		
	}
	@FXML public void addProdcutToTable()
	{
		
	}
	
	
}
