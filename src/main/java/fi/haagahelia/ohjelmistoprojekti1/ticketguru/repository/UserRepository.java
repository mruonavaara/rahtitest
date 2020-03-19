package fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
