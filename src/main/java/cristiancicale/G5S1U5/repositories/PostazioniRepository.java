package cristiancicale.G5S1U5.repositories;

import cristiancicale.G5S1U5.entities.Postazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostazioniRepository extends JpaRepository<Postazione, Long> {
    boolean existsByCodice(String codice);
}
