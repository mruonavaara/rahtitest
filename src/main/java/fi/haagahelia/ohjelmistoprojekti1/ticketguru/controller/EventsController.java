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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.Event;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository.EventRepository;

@RestController
@RequestMapping("/api/events")
public class EventsController {

	@Autowired
	private EventRepository repository;

	@GetMapping
	public List<Event> getEvents() {

		List<Event> result = repository.findAll();

		return result;
	}

	@GetMapping(value = "/{id}")
	public Event getEventById(@PathVariable Long id) throws ResponseStatusException {

		Optional<Event> result = repository.findById(id);

		if (!result.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		return result.get();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Event addEvent(@Valid @RequestBody Event newEvent) {
		System.out.println(newEvent);
		return repository.save(newEvent);
	}

	@PutMapping("/{id}")
	public Event updateEventById(@PathVariable Long id, @Valid @RequestBody Event updated) {
			
		Optional<Event> result = repository.findById(id);

		if (!result.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		Event event = result.get();
		event.setName(updated.getName());
		event.setTime(updated.getTime());
		event.setVenue(updated.getVenue());
		event.setCity(updated.getCity());
		event.setTicketsTotal(updated.getTicketsTotal());
		
		return repository.save(event);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEventById(@PathVariable Long id) {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}
	}

}
