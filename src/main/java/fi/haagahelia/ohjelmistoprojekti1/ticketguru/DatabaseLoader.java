package fi.haagahelia.ohjelmistoprojekti1.ticketguru;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fi.haagahelia.ohjelmistoprojekti1.ticketguru.model.User;
import fi.haagahelia.ohjelmistoprojekti1.ticketguru.repository.UserRepository;

@Component
public class DatabaseLoader implements ApplicationRunner {
	@Autowired
	private UserRepository users;

	public DatabaseLoader(UserRepository users) {
		this.users = users;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		List<User> userList = Arrays.asList(new User("user1", "password1", new String[] { "ROLE_USER" }),
				new User("user2", "password2", new String[] { "ROLE_USER", "ROLE_ADMIN" }));

		users.saveAll(userList);
	}

}
