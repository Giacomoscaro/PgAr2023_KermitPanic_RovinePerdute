import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;

public class Main {
	private static final String INPUT_FILE = "./fileXML/PgAr_Map_10000.xml";
	private static final String OUTPUT_FILE = "./fileXML/percorsiRovine.xml";

	public static void main(String[] args) throws XMLStreamException {
		Reader r=null;
    	try {
			r = new Reader(INPUT_FILE);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        ArrayList<Citta> mappa = r.leggi_mappa(); // legge le città dal file di input
        RovineManager m = new RovineManager(mappa);
        Percorso p1 = m.calcola_peso_team1(m.getLista_citta()); // percorso per il team 1
        Percorso p2 = m.calcola_peso_team2(m.getLista_citta()); // percorso per il team 2
        Writer w = new Writer(OUTPUT_FILE);
        w.scrivi_percorsi(p1, p2);
    }
}