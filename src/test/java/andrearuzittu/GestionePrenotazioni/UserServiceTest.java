package andrearuzittu.GestionePrenotazioni;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import andrearuzittu.GestionePrenotazioni.model.Utente;
import andrearuzittu.GestionePrenotazioni.repository.UserRepository;
import andrearuzittu.GestionePrenotazioni.service.UserService;

@SpringBootTest
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@Test
	@DisplayName("Test creazione utente")
	void testCreateUser() {
		Utente user = new Utente("username", "Nome Cognome", "email@example.com");

		when(userRepository.save(user)).thenReturn(user);

		Utente createdUser = userService.createUser(user);

		assertNotNull(createdUser, "L'utente creato non dovrebbe essere nullo");
		assertEquals(user, createdUser, "L'utente creato dovrebbe corrispondere all'utente specificato");
	}

	@Test
	@DisplayName("Test ottenimento di tutti gli utenti")
	void testGetAllUsers() {
		List<Utente> userList = new ArrayList<>();
		userList.add(new Utente("user1", "Nome1 Cognome1", "email1@example.com"));
		userList.add(new Utente("user2", "Nome2 Cognome2", "email2@example.com"));

		when(userRepository.findAll()).thenReturn(userList);

		List<Utente> allUsers = userService.getAllUsers();

		assertNotNull(allUsers, "La lista di utenti non dovrebbe essere nulla");
		assertEquals(2, allUsers.size(), "La lista dovrebbe contenere due utenti");
	}

	@Test
	@DisplayName("Test trova un utente per ID")
	void testGetUserById() {
		Long userId = 1L;
		Utente user = new Utente("username", "Nome Cognome", "email@example.com");
		user.setId(userId);

		when(userRepository.findById(userId)).thenReturn(Optional.of(user));

		Utente foundUser = userService.getUserById(userId);

		assertNotNull(foundUser, "L'utente trovato non dovrebbe essere nullo");
		assertEquals(userId, foundUser.getId(), "L'ID dell'utente dovrebbe corrispondere all'ID specificato");
	}

	@Test
	@DisplayName("Test eliminazione di un utente per ID")
	void testDeleteUserById() {
		Long userId = 1L;

		userService.deleteUserById(userId);

		verify(userRepository, times(1)).deleteById(userId);
	}

}
