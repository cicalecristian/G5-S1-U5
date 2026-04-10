package cristiancicale.G5S1U5.services;

import cristiancicale.G5S1U5.repositories.EdificiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EdificiService {
    private final EdificiRepository edificiRepository;

    @Autowired
    public EdificiService(EdificiRepository edificiRepository) {
        this.edificiRepository = edificiRepository;
    }
}
