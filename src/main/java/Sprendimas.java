import javax.persistence.*;


@Entity
@Table(name = "Sprendimas")
public class Sprendimas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne()
    @JoinColumn(name = "atsakymo_id")
    Atsakymai atsakymai;

    @ManyToOne()
    @JoinColumn(name = "studento_id")
    Studentas studentas;

    private String pasirinktas_atsakymas;


    public Sprendimas() {
    }

    public Sprendimas(String pasirinktas_atsakymas) {

        this.pasirinktas_atsakymas = pasirinktas_atsakymas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPasirinktas_atsakymas() {
        return pasirinktas_atsakymas;
    }

    public void setPasirinktas_atsakymas(String pasirinktas_atsakymas) {
        this.pasirinktas_atsakymas = pasirinktas_atsakymas;
    }

    public Atsakymai getAtsakymai() {
        return atsakymai;
    }

    public void setAtsakymai(Atsakymai atsakymai) {
        this.atsakymai = atsakymai;
    }

    public Studentas getStudentas() {
        return studentas;
    }

    public void setStudentas(Studentas studentas) {
        this.studentas = studentas;
    }
}
