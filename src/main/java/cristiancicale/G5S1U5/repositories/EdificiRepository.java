package cristiancicale.G5S1U5.repositories;

import cristiancicale.G5S1U5.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdificiRepository extends JpaRepository<Edificio, Long> {
    boolean existsByNomeAndIndirizzoAndCitta(String nome, String indirizzo, String citta);
}
