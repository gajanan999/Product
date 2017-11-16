package com.billdiary.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.billdiary.config.SpringFxmlLoader;

import com.billdiary.model.ProductDetails;
import com.billdiary.service.ProductService;
//import com.billdiary.service.ProductService;
import com.billdiary.utility.Constants;
import com.billdiary.utility.URLS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

@Controller("ManageProductController")
@Service
public class ManageProductController implements Initializable{
	@Autowired
	public LayoutController layoutController;
	//final static Logger LOGGER = Logger.getLogger(ManageProductController.class);
	@Autowired
	private ProductService productService;
	
	List<ProductDetails> productList=new ArrayList<>();
	@FXML
	TextField productName;
	@FXML 
	TextField productId;
	@FXML
	private TableView < ProductDetails > ProductTable;
	
    private ObservableList < ProductDetails > data = FXCollections.observableArrayList();
    
@FXML public void searchProduct()
{
	
	System.out.println("Inside Search product");
	ObservableList<ProductDetails> data = FXCollections.observableArrayList();
	List<ProductDetails> obj = productService.fetchProducts();
	int size = obj.size();
	String ProdName = productName.getText();
	if(productId.getText().equals(""))
	{
		productId.setText("0");
	}
	Integer ProdId = Integer.parseInt(productId.getText());
	System.out.println("prod name is:"+productName.getText());
	boolean search=true;
	int count=0;
	
	for(ProductDetails pd:obj)
	{	
		count++;
		if(ProdName.equals("") && ProdId==0)
		{	System.out.println("inside if");
			initialize(null, null);
			search=false;
			break;
		}
		else if(!ProdName.equals("") && ProdId!=0)
		{
			search=false;
		}
		
		System.out.println(Integer.parseInt(productId.getText()));
		if(ProdId==(pd.getProductId()) && ProdName.toLowerCase().equals(pd.getName().toLowerCase()))
		{
			data.add(pd);
			ProductTable.setItems(data);
			int pid=pd.getProductId();
			pd.getDelete().setOnAction(e->deleteButtonClickedThroughHyperlink(pid));
			search=false;
			break;
		}
		if(count>=size)
		{
			System.out.println("inside else");
			ObservableList< ProductDetails> ListItems;
			ListItems=ProductTable.getItems();
			System.out.println(ListItems.size());
			while(ListItems.size()!=0)
			{
				ListItems.remove(0);
			}	
		}
	}	
	if(search == true)
	{	
		count = 0;
		for(ProductDetails pd:obj)
		{
			if(ProdId ==(pd.getProductId())|| ProdName.toLowerCase().equals(pd.getName().toLowerCase()))
			{
				data.add(pd);
				ProductTable.setItems(data);
				int pid=pd.getProductId();
				pd.getDelete().setOnAction(e->deleteButtonClickedThroughHyperlink(pid));
				count++;
				search=false;	
			}
			if(count>=obj.size()-1&& search ==true)
			{
				System.out.println("inside else");
				ObservableList< ProductDetails> ListItems;
				ListItems=ProductTable.getItems();
				System.out.println(ListItems.size());
				while(ListItems.size()!=0)
				{
					ListItems.remove(0);
				}
			}
		}
	}
	productId.setText("");
	productName.setText("");
	
}



@FXML public void addNewProduct()
{
	SpringFxmlLoader loader=SpringFxmlLoader.getInstance();
	AnchorPane addProduct=(AnchorPane) loader.load(URLS.ADD_PRODUCT_DETAILS_PAGE);
	BorderPane root = new BorderPane();
	root.setCenter(addProduct);
	layoutController.loadWindow(root,"Add Product Details",Constants.POPUP_WINDOW_WIDTH,Constants.POPUP_WINDOW_HEIGHT);
}

public void deleteButtonClickedThroughHyperlink(int productId)
{
	System.out.println("Inside DeleteButtonClicked");
	ObservableList< ProductDetails> ListItems;
	ListItems=ProductTable.getItems();
	for(int j = 0;j<ListItems.size();j++)
	{
		if(productId==ListItems.get(j).getProductId())
		{
			ListItems.remove(j);
		}
	}
	productService.deleteProduct(productId);
	
}
@FXML public void deleteButtonClicked()
{
	
	System.out.println("Inside DeleteButtonClicked");
	ObservableList< ProductDetails> ListItems,SelectedListItem;
	ListItems=ProductTable.getItems();
	SelectedListItem=ProductTable.getSelectionModel().getSelectedItems();	
	int id=SelectedListItem.get(0).getProductId();
	SelectedListItem.forEach(ListItems::remove);
	productService.deleteProduct(id);
	
	
	
}
@Override
public void initialize(URL arg0, ResourceBundle arg1) 
{	
	//this.customerName.textProperty().bind(this.customer.getName());
	System.out.println("Inside Initialize");
	ProductTable.setItems(data);
	populate(retrieveData());	
}
	
	
private List<ProductDetails> retrieveData(){
	
	try 
	{
		if(productList.isEmpty())
		{
			productList=productService.fetchProducts();
		}
		System.out.println(productList.get(0).getDescription());
	return productList;
			
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	return new ArrayList<ProductDetails>();
	
	
}
	
private void populate(final List < ProductDetails > products) 
{
	try {
	System.out.println("inside populate");
	if(data.isEmpty())
	{
        for(ProductDetails prods:products)
        {
        	data.add(prods);
        	int pid=prods.getProductId();
        	prods.getDelete().setOnAction(e->deleteButtonClickedThroughHyperlink(pid));
        }
      // System.out.println( data.get(0).getName());   /**  Do not get element from data cause if its empty then it will cause an exception **/
	}
	}catch(Exception e)
	{
		System.out.println(e.getMessage());
	}

}

}