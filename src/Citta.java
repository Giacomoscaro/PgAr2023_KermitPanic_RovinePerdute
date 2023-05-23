import java.util.HashMap;

public class Citta {
    private int id;
    private String nome;
    private Posizione posizione;
    private double peso;
    private HashMap<Integer,Double> strade;
    private Citta citta_precedente;

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

}
