package fi.haagahelia.ohjelmistoprojekti1.ticketguru;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.haagahelia.ohjelmistoprojekti1.ticketguru.controller.DetailsService;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.controller.TicketDTO;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.Event;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.Ticket;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.TicketType;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.Transaction;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository.EventRepository;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository.TicketRepository;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository.TicketTypeRepository;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository.TransactionRepository;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Transaction integrations tests")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionIntegrationTests {

	@Autowired
	private TestRestTemplate template;

	@MockBean
	private EventRepository mockEvents;
	@MockBean
	private TicketTypeRepository mockTicketTypes;
	@MockBean
	private TransactionRepository mockTransactions;
	@MockBean
	private TicketRepository mockTickets;
	
	private Event event;
	
	@BeforeEach
	void createEventAndTicketTypes() {
		Event e = new Event(1L, "Test", LocalDateTime.of(2020, 4, 1, 18, 00), "Messukeskus", "Helsinki", 100);
		
		List<TicketType> ttList = new ArrayList<>();
		ttList.add(new TicketType(2, "Adult", 20.0, e));
		ttList.add(new TicketType(3, "Child", 10.0, e)); 
		
		when(mockEvents.findById(1L)).thenReturn(Optional.of(e));
		when(mockTicketTypes.findAllByEvent_Id(1L)).thenReturn(ttList);
		this.event = e;
	}

	@Test
	void createTransaction() {
		String url = "/transactions";
		Transaction t = new Transaction(100, Instant.now(), 0.0);
		
		when(mockTransactions.save(any(Transaction.class))).thenReturn(t);

		ResponseEntity<Transaction> tResp = template.withBasicAuth("user1", "password1").postForEntity(url, t, Transaction.class);
		assertEquals(HttpStatus.CREATED, tResp.getStatusCode());
		assertNotNull(tResp.getBody());
		assertEquals(100, tResp.getBody().getId());
	}

	@Test
	void deleteTransaction() {
		String url = "/transactions/100"; 
		
		doNothing().when(mockTransactions).deleteById(100L);

		HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
		ResponseEntity<String> resp = template.withBasicAuth("user1", "password1").exchange(url, HttpMethod.DELETE, entity, String.class);
		assertNull(resp.getBody());
		assertEquals(HttpStatus.NO_CONTENT, resp.getStatusCode());
	}

	@Test
	void createTicketForTransaction() {
		String url = "/transactions/100/tickets";
		
		Transaction t = new Transaction(100, Instant.now(), 0.0);
		when(mockTransactions.findById(100L)).thenReturn(Optional.of(t));
		
		Ticket ti = new Ticket(1000, 20.0, "Aikuinen", this.event, t);
		when(mockTickets.save(any(Ticket.class))).thenReturn(ti);
		
		TicketDTO tiDto = new TicketDTO(ti); 
		
		ResponseEntity<TicketDTO> resp = template.withBasicAuth("user1", "password1").postForEntity(url, tiDto, TicketDTO.class);

		assertEquals(HttpStatus.CREATED, resp.getStatusCode());
		assertEquals(ti.getId(), resp.getBody().getId());
	}
}
