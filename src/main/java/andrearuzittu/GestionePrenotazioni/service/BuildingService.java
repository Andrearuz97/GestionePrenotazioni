package andrearuzittu.GestionePrenotazioni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andrearuzittu.GestionePrenotazioni.model.Building;
import andrearuzittu.GestionePrenotazioni.repository.BuildingRepository;

@Service
public class BuildingService {
	@Autowired
	private BuildingRepository buildingRepository;

	public Building createBuilding(Building building) {
		return buildingRepository.save(building);
	}

	public Building getBuildingById(Long id) {
		return buildingRepository.findById(id).orElse(null);
	}

}
