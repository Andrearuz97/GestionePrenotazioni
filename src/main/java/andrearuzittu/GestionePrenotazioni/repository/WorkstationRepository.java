package andrearuzittu.GestionePrenotazioni.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import andrearuzittu.GestionePrenotazioni.model.Workstation;
import andrearuzittu.GestionePrenotazioni.model.WorkstationType;

@Repository
public interface WorkstationRepository extends JpaRepository<Workstation, Long> {
	List<Workstation> findByTypeAndBuilding_City(WorkstationType type, String city);
}
