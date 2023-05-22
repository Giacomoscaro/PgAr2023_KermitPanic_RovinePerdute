import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Reader r = new Reader();
        ArrayList<Citta> mappa = r.leggi_mappa();
        for(Citta c : mappa)
            System.out.println(c);

    }
}