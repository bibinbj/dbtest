package com.code.dbtest.mongo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reservation")
public class Reservation {

    @Id
    private long id;
    private String orderId;
    private long deliverId;
    private long fullLoc;
    private Date creationDate = new Date();
    private String address;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public long getDeliverId() {
		return deliverId;
	}
	public void setDeliverId(long deliverId) {
		this.deliverId = deliverId;
	}
	public long getFullLoc() {
		return fullLoc;
	}
	public void setFullLoc(long fullLoc) {
		this.fullLoc = fullLoc;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    
}
