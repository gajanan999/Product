package com.billdiary.ui;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.billdiary.config.SpringFxmlLoader;
import com.billdiary.dao.LoginDAO;
import com.billdiary.dao.ProductDAO;
import com.billdiary.entities.Product;
import com.billdiary.model.ProductDetails;

import com.billdiary.utility.Constants;
import com.billdiary.utility.URLS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

@Controller("ManageProductController")
@Service
public class ManageProductController {
	//final static Logger LOGGER = Logger.getLogger(ManageProductController.class);
	@Autowired
	public LoginDAO loginDAO;
	@Autowired
	public ProductDAO productDAO;
	@Autowired
	public LayoutController layoutController;
	@FXML private TextField productName;
	@FXML private TextField productId;
	@FXML private TableView<ProductDetails>ProductTable;
	@FXML private TableColumn<ProductDetails,Integer>Id;
	@FXML private TableColumn<ProductDetails,String>NameOfProduct;
	@FXML private TableColumn<ProductDetails,String>Description;
	@FXML private TableColumn<ProductDetails,Double>WholesalePrice;
	@FXML private TableColumn<ProductDetails,Double>RetailPrice;
	@FXML private TableColumn<ProductDetails,Double>Discount;
	@FXML private TableColumn<ProductDetails,Integer>Stock;
	@FXML private TableColumn<ProductDetails, Hyperlink>Delete;	

@FXML public void searchProduct()
{
	System.out.println("Inside Search product");
	ObservableList<ProductDetails> data1 = FXCollections.observableArrayList();
	List<Product> obj = productDAO.fetchProducts();
	ProductDetails[] pd = new ProductDetails[obj.size()];
	String ProdName = productName.getText();
	if(productId.getText().equals(""))
	{
		productId.setText("0");
	}
	Integer ProdId = Integer.parseInt(productId.getText());
	System.out.println("prod name is:"+productName.getText());
	 
	boolean search=true;
	for(int i=0;i<obj.size();i++)
	{
		
		if(ProdName.equals("") && ProdId==0)
		{	System.out.println("inside if");
			initialize();
			search=false;
			break;
		}
		else if(!ProdName.equals("") && ProdId!=0)
		{
			search=false;
		}
		
		System.out.println(Integer.parseInt(productId.getText()));
		if(ProdId==(obj.get(i).getId()) && ProdName.equals(obj.get(i).getName()))
		{
			Hyperlink Delete1 = new Hyperlink();
			pd[i]=new ProductDetails(obj.get(i).getId(), obj.get(i).getName(), obj.get(i).getWholesale_price(), obj.get(i).getRetail_price(), obj.get(i).getDescription(), obj.get(i).getStock(), obj.get(i).getDiscount(), Delete1);
			data1.add(pd[i]);
			System.out.println(data1.size());
			
			NameOfProduct.setCellValueFactory(new PropertyValueFactory<ProductDetails,String>("name"));
			Id.setCellValueFactory(new PropertyValueFactory<ProductDetails,Integer>("productId"));
			Description.setCellValueFactory(new PropertyValueFactory<ProductDetails,String>("description"));
			WholesalePrice.setCellValueFactory(new PropertyValueFactory<ProductDetails,Double>("wholesalePrice"));
			RetailPrice.setCellValueFactory(new PropertyValueFactory<ProductDetails,Double>("retailPrice"));
			Discount.setCellValueFactory(new PropertyValueFactory<ProductDetails,Double>("discount"));
			Stock.setCellValueFactory(new PropertyValueFactory<ProductDetails,Integer>("stock"));
			ProductTable.setItems(data1);
			int pid=pd[i].getProductId();
			pd[i].getDelete().setOnAction(e->deleteButtonClicked1(pid));
			search=false;
			break;
		}
		if(i==obj.size()-1)
		{
			System.out.println("inside else");
			ObservableList< ProductDetails> ListItems,SelectedListItem;
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
		for(int i =0 ; i<obj.size();i++)
		{
			if(ProdId ==(obj.get(i).getId())|| ProdName.equals(obj.get(i).getName()))
			{
				Hyperlink Delete1 = new Hyperlink();
				pd[i]=new ProductDetails(obj.get(i).getId(), obj.get(i).getName(), obj.get(i).getWholesale_price(), obj.get(i).getRetail_price(), obj.get(i).getDescription(), obj.get(i).getStock(), obj.get(i).getDiscount(), Delete1);
				data1.add(pd[i]);
				System.out.println(data1.size());
				NameOfProduct.setCellValueFactory(new PropertyValueFactory<ProductDetails,String>("name"));
				Id.setCellValueFactory(new PropertyValueFactory<ProductDetails,Integer>("productId"));
				Description.setCellValueFactory(new PropertyValueFactory<ProductDetails,String>("description"));
				WholesalePrice.setCellValueFactory(new PropertyValueFactory<ProductDetails,Double>("wholesalePrice"));
				RetailPrice.setCellValueFactory(new PropertyValueFactory<ProductDetails,Double>("retailPrice"));
				Discount.setCellValueFactory(new PropertyValueFactory<ProductDetails,Double>("discount"));
				Stock.setCellValueFactory(new PropertyValueFactory<ProductDetails,Integer>("stock"));
				ProductTable.setItems(data1);
				int k=0;
				int pid=pd[i].getProductId();
				pd[i].getDelete().setOnAction(e->deleteButtonClicked1(pid));
				search=false;	
			}
			if(i==obj.size()-1&& search ==true)
			{
				System.out.println("inside else");
				ObservableList< ProductDetails> ListItems,SelectedListItem;
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

@FXML public void initialize()
{
	ObservableList<ProductDetails> data = FXCollections.observableArrayList();
	List<Product> obj = new ArrayList<Product>();
	System.out.println("Calling Fetch Products");
	obj = productDAO.fetchProducts();
	 int length = obj.size();
	 System.out.println("length is:"+length);
	 ProductDetails[] pd = new ProductDetails[length];
	 int count=0;
	 for(int i=0;i<obj.size();i++)
	 {
		 try
		 {
			 Hyperlink Delete1 = new Hyperlink();
			 pd[i]=new ProductDetails(obj.get(i).getId(), obj.get(i).getName(), obj.get(i).getWholesale_price(), obj.get(i).getRetail_price(), obj.get(i).getDescription(), obj.get(i).getStock(), obj.get(i).getDiscount(), Delete1);
		 	 data.add(pd[i]);
		 }
		 catch(Exception e)
		 {
			 System.out.println("error is:"+e.getMessage());
		 }
		
	 }	
	 System.out.println("After calling fetch products");
	NameOfProduct.setCellValueFactory(new PropertyValueFactory<ProductDetails,String>("name"));
	Id.setCellValueFactory(new PropertyValueFactory<ProductDetails,Integer>("productId"));
	Description.setCellValueFactory(new PropertyValueFactory<ProductDetails,String>("description"));
	WholesalePrice.setCellValueFactory(new PropertyValueFactory<ProductDetails,Double>("wholesalePrice"));
	RetailPrice.setCellValueFactory(new PropertyValueFactory<ProductDetails,Double>("retailPrice"));
	Discount.setCellValueFactory(new PropertyValueFactory<ProductDetails,Double>("discount"));
	Stock.setCellValueFactory(new PropertyValueFactory<ProductDetails,Integer>("stock"));
	Delete.setCellValueFactory(new PropertyValueFactory<ProductDetails,Hyperlink>("Delete"));
	int k=0;
	for( k=0;k<obj.size();k++)
	{
		int pid=pd[k].getProductId();
		pd[k].getDelete().setOnAction(e->deleteButtonClicked1(pid));
	}
	
	ProductTable.setItems(data);
}

@FXML public void addNewProduct()
{
	SpringFxmlLoader loader=SpringFxmlLoader.getInstance();
	AnchorPane addProduct=(AnchorPane) loader.load(URLS.ADD_PRODUCT_DETAILS_PAGE);
	BorderPane root = new BorderPane();
	root.setCenter(addProduct);
	layoutController.loadWindow(root,"Add Product Details",Constants.POPUP_WINDOW_WIDTH,Constants.POPUP_WINDOW_HEIGHT);
	
	
}

public void deleteButtonClicked1(int productId)
{
	System.out.println("Inside DeleteButtonClicked");
	
	ObservableList< ProductDetails> ListItems,SelectedListItem;
	ListItems=ProductTable.getItems();
	for(int j = 0;j<ListItems.size();j++)
	{
		if(productId==ListItems.get(j).getProductId())
		{
			ListItems.remove(j);
		}
	}
	
}
@FXML
public void deleteButtonClicked()
{
	System.out.println("Inside DeleteButtonClicked");
	
	ObservableList< ProductDetails> ListItems,SelectedListItem;
	ListItems=ProductTable.getItems();
	
	SelectedListItem=ProductTable.getSelectionModel().getSelectedItems();	
	SelectedListItem.forEach(ListItems::remove);
	
}
}