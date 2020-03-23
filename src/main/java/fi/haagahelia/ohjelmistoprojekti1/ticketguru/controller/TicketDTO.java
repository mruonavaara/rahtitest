package fi.haagahelia.ohjelmistoprojekti1.ticketguru.controller;


import java.time.Instant;
import java.time.LocalDateTime;

import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.Ticket;

public class TicketDTO {
	private long id;
	private String code;
	private Instant used;
	private double price;
	private String type;
	private long eventId;
	private long transactionId;
	
	public TicketDTO(long id, String code, Instant used, double price, String type, long eventId, long transactionId) {
		super();
		this.id = id;
		this.code = code;
		this.used = used;
		this.price = price;
		this.type = type;
		this.eventId = eventId;
		this.transactionId = transactionId;
	}
	
	public TicketDTO(Ticket ticket) {
		this.id = ticket.getId();
		this.code = ticket.getCode();
		this.used = ticket.getUsed();
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Instant getUsed() {
		return used;
	}

	public void setUsed(Instant used) {
		this.used = used;
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
