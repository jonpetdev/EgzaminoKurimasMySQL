

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Atsakymai")
public class Atsakymai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne()
    @JoinColumn(name = "klausimo_id")
    Klausimai klausimai;

    private String atsakymas;
    private String teisingas_neteisingas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atsakymai")
    private List<Sprendimas> sprendimasList = new ArrayList<Sprendimas>();

    public Atsakymai() {
    }

    public Atsakymai(String atsakymas, String teisingas_neteisingas) {
        this.atsakymas = atsakymas;
        this.teisingas_neteisingas = teisingas_neteisingas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getAtsakymas() {
        return atsakymas;
    }

    public void setAtsakymas(String atsakymas) {
        this.atsakymas = atsakymas;
    }

    public String getTeisingas_neteisingas() {
        return teisingas_neteisingas;
    }

    public void setTeisingas_neteisingas(String teisingas_neteisingas) {
        this.teisingas_neteisingas = teisingas_neteisingas;
    }

    public Klausimai getKlausimai() {
        return klausimai;
    }

    public void setKlausimai(Klausimai klausimai) {
        this.klausimai = klausimai;
    }

    public List<Sprendimas> getSprendimasList() {
        return sprendimasList;
    }

    public void setSprendimasList(List<Sprendimas> sprendimasList) {
        this.sprendimasList = sprendimasList;
    }
}
