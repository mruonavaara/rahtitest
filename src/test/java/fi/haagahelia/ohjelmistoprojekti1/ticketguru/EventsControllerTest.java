package fi.haagahelia.ohjelmistoprojekti1.ticketguru;

import java.time.LocalDateTime;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.haagahelia.ohjelmistoprojekti1.ticketguru.controller.EventsController;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.Event;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository.EventRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EventsController.class)
public class EventsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EventRepository eventRepositoryMock;

	@Test
	@WithMockUser(username = "user1", password = "password1", roles = "USER")
	public void shouldGetAllEvents() throws Exception {
		// Event(long id, String name, LocalDateTime time, String venue, String city, int ticketsTotal)
		Event event = new Event(0L, "Nimi", LocalDateTime.of(2020, 3, 1, 19, 0), "Paikka", "Kaupunki", 100);
		
		List<Event> events = Arrays.asList(event);
		
		when(eventRepositoryMock.findAll()).thenReturn(events);
		
		
		mockMvc.perform(get("/api/events")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$", hasSize(1)))
			      .andExpect(jsonPath("$[0].name", is("Nimi")));
	}
}
