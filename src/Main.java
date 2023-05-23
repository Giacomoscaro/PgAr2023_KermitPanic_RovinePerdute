import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        
        //test per citta
        ArrayList<Citta> listaCitta = new ArrayList<Citta>();
        listaCitta.add(new Citta(1, "Brescia", new Posizione(0,0,0)));
        listaCitta.add(new Citta(2, "Bergamo", new Posizione(2.5,3.1,0)));
        listaCitta.add(new Citta(3, "Sondrio", new Posizione(4.5,1.5,7)));
        listaCitta.get(1).aggiungi_strada(2, 0);
        listaCitta.get(1).aggiungi_strada(3,0);
        
        
        CalcoloPercorso.inizializza_citta(listaCitta, 1);
        CalcoloPercorso.visualizza_pesi(listaCitta);
    }
}