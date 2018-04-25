package com.cschool.shop.managment.client.viewshop;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.cschool.shop.managment.client.service.CategoryServiceRPC;
import com.cschool.shop.managment.client.service.CategoryServiceRPCAsync;
import com.cschool.shop.managment.shared.model.Category;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CategoryLayout extends VerticalPanel {
	
	private final CategoryServiceRPCAsync categoryService = GWT.create(CategoryServiceRPC.class);	
	
	private AddCategoryLayout addCategoryLayout;
	
	private CellTable<Category> categoryTable;
	private TextColumn<Category> idTable, nameTable;
	private Button addButton;
	
	public CategoryLayout() {
		initialize();
		this.add(addButton);
		this.add(categoryTable);
		
		addButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addCategoryLayout = new AddCategoryLayout();
				add(addCategoryLayout);
				addCategoryLayout.show();
				addCategoryLayout.saveButton.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						String name = addCategoryLayout.nameBox.getText();
						if (name.length() == 0) {
							Window.alert("Cannot add category with empty name");
						} else {
							categoryService.addCategory(new Category(name), new AsyncCallback<Boolean>() {

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void onSuccess(Boolean result) {
									refreshCategoryTable();
									
								}
								
							});
						}
						
					}
				});
			}
		});

	}
	
	private void initialize() {
		addButton = new Button("Add category");
		
		categoryTable = new CellTable<>();
		
		idTable = new TextColumn<Category>() {
			@Override
			public String getValue(Category category) {
				return String.valueOf(category.getCategoryId());
			}
		};
		nameTable = new TextColumn<Category>() {
			@Override
			public String getValue(Category category) {
				return category.getCategoryName();
			}
		};
		
		categoryTable.addColumn(idTable, "ID");
		categoryTable.addColumn(nameTable, "Category");

		refreshCategoryTable();
		
//		categoryService.getAllCategories(new AsyncCallback<List<Category>>() {
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("B³¹d w Category async (onFailure)");	
//			}
//
//			@Override
//			public void onSuccess(List<Category> result) {	
//				categoryTable.setRowData(result);
//			}	
//		});	
	}
	
	private void refreshCategoryTable() {
		categoryService.getAllCategories(new AsyncCallback<List<Category>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("B³¹d w Category async (onFailure)");	
			}

			@Override
			public void onSuccess(List<Category> result) {	
				categoryTable.setRowData(result);
			}	
		});	
	}
}
