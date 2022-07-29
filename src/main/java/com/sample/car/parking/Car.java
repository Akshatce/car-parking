package com.sample.car.parking;

public class Car {
	
	public Car(int slotNo) {
		super();
		this.slotNo = slotNo;
	}

	public Car(String registrationNumber, String color, int slotNo) {
		super();
		this.registrationNumber = registrationNumber;
		this.color = color;
		this.slotNo = slotNo;
	}

	private String registrationNumber;
	private String color;
	private int slotNo;

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getSlotNo() {
		return slotNo;
	}

	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}

	@Override
	public String toString() {
		return "Car [registrationNumber=" + registrationNumber + ", color=" + color + ", slotNo=" + slotNo + "]";
	}
	
	

}
