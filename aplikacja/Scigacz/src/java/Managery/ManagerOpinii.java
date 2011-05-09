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
 * Manager odpowiedzialny za kontakt z tabelą opinii w bazie.
 */
public class ManagerOpinii {

    ManagerNadManagery M;
    EntityManager em;
/**
 * Tworzy ManagerNadManagery dla managera opinii.
 */
    public ManagerOpinii() {
        M = new ManagerNadManagery();
    }

    /**
     * Funnkcja dodaje opinię do bazy.
     * @param o Opinia do dodania.
     */
    public void DodajOpinie(Opinie o) {
        persist(o);
    }

    /**
     * Pokazuje informacje o opinii o podanym id.
     * @param id    Id poszukiwanej opinii.
     * @return      Obiekt typu Opinia
     */
    public Opinie PokazOpinie(int id) {
        Opinie opinia;
        em = M.DajEM();
        Query q = em.createQuery("SELECT o from Opinie o where o.idOpinii=" + id);
        opinia = (Opinie) q.getSingleResult();
        return opinia;
    }

    /**
     * Usuwa opinię o podanym id z bazy.
     * @param id    Id usuwanej opinii.
     */
    public void UsunOpinie(int id) {
        Opinie o = PokazOpinie(id);
        remove(o);
    }

/**
 * Listuje wszystkie opinie użytkownika o podanym id.
 * @param id    Id użytkownika.
 * @return      Zwraca listę obiektów Opinia.
 */
    public List<Opinie> ListujOpinieUzytkownika(int id) {

        List<Opinie> listaOpinii;
        em = M.DajEM();
        Query q = em.createQuery("SELECT o from Opinie o where idUzytkownika=" + id + " order by o.data desc");

        listaOpinii = q.getResultList();
        return listaOpinii;
    }

    /**
     * Funkcja do edycji istniejącej opinii.
     * @param o     Opinia do edycji.
     */
    public void EdytujOpinie(Opinie o) {
        merge(o);
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
            //       em.close();
        //    em.flush();
        }
    }
}
