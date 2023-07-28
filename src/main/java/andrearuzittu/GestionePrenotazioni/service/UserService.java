package andrearuzittu.GestionePrenotazioni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andrearuzittu.GestionePrenotazioni.model.Utente;
import andrearuzittu.GestionePrenotazioni.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public Utente createUser(Utente user) {
		return userRepository.save(user);
	}

	public List<Utente> getAllUsers() {
		return userRepository.findAll();
	}

	public Utente getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

}
