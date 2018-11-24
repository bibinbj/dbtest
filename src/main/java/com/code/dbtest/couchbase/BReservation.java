package com.code.dbtest.couchbase;

import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class BReservation {

    @Id
    private long id;
    private String orderId;
    private long deliverId;
    private long fullLoc;
    private String creationDate;
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
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    
}
