package cristiancicale.G5S1U5.entities;

import cristiancicale.G5S1U5.enums.TipoPostazione;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "postazioni")
@NoArgsConstructor
@Getter
@Setter
@ToString
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

    @OneToMany(mappedBy = "postazione", cascade = CascadeType.ALL)
    private List<Prenotazione> prenotazioni;

    public Postazione(String descrizione, TipoPostazione tipo, int maxOccupanti, Edificio edificio, List<Prenotazione> prenotazioni) {
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.maxOccupanti = maxOccupanti;
        this.edificio = edificio;
        this.prenotazioni = prenotazioni;
    }
}
