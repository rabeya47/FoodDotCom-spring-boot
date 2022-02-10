package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;

private String customerName;
private String productName;
private int quantity;
private double price;
private  String status;
private String remarks;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public double getPrice() {
	return price;
}


public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public void setPrice(double price) {
	this.price = price;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public Order(int id, String customerName, String productName, int quantity, double price, String status,
		String remarks) {
	super();
	this.id = id;
	this.customerName = customerName;
	this.productName = productName;
	this.quantity = quantity;
	this.price = price;
	this.status = status;
	this.remarks = remarks;
}
public Order() {
	super();
}




}
