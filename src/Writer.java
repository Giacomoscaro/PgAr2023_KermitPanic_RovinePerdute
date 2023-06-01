import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Writer {
    private String outputFile;
	
    public Writer(String outputFile) {
	   this.outputFile = outputFile;
   }
	
    /**
     * Scrive su outputFile i percorsi dei due team
     * @param percorso1
     * @param percorso2
     * @throws XMLStreamException
     */
	public void scrivi_percorsi(Percorso<Citta> percorso1, Percorso<Citta> percorso2) throws XMLStreamException {
        FileOutputStream risultato;
        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer=null;
        
        try {//inizializzazione del writer per generare l'xml definitivo
            risultato = new FileOutputStream(outputFile);
            writer = output.createXMLStreamWriter(risultato);
            writer.writeStartDocument("UTF-8", "1.0");
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del writer:");
            System.out.println(e.getMessage());
        }
        
        writer.writeStartElement("routes");
        
        writer.writeStartElement("route");
        writer.writeAttribute("team", "Tonathiu");
        writer.writeAttribute("cost", Double.toString(percorso1.getCosto()) ); // il costo totale è uguale al costo cumulativo della città di arrivo
        writer.writeAttribute("cities", Integer.toString(percorso1.getNodi().size()));
        
        // scrittura del percorso 1
        for(Citta c : percorso1.getNodi()){
            writer.writeEmptyElement("city");
            writer.writeAttribute("id", Integer.toString(c.getId()));
            writer.writeAttribute("name", c.getNome());
        }
        writer.writeEndElement();
        
        writer.writeStartElement("route");
        writer.writeAttribute("team", "Metztli");
        writer.writeAttribute("cost", Double.toString(percorso2.getCosto()) );
        writer.writeAttribute("cities", Integer.toString(percorso2.getNodi().size()));
        
        // scrittura del percorso 2
        for(Citta c : percorso2.getNodi()){
            writer.writeEmptyElement("city");
            writer.writeAttribute("id", Integer.toString(c.getId()));
            writer.writeAttribute("name", c.getNome());
        }
        writer.writeEndElement();
        
        writer.writeEndElement();
        writer.writeEndDocument();
        writer.flush();
        writer.close();
    }
}
