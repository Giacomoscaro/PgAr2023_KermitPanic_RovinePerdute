import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.util.ArrayList;
import java.lang.Exception;

public class Reader {
	private String inputFile;
	
	public Reader(String inputFile) throws Exception {
		Path inputPath = Paths.get(inputFile);
		if( !Files.exists(inputPath)) { //se il file specificato non esiste, genera un'eccezione
			throw new Exception("File: " + inputFile + " non trovato");
		}
		this.inputFile = inputFile;
	}
	
    public ArrayList<Citta> leggi_mappa(){
        ArrayList<Citta> mappa = new ArrayList<>();
        FileInputStream file;
        XMLInputFactory input = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        {//inizializzazione dello StreamReader
            try {
                file = new FileInputStream(inputFile);
                reader = input.createXMLStreamReader(file);
                reader.next();
            } catch (Exception e) {
                System.out.println("Errore nell'inizializzazione del reader:");
                System.out.println(e.getMessage());
            }
        }
        while (true) {//continua finché non viene lanciata un'eccezione quando sono finiti gli eventi dell'xml
            try {
                if (!reader.hasNext()) break;
                //lettura delle città
                if (reader.getEventType() == XMLStreamConstants.START_ELEMENT && reader.getLocalName().equals("city")) {
                    int id = Integer.parseInt(reader.getAttributeValue(0));
                    String nome = reader.getAttributeValue(1);
                    Posizione posizione = new Posizione(Integer.parseInt(reader.getAttributeValue(2)),Integer.parseInt(reader.getAttributeValue(3)),Integer.parseInt(reader.getAttributeValue(4)));
                    mappa.add(new Citta(id, nome, posizione));
                    reader.next();
                    reader.next();
                    
                    //lettura dei collegamenti di ogni città
                    while(reader.getLocalName().equals("link")) {
                        mappa.get(id).aggiungi_strada(Integer.parseInt(reader.getAttributeValue(0)), Double.POSITIVE_INFINITY);
                        reader.next();
                        reader.next();
                        reader.next();
                    }
                    
                }
                reader.next();
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }
        }
        return mappa;
    }
}
