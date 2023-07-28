package andrearuzittu.GestionePrenotazioni;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import andrearuzittu.GestionePrenotazioni.model.Building;
import andrearuzittu.GestionePrenotazioni.model.Workstation;
import andrearuzittu.GestionePrenotazioni.model.WorkstationType;
import andrearuzittu.GestionePrenotazioni.repository.WorkstationRepository;
import andrearuzittu.GestionePrenotazioni.service.WorkstationService;

@SpringBootTest
public class WorkstationServiceTest {

	@Mock
	private WorkstationRepository workstationRepository;

	@InjectMocks
	private WorkstationService workstationService;

	@Test
	@DisplayName("Test creazione postazione")
	void testCreateWorkstation() {
		Building building = new Building("Edificio A", "Via xx Settembre 16C", "Roma");
		Workstation workstation = new Workstation("WS001", "Scrivania 1", WorkstationType.PRIVATO, 1, building);

		when(workstationRepository.save(workstation)).thenReturn(workstation);

		Workstation createdWorkstation = workstationService.createWorkstation(workstation);

		assertNotNull(createdWorkstation, "La postazione creata non dovrebbe essere nulla");
		assertEquals(workstation, createdWorkstation,
				"La postazione creata dovrebbe corrispondere a quella specificata");
	}

	@Test
	@DisplayName("Test ricerca postazioni disponibili per tipo e città")
	void testSearchAvailableWorkstationsByTypeAndCity() {
		Building building1 = new Building("Edificio A", "Via xx Settembre 16C", "Roma");
		Building building2 = new Building("Edificio B", "Via Maradona 80B", "Napoli");

		Workstation workstation1 = new Workstation("WS001", "Scrivania 1", WorkstationType.PRIVATO, 1, building1);
		Workstation workstation2 = new Workstation("WS002", "Scrivania 2", WorkstationType.OPENSPACE, 1, building1);
		Workstation workstation3 = new Workstation("WS003", "Scrivania 3", WorkstationType.OPENSPACE, 1, building2);

		List<Workstation> allWorkstations = new ArrayList<>();
		allWorkstations.add(workstation1);
		allWorkstations.add(workstation2);
		allWorkstations.add(workstation3);

		when(workstationRepository.findByTypeAndBuilding_City(WorkstationType.OPENSPACE, "Roma"))
				.thenReturn(List.of(workstation2));
		when(workstationRepository.findByTypeAndBuilding_City(WorkstationType.OPENSPACE, "Napoli"))
				.thenReturn(List.of(workstation3));

		List<Workstation> openSpaceWorkstationsInRome = workstationService
				.searchAvailableWorkstationsByTypeAndCity(WorkstationType.OPENSPACE, "Roma");
		List<Workstation> openSpaceWorkstationsInNaples = workstationService
				.searchAvailableWorkstationsByTypeAndCity(WorkstationType.OPENSPACE, "Napoli");

		assertNotNull(openSpaceWorkstationsInRome, "La lista di postazioni non dovrebbe essere nulla");
		assertNotNull(openSpaceWorkstationsInNaples, "La lista di postazioni non dovrebbe essere nulla");

		assertEquals(1, openSpaceWorkstationsInRome.size(), "La lista dovrebbe contenere una postazione");
		assertEquals(1, openSpaceWorkstationsInNaples.size(), "La lista dovrebbe contenere una postazione");
	}

}
