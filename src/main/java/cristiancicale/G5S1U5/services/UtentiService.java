package cristiancicale.G5S1U5.services;

import cristiancicale.G5S1U5.entities.Utente;
import cristiancicale.G5S1U5.exceptions.NotFoundException;
import cristiancicale.G5S1U5.exceptions.ValidationException;
import cristiancicale.G5S1U5.repositories.UtentiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UtentiService {
    private final UtentiRepository utentiRepository;

    @Autowired
    public UtentiService(UtentiRepository utentiRepository) {
        this.utentiRepository = utentiRepository;
    }

    public List<Utente> trovaTutti() {
        return this.utentiRepository.findAll();
    }

    public Utente trovaPerId(Long utenteId) {
        return this.utentiRepository.findById(utenteId).orElseThrow(() -> new NotFoundException(utenteId));
    }

    public void salvaNuovoUtente(Utente nuovoUtente) {
        if (this.utentiRepository.existsByUsername(nuovoUtente.getUsername()))
            throw new ValidationException("Username " + nuovoUtente.getUsername() + " già in uso!");
        this.utentiRepository.save(nuovoUtente);
        log.info("L'utente con username {} è stato salvato!", nuovoUtente.getUsername());
    }

    public Utente aggiornaUtente(Long id, Utente nuovoUtente) {

        Utente esistente = trovaPerId(id);

        if (!esistente.getUsername().equals(nuovoUtente.getUsername())) {

            boolean exists = utentiRepository.existsByUsername(nuovoUtente.getUsername());

            if (exists) {
                throw new ValidationException("L'username " + nuovoUtente.getUsername() + "già esiste!");
            }

            esistente.setUsername(nuovoUtente.getUsername());
        }

        esistente.setNomeCompleto(nuovoUtente.getNomeCompleto());

        if (!esistente.getEmail().equals(nuovoUtente.getEmail())) {

            boolean exists = utentiRepository.existsByEmail(nuovoUtente.getEmail());

            if (exists) {
                throw new ValidationException("L'email " + nuovoUtente.getEmail() + "già esiste!");
            }

            esistente.setEmail(nuovoUtente.getEmail());
        }

        return utentiRepository.save(nuovoUtente);
    }
}
