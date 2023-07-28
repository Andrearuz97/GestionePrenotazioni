package andrearuzittu.GestionePrenotazioni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import andrearuzittu.GestionePrenotazioni.model.Building;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
}
