package andrearuzittu.GestionePrenotazioni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andrearuzittu.GestionePrenotazioni.model.Workstation;
import andrearuzittu.GestionePrenotazioni.model.WorkstationType;
import andrearuzittu.GestionePrenotazioni.repository.WorkstationRepository;

@Service
public class WorkstationService {
	@Autowired
	private WorkstationRepository workstationRepository;

	public Workstation createWorkstation(Workstation workstation) {

		return workstationRepository.save(workstation);
	}

	public List<Workstation> searchAvailableWorkstationsByTypeAndCity(WorkstationType type, String city) {
		return workstationRepository.findByTypeAndBuilding_City(type, city);
	}

}
