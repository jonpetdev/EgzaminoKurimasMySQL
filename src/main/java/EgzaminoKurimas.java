import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class EgzaminoKurimas {
    public SessionFactory factory;

    public void egzoKurimas() {


        Scanner sc = new Scanner(System.in);
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (
                HibernateException e) {
            e.printStackTrace();
        }

        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();


        Egzaminai egzaminai = new Egzaminai();
        System.out.println("Iveskite egzamino Pavadinima");
        String pavadinimas = sc.nextLine();
        egzaminai.setPavadinimas(pavadinimas);

        System.out.println("Iveskite egzamino Klausimus:");
        String pabaiga = "";
        int klausimuNr = 1;
        List<Klausimai> klausimaiList = new ArrayList<>();
        while (!pabaiga.equals("X")) {
            Klausimai klausimai = new Klausimai();
            klausimai.setEgzaminai(egzaminai);
            System.out.println(klausimuNr + "klausimas:");
            String klausimas = sc.nextLine();
            klausimai.setKlausimas(klausimas);


            System.out.println("-".repeat(30));
            System.out.println("Iveskite tris Atsakymo variantus, nurodydami ar jis teisingas ar ne \"N\"-neteisingas \"T\"-teisingas  ");
            System.out.println("-".repeat(30));
            Atsakymai atsakymai = new Atsakymai();
            atsakymai.setKlausimai(klausimai);

            System.out.println("1 atsakymo variantas:");
            String variantas1 = sc.nextLine();
            atsakymai.setAtsakymas("A." + variantas1);
            System.out.println("Ar tesingas? N/T");
            String variantasA1 = sc.nextLine();
            atsakymai.setTeisingas_neteisingas(variantasA1);


            System.out.println("-".repeat(30));
            Atsakymai atsakymai1 = new Atsakymai();
            atsakymai1.setKlausimai(klausimai);
            System.out.println("2 atsakymo variantas:");
            String variantas2 = sc.nextLine();
            atsakymai1.setAtsakymas("B. " + variantas2);
            System.out.println("Ar tesingas? N/T");
            String variantasA2 = sc.nextLine();
            atsakymai1.setTeisingas_neteisingas(variantasA2);

            System.out.println("-".repeat(30));
            Atsakymai atsakymai2 = new Atsakymai();
            atsakymai2.setKlausimai(klausimai);
            System.out.println("3 atsakymo variantas:");
            String variantas3 = sc.nextLine();
            atsakymai2.setAtsakymas("C. " + variantas3);
            System.out.println("Ar tesingas? N/T");
            String variantasA3 = sc.nextLine();
            atsakymai2.setTeisingas_neteisingas(variantasA3);

            List<Atsakymai> ats = new ArrayList<>();
            ats.add(atsakymai);
            ats.add(atsakymai1);
            ats.add(atsakymai2);


            klausimai.setAtsakymai(ats);

            System.out.println("Ar norite prideti dar klausimu?\n" +
                    "Taip- spauskite ENTER\n" +
                    "Ne- Irasykite \"X\" ir spauskite ENTER");
            pabaiga = sc.nextLine();
            klausimuNr++;

            klausimaiList.add(klausimai);


        }

        egzaminai.setKlausimai(klausimaiList);

        session.save(egzaminai);
        tx.commit();

        session.close();
        factory.close();
    }
}
