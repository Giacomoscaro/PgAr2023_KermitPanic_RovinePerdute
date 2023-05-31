import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.math.*;
import java.lang.Exception;

public class CalcoloPercorso {
	/**
	 * Visualizza i pesi delle strade delle città
	 * @param listaCitta elenco delle città da cui visualizzare le strade con i loro pesi
	 */
	public static void visualizza_pesi(ArrayList<Citta> listaCitta) {
		for(Citta c : listaCitta) {
			System.out.println("Citta: " + c.getId() + " " +  c.getNome());
			if(!c.getStrade().isEmpty())
				for(Entry<Integer,Double> s : c.getStrade().entrySet()) {
					System.out.println("Peso di " + c.getNome() + " verso città: " + s.getKey() + " : " + s.getValue());
			}
		}
	}
	
	/**
	 * Distanza per team Tonathiu (distanza euclidea in piano)
	 * @param c1
	 * @param c2
	 * @return distanza tra le città specificate per il team Tonathiu
	 */
	public static double distanza1(Citta c1, Citta c2) {
		return Math.sqrt(Math.pow(c1.getPosizione().getX()-c2.getPosizione().getX(),2) + Math.pow(c1.getPosizione().getY()-c2.getPosizione().getY(),2));
	}
	
	/**
	 * Distanza per team Metztli (differenza di quota)
	 * @param c1
	 * @param c2
	 * @return distanza tra le città specificate per il team Metztli
	 */
	public static double distanza2(Citta c1, Citta c2) {
		return Math.abs(c1.getPosizione().getZ() - c2.getPosizione().getZ());
	}
	
	/**
	 * Ritorna la città che ha peso cumulativo minore tra quelle specificate
	 * @param listaCitta elenco delle città da considerare
	 * @return la città che ha il peso cumulativo minore
	 */
	private static Citta cittaPesoMinore(ArrayList<Citta> listaCitta) {
		if(listaCitta.size()==0)
			return null;
		
		Citta cittaMinore=listaCitta.get(0); //imposta la prima città come quella minore
		
		if(listaCitta.size()==1)
			return cittaMinore;
		
		for(Citta c : listaCitta.subList(1, listaCitta.size()-1)) //scorre listaCittà partendo dal secondo elemento
			if(c.getPeso() < cittaMinore.getPeso())
				cittaMinore = c;
		
		return cittaMinore;
	}

	/**
	 * Controlla se un dato id corrisponde a uno dei nodi in ingresso
	 * @param id
	 * @param nodi lista dei nodi dove effettuare la ricerca
	 * @return true se uno dei nodi ha per ID l'id specificato
	 */
	private static boolean nodoPresente(int id, ArrayList<Citta>nodi){
		for(Citta c : nodi)
			if(c.getId() == id)
				return true;

		return false;
	}
	
	/**
	 * Restituisce un elenco di ID delle città adiacenti a quella data e presenti nella lista di nodi specificata
	 * @param citta
	 * @param nodiValidi
	 * @return città collegate a quella data e presenti in nodiValidi
	 */
	private static ArrayList<Integer> idCittaVicine(Citta citta, ArrayList<Citta>nodiValidi) {
		ArrayList<Integer>cittaVicine = new ArrayList<Integer>();
		for(Entry c : citta.getStrade().entrySet())
			if(nodoPresente((Integer)c.getKey(), nodiValidi)) //se è presente una strata verso un nodo valido
					cittaVicine.add((Integer)c.getKey()); // aggiungi la città a cittaVicine
		
		return cittaVicine;
	}
	
	/**
	 * Calcola il percorso usando l'algoritmo di Dijkstra
	 * @param partenza
	 * @param arrivo
	 * @param listaCitta elenco di tutte le città
	 * @return lista di città per andare da partenza a arrivo
	 */
	public static ArrayList<Citta> calcolo_percorso(Citta partenza, Citta arrivo, ArrayList<Citta>listaCitta) {
		/*
		 *  Creazione di una lista nodiCitta contenente i nodi da considerare durante le operazioni di ricerca
		 *  Per evitare di modificare listaCitta, si inizializza nodiCitta ad una copia di listaCitta
		 */
		ArrayList<Citta>nodiCitta=(ArrayList<Citta>) listaCitta.clone(); 
		partenza.setPeso(0); //imposta a 0 il peso cumulativo della città di partenza
		
		
		while(!nodiCitta.isEmpty()) {
			
			Citta cittaMinore = cittaPesoMinore(nodiCitta);
			
			nodiCitta.remove(cittaMinore);
			
			// scorri tutte le citta adiacenti a cittaMinore
			for(Integer i : idCittaVicine(cittaMinore,nodiCitta)) {
				double pesoVicino = cittaMinore.getPeso() + cittaMinore.getStrade().get(i); // calcola il nuovo peso cumulativo per andare alla città con ID i
				
				// se il nuovo peso cumulativo è minore di quello già presente, aggiornalo con quello calcolato
				if(pesoVicino < listaCitta.get(i).getPeso()) { 
					listaCitta.get(i).setPeso(pesoVicino);
					listaCitta.get(i).setCitta_precedente(cittaMinore);
				}
			}
			if(cittaMinore == arrivo) // se si è giunti all'arrivo, si termina prima l'algoritmo
				break;
			
		}
		
		ArrayList<Citta> percorso = new ArrayList<Citta>();
		/*
		 * Scorre tutte le città dall'arrivo alla partenza sfruttando l'attributo cittaPrecedente
		 * e crea la lista delle città che compongono il percorso
		 */
		Citta tappa = arrivo;
		do {
			percorso.add(0,tappa); // aggiungi la tappa corrente all'inizio del percorso
			tappa = tappa.getCitta_precedente();
		}while(percorso.get(0) != partenza);
		
		return percorso;
	}
	
}
