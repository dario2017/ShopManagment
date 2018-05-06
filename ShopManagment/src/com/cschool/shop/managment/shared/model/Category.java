package com.cschool.shop.managment.shared.model;

import java.io.Serializable;

import com.cschool.shop.managment.client.viewshop.StaticFields;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Category implements IsSerializable {
	private static int idCounter;
	private int categoryId;
	private String categoryName;
	
	public Category() {
	}
	
	public Category(String categoryName) {
		idCounter++;
		if (StaticFields.getCategorySet().size() != 0) {
			int highestId = 0;
			for (Category each: StaticFields.getCategorySet()) {
				if (each.getCategoryId() > highestId) {
					highestId = each.getCategoryId();
				}
			}
			this.categoryId = highestId + 1;
		} else {
			this.categoryId = idCounter;
		}
		this.categoryName = categoryName;
	}
	
//	Contructor which manually sets Id
	public Category(int startId, String categoryName) {
		this.categoryId = startId;
		this.categoryName = categoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	@Override
	public String toString() {
		return categoryName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (categoryId != other.categoryId)
			return false;
		return true;
	}
}
