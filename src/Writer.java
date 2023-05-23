import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Writer {
    public void scrivi_percorsi(ArrayList<Citta> percorso1, ArrayList<Citta> percorso2){
        FileOutputStream risultato;
        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer;
        {
            try {//inizializzazione del writer per generare l'xml definitivo
                risultato = new FileOutputStream("fileXML/percorsiRovine.xml");
                writer = output.createXMLStreamWriter(risultato);
                writer.writeStartDocument("UTF-8", "1.0");
            } catch (Exception e) {
                System.out.println("Errore nell'inizializzazione del writer:");
                System.out.println(e.getMessage());
            }
        }

    }
}
