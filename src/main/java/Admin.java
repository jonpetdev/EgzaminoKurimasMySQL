import javax.persistence.*;

@Entity
@Table(name = "Adminas")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String vardas;
    private String slaptazodis;


    public Admin() {
    }

    public Admin(String vardas, String slaptazodis) {
        this.vardas = vardas;
        this.slaptazodis = slaptazodis;
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


    public String getSlaptazodis() {
        return slaptazodis;
    }


}
