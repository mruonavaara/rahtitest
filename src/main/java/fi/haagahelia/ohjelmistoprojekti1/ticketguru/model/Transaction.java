package fi.haagahelia.ohjelmistoprojekti1.ticketguru.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Instant time;
	
	private double amount;
	
	public Transaction() {
		super();
	}

	
	public Transaction(long id, Instant time, double amount) {
		super();
		this.id = id;
		this.time = time;
		this.amount = amount;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

	public Instant getTime() {
		return time;
	}


	public void setTime(Instant time) {
		this.time = time;
	}


	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
