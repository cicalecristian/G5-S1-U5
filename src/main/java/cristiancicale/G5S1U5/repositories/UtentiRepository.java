package cristiancicale.G5S1U5.repositories;

import cristiancicale.G5S1U5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtentiRepository extends JpaRepository<Utente, Long> {

    boolean existsByEmail(String email);
}
