package cristiancicale.G5S1U5.services;

import cristiancicale.G5S1U5.entities.Postazione;
import cristiancicale.G5S1U5.entities.Prenotazione;
import cristiancicale.G5S1U5.entities.Utente;
import cristiancicale.G5S1U5.exceptions.NotFoundException;
import cristiancicale.G5S1U5.exceptions.PostazioneOccupataException;
import cristiancicale.G5S1U5.exceptions.UtenteGiaPrenotatoException;
import cristiancicale.G5S1U5.exceptions.ValidationException;
import cristiancicale.G5S1U5.repositories.PrenotazioniRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class PrenotazioniService {
    private final PrenotazioniRepository prenotazioniRepository;

    private final UtentiService utentiService;

    private final PostazioniService postazioniService;

    public PrenotazioniService(PrenotazioniRepository prenotazioniRepository, UtentiService utentiService,
                               PostazioniService postazioniService) {
        this.prenotazioniRepository = prenotazioniRepository;
        this.utentiService = utentiService;
        this.postazioniService = postazioniService;
    }

    public Prenotazione prenotazione(Long utenteId, Long postazioneId, LocalDate data) {

        if (data.isBefore(LocalDate.now())) {
            throw new ValidationException("Non puoi prenotare una data passata");
        }

        Utente utente = utentiService.trovaPerId(utenteId);
        Postazione postazione = postazioniService.trovaPerId(postazioneId);

        if (prenotazioniRepository.existsByUtenteAndData(utente, data)) {
            throw new UtenteGiaPrenotatoException(
                    "L'utente ha già una prenotazione per questa data"
            );
        }

        if (prenotazioniRepository.existsByPostazioneAndData(postazione, data)) {
            throw new PostazioneOccupataException(
                    "La postazione è già occupata per questa data"
            );
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(utente);
        prenotazione.setPostazione(postazione);
        prenotazione.setDataPrenotazione(data);

        return prenotazioniRepository.save(prenotazione);
    }

    public List<Prenotazione> trovaTutte() {
        return prenotazioniRepository.findAll();
    }

    public Prenotazione trovaPerId(Long prenotazioneId) {
        return this.prenotazioniRepository.findById(prenotazioneId).orElseThrow(() -> new NotFoundException(prenotazioneId));
    }

    public List<Prenotazione> trovaPerUtente(Long utenteId) {

        Utente utente = utentiService.trovaPerId(utenteId);
        return prenotazioniRepository.findByUtente(utente);
    }

    public void eliminaPrenotazione(Long id) {
        Prenotazione prenotazione = trovaPerId(id);
        prenotazioniRepository.delete(prenotazione);
    }
}
