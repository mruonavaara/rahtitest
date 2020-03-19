package fi.haagahelia.ohjelmistoprojekti1.ticketguru.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.Event;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.Ticket;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.TicketType;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.Transaction;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository.EventRepository;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository.TicketRepository;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository.TicketTypeRepository;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository.TransactionRepository;

@RestController
public class TicketController {
	@Autowired
	private TicketRepository tickets;
	
	@Autowired
	private TransactionRepository transactions;
	
	@Autowired
	private EventRepository events;
	
	@Autowired 
	private TicketTypeRepository ticketTypes;
	
	@PostMapping("/transactions/{transactionId}/tickets")
	@ResponseStatus(HttpStatus.CREATED)
	public Ticket addTicketToTransaction(@PathVariable long transactionId, @RequestBody TicketDTO ticketDto) {
		
		Optional<Transaction> transactionResult = transactions.findById(transactionId);
		
		if (!transactionResult.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction id not valid: " + transactionId);
		}
		
		Optional<Event> eventResult = events.findById(ticketDto.getEventId());
		
		if (!eventResult.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event id not valid: " + ticketDto.getEventId());
		}

		Ticket ticket = new Ticket();
		Transaction transaction = transactionResult.get();
		Event event = eventResult.get();
		
		double totalAmount = 0.0;
		for (Ticket t: tickets.findAllByTransaction_Id(transactionId)) {
			totalAmount += t.getPrice();
		}
		transaction.setAmount(totalAmount + ticketDto.getPrice());
		transactions.save(transaction);
		
		ticket.setTransaction(transaction);
		ticket.setEvent(event);
		ticket.setPrice(ticketDto.getPrice());
		ticket.setType(ticketDto.getType());

		return tickets.save(ticket);
	}

	@GetMapping("/transactions/{transactionId}/tickets") 
	public List<Ticket> getTicketsInTransaction(@PathVariable long transactionId) {
		Optional<Transaction> result = transactions.findById(transactionId);
		
		if (!result.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction id not valid: " + transactionId);
		}
					
		return tickets.findAllByTransaction_Id(transactionId);
	}

	@GetMapping("/tickets")
	public List<Ticket> getAllTickets() {
		return tickets.findAll();
	}

}
