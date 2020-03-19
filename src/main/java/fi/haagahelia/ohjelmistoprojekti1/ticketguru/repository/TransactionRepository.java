package fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
