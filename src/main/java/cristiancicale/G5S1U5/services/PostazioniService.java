package cristiancicale.G5S1U5.services;

import cristiancicale.G5S1U5.entities.Postazione;
import cristiancicale.G5S1U5.exceptions.NotFoundException;
import cristiancicale.G5S1U5.exceptions.ValidationException;
import cristiancicale.G5S1U5.repositories.PostazioniRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PostazioniService {
    private final PostazioniRepository postazioniRepository;

    @Autowired
    public PostazioniService(PostazioniRepository postazioniRepository) {
        this.postazioniRepository = postazioniRepository;
    }

    public List<Postazione> trovaTutte() {
        return this.postazioniRepository.findAll();
    }

    public Postazione trovaPerId(Long postazioneId) {
        return this.postazioniRepository.findById(postazioneId).orElseThrow(() -> new NotFoundException(postazioneId));
    }

    public void salvaNuovaPostazione(Postazione nuovaPostazione) {
        try {
            postazioniRepository.save(nuovaPostazione);
            log.info("Postazione creata con codice {}", nuovaPostazione.getCodice());
        } catch (DataIntegrityViolationException ex) {
            throw new ValidationException("Codice postazione già esistente, riprova");
        }
    }

    public Postazione aggiornaPostazione(Long id, Postazione nuovaPostazione) {

        Postazione esistente = trovaPerId(id);

        esistente.setDescrizione(nuovaPostazione.getDescrizione());
        esistente.setTipo(nuovaPostazione.getTipo());
        esistente.setMaxOccupanti(nuovaPostazione.getMaxOccupanti());
        esistente.setEdificio(nuovaPostazione.getEdificio());

        return postazioniRepository.save(esistente);
    }

    public void eliminaPostazione(Long id) {
        Postazione postazione = trovaPerId(id);
        postazioniRepository.delete(postazione);
    }
}
