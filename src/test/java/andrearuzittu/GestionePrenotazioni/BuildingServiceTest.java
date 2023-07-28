package andrearuzittu.GestionePrenotazioni;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import andrearuzittu.GestionePrenotazioni.model.Building;
import andrearuzittu.GestionePrenotazioni.repository.BuildingRepository;
import andrearuzittu.GestionePrenotazioni.service.BuildingService;

@SpringBootTest
public class BuildingServiceTest {

	@Mock
	private BuildingRepository buildingRepository;

	@InjectMocks
	private BuildingService buildingService;

	@Test
	@DisplayName("Test creazione edificio")
	void testCreateBuilding() {
		Building building = new Building("Edificio Test", "Via Test 123", "Città Test");

		when(buildingRepository.save(building)).thenReturn(building);

		Building createdBuilding = buildingService.createBuilding(building);

		assertEquals(building, createdBuilding, "L'edificio creato dovrebbe corrispondere all'edificio passato");
	}

}
