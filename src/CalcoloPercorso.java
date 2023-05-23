import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.math.*;
import java.lang.Exception;

public class CalcoloPercorso {
	/**
	 * Porta tutti i pesi delle strade a Double.POSITIVE_INFINITY
	 * @param listaCitta lista delle città dove resettare i pesi
	 */
	public static void inizializza_citta(ArrayList<Citta> listaCitta, int team) {
		for(int i=0; i<listaCitta.size(); i++) {
			listaCitta.get(i).setCitta_precedente(null);
			for(Entry<Integer,Double> s : listaCitta.get(i).getStrade().entrySet()) {
				if(team==1)
					s.setValue(distanza1( listaCitta.get(i), listaCitta.get(s.getKey()) ));
				else s.setValue(distanza2( listaCitta.get(i), listaCitta.get(s.getKey()) ));
			}
		}
	}
	
	/**
	 * Visualizza i pesi delle strade delle città
	 * @param listaCitta
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
	
	private static double distanza1(Citta c1, Citta c2) {
		return Math.sqrt(Math.pow(c1.getPosizione().getX()-c2.getPosizione().getX(),2) + Math.pow(c1.getPosizione().getY()-c2.getPosizione().getY(),2));
	}
	
	private static double distanza2(Citta c1, Citta c2) {
		return Math.abs(c1.getPosizione().getZ() - c2.getPosizione().getZ());
	}
	
	private static Citta cittaPesoMinore(ArrayList<Citta> listaCitta) {
		if(listaCitta.size()==0)
			return null;
		
		Citta cittaMinore=listaCitta.get(0);
		if(listaCitta.size()==1)
			return cittaMinore;
		for(Citta c : listaCitta.subList(1, listaCitta.size()-1))
			if(c.getPeso() < cittaMinore.getPeso())
				cittaMinore = c;
		
		return cittaMinore;
	}

	private static boolean nodoPresente(int id, ArrayList<Citta>nodi){
		for(Citta c : nodi)
			if(c.getId() == id)
				return true;

		return false;
	}
	private static ArrayList<Integer> idCittaVicine(Citta citta, ArrayList<Citta>nodiValidi) {
		ArrayList<Integer>cittaVicine = new ArrayList<Integer>();
		for(Entry c : citta.getStrade().entrySet())
			if(nodoPresente((Integer)c.getKey(), nodiValidi))
					cittaVicine.add((Integer)c.getKey());
		
		return cittaVicine;
	}
	public static ArrayList<Citta> calcolo_percorso(Citta partenza, Citta arrivo, ArrayList<Citta>listaCitta) {
		ArrayList<Citta>nodiCitta=(ArrayList<Citta>) listaCitta.clone();
		partenza.setPeso(0);
		while(!nodiCitta.isEmpty()) {
			
			Citta cittaMinore = cittaPesoMinore(nodiCitta);
			
			if(cittaMinore == arrivo)
				break;
			
			nodiCitta.remove(cittaMinore);
			
			for(Integer i : idCittaVicine(cittaMinore,nodiCitta)) {
				double pesoVicino = cittaMinore.getPeso() + cittaMinore.getStrade().get(i);
				
				if(pesoVicino < listaCitta.get(i).getPeso()) {
					listaCitta.get(i).setPeso(pesoVicino);
					listaCitta.get(i).setCitta_precedente(cittaMinore);
				}
			}
			
		}
		
		ArrayList<Citta> percorso = new ArrayList<Citta>();
		Citta tappa = arrivo;
		do {
			percorso.add(0,tappa);
			tappa = tappa.getCitta_precedente();
		}while(percorso.get(0) == partenza);
		
		return percorso;
	}
	
}
