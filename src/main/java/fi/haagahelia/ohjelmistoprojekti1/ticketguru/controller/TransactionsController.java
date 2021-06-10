package fi.haagahelia.ohjelmistoprojekti1.ticketguru.controller;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.Transaction;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository.TransactionRepository;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {
	
	@Autowired
	private TransactionRepository transactions;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Transaction addTransaction() {
		Transaction newTransaction = new Transaction();
		newTransaction.setTime(Instant.now());
		return transactions.save(newTransaction);
	}
	
	@GetMapping
	public List<Transaction> getTransactions() {
		return transactions.findAll();
	}
	
	@GetMapping("/{id}")
	public Transaction getTransactionById(@PathVariable long id) throws Exception {
		Optional<Transaction> result = transactions.findById(id);
		
		if (!result.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction id not valid: " + id);
		}
		
		return result.get();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTransactionById(@PathVariable Long id) {
		transactions.deleteById(id);
	}

}
