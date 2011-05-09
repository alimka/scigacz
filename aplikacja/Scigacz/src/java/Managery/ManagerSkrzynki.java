/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Managery;

import ScigaczDB.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author pedros
 * Manager odpowiedzialny za wiadomości w systemie.
 */
public class ManagerSkrzynki {

    ManagerNadManagery M;
    EntityManager em;

    /**
     *Tworzy ManageraNadManagery odpowiedzialnego za operacje na tabelach Wiadomosci i Systemowe.
     */
    public ManagerSkrzynki() {
        M = new ManagerNadManagery();
    }

    /**
     * Dodaje wiadomość do tabeli Wiadomości.
     * @param w     Obiekt typu Wiadomości.
     * @param idOdbiorcy    Id odbiorcy wiadomości.
     * @param idNadawcy     Id nadawcy wiadomości.
     */
    public Wiadomosci DodajWiadomosc(Wiadomosci w, Uzytkownicy idOdbiorcy, Uzytkownicy idNadawcy) {
        w.setIdNadawcy(idNadawcy);
        w.setIdOdbiorcy(idOdbiorcy);
        merge(w);
        return w;
    }

    /**
     * Dodaje wiadomość do tabeli Systemowe.
     * @param w     Obiekt typu Wiadomości.
     * @param idOdbiorcy    Id odbiorcy wiadomości.
     */
    public void DodajWiadomoscSystemowa(Systemowe w, Uzytkownicy idOdbiorcy) {
        w.setIdOdbiorcy(idOdbiorcy);
        merge(w);
    }

    /**
     * Edycja wiadomości w bazie.
     * @param w Obiekt typu Wiadomości do edycji.
     */
    public void EdytujWiadomosc(Wiadomosci w) {
        merge(w);
    }

    /**
     * Usunięcie wiadomości z bazy dla użytkownika o podanym id.
     * @param id    Id wiadomości.
     * @param idUzytkownika Id użytkownika.
     */
    public void UsunWiadomosc(int id, int idUzytkownika) {
        Wiadomosci w = PokazWiadomosc(id);
        if (w.getIdNadawcy().getIdUzytkownika() == idUzytkownika) {
            w.setJestNadawca(false);
        } else {
            w.setJestOdbiorca(false);
        }
        if (!w.getJestNadawca() && !w.getJestOdbiorca()) {
            remove(w);
        }
        merge(w);
    }

    /**
     * Usunięcie wiadomości systemowej z bazy dla uzytkownika o podanym id.
     * @param id    Id wiadomości systemowej.
     * @param idUzytkownika Id użytkowika. Jeśli to administrator chce usunąć wiadomości - id<0.
     */
    public void UsunWiadomoscSystemowa(int id, int idUzytkownika) {
        ///jeśli admin chce usunac wiadomosci systemowe to idUzytkownika < 0

        Systemowe s = PokazWiadomoscSystemowa(id);
        if (idUzytkownika < 0) {
            remove(s);
        } else {
            s.setJestOdbiorca(false);
        }
        merge(s);
    }

    /**
     * Wysłanie wiadomości do administratora przez danego użytkownika.
     * @param w     Obiekt typu Wiadomości.
     * @param nadawca   Obiekt typu Uzytkownicy.
     */
    public void WyslijDoAdmina(Wiadomosci w, Uzytkownicy nadawca) {
        em = M.DajEM();
        Query q = em.createQuery("SELECT u from Uzytkownicy u where u.uprawnienia=1");
        List<Uzytkownicy> admini = q.getResultList();

        for (Uzytkownicy uzytkownicy : admini) {
            Wiadomosci ww = w;
            ww.setIdOdbiorcy(uzytkownicy);
            ww.setIdNadawcy(nadawca);
            merge(ww);
        }


    }

