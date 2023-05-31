import java.util.HashMap;

public class Citta {
    private int id;
    private String nome;
    private Posizione posizione;
    private double peso;
    private HashMap<Integer,Double> strade = new HashMap<>(); // HashMap contenente le città collegate con il loro peso (distanza) relativo a quella attuale
    private Citta citta_precedente; // città attraversata in precedenza prima di arrivare a quella attuale

    public Citta(int id, String nome, Posizione posizione) {
        this.id = id;
        this.nome = nome;
        this.posizione = posizione;
        this.strade = new HashMap<Integer,Double>();
    }

    /**
     * Presi l'id di una città e il peso per arrivare a essa dalla città attuale
     * aggiunge un nuovo elemento alle strade della città
     * @param id id della città collegata
     * @param peso peso in carburante per arrivarci dalla città che invoca il metodo
     */
    public void aggiungi_strada(int id, double peso){
        strade.put(id, peso);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public double getPeso() {
        return peso;
    }

    public HashMap<Integer, Double> getStrade() {
        return strade;
    }

    public Citta getCitta_precedente() {
        return citta_precedente;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setCitta_precedente(Citta citta_precedente) {
        this.citta_precedente = citta_precedente;
    }
    
    /**
     * Ritorna una stringa che descrive una citta: nome, posizione, collegamenti con altre città
     */
    public String toString() {
        String citta = "id:\t" + id + "\nnome:\t" + nome + "\nposizione:\t(" + posizione.getX() + ", " + posizione.getY() + ", " + posizione.getZ() + ")";
        if (strade.size() > 0) {
            citta += "\nlink to:\t";
            citta += "\n" + strade.toString();
        }
        else{
            citta += "\nla città non ha strade praticabili verso le altre città";
        }
        return citta;
    }
}
