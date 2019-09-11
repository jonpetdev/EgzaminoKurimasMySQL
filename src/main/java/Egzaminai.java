import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Egzaminai")
public class Egzaminai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String pavadinimas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egzaminai")
    private List<Klausimai> klausimai = new ArrayList<Klausimai>();

    public Egzaminai() {
    }

    public Egzaminai(String pavadinimas, Klausimai klausimai) {
        this.pavadinimas = pavadinimas;
        this.klausimai.add(klausimai);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public List<Klausimai> getKlausimai() {
        return klausimai;
    }

    public void setKlausimai(List<Klausimai> klausimai) {
        this.klausimai = klausimai;
    }
}
