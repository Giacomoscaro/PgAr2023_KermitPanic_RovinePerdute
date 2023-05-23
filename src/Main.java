import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws XMLStreamException {
        Reader r = new Reader();
        ArrayList<Citta> mappa = r.leggi_mappa();
        RovineManager m = new RovineManager(mappa);
        ArrayList<Citta> p1 = m.calcola_peso_team1(m.getLista_citta());
        ArrayList<Citta> p2 = m.calcola_peso_team2(m.getLista_citta());
        Writer w = new Writer();
        w.scrivi_percorsi(p1, p2);
    }
}