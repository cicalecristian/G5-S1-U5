package cristiancicale.G5S1U5.runners;

import cristiancicale.G5S1U5.entities.Edificio;
import cristiancicale.G5S1U5.entities.Postazione;
import cristiancicale.G5S1U5.entities.Prenotazione;
import cristiancicale.G5S1U5.entities.Utente;
import cristiancicale.G5S1U5.enums.TipoPostazione;
import cristiancicale.G5S1U5.services.EdificiService;
import cristiancicale.G5S1U5.services.PostazioniService;
import cristiancicale.G5S1U5.services.PrenotazioniService;
import cristiancicale.G5S1U5.services.UtentiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class Runner implements CommandLineRunner {

    private final EdificiService edificiService;
    private final PostazioniService postazioniService;
    private final PrenotazioniService prenotazioniService;
    private final UtentiService utentiService;

    @Autowired
    public Runner(EdificiService edificiService, PostazioniService postazioniService,
                  PrenotazioniService prenotazioniService, UtentiService utentiService) {
        this.edificiService = edificiService;
        this.postazioniService = postazioniService;
        this.prenotazioniService = prenotazioniService;
        this.utentiService = utentiService;
    }

    @Override
    public void run(String... args) throws Exception {

//        Edificio e1 = new Edificio("CapGemini", "via Roma 170", "Napoli");
//        this.edificiService.salvaNuovoEdificio(e1);

        Edificio e1FromDB = this.edificiService.trovaPerId(1L);

        Postazione p1 = new Postazione("sviluppo siti web", TipoPostazione.PRIVATO, 3, e1FromDB);
//        this.postazioniService.salvaNuovaPostazione(p1);

        Postazione p1FromDB = this.postazioniService.trovaPerId(1L);

        Utente u1 = new Utente("cristiancicale05", "Cristian Cicale", "cricica05@gmail.com");
//        this.utentiService.salvaNuovoUtente(u1);

        Utente u1FromDB = this.utentiService.trovaPerId(1L);

//        prenotazioniService.salvaNuovaPrenotazione(1L, 1L, LocalDate.of(2026, 5, 20));

        List<Edificio> edifici = edificiService.trovaTutti();
//        log.info("Edifici: {}", edifici);

        List<Postazione> postazioni = postazioniService.trovaTutte();
//        log.info("Postazioni: {}", postazioni);

        List<Utente> utenti = utentiService.trovaTutti();
//        log.info("Utenti: {}", utenti);

        List<Prenotazione> prenotazioni = prenotazioniService.trovaTutte();
        log.info("Prenotazioni: {}", prenotazioni);
    }
}
