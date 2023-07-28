package andrearuzittu.GestionePrenotazioni;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import andrearuzittu.GestionePrenotazioni.model.Booking;
import andrearuzittu.GestionePrenotazioni.model.Utente;
import andrearuzittu.GestionePrenotazioni.model.Workstation;
import andrearuzittu.GestionePrenotazioni.repository.BookingRepository;
import andrearuzittu.GestionePrenotazioni.service.BookingService;

@SpringBootTest
public class BookingServiceTest {

	@Mock
	private BookingRepository bookingRepository;

	@InjectMocks
	private BookingService bookingService;

	@Test
	@DisplayName("Test prenotazione con postazione disponibile")
	void testCreateBookingWithAvailableWorkstation() {
		Workstation workstation = new Workstation();
		Utente user = new Utente();
		LocalDate date = LocalDate.now();

		when(bookingRepository.findByDateAndWorkstation(date, workstation)).thenReturn(null);

		Booking newBooking = bookingService.createBooking(date, workstation, user);

		assertNotNull(newBooking, "La prenotazione non dovrebbe essere nulla");
		assertEquals(date, newBooking.getDate(), "La data della prenotazione dovrebbe essere quella specificata");
		assertEquals(workstation, newBooking.getWorkstation(),
				"La postazione prenotata dovrebbe essere quella specificata");
		assertEquals(user, newBooking.getUser(), "L'utente della prenotazione dovrebbe essere quello specificato");
	}

	@Test
	@DisplayName("Test prenotazione con postazione già prenotata")
	void testCreateBookingWithBookedWorkstation() {
		Workstation workstation = new Workstation();
		Utente user = new Utente();
		LocalDate date = LocalDate.now();

		Booking existingBooking = new Booking();
		existingBooking.setId(1L);

		when(bookingRepository.findByDateAndWorkstation(date, workstation)).thenReturn(existingBooking);

		Booking newBooking = bookingService.createBooking(date, workstation, user);

		assertNull(newBooking,
				"La prenotazione dovrebbe essere nulla perché la postazione è già prenotata per quella data.");
	}

	@Test
	@DisplayName("Test prenotazione con postazione nulla")
	void testCreateBookingWithNullWorkstation() {
		Utente user = new Utente();
		LocalDate date = LocalDate.now();

		Booking newBooking = bookingService.createBooking(date, null, user);

		assertNull(newBooking, "La prenotazione dovrebbe essere nulla perché la postazione è nulla.");
	}

	@Test
	@DisplayName("Test prenotazione con data nulla")
	void testCreateBookingWithNullDate() {
		Workstation workstation = new Workstation();
		Utente user = new Utente();

		Booking newBooking = bookingService.createBooking(null, workstation, user);

		assertNull(newBooking, "La prenotazione dovrebbe essere nulla perché la data è nulla.");
	}

	@Test
	@DisplayName("Test prenotazione con utente nullo")
	void testCreateBookingWithNullUser() {
		Workstation workstation = new Workstation();
		LocalDate date = LocalDate.now();

		Booking newBooking = bookingService.createBooking(date, workstation, null);

		assertNull(newBooking, "La prenotazione dovrebbe essere nulla perché l'utente è nullo.");
	}
}