    /**
     * Sprawdzenie czy istnieje już prośba o wypożyczenie danego zasobu od danego użytkownika.
     * @param idZasobu   Id zasobu.
     * @param idUzytkownika  Id użytkownika.
     * @return   True jeśli istnieje prośba, false w przeciwnym wypadku.
     */
    public boolean CzyJuzJestProsba(int idZasobu, int idUzytkownika) {
        em = M.DajEM();
        Query q = em.createQuery("SELECT s from Systemowe s where sidZasobu=" + idZasobu + " and s.idDoZwrotu=" + idUzytkownika);
        List<Systemowe> sys = q.getResultList();
        if (sys.size() != 0) {
            return true;
        } else {
            return false;
        }

    }
/**
 * Pokazuje wiadomość o podanym id.
 * @param id    Id wiadomości.
 * @return  Obiekt typu Wiadomości.
 */
    public Wiadomosci PokazWiadomosc(int id) {
        Wiadomosci wiadomosc;
        em = M.DajEM();
        Query q = em.createQuery("SELECT w from Wiadomosci w where w.idWiadomosci=" + id);
        wiadomosc = (Wiadomosci) q.getSingleResult();
        return wiadomosc;
    }
/**
 * Pokazuje wiadomość systemową o podanym id.
 * @param id    Id wiadomości systemowej.
 * @return  Obiekt typu Systemowe.
 */
    public Systemowe PokazWiadomoscSystemowa(int id) {
        Systemowe wiadomosc;
        em = M.DajEM();
        Query q = em.createQuery("SELECT s from Systemowe s where s.idSys=" + id);
        wiadomosc = (Systemowe) q.getSingleResult();
        return wiadomosc;
    }
/**
 * Oznaczenie wiadomości jako przeczytanej.
 * @param id    Id wiadomości.
 */
    public void OznaczJakoPrzeczytana(int id) {
        Wiadomosci w = PokazWiadomosc(id);
        w.setCzyPrzeczytana(true);
        merge(w);
    }
/**
 * Oznaczenie wiadomości jako nieprzeczytanej.
 * @param id    Id wiadomości.
 */
    public void OznaczJakoNieprzeczytana(int id) {
        Wiadomosci w = PokazWiadomosc(id);
        w.setCzyPrzeczytana(false);
        merge(w);
    }

/**
 * Oznaczenie wiadomości systemowej jako przeczytanej.
 * @param id    Id wiadomości systemowej.
 */
    public void OznaczJakoPrzeczytanaSys(int id) {
        Systemowe w = PokazWiadomoscSystemowa(id);
        w.setCzyPrzeczytana(true);
        merge(w);
    }

/**
 * Oznaczenie wiadomości systemowej jako nieprzeczytanej.
 * @param id    Id wiadomości systemowej.
 */
    public void OznaczJakoNieprzeczytanaSys(int id) {
        Systemowe w = PokazWiadomoscSystemowa(id);
        w.setCzyPrzeczytana(false);
        merge(w);
    }
/**
 * Listuje wiadomości w skrzynce odbiorczej dla użytkownika o podanym id.
 * @param id    Id użytkownika.
 * @return      Lista wiadomości.
 */
    public List<Wiadomosci> ListujWiadomosciOdbiorcza(int id) {
        List<Wiadomosci> listaWiadomosci;
        em = M.DajEM();
        Query q = em.createQuery("SELECT w from Wiadomosci w where w.idOdbiorcy=" + id + " and w.jestOdbiorca=1 order by w.data desc");

        listaWiadomosci = q.getResultList();
        return listaWiadomosci;
    }
/**
 * Listuje wiadomości systemowe użytkownika o podanym id.
 * @param id    Id użytkownika.
 * @return  Lista wiadomości systemowych.
 */
    public List<Systemowe> ListujWiadomosciSystemowe(int id) {
        List<Systemowe> listaSys;
        em = M.DajEM();
        Query q = em.createQuery("SELECT s from Systemowe s where s.idOdbiorcy=" + id + " and s.jestOdbiorca=1 order by s.data desc");

        listaSys = q.getResultList();
        return listaSys;
    }
/**
 * Listuj wiadomości systemowe użytkownika o podanym id (nawet przez niego usunięte).
 * @param id    Id użtkownika, jeśli id<0 wylistuje wszystkie wiadomości.
 * @return  Lista wiadomości systemowych.
 */
    public List<Systemowe> ListujWszystkieSystemowe(int id) {
        //jesli podacie id<0 wylistuje wszysytkie
        List<Systemowe> listaSys;
        String query = "SELECT s from Systemowe s";
        if (id > 0) {
            query += "where s.idOdbiorcy=" + id + " order by.s.data desc";
        }
        em = M.DajEM();
        Query q = em.createQuery(query);

        listaSys = q.getResultList();
        return listaSys;
    }
/**
 * Listuje nagany użytkownika o podanym id.
 * @param id    Id użytkownika, po podaniu id<0 wylistuje wszystkie nagany.
 * @return  Lista wiadomości systemowych typu Nagana.
 */
    public List<Systemowe> ListujNagany(int id) {
        //jesli podacie id<0 wylistuje wszysytkie
        List<Systemowe> listaSys;
        String query = "SELECT s from Systemowe s where s.rodzaj=3 ";
        if (id > 0) {
            query += "and s.idOdbiorcy=" + id;
        }
        em = M.DajEM();
        Query q = em.createQuery(query);

        listaSys = q.getResultList();
        return listaSys;

    }
/**
 * Listuje wiadomości w skrzynce nadawczej użytkownika o podanym id.
 * @param id    Id użytkownika.
 * @return  Lista wiadomości.
 */
    public List<Wiadomosci> ListujWiadomosciNadawcza(int id) {
        List<Wiadomosci> listaWiadomosci;
        em = M.DajEM();
        Query q = em.createQuery("SELECT w from Wiadomosci w where w.idNadawcy=" + id + " and w.jestNadawca=1 order by w.data desc");

        listaWiadomosci = q.getResultList();
        return listaWiadomosci;
    }

    /**
     * Dodaje obiekt do bazy.
     * @param object
     */
    public void persist(Object object) {
        em = M.DajEM();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            //em.flush();
        }
    }

    /**
     * Jeśli podobny obiekt już istnieje w bazie scala go z nowym obiektem.
     * @param object
     */
    public void merge(Object object) {
        em = M.DajEM();
        try {
            em.getTransaction().begin();

            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            //em.flush();
        }
    }

    /**
     * Usuwa podany obiekt z bazy.
     * @param object
     */
    public void remove(Object object) {
        em = M.DajEM();
        try {
            em.getTransaction().begin();

            em.remove(em.merge(object));
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
          //  em.flush();
        }
    }
}
