import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Meniu {

    public int pabaiga;
    public boolean pab = false;

    public SessionFactory factory;
    public Session session;
    Scanner sc = new Scanner(System.in);

    public void meniu() {


        meniuSoutas();

        System.out.println("Pasirinkite:");
        tikrintiInt();
        pabaiga = sc.nextInt();
        switch (pabaiga) {
            case 1:
                System.out.println("iveskite Admino username:");
                sc.nextLine();
                String vardas = sc.nextLine();
                System.out.println("iveskite Admino slaptazodi:");
                String slaptazodis = sc.nextLine();
                if (patikrinimasAdmin(vardas, slaptazodis) == true) {
                    StudentoKurimas studentoKurimas = new StudentoKurimas();
                    studentoKurimas.pridetiStudenta();
                } else {
                    System.out.println("Slaptazodis ir/ar Username yra neteisingas");
                }
                break;
            case 2:
                System.out.println("iveskite Admino username:");
                sc.nextLine();
                String vardas2 = sc.nextLine();
                System.out.println("iveskite Admino slaptazodi:");
                String slaptazodis2 = sc.nextLine();
                if (patikrinimasAdmin(vardas2, slaptazodis2) == true) {
                    EgzaminoKurimas egzaminoKurimas = new EgzaminoKurimas();
                    egzaminoKurimas.egzoKurimas();
                } else {
                    System.out.println("Slaptazodis ir/ar Username yra neteisingas");
                }
                break;
            case 3:
                System.out.println("iveskite Admino username:");
                sc.nextLine();
                String vardas3 = sc.nextLine();
                System.out.println("iveskite Admino slaptazodi:");
                String slaptazodis3 = sc.nextLine();
                if (patikrinimasAdmin(vardas3, slaptazodis3) == true) {
                    Redaguoti redaguoti = new Redaguoti();
                    redaguoti.redaguoti();
                } else {
                    System.out.println("Slaptazodis ir/ar Username yra neteisingas");
                }
                break;
            case 4:
                EgzaminoSprendimas egzaminoSprendimas = new EgzaminoSprendimas();
                egzaminoSprendimas.sprendimas();
                break;
            case 5:
                Tikrinimas tikrinimas = new Tikrinimas();
                tikrinimas.tikrinimas();
                break;
            case 0:
                pab = true;
                break;
            default:
                System.out.println("nera tokio pasirinkimo ......");
        }

    }

    public void meniuSoutas() {
        System.out.println("-".repeat(40));
        System.out.println("[1]-Prideti studenta-[1]\n" +
                "[2]-Prideti egzamina-[2]\n" +
                "[3]-Koreguoti egzamina-[3]\n" +
                "[4]-Spresti egzamina-[4]\n" +
                "[5]-Statistika-[5]\n" +
                "[0]-EXIT program-[0]");
        System.out.println("-".repeat(40));
    }


    public boolean patikrinimasAdmin(String username, String slaptazodis) {

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (
                HibernateException e) {
            e.printStackTrace();
        }

        session = factory.openSession();
        org.hibernate.query.Query qry = session.createQuery("FROM Admin");
        List<Admin> adminas = qry.list();
        for (Admin a : adminas) {
            if (a.getVardas().equals(username) && a.getSlaptazodis().equals(slaptazodis)) {
                session.close();
                factory.close();
                return true;
            }
        }

        session.close();
        factory.close();
        return false;

    }

    public void tikrintiInt() {
        while (!sc.hasNextInt()) {
            System.out.println("Irasykite skaiciu!!!");
            sc.next();
        }
    }
}
