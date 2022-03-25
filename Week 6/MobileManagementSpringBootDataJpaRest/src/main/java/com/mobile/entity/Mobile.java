package com.mobile.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mobile {
@Id
private int mobileId;
private String mobileName;
private String mobileType;
private int mobilePrice;
private int mobileWarenty;
private int mobileNumber;
public int getMobileId() {
	return mobileId;
}
public void setMobileId(int mobileId) {
	this.mobileId = mobileId;
}
public String getMobileName() {
	return mobileName;
}
public void setMobileName(String mobileName) {
	this.mobileName = mobileName;
}
public String getMobileType() {
	return mobileType;
}
public void setMobileType(String mobileType) {
	this.mobileType = mobileType;
}
public int getMobilePrice() {
	return mobilePrice;
}
public void setMobilePrice(int mobilePrice) {
	this.mobilePrice = mobilePrice;
}
public int getMobileWarenty() {
	return mobileWarenty;
}
public void setMobileWarenty(int mobileWarenty) {
	this.mobileWarenty = mobileWarenty;
}
public int getMobileNumber() {
	return mobileNumber;
}
public void setMobileNumber(int mobileNumber) {
	this.mobileNumber = mobileNumber;
}

public Mobile() {
	// TODO Auto-generated constructor stub
}
public Mobile(int mobileId, String mobileName, String mobileType, int mobilePrice, int mobileWarenty,
		int mobileNumber) {
	super();
	this.mobileId = mobileId;
	this.mobileName = mobileName;
	this.mobileType = mobileType;
	this.mobilePrice = mobilePrice;
	this.mobileWarenty = mobileWarenty;
	this.mobileNumber = mobileNumber;
}
@Override
public String toString() {
	return "Mobile [mobileId=" + mobileId + ", mobileName=" + mobileName + ", mobileType=" + mobileType
			+ ", mobilePrice=" + mobilePrice + ", mobileWarenty=" + mobileWarenty + ", mobileNumber=" + mobileNumber
			+ "]";
}

}
