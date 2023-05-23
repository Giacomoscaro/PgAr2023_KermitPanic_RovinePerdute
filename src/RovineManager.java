import java.util.ArrayList;
import java.util.Map;

public class RovineManager {
    ArrayList<Citta> lista_citta = new ArrayList<>();
    public RovineManager(ArrayList<Citta> lista_citta) {
        this.lista_citta = lista_citta;
    }
    public ArrayList<Citta> getLista_citta() {
        return lista_citta;
    }
    /**
     * Porta tutti i pesi delle strade a Double.POSITIVE_INFINITY
     * @param listaCitta lista delle città dove resettare i pesi
     */
    public static void inizializza_citta(ArrayList<Citta> listaCitta, int team) {
        for(int i=0; i<listaCitta.size(); i++) {
            listaCitta.get(i).setCitta_precedente(null);
            listaCitta.get(i).setPeso(Double.POSITIVE_INFINITY);
            for(Map.Entry<Integer,Double> s : listaCitta.get(i).getStrade().entrySet()) {
                if(team==1)
                    s.setValue(CalcoloPercorso.distanza1( listaCitta.get(i), listaCitta.get(s.getKey()) ));
                else s.setValue(CalcoloPercorso.distanza2( listaCitta.get(i), listaCitta.get(s.getKey()) ));
            }
        }
    }

    public ArrayList<Citta> calcola_peso_team1(ArrayList<Citta> lista_citta){
        inizializza_citta(lista_citta, 1);
        return CalcoloPercorso.calcolo_percorso(lista_citta.get(0), lista_citta.get(lista_citta.size()-1), lista_citta);
    }
    public ArrayList<Citta> calcola_peso_team2(ArrayList<Citta> lista_citta){
        inizializza_citta(lista_citta, 2);
        return CalcoloPercorso.calcolo_percorso(lista_citta.get(0), lista_citta.get(lista_citta.size()-1), lista_citta);
    }

}
