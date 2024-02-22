/*
Author: Shubham Samarth
Date: February 14, 2024
Description: This class represents a delivery partner entity in the FarmBazaar application. It extends the AbstractUser class and inherits common user properties such as username, password, name, phone number, address, role, and active status.
Last Modified: February 22, 2024
*/

package com.farmbazaar.model.entity;

import javax.persistence.*;

@Entity
public class DeliveryPartner extends AbstractUser {
	private int workload;
	
	// Method to get the current workload (number of assigned orders)
	public int getWorkload() {
        return workload;
    }
	
	// Method to increment the workload when a new order is assigned
	public void incrementWorkload() {
        workload++;
    }
}
