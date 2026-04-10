package cristiancicale.G5S1U5.services;

import cristiancicale.G5S1U5.repositories.PrenotazioniRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrenotazioniService {
    private final PrenotazioniRepository prenotazioniRepository;

    public PrenotazioniService(PrenotazioniRepository prenotazioniRepository) {
        this.prenotazioniRepository = prenotazioniRepository;
    }
}
