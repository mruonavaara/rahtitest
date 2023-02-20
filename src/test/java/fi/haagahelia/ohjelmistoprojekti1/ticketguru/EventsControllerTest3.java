package fi.haagahelia.ohjelmistoprojekti1.ticketguru;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDateTime;

import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.Event;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository.EventRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventsControllerTest3 {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private EventRepository eventRepository;

	@BeforeEach
	void setup() {
		eventRepository.save(new Event(0L, "Nimi1", LocalDateTime.of(2020, 3, 1, 19, 0), "Paikka1", "Kaupunki1", 100));
		eventRepository.save(new Event(0L, "Nimi2", LocalDateTime.of(2020, 3, 2, 19, 0), "Paikka2", "Kaupunki2", 200));
		eventRepository.save(new Event(0L, "Nimi3", LocalDateTime.of(2020, 3, 3, 19, 0), "Paikka3", "Kaupunki3", 300));
	}

	@Test
	@WithMockUser(username = "user1", password = "password1", roles = "USER")
	void getEvents() throws Exception {
		mockMvc.perform(get("/api/events")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)));
	}

	/*
	test setup fails because the primary key increases
	
	@Test
	@WithMockUser(username = "user1", password = "password1", roles = "USER")
	void getEvent() throws Exception {
		mockMvc.perform(get("/api/events/1"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", contains("Nimi1")));
	}
	 */
}
