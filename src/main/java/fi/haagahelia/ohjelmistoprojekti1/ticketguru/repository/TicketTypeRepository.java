package fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.TicketType;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
	public List<TicketType> findAllByEvent_Id(long id);

}
