package cristiancicale.G5S1U5.entities;

import cristiancicale.G5S1U5.enums.TipoPostazione;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "postazioni")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"prenotazioni", "edificio"})
public class Postazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(AccessLevel.NONE)
    @Column(unique = true, nullable = false, updatable = false)
    private String codice;

    @Column(nullable = false)
    private String descrizione;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_postazione", nullable = false)
    private TipoPostazione tipo;

    @Column(name = "max_occupanti", nullable = false)
    private int maxOccupanti;

    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;

    @OneToMany(mappedBy = "postazione")
    private List<Prenotazione> prenotazioni;

    public Postazione(String descrizione, TipoPostazione tipo, int maxOccupanti, Edificio edificio) {
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.maxOccupanti = maxOccupanti;
        this.edificio = edificio;
        this.prenotazioni = new ArrayList<>();
    }

    @PrePersist
    private void generaCodice() {
        this.codice = "COD-" + UUID.randomUUID().toString().substring(0, 8);
    }
}
