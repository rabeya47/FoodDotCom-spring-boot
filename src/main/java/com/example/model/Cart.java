package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="carts")
public class Cart {
	
	
	 @Transient
	 @JsonIgnore
	 MultipartFile file;	
	

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

private String productName;
private int quantity;
private double price;
private String status;
private double total;

private String images;
private String imagesUri;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
public void setPrice(double price) {
	this.price = price;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public MultipartFile getFile() {
	return file;
}
public void setFile(MultipartFile file) {
	this.file = file;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}
public String getImages() {
	return images;
}
public void setImages(String images) {
	this.images = images;
}
public String getImagesUri() {
	return imagesUri;
}
public void setImagesUri(String imagesUri) {
	this.imagesUri = imagesUri;
}



}
