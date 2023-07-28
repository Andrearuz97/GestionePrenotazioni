package andrearuzittu.GestionePrenotazioni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import andrearuzittu.GestionePrenotazioni.model.Utente;

@Repository
public interface UserRepository extends JpaRepository<Utente, Long> {
}
