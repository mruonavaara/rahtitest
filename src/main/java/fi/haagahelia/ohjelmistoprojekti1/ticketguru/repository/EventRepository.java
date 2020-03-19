/**
 * 
 */
package fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.Event;

/**
 * @author mruonavaara
 *
 */
public interface EventRepository extends JpaRepository<Event, Long> {

}
