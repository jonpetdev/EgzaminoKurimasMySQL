import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Tikrinimas {

    public SessionFactory factory;

    public void tikrinimas() {

        Scanner sc = new Scanner(System.in);
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        Session session = factory.openSession();


        System.out.println("Iveskite studento varda");
        System.out.println("-".repeat(30));
        String studVardas = sc.nextLine();
        System.out.println("-".repeat(30));
        org.hibernate.query.Query qryStud = session.createQuery("From Studentas");
        List<Studentas> stud = qryStud.list();
        int studID = 0;
        for (Studentas s : stud) {
            if (s.getVardas().equals(studVardas)) {
                studID = s.getId();
            }
        }

        org.hibernate.query.Query qry = session.createQuery("FROM Sprendimas WHERE studento_id=" + studID);
        List<Sprendimas> sp = qry.list();

        List<Integer> sprestuEgzaminuID = new ArrayList<>();
        for (Sprendimas s : sp) {
            sprestuEgzaminuID.add(s.getAtsakymai().getKlausimai().getEgzaminai().getId());
        }
        sprestuEgzaminuID = sprestuEgzaminuID.stream().distinct().collect(Collectors.toList());


        for (int i = 0; i < sprestuEgzaminuID.size(); i++) {
            float teisingi = 0;
            String egzaminoPavadinimas = "";
            float isVisoKlausimu = 0;
            int A = 0;
            int B = 0;
            int C = 0;
            float rezultatas;
            for (Sprendimas ss : sp) {

                if (sprestuEgzaminuID.get(i) == ss.getAtsakymai().getKlausimai().getEgzaminai().getId()) {
                    egzaminoPavadinimas = ss.getAtsakymai().getKlausimai().getEgzaminai().getPavadinimas();
                    if (ss.getAtsakymai().getTeisingas_neteisingas().equals("T")) {
                        teisingi++;
                    }
                    if (ss.getAtsakymai().getAtsakymas().matches("^A..+")) {
                        A++;
                    }
                    if (ss.getAtsakymai().getAtsakymas().matches("^B..+")) {
                        B++;
                    }
                    if (ss.getAtsakymai().getAtsakymas().matches("^C..+")) {
                        C++;
                    }
                    isVisoKlausimu++;
                }

            }
            rezultatas = (teisingi * 10) / isVisoKlausimu;
            System.out.println("Egzamino pavadinimas: " + egzaminoPavadinimas + " | Teisingai atsakyta: " + teisingi + " is " + isVisoKlausimu + " | Rezultatas: " + rezultatas
                    + " | A:" + A + " B:" + B + " C:" + C);
        }


        session.close();

        factory.close();

    }
}
