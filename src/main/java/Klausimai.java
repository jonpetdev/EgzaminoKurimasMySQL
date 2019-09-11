import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Klausimai")
public class Klausimai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne()
    @JoinColumn(name = "egzaminai_id")
    Egzaminai egzaminai;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klausimai")
    private List<Atsakymai> atsakymai = new ArrayList<Atsakymai>();

    private String klausimas;

    public Klausimai() {
    }

    public Klausimai(String klausimas, Atsakymai atsakymai) {
        this.klausimas = klausimas;
        this.atsakymai.add(atsakymai);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getKlausimas() {
        return klausimas;
    }

    public void setKlausimas(String klausimas) {
        this.klausimas = klausimas;
    }

    public Egzaminai getEgzaminai() {
        return egzaminai;
    }

    public void setEgzaminai(Egzaminai egzaminai) {
        this.egzaminai = egzaminai;
    }

    public List<Atsakymai> getAtsakymai() {
        return atsakymai;
    }

    public void setAtsakymai(List<Atsakymai> atsakymai) {
        this.atsakymai = atsakymai;
    }
}
