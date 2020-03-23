package fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	public List<Ticket> findAllByTransaction_Id(long id); 
	public List<Ticket> findAllByCode(String code);
}
