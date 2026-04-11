package cristiancicale.G5S1U5.runners;

import cristiancicale.G5S1U5.services.EdificiService;
import cristiancicale.G5S1U5.services.PostazioniService;
import cristiancicale.G5S1U5.services.PrenotazioniService;
import cristiancicale.G5S1U5.services.UtentiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        
    }
}
