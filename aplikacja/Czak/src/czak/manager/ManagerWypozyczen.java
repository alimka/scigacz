package czak.manager;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import czak.entity.*;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author pedros
 * Manager odpowiedzialny za tabelę Wypozyczenia.
 */
public class ManagerWypozyczen {
    ManagerNadManagery M;
    EntityManager em;

    /**
     * Tworzenie ManageraNadManagery dla tabeli Wypozyczenia.
     */
    public ManagerWypozyczen() {
              M = new ManagerNadManagery();

    }
/**
 * Dodanie wypożyczenia do bazy.
 * @param w Obiekt typu Wypozyczenia.
 * @return  True - jeśli pomyślnie zostało dodane wypożyczenie, false - jeśli wypożyczenie o taki zasób już istnieje.
 */
    public ArrayList<Wypozyczenia> listujTydzienPrzed() {
        ArrayList<Wypozyczenia> listaWypozyczen;
        em = M.DajEM();
        Query q = em.createQuery("SELECT w from Wypozyczenia w where DATEDIFF(w.dataZwrotu,CURRENT_DATE)=7");
        listaWypozyczen = (ArrayList<Wypozyczenia>) q.getResultList();
        return listaWypozyczen;
    }
/**
 * Listuje wypożyczenia, ktorych termin zwrotu upływa dziś.
 * @return  Lista wypożyczeń.
 */
    public List<Wypozyczenia> listujWDniu() {
              em = M.DajEM();
        Query q = em.createQuery("SELECT w from Wypozyczenia w where DATEDIFF(w.dataZwrotu,CURRENT_DATE)=0");
        List listaWypozyczen = q.getResultList();
        return listaWypozyczen;
    }
/**
 * Listuje wypozyczenia przedawnione.
 * @return  lista wypożyczeń.
 */
    public List<Wypozyczenia> listujWszystkichPrzedawnionych() {
        List<Wypozyczenia> listaWypozyczen;
        Date now = new Date();
        try {
                  em = M.DajEM();
            Query q = em.createQuery("SELECT w from Wypozyczenia w where DATEDIFF(w.dataZwrotu,CURRENT_DATE)<0");
            listaWypozyczen = q.getResultList();
            return listaWypozyczen;
        } catch (Exception e) {
            System.out.print("blad pytania!!!!"+e);
            return null;
        }
    }
    /**
     * Dodaje obiekt do bazy.
     * @param object
     */
    public void persist(Object object) {
       try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
         //   em.flush();
        }
    }

    /**
     * Jeśli podobny obiekt już istnieje w bazie scala go z nowym obiektem.
     * @param object
     */
    public void merge(Object object) {
        try {
            em.getTransaction().begin();

            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
       //     em.flush();
        }
    }

    /**
     * Usuwa podany obiekt z bazy.
     * @param object
     */
    public void remove(Object object) {
        try {
            em.getTransaction().begin();

            em.remove(em.merge(object));
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
     //       em.flush();
        }
    }
}
