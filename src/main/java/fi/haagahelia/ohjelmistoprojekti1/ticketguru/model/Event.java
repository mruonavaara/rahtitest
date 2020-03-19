/**
 * 
 */
package fi.haagahelia.ohjelmistoprojekti1.ticketguru.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author mruonavaara
 *
 */
@Entity
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotBlank
	private String name; 
	
	@NotNull
	private LocalDateTime time;
	
	@NotBlank
	private String venue;
	
	@NotBlank
	private String city;
	
	private int ticketsTotal;


	public Event() {
		super();
	}

	public Event(long id, String name, LocalDateTime time, String venue, String city, int ticketsTotal) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
		this.venue = venue;
		this.city = city;
		this.ticketsTotal = ticketsTotal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getTicketsTotal() {
		return ticketsTotal;
	}

	public void setTicketsTotal(int ticketsTotal) {
		this.ticketsTotal = ticketsTotal;
	}

}
