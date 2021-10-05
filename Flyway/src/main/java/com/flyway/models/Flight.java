package com.flyway.models;

import java.sql.Date;

public class Flight {

	private Long flightId;
	private String flightCode;

	private Long airlineId;
	private Long fromPlaceId;
	private Long toPlaceId;

	private Integer passengerCapacity;
	private Date departureDate;

	public Long getFlightId() {
		return flightId;
	}
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
	public String getFlightCode() {
		return flightCode;
	}
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}
	public Long getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(Long airlineId) {
		this.airlineId = airlineId;
	}
	public Long getFromPlaceId() {
		return fromPlaceId;
	}
	public void setFromPlaceId(Long fromPlaceId) {
		this.fromPlaceId = fromPlaceId;
	}
	public Long getToPlaceId() {
		return toPlaceId;
	}
	public void setToPlaceId(Long toPlaceId) {
		this.toPlaceId = toPlaceId;
	}

	public Integer getPassengerCapacity() {
		return passengerCapacity;
	}
	public void setPassengerCapacity(Integer passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

}
