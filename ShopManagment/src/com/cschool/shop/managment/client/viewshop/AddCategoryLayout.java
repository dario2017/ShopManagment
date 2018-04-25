package com.cschool.shop.managment.client.viewshop;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AddCategoryLayout extends DialogBox {
	
	
	private VerticalPanel verticalPanel;
	private HorizontalPanel hpName, hpButton;
	private Label nameLabel;
	protected TextBox nameBox;
	public Button saveButton, cancelButton;
	
	public AddCategoryLayout() {
		this.initialize();
		
		this.setText("Add category");
		this.setAnimationEnabled(true);
		this.center();
		
		this.add(verticalPanel);
		
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
	}
	
	private void initialize() {
		verticalPanel = new VerticalPanel();
		hpName = new HorizontalPanel();
		hpButton = new HorizontalPanel();
		nameLabel = new Label("Category name:");
		nameBox = new TextBox();
		saveButton = new Button("Save");
		cancelButton = new Button("Cancel");
		
		hpName.add(nameLabel);
		hpName.add(nameBox);
		
		hpButton.add(saveButton);
		hpButton.add(cancelButton);
		
		verticalPanel.add(hpName);
		verticalPanel.add(hpButton);
		
	}
}
