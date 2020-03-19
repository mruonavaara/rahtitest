package fi.haagahelia.ohjelmistoprojekti1.ticketguru.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private double price;
	private String type;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="event")
	private Event event;

	/*
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tickettype")
	private TicketType ticketType;
*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="transaction")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Transaction transaction;

	public Ticket() {
		super();
	}

	public Ticket(long id, double price, String type, Event event, Transaction transaction) {
		super();
		this.id = id;
		this.price = price;
		this.type = type;
		this.event = event;
		this.transaction = transaction;
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

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
/*
	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}
*/
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
}
