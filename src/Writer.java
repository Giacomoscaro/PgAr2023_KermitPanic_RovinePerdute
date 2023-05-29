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
	
	public void scrivi_percorsi(ArrayList<Citta> percorso1, ArrayList<Citta> percorso2) throws XMLStreamException {
        FileOutputStream risultato;
        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer=null;
        {
        try {//inizializzazione del writer per generare l'xml definitivo
            risultato = new FileOutputStream(outputFile);
            writer = output.createXMLStreamWriter(risultato);
            writer.writeStartDocument("UTF-8", "1.0");
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del writer:");
            System.out.println(e.getMessage());
        }
        }
        writer.writeStartElement("routes");
        writer.writeStartElement("route");
        writer.writeAttribute("team", "Tonathiu");
        writer.writeAttribute("cost", Double.toString(percorso1.get(percorso1.size()-1).getPeso()));
        writer.writeAttribute("cities", Integer.toString(percorso1.size()));
        for(Citta c : percorso1){
            writer.writeEmptyElement("city");
            writer.writeAttribute("id", Integer.toString(c.getId()));
            writer.writeAttribute("name", c.getNome());
        }
        writer.writeEndElement();
        writer.writeStartElement("route");
        writer.writeAttribute("team", "Metztli");
        writer.writeAttribute("cost", Double.toString(percorso2.get(percorso2.size()-1).getPeso()));
        writer.writeAttribute("cities", Integer.toString(percorso2.size()));
        for(Citta c : percorso2){
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
