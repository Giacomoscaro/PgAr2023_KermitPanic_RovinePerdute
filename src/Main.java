import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Reader r = new Reader();
        ArrayList<Citta> mappa = r.leggi_mappa();
        RovineManager m = new RovineManager(mappa);
        ArrayList<Citta> p1 = m.calcola_peso_team1(m.getLista_citta());
        ArrayList<Citta> p2 = m.calcola_peso_team2(m.getLista_citta());
        for(Citta c : p1)
            System.out.println(c);
        for(Citta c : p2)
            System.out.println(c);
    }
}