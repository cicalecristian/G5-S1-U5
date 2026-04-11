package cristiancicale.G5S1U5.services;

import cristiancicale.G5S1U5.entities.Edificio;
import cristiancicale.G5S1U5.exceptions.NotFoundException;
import cristiancicale.G5S1U5.exceptions.ValidationException;
import cristiancicale.G5S1U5.repositories.EdificiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EdificiService {
    private final EdificiRepository edificiRepository;

    @Autowired
    public EdificiService(EdificiRepository edificiRepository) {
        this.edificiRepository = edificiRepository;
    }

    public List<Edificio> trovaTutti() {
        return this.edificiRepository.findAll();
    }

    public Edificio trovaPerId(Long edificioId) {
        return this.edificiRepository.findById(edificioId).orElseThrow(() -> new NotFoundException(edificioId));
    }

    public void salvaNuovoEdificio(Edificio nuovoEdificio) {
        if (this.edificiRepository.existsByNomeAndIndirizzoAndCitta(
                nuovoEdificio.getNome(),
                nuovoEdificio.getIndirizzo(),
                nuovoEdificio.getCitta()))
            throw new ValidationException("L'edificio con id " + nuovoEdificio.getId() + " già esiste");
        this.edificiRepository.save(nuovoEdificio);
        log.info("L'edificio con id {} è stato salvato!", nuovoEdificio.getId());
    }
}
