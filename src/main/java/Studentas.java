import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Studentas")
public class Studentas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String vardas;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentas")
    private List<Sprendimas> sprendimasList = new ArrayList<Sprendimas>();

    public Studentas() {
    }


    public Studentas(String vardas) {
        this.vardas = vardas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public List<Sprendimas> getSprendimasList() {
        return sprendimasList;
    }

    public void setSprendimasList(List<Sprendimas> sprendimasList) {
        this.sprendimasList = sprendimasList;
    }
}
