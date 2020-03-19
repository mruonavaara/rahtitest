package fi.haagahelia.ohjelmistoprojekti1.ticketguru.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TicketType {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String description;
	private double price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="event")
	Event event;
	

	public TicketType() {
		super();
	}

	public TicketType(long id, String description, double price, Event event) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
		this.event = event;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@JsonIgnore
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	} 
	
}
