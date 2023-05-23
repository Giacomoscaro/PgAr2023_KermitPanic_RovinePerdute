import java.util.ArrayList;

public class RovineManager {
    ArrayList<Citta> lista_citta = new ArrayList<>();
    public RovineManager(ArrayList<Citta> lista_citta) {
        this.lista_citta = lista_citta;
    }
    public ArrayList<Citta> getLista_citta() {
        return lista_citta;
    }

    public ArrayList<Citta> calcola_peso_team1(ArrayList<Citta> lista_citta){
        CalcoloPercorso.inizializza_citta(lista_citta, 1);
        return CalcoloPercorso.calcolo_percorso(lista_citta.get(0), lista_citta.get(lista_citta.size()-1), lista_citta);
    }
    public ArrayList<Citta> calcola_peso_team2(ArrayList<Citta> lista_citta){
        CalcoloPercorso.inizializza_citta(lista_citta, 2);
        return CalcoloPercorso.calcolo_percorso(lista_citta.get(0), lista_citta.get(lista_citta.size()-1), lista_citta);
    }

}
