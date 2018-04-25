package com.cschool.shop.managment.client.viewshop;

import java.util.HashSet;
import java.util.Set;

import com.cschool.shop.managment.client.service.ProductServiceRPC;
import com.cschool.shop.managment.client.service.ProductServiceRPCAsync;
import com.cschool.shop.managment.shared.model.Category;
import com.cschool.shop.managment.shared.model.Product;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.sun.java.swing.plaf.windows.resources.windows;

public class AddProductLayout extends DialogBox {
	
	
	private VerticalPanel verticalDialog;
	private HorizontalPanel hpName, hpPrice, hpAvailable, hpImage, hpButtons;
	private FormPanel fp;
	
	private Label nameLabel, priceLabel, availableLabel, imageLabel, categoriesLabel;
	protected TextBox nameBox, priceBox, imageBox;
	protected CheckBox availableBox;
	protected Button saveButton, cancelButton;
	
	
	public AddProductLayout() {
		this.initialize();
		
		this.setText("Add product");
		this.setAnimationEnabled(true);
		this.center();

		this.setWidget(verticalDialog);

		cancelButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				hide();	
			}
		});
		
	}
	
	private void initialize() {
		verticalDialog = new VerticalPanel();
		
		hpName = new HorizontalPanel();
		nameLabel = new Label("Name:");
		nameBox = new TextBox();
		hpName.add(nameLabel);
		hpName.add(nameBox);
		
		hpPrice = new HorizontalPanel();
		priceLabel = new Label("Price: ");
		priceBox = new TextBox();
		hpPrice.add(priceLabel);
		hpPrice.add(priceBox);
		
		hpAvailable = new HorizontalPanel();
		availableLabel = new Label("Available:");
		availableBox = new CheckBox();
		hpAvailable.add(availableLabel);
		hpAvailable.add(availableBox);
		
		hpImage = new HorizontalPanel();
		imageLabel = new Label("Image:");
		imageBox = new TextBox();
		hpImage.add(imageLabel);
		hpImage.add(imageBox);
		
		
		categoriesLabel = new Label("Categorioes:");
		
		hpButtons = new HorizontalPanel();
		saveButton = new Button("Save");
		cancelButton = new Button("Cancel");
		hpButtons.add(saveButton);
		hpButtons.add(cancelButton);
		
		
		verticalDialog.add(hpName);
		verticalDialog.add(hpPrice);
		verticalDialog.add(hpAvailable);
		verticalDialog.add(hpImage);
		verticalDialog.add(hpButtons);
	}
	
	private void initializeFormPanel() {
		fp = new FormPanel();
		fp.setTitle("Tytulek");
		
		verticalDialog = new VerticalPanel();
		
		fp.setWidget(verticalDialog);
		
		nameLabel = new Label("Name:");
		nameBox = new TextBox();
		nameBox.setName("nazwa boxu");
		verticalDialog.add(nameBox);
		
		fp.addSubmitHandler(new FormPanel.SubmitHandler() {
			
			@Override
			public void onSubmit(SubmitEvent event) {
				if (nameBox.getText().length() == 0) {
					
				}
				
			}			
		});
		

	}
	
}
