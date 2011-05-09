package czak;

import czak.entity.Wypozyczenia;
import java.util.List;
/**
 * @author Paulina Łoś
 * Obsługa zdarzenia zostalTydzień, polegającego na tym, że
 * dla danego wypożyczenia za dokladnie tydzień upływa termin zwrotu
 */


public class InfoZostalTydzien implements Runnable  {
    List<Wypozyczenia> lista;
    /**
     * nadpisana funkcja public void run() interfacu Runnable
     * wykonująca się przy tworzeniu każdego obiektu
     */
    @Override
    public void run(){
        lista=czak.Main.mw.listujTydzienPrzed();
        new Mail(1,lista).wyslijMaileDoWszystkichZListy();
        new WiadomoscWew(1,lista).wyslijWiadomoscDoWszystkichZListy();
    }}