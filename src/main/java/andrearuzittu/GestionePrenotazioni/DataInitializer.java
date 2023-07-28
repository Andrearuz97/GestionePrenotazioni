package andrearuzittu.GestionePrenotazioni;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import andrearuzittu.GestionePrenotazioni.model.Booking;
import andrearuzittu.GestionePrenotazioni.model.Building;
import andrearuzittu.GestionePrenotazioni.model.Utente;
import andrearuzittu.GestionePrenotazioni.model.Workstation;
import andrearuzittu.GestionePrenotazioni.model.WorkstationType;
import andrearuzittu.GestionePrenotazioni.repository.BookingRepository;
import andrearuzittu.GestionePrenotazioni.repository.BuildingRepository;
import andrearuzittu.GestionePrenotazioni.repository.UserRepository;
import andrearuzittu.GestionePrenotazioni.repository.WorkstationRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private WorkstationRepository workstationRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public void run(String... args) throws Exception {
		// Popolamento dell'utente
		Utente user1 = new Utente("Andrea_ruz97", "Andrea Ruzittu", "ruzittu.andrea97@gmail.com");
		user1 = userRepository.save(user1);

		Utente user2 = new Utente("Mario_123", "Mario Rossi", "mario.rossi@live.com");
		user2 = userRepository.save(user2);

		Utente user3 = new Utente("Gerolamo_xD", "Gerolamo Proietti", "geroproie@hotmail.it");
		user3 = userRepository.save(user3);

		// Popolamento dell'edificio
		Building building1 = new Building("Edificio A", "Via xx Settembre 16C", "Roma");
		building1 = buildingRepository.save(building1);

		Building building2 = new Building("Edificio B", "Via Maradona 80B", "Napoli");
		building2 = buildingRepository.save(building2);

		Building building3 = new Building("Edificio C", "Via Giotto 15A", "Olbia");
		building3 = buildingRepository.save(building3);

		// Popolamento della postazione
		Workstation workstation1 = new Workstation("WS001", "Scrivania 1", WorkstationType.PRIVATO, 1, building1);
		workstation1 = workstationRepository.save(workstation1);

		Workstation workstation2 = new Workstation("WS002", "Scrivania 2", WorkstationType.OPENSPACE, 1, building2);
		workstation2 = workstationRepository.save(workstation2);

		Workstation workstation3 = new Workstation("WS003", "Scrivania 3", WorkstationType.OPENSPACE, 1, building3);
		workstation3 = workstationRepository.save(workstation3);

		// Prenotazione delle postazioni per gli utenti
		Booking booking1 = new Booking(LocalDate.now(), workstation1, user1);
		booking1 = bookingRepository.save(booking1);

		Booking booking2 = new Booking(LocalDate.now().plusDays(1), workstation2, user2);
		booking2 = bookingRepository.save(booking2);

		Booking booking3 = new Booking(LocalDate.now().plusDays(1), workstation3, user3);
		booking3 = bookingRepository.save(booking3);
	}
}
