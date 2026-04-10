package cristiancicale.G5S1U5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "edifici")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Edificio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String indirizzo;

    @Column(nullable = false)
    private String citta;

    @OneToMany(mappedBy = "edificio")
    private List<Postazione> postazioni;

    public Edificio(String nome, String indirizzo, String citta, List<Postazione> postazioni) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.postazioni = postazioni;
    }
}
