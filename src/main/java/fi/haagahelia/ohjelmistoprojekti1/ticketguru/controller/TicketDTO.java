package fi.haagahelia.ohjelmistoprojekti1.ticketguru.controller;

import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.Ticket;

public class TicketDTO {
	private long id;
	private double price;
	private String type;
	private long eventId;
	private long transactionId;
	
	public TicketDTO(long id, double price, String type, long eventId, long transactionId) {
		super();
		this.id = id;
		this.price = price;
		this.type = type;
		this.eventId = eventId;
		this.transactionId = transactionId;
	}
	
	public TicketDTO(Ticket ticket) {
		this.id = ticket.getId();
		this.price = ticket.getPrice();
		this.type = ticket.getType();
		this.eventId = ticket.getEvent().getId();
		this.transactionId = ticket.getTransaction().getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	
}
