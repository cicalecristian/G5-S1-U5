package cristiancicale.G5S1U5.entities;

import cristiancicale.G5S1U5.enums.TipoPostazione;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "postazioni")
public class Postazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String codice;

    private String descrizione;

    @Enumerated(EnumType.STRING)
    private TipoPostazione tipo;

    private int maxOccupanti;

    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;

    @OneToMany(mappedBy = "postazione", cascade = CascadeType.ALL)
    private List<Prenotazione> prenotazioni;

    protected Postazione() {
    }

    public Postazione(String descrizione, TipoPostazione tipo, int maxOccupanti, Edificio edificio, List<Prenotazione> prenotazioni) {
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.maxOccupanti = maxOccupanti;
        this.edificio = edificio;
        this.prenotazioni = prenotazioni;
    }

    public Long getId() {
        return id;
    }

    public String getCodice() {
        return codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public TipoPostazione getTipo() {
        return tipo;
    }

    public void setTipo(TipoPostazione tipo) {
        this.tipo = tipo;
    }

    public int getMaxOccupanti() {
        return maxOccupanti;
    }

    public void setMaxOccupanti(int maxOccupanti) {
        this.maxOccupanti = maxOccupanti;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    @Override
    public String toString() {
        return "Postazione{" +
                "id=" + id +
                ", codice='" + codice + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", tipo=" + tipo +
                ", maxOccupanti=" + maxOccupanti +
                ", edificio=" + edificio +
                ", prenotazioni=" + prenotazioni +
                '}';
    }
}
