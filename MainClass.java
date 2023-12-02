import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int asiakkaidenLukumaara = 0;
        while (asiakkaidenLukumaara <= 0 || asiakkaidenLukumaara > 10) {
            System.out.println("Syötä asiakkaiden lukumäärä (enintään 10):");
            if (scanner.hasNextInt()) {
                asiakkaidenLukumaara = scanner.nextInt();
                if (asiakkaidenLukumaara <= 0 || asiakkaidenLukumaara > 10) {
                    System.out.println("Virheellinen asiakkaiden lukumäärä. Syötä uusi arvo väliltä 1-10.");
                }
            } else {
                System.out.println("Virheellinen syöte. Syötä asiakkaiden lukumäärä kokonaislukuna väliltä 1-10.");
                scanner.next();
            }
        }

        List<List<Integer>> asiakkaidenNumerot = new ArrayList<>();
        for (int i = 0; i < asiakkaidenLukumaara; i++) {
            System.out.println("Syötä asiakkaan " + (i + 1) + " numerot (7 numeroa väliltä 1-39):");
            List<Integer> numerot = lueNumerot(scanner);
            asiakkaidenNumerot.add(numerot);
        }

        System.out.println("\nArvotut numerot:");
        List<Integer> arvotutNumerot = arvoNumerot();
        tulostaNumerot(arvotutNumerot);

        System.out.println("\nTarkistus:\n");

        for (int i = 0; i < asiakkaidenLukumaara; i++) {
            List<Integer> asiakkaanNumerot = asiakkaidenNumerot.get(i);
            String asiakasNimi = "Asiakas " + (i + 1);
            tarkistaNumerot(asiakkaanNumerot, arvotutNumerot, asiakasNimi);
        }

        exit();
    }

    private static List<Integer> lueNumerot(Scanner scanner) {
        List<Integer> numerot = new ArrayList<>();
        int syotteita = 0;
        while (syotteita < 7) {
            if (scanner.hasNextInt()) {
                int numero = scanner.nextInt();
                if (numero >= 1 && numero <= 39 && !numerot.contains(numero)) {
                    numerot.add(numero);
                    syotteita++;
                } else {
                    System.out.println("Virheellinen numero. Syötä uusi numero väliltä 1-39.");
                }
            } else {
                System.out.println("Virheellinen syöte. Syötä numerot väliltä 1-39.");
                scanner.next();
            }
        }
        return numerot;
    }

    private static List<Integer> arvoNumerot() {
        List<Integer> numerot = new ArrayList<>();
        Random random = new Random();
        while (numerot.size() < 7) {
            int numero = random.nextInt(39) + 1;
            if (!numerot.contains(numero)) {
                numerot.add(numero);
            }
        }
        return numerot;
    }

    private static void tarkistaNumerot(List<Integer> asiakkaanNumerot, List<Integer> arvotutNumerot, String asiakasNimi) {
        int oikeatNumerot = 0;
        List<Integer> oikeatNumerotLista = new ArrayList<>();

        for (int numero : asiakkaanNumerot) {
            if (arvotutNumerot.contains(numero)) {
                oikeatNumerot++;
                oikeatNumerotLista.add(numero);
            }
        }

        System.out.println(asiakasNimi + " numerot:");
        tulostaNumerot(asiakkaanNumerot);
        System.out.println("Oikeita numeroita: " + oikeatNumerot);

        if (oikeatNumerot == 7) {
            System.out.println(asiakasNimi + ": Onneksi olkoon! Voitit päävoiton!");
        } else {
            System.out.println(asiakasNimi + ": Valitettavasti et voittanut päävoittoa tällä kertaa.");
        }

        if (!oikeatNumerotLista.isEmpty()) {
            System.out.println("Oikeat numerot:");
            tulostaNumerot(oikeatNumerotLista);
        }

        System.out.println();
    }

    private static void tulostaNumerot(List<Integer> numerot) {
        for (int numero : numerot) {
            System.out.print(numero + " ");
        }
        System.out.println();
    }

    private static void exit() {
        System.out.println("Ohjelma päättyy.");
        System.exit(0);
    }
}
