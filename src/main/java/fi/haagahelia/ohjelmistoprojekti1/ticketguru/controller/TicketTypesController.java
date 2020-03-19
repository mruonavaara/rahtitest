package fi.haagahelia.ohjelmistoprojekti1.ticketguru.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.Event;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.TicketType;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository.EventRepository;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository.TicketTypeRepository;

@RestController
public class TicketTypesController {
	@Autowired
	TicketTypeRepository ticketTypeRepository;
	
	@Autowired 
	EventRepository eventRepository;
	
	@GetMapping("/events/{eventId}/tickettypes")
	public List<TicketType> getTicketTypes(@PathVariable long eventId) {
		
		return ticketTypeRepository.findAllByEvent_Id(eventId); 
	}
	
	@PostMapping("/events/{eventId}/tickettypes")
	@ResponseStatus(HttpStatus.CREATED)
	public TicketType addTicketType(@Valid @RequestBody TicketType newTicketType, @PathVariable long eventId) {
		Optional<Event> result = eventRepository.findById(eventId);

		if (!result.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
		}
		
		newTicketType.setEvent(result.get());
		System.out.println(newTicketType);
		return ticketTypeRepository.save(newTicketType);
	}
	
	@PutMapping("/tickettypes/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TicketType updateTicketType(@Valid @RequestBody TicketType updatedTicketType, @PathVariable long id) {
		Optional<TicketType> ticketTypeResult = ticketTypeRepository.findById(id);
		
		if (!ticketTypeResult.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
		}
		
		TicketType ticketType = ticketTypeResult.get();
		ticketType.setDescription(updatedTicketType.getDescription());
		ticketType.setPrice(updatedTicketType.getPrice());
		
		return ticketTypeRepository.save(updatedTicketType);
	}

	
	@DeleteMapping("/tickettypes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTicketType(@PathVariable long id) {
		
		ticketTypeRepository.deleteById(id); 
	}
	
}
