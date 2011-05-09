/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Managery;

import ScigaczDB.*;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
    public boolean DodajWypozyczenie(Wypozyczenia w) {
        em = M.DajEM();
        Query q = em.createQuery("SELECT w from Wypozyczenia w where w.idZasobu=" + w.getIdZasobu().getIdZasobu());
        List<Wypozyczenia> wy = q.getResultList();
        if (wy.size() == 0) {
            persist(w);
            return true;
        } else {
            return false;
        }
    }
/**
 * Edycja wypożyczenia.. w zasadzie nieużywane.
 * @param w Obiekt typu Wypożyczenie.
 */
    public void EdytujWypozyczenie(Wypozyczenia w) {
        merge(w);
    }
/**
 * Oznaczenie daty zwrotu w danym wypożyczeniu.
 * @param w Obiekt typu wypożyczenie.
 * @param data  Data zwrotu.
 */
    public void OznaczDateZwrotu(Wypozyczenia w, Date data) {
        w.setDataZwrotu(data);
        merge(w);
    }
/**
 * Usunięcie wypożyczenia po zwrocie przedmiotu.
 * @param id    Id wypożyczenia.
 */
    public void UsunWypozyczenie(int id) {
        Wypozyczenia w = PokazWypozyczenie(id);
        remove(w);
    }
/**
 * Pokazuje wypozyczenie o podanym id.
 * @param id    Id wypożyczenia.
 * @return  Obiekt typu Wypozyczenie.
 */
    public Wypozyczenia PokazWypozyczenie(int id) {
        Wypozyczenia wyp;
        em = M.DajEM();
        Query q = em.createQuery("SELECT w from Wypozyczenia w where w.idWypozyczenia=" + id);
        wyp = (Wypozyczenia) q.getSingleResult();
        return wyp;
    }
/**
 * Pokazuje wypożyczenie dla konkretnego zasobu po jego id.
 * @param id    Id zasobu.
 * @return  Obiekt typu Wypozyczenia.
 */
    public Wypozyczenia PokazWypozyczeniePoZasobie(int id) {
        Wypozyczenia wyp;
        em = M.DajEM();
        Query q = em.createQuery("SELECT w from Wypozyczenia w where w.idZasobu=" + id);
        wyp = (Wypozyczenia) q.getSingleResult();
        int d = 5;
        return wyp;
    }
/**
 * Listuje wszystkie wypozyczenia.
 * @return  Lista wypożyczeń.
 */
    public List<Wypozyczenia> ListujWypozyczenia() {
        List<Wypozyczenia> listaWypozyczen;
        em = M.DajEM();
        Query q = em.createQuery("SELECT w from Wypozyczenia w where w.DataZwrotu=0"); //tu jeszcze bedzie where
        listaWypozyczen = q.getResultList();
        return listaWypozyczen;
    }
/**
 * Listuje wszystkie zasoby wypożyczone przez danego użytkownika.
 * @param u     Obiekt typu Uzytkownicy.
 * @return  Lista wypozyczeń.
 */
    public List<Wypozyczenia> ListujWypozyczeniaUdostepniacz(Uzytkownicy u) {
        List<Wypozyczenia> listaWypozyczen;
        em = M.DajEM();
        Query q = em.createQuery("SELECT w from Wypozyczenia w where idUdostepniacza=" + u.getIdUzytkownika()); //tu jeszcze bedzie where
        listaWypozyczen = q.getResultList();
        return listaWypozyczen;
    }
/**
 * Listuje wszystkie zasoby wypożyczone danemu użytkownikowi.
 * @param u Obiekt typu Uzytkownicy.
 * @return  Lista wypożyczeń.
 */
    public List<Wypozyczenia> ListujWypozyczeniaWypozyczacz(Uzytkownicy u) {
        List<Wypozyczenia> listaWypozyczen;
        em = M.DajEM();
        Query q = em.createQuery("SELECT w from Wypozyczenia w where idWypozyczacza=" + u.getIdUzytkownika()); //tu jeszcze bedzie where
        listaWypozyczen = q.getResultList();
        return listaWypozyczen;
    }
/**
 * Listuje wszystkie wypożyczenia, których termin zwrotu upływa za tydzień.
 * @return  Lista wypożyczeń.
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
        em = M.DajEM();
        Date now = new Date();
        try {
            Query q = em.createQuery("SELECT w from Wypozyczenia w where DATEDIFF(w.dataZwrotu,CURRENT_DATE)<0");
            listaWypozyczen = q.getResultList();
            return listaWypozyczen;
        } catch (Exception e) {
            System.out.print("blad pytania!!!!");
            return null;
        }
    }
/**
 * Podaje właściciela podanego zasobu.
 * @param z Obiekt typu Zasoby.
 * @return  Obiekt typu Uzytkownicy.
 */
    public Uzytkownicy PodajWlasciciela(Zasoby z) {
        Uzytkownicy u = z.getIdUzytkownika();
        return u;
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
         //   em.flush();
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
       //     em.flush();
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
     //       em.flush();
        }
    }
}
