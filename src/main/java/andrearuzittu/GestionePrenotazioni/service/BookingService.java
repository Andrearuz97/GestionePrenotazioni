package andrearuzittu.GestionePrenotazioni.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andrearuzittu.GestionePrenotazioni.model.Booking;
import andrearuzittu.GestionePrenotazioni.model.Utente;
import andrearuzittu.GestionePrenotazioni.model.Workstation;
import andrearuzittu.GestionePrenotazioni.repository.BookingRepository;

@Service
public class BookingService {
	@Autowired
	private BookingRepository bookingRepository;

	public Booking createBooking(LocalDate date, Workstation workstation, Utente user) {
		if (isWorkstationAvailableForDate(workstation, date)) {
			Booking booking = new Booking(date, workstation, user);
			return bookingRepository.save(booking);
		} else {
			return null;
		}
	}

	public boolean isWorkstationAvailableForDate(Workstation workstation, LocalDate date) {

		Booking existingBooking = bookingRepository.findByDateAndWorkstation(date, workstation);
		return existingBooking == null;
	}

	public List<Booking> getUserBookings(Utente user) {
		return bookingRepository.findByUser(user);
	}

}
