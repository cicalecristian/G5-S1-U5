package cristiancicale.G5S1U5.services;

import cristiancicale.G5S1U5.entities.Utente;
import cristiancicale.G5S1U5.exceptions.NotFoundException;
import cristiancicale.G5S1U5.exceptions.ValidationException;
import cristiancicale.G5S1U5.repositories.UtentiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UtentiService {
    private final UtentiRepository utentiRepository;

    public UtentiService(UtentiRepository utentiRepository) {
        this.utentiRepository = utentiRepository;
    }

    public List<Utente> findAll() {
        return this.utentiRepository.findAll();
    }

    public Utente trovaPerId(Long utenteId) {
        return this.utentiRepository.findById(utenteId).orElseThrow(() -> new NotFoundException(utenteId));
    }

    public void salvaNuovoUtente(Utente nuovoUtente) {
        if (this.utentiRepository.existsByEmail(nuovoUtente.getEmail()))
            throw new ValidationException("Email " + nuovoUtente.getEmail() + " già in uso!");
        this.utentiRepository.save(nuovoUtente);
        log.info("L'utente con email {} è stato salvato!", nuovoUtente.getUsername());
    }
}
