package com.farmbazaar.model.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;
    private double quantity;
    private double pre_order_quantity;

    // Other product attributes

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPre_order_quantity() {
		return pre_order_quantity;
	}

	public void setPre_order_quantity(double pre_order_quantity) {
		this.pre_order_quantity = pre_order_quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	

   
}