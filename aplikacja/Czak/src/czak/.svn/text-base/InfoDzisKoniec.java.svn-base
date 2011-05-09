package czak;

import czak.entity.Wypozyczenia;
import java.util.List;
/**
 * @author Paulina Łoś
 * Obsługa zdarzenia dzisKoniec, polegającego na tym, że
 * dla danego wypożyczenia wlaśnie dziś upływa termin zwrotu, a zasób nie
 * został odznaczony przez udostępniacza jako zwrócony
 */

public class InfoDzisKoniec implements Runnable  {
    List<Wypozyczenia> lista;
    /**
     * nadpisana funkcja public void run() interfacu Runnable
     * wykonująca się przy tworzeniu każdego obiektu
     */
    @Override
    public void run(){
        lista=czak.Main.mw.listujWDniu();
        new Mail(2,lista).wyslijMaileDoWszystkichZListy();
        new WiadomoscWew(2,lista).wyslijWiadomoscDoWszystkichZListy();
    }}