package czak;

import czak.entity.Wypozyczenia;
import java.util.List;
/**
 * @author Paulina Łoś
 * Obsługa zdarzenia czasJuzUpłynął, polegającego na tym, że
 * dla danego wypożyczenia upłynął już termin zwrotu, a zasób nie
 * został odznaczony przez udostępniacza jako zwrócony
 */

public class InfoCzasJuzUplynal implements Runnable  {
    List<Wypozyczenia> lista;
    /**
     * nadpisana funkcja public void run() interfacu Runnable
     * wykonująca się przy tworzeniu każdego obiektu
     */
    @Override
    public void run(){
              
        lista=czak.Main.mw.listujWszystkichPrzedawnionych();
        new Mail(3,lista).wyslijMaileDoWszystkichZListy();
        new WiadomoscWew(3,lista).wyslijWiadomoscDoWszystkichZListy();
    }}