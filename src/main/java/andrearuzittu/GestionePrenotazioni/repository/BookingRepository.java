package andrearuzittu.GestionePrenotazioni.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import andrearuzittu.GestionePrenotazioni.model.Booking;
import andrearuzittu.GestionePrenotazioni.model.Utente;
import andrearuzittu.GestionePrenotazioni.model.Workstation;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByUser(Utente user);

	Booking findByDateAndWorkstation(LocalDate date, Workstation workstation);
}
