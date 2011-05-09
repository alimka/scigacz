/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Managery;

import ScigaczDB.*;
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
 * Manager odpowiedzialny za tabelę Zasoby, Kategorie i Zdjecia
 */
public class ManagerZasobow {

    ManagerNadManagery M;
    EntityManager em;

    /**
     * Tworzy ManagerNadManagery obsługujący operacje na tabelach Zdjecia, Kategorie i Zasoby.
     */
    public ManagerZasobow() {
        M = new ManagerNadManagery();
    }
/**
 * Wyszukuje zasoby po podanym kluczu.
 * @param klucz Klucz wyszukiwania.
 * @return  Lista zasobów.
 */
    public List<Zasoby> SzukajPoZasobach(String klucz) {
        List<Zasoby> listaZasobow;
        em = M.DajEM();
        Query q = em.createQuery("SELECT z from Zasoby z where z.nazwa like '%" + klucz + "%' or z.opis like '%" + klucz + "%'");
        listaZasobow = q.getResultList();
        return listaZasobow;

    }
/**
 * Listuje wszystkie kategorie.
 * @return  Lista kategorii.
 */
    public List<Kategorie> PokazKategorie() {
        List<Kategorie> listaKategorii;
        em = M.DajEM();
        Query q = em.createQuery("SELECT k from Kategorie k");
        listaKategorii = q.getResultList();
        return listaKategorii;
    }

    /**
     * Pokazuje kategorię o podanym id.
     * @param id    Id kategorii.
     * @return  Obiekt typu Kategorie
     */
    public Kategorie PokazKat(int id) {
        em = M.DajEM();
        Query q = em.createQuery("SELECT k from Kategorie k where k.idKategorii=" + id);
        Kategorie k = (Kategorie) q.getSingleResult();
        return k;
    }
/**
 * Dodaje zasób do bazy.
 * @param z Obiekt typu Zasoby
 */
    public void DodajZasob(Zasoby z) {
        persist(z);
    }
/**
 * Usuwa zasób z bazy o podanym id.
 * @param id    Id zasobu
 */
    public void UsunZasob(int id) {
        Zasoby z = PokazZasob(id);
        em = M.DajEM();
        Query q = em.createQuery("DELETE from Zasoby z where z.idZasobu=" + id);
        try {
            em.getTransaction().begin();
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.flush();
        }

           q = em.createQuery("DELETE from Zdjecia z where z.idZasobu=" + id);
        try {
            em.getTransaction().begin();
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.flush();
        }

           q = em.createQuery("DELETE from Wypozyczenia w where w.idZasobu=" + id);
        try {
            em.getTransaction().begin();
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.flush();
        }

        //   remove(z);
    }
/**
 * Pokazuje zasób o podanym id.
 * @param id       Id zasobu.
 * @return  Obiekt typu Zasoby.
 */
    public Zasoby PokazZasob(int id) {
        Zasoby zasob;
        em = M.DajEM();
        Query q = em.createQuery("SELECT z from Zasoby z where z.idZasobu=" + id);
        zasob = (Zasoby) q.getSingleResult();

        return zasob;
    }
/**
 * Pokazuje informacje o zdjęciu o podanym id.
 * @param id    Id zdjęcia.
 * @return  Obiekt typu Zdjecia.
 */
    public Zdjecia PokazZdjecie(int id) {
        Zdjecia zdjecie;
        em = M.DajEM();
        Query q = em.createQuery("SELECT z from Zdjecia z where z.idZdjecia=" + id);
        zdjecie = (Zdjecia) q.getSingleResult();

        return zdjecie;
    }
/**
 * Dodaje kategorię do bazy.
 * @param nazwa Nazwa kategorii.
 */
    public void DodajKategorie(String nazwa) {
        Kategorie k = new Kategorie(null, nazwa);
        persist(k);
    }
/**
 * Edytuje kategorię o podanym id.
 * @param id    Id kategorii.
 * @param X Nowa nazwa kategorii.
 */
    public void EdytujKategorie(int id, String X) {
        Kategorie k = PokazKat(id);
        k.setNazwa(X);
        merge(k);
    }
/**
 * Usuwa kategorię o podanym id z bazy.
 * @param id    Id kategorii.
 */
    public void UsunKategorie(int id) {
   Kategorie k=PokazKat(id);
   remove(k);

    }
/**
 * Dodaje zdjęcie do bazy.
 * @param z Obiekt typu Zdjecia.
 */
    public void DodajZdjecieZasobu(Zdjecia z) {
        persist(z);
    }
/**
 * Usuwa zdjęcie z bazy o podanym id.
 * @param id    Id zdjęcia.
 */
    public void UsunZdjecieZasobu(int id) {
        Zdjecia z = PokazZdjecie(id);
        remove(z);
    }
/**
 * Ustawia widoczność danego zasobu w zależności od bliskości znajomości.
 * @param widocznosc    0-dla wszystkich, 1-dla znajomych, 2-dla bliskich znajomych, 3-dla nikogo.
 * @param id    Id zasobu.
 */
    public void UstawWidocznosc(int widocznosc, int id) {
        Zasoby z = PokazZasob(id);
        z.setDostepnosc(widocznosc);
        merge(z);
    }
/**
 * Edycja danego zasobu.
 * @param z Obiekt typu Zasoby.
 */
    public void EdytujZasob(Zasoby z) {
        merge(z);
    }
/**
 * Edycja danego zdjęcia.
 * @param z Obiekt typu Zdjecia.
 */
    public void EdytujZdjecie(Zdjecia z) {
        merge(z);
    }
/**
 * Listuje zasobu użytkownika o podanym id.
 * @param id    Id użytkownika.
 * @return  Lista zasobów.
 */
    public List<Zasoby> ListujZasobyUzytkownika(int id) {
        List<Zasoby> listaZasobow;
        em = M.DajEM();
        Query q = em.createQuery("SELECT z from Zasoby z where z.idUzytkownika=" + id);
        listaZasobow = q.getResultList();
        // k8 - moj dodatek - pewnie do zmiany
        for (int i = 0; i < listaZasobow.size(); i++) {
            listaZasobow.get(i).setZdjeciaList(PokazZdjeciaZasobu(listaZasobow.get(i).getIdZasobu()));
        }
        return listaZasobow;
    }
/**
 * Listuje wszystkie kategorie.
 * @return  Lista kategorii.
 */
    public List<Kategorie> ListujKategorie() {
        List<Kategorie> listaKategorii;
        em = M.DajEM();
        Query q = em.createQuery("SELECT k from Kategorie k");
        listaKategorii = q.getResultList();

        return listaKategorii;
    }

/**
 * Listuje zdjęcia dla zasobu o podanym id.
 * @param id    Id zasobu.
 * @return  Lista zdjęć.
 */
    public List<Zdjecia> PokazZdjeciaZasobu(int id) {
        List<Zdjecia> listaZdjec;
        em = M.DajEM();
        Query q = em.createQuery("SELECT z from Zdjecia z where z.idZasobu=" + id + " order by z.nrZdjecia");
        listaZdjec = q.getResultList();

        return listaZdjec;
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
          //  em.flush();
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
        //    em.flush();
        }
    }
}
