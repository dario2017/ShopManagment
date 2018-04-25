package com.cschool.shop.managment.client.viewshop;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ShopLayout extends TabPanel {
	
	public ShopLayout() {
		
//	w products mamy liste produktow a powyzej tej listy jest button Add
//	po kliknieciu na dany produkt powinien wyswietlac sie button remove oraz edit obok 
//		buttonu add
		
		
		String tab1 = "Products";
		String tab2 = "Categories";

		this.add(new ProductsLayout(), tab1);
		this.add(new CategoryLayout(), tab2);

	}
	
}
