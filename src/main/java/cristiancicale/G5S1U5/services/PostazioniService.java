package cristiancicale.G5S1U5.services;

import cristiancicale.G5S1U5.repositories.PostazioniRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PostazioniService {
    private final PostazioniRepository postazioniRepository;

    @Autowired
    public PostazioniService(PostazioniRepository postazioniRepository) {
        this.postazioniRepository = postazioniRepository;
    }
}
