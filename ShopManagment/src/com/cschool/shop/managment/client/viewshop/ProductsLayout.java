package com.cschool.shop.managment.client.viewshop;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.cschool.shop.managment.client.service.ProductServiceRPC;
import com.cschool.shop.managment.client.service.ProductServiceRPCAsync;
import com.cschool.shop.managment.shared.model.Category;
import com.cschool.shop.managment.shared.model.Product;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class ProductsLayout extends VerticalPanel {

	private final ProductServiceRPCAsync productService = GWT.create(ProductServiceRPC.class);
	
	private AddProductLayout addProductLayout;
	
	SingleSelectionModel<Product> selectionModel;
	Product selected;
	
	private HorizontalPanel horizontalPanel;
	private CellTable<Product> productTable;
	private TextColumn<Product> idColumn, nameColumn, categoryColumn, priceColumn, availableColumn, imageColumn;
	private Button addButton;
	private Button editButton;
	public Button removeButton;
	
	public ProductsLayout() {
		initialize();
		
		this.add(horizontalPanel);;
		this.add(productTable);
		
	}
	
	private void initialize() {
		addButton = new Button("Add");

		removeButton = new Button("Remove");
		removeButton.setEnabled(false);
		
		editButton = new Button("Edit");
		editButton.setEnabled(false);
		
		productTable = new CellTable<>();
		productTable.setPageSize(10);
		
		idColumn = new TextColumn<Product>() {
			@Override
			public String getValue(Product product) {
				return String.valueOf(product.getProductId());
			}
		};
		nameColumn = new TextColumn<Product>() {
			@Override
			public String getValue(Product product) {
				return product.getName();
			}
		};
		categoryColumn = new TextColumn<Product>() {
			@Override
			public String getValue(Product product) {
				return product.getCategorySet().toString();
			}
		};
		priceColumn = new TextColumn<Product>() {
			@Override
			public String getValue(Product product) {
				return String.valueOf(product.getPrice());
			}
		};
		availableColumn = new TextColumn<Product>() {
			@Override
			public String getValue(Product product) {
				return Boolean.toString(product.isAvailable());
			}
		};
		imageColumn = new TextColumn<Product>() {
			@Override
			public String getValue(Product product) {
				return product.getImage();
			}
		};
		
		idColumn.setSortable(true);
		nameColumn.setSortable(true);
		categoryColumn.setSortable(true);
		priceColumn.setSortable(true);
		availableColumn.setSortable(true);
		imageColumn.setSortable(true);

		productTable.addColumn(idColumn, "ID");
		productTable.addColumn(nameColumn, "Name");
		productTable.addColumn(categoryColumn, "Category");
		productTable.addColumn(priceColumn, "Price");
		productTable.addColumn(availableColumn, "Availablity");
		productTable.addColumn(imageColumn, "Image");
		
		
	
		

		horizontalPanel = new HorizontalPanel();
		horizontalPanel.add(addButton);
		horizontalPanel.add(editButton);
		horizontalPanel.add(removeButton);
		horizontalPanel.setSpacing(15);
			
		
		selectionModel();
//	sets list of all products to table
		refreshProductsTable();
		
		addButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				addProductLayout = new AddProductLayout();
				add(addProductLayout);
				addProductLayout.show();
				saveButtonHandler();
	
			}
		});
		
		removeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				selected = selectionModel.getSelectedObject();
				productService.removeProduct(selected, new AsyncCallback<Boolean>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Some error occured");
					}

					@Override
					public void onSuccess(Boolean result) {
						Window.alert("Product + " + selected.toString() + " has been deleted");
						refreshProductsTable();	
						removeButton.setEnabled(false);
						editButton.setEnabled(false);
					}
				});
			}
		});
		
		
	}	
	
	private void selectionModel() {
		selectionModel = new SingleSelectionModel<>();
		productTable.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				selected = selectionModel.getSelectedObject();
				if (selected != null) {
					editButton.setEnabled(true);
					removeButton.setEnabled(true);	
				}	
			}		
		});
	}
	
	private void saveButtonHandler() {
		addProductLayout.saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Set<Category> category = new HashSet<>();
				String name = addProductLayout.nameBox.getText();
				Double price = Double.parseDouble(addProductLayout.priceBox.getText());				
				String image = addProductLayout.imageBox.getText();
				boolean available = addProductLayout.availableBox.getValue();
				
				if (name.length() == 0 || price == null || image.length() == 0) {
					Window.alert("NIe dodam produktu bo cos jest nullem");
				} else {
					productService.addProduct(new Product(name, category, price, available, image), new AsyncCallback<Boolean>() {
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stu	
						}
	
						@Override
						public void onSuccess(Boolean result) {
							refreshProductsTable();	
						}
					});
				} 
			}
		});
	}
	
//	sets list of all products to table
	public void refreshProductsTable() {
		productService.getAllProducts(new AsyncCallback<List<Product>>() {	
			@Override
			public void onSuccess(List<Product> result) {
				productTable.setRowCount(result.size(), true);
				productTable.setRowData(0, result);	
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("b³ad w huj");
				
			}
		});
	}
}
