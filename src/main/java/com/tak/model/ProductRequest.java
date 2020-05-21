package com.tak.model;

import java.io.Serializable;

public class ProductRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3059383208937939320L;
	String userId;
	String productName;
	int productQuantity;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productQuantity=" + productQuantity + "]";
	}
	
	

}
