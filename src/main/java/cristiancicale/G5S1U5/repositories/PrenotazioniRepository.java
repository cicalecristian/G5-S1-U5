package cristiancicale.G5S1U5.repositories;

import cristiancicale.G5S1U5.entities.Postazione;
import cristiancicale.G5S1U5.entities.Prenotazione;
import cristiancicale.G5S1U5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione, Long> {

    boolean existsByUtenteAndDataPrenotazione(Utente utente, LocalDate data);

    boolean existsByPostazioneAndDataPrenotazione(Postazione postazione, LocalDate data);

    List<Prenotazione> findByUtente(Utente utente);
}
