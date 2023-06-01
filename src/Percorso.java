import java.util.ArrayList;

public class Percorso<T> {
	private ArrayList<T> nodi;
	private double costo;
	
	Percorso(ArrayList<T> nodi, double costo){
		this.costo = costo;
		this.nodi = nodi;
	}
	
	public ArrayList<T>getNodi(){
		return nodi;
	}
	
	public double getCosto() {
		return costo;
	}
}
