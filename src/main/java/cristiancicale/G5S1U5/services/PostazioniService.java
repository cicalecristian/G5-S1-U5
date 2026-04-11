package cristiancicale.G5S1U5.services;

import cristiancicale.G5S1U5.entities.Postazione;
import cristiancicale.G5S1U5.exceptions.NotFoundException;
import cristiancicale.G5S1U5.exceptions.ValidationException;
import cristiancicale.G5S1U5.repositories.PostazioniRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (this.postazioniRepository.existsByCodice(nuovaPostazione.getCodice()))
            throw new ValidationException("Postazione " + nuovaPostazione.getCodice() + "già esistente");
        this.postazioniRepository.save(nuovaPostazione);
        log.info("La postazione con codice {} è stata salvata!", nuovaPostazione.getCodice());
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
