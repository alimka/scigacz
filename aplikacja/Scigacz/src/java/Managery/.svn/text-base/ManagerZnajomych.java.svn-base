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
import javax.persistence.Query;

/**
 *
 * @author pedros
 * Manager odpowiedzialny za tabelę Znajomi.
 */
public class ManagerZnajomych {

    ManagerNadManagery M;
    EntityManager em;

    /**
     * Tworzy ManagerNadManagery obsługującego operacje na tabeli Znajomi.
     */
    public ManagerZnajomych() {
        M = new ManagerNadManagery();
    }

    /**
     * Dodanie znajomości do tabeli Znajomi.
     * @param z Obiekt typu Znajomi.
     */
    public void DodajZnajomego(Znajomi z) {

        em = M.DajEM();
        Query x = em.createQuery("SELECT z from Znajomi z where (idUzytkownika=" + z.getIdUzytkownika().getIdUzytkownika() + " and idZnajomego=" + z.getIdZnajomego().getIdUzytkownika() + " ) or (idUzytkownika=" + z.getIdZnajomego().getIdUzytkownika() + " and idZnajomego=" + z.getIdUzytkownika().getIdUzytkownika() + ")");
        if (x.getResultList().size() == 0) {
            persist(z);
        }

    }

    /**
     * Sprawdzenie stopnia znajomości jednego użytkownika względem drugiego.
     * @param id1   Id pierwszego użytkownika.
     * @param id2   Id drugiego użytkownika.
     * @return  -1-nie ma znajomości, 0-Drugi jest dla pierwszego znajomym, 1-Drugi jest dla pirwszego bliskim znajomym.
     */
    public int JakBliscy(int id1, int id2) {
        em = M.DajEM();
        Query x = em.createQuery("SELECT z from Znajomi z where (idUzytkownika=" + id1 + " and idZnajomego=" + id2 + " ) or (idUzytkownika=" + id2 + " and idZnajomego=" + id1 + ")");
        if (x.getResultList().size() == 0) {
            return -1;
        } else if (((Znajomi) x.getSingleResult()).getIdUzytkownika().getIdUzytkownika() == id1) {
            return (((Znajomi) x.getSingleResult()).getCzyBliskiUzytkownika()) ? 1 : 0;
        } else {
            return (((Znajomi) x.getSingleResult()).getCzyBliskiZnajomego()) ? 1 : 0;
        }

    }

    /**
     * Edycja znajomości.
     * @param z Obiekt typu Znajomi.
     */
    public void EdytujZnajomego(Znajomi z) {
        merge(z);
    }

    /**
     * Usunięcie znajomego.
     * @param id    Id znajomości.
     */
    public void UsunZnajomego(int id) {
        Znajomi z = PokazZnajomego(id);
        remove(z);
    }

    /**
     * Oddaje id znajomości na podstawie id użytkowników.
     * @param idZ1  Id pierwszego użytkownika.
     * @param idZ2  Id drugiego użytkownika.
     * @return  Id znajomości.
     */
    public int IdZnajomosci(int idZ1, int idZ2) {
        em = M.DajEM();
        int Id = -10;
        Query x = em.createQuery("SELECT z from Znajomi z where (z.idUzytkownika=" + idZ1 + " and z.idZnajomego=" + idZ2 + ") or (z.idUzytkownika=" + idZ2 + " and z.idZnajomego=" + idZ1 + ") ");
        if (x.getResultList().size() > 0) {
            Object result = x.getSingleResult();
            try {
                Id = ((Znajomi) result).getIdZnajomosci();
            } catch (Exception e) {
            }
        }

        return Id;
    }
/**
 * Zmienia rodzaj znajomości.
 * @param idZnajomosci  Id znajomości.
 * @param idUzytkownika Id użytkownika, który zmienia stopień.
 */
    public void ZmienRodzajZnajomosci(int idZnajomosci, int idUzytkownika) {
        em = M.DajEM();
        Znajomi z = PokazZnajomego(idZnajomosci);
        if (z.getIdUzytkownika().getIdUzytkownika() == idUzytkownika) {
            z.setCzyBliskiUzytkownika(!z.getCzyBliskiUzytkownika());
        } else {
            z.setCzyBliskiZnajomego(!z.getCzyBliskiZnajomego());
        }

        merge(z);
    }
/**
 * Sprawdzenie stopnia znajomości na podstawie id znajomości i id użytkownika.
 * @param idZnajomosci  Id znajomości.
 * @param idUsera   Id użytkownika.
 * @return  True - bliski znajomy, false - zwykły znajomy.
 */
    public boolean CzyBliski(int idZnajomosci, int idUsera) {
        Znajomi z = PokazZnajomego(idZnajomosci);
        if (z.getIdUzytkownika().getIdUzytkownika() == idUsera) {
            return z.getCzyBliskiUzytkownika();
        } else {
            return z.getCzyBliskiZnajomego();
        }
    }
/**
 * Listuje wszystkie znajomości.
 * @return  Lista znajomości.
 */
    public List<Znajomi> ListujZnajomych() {
        List<Znajomi> listaZnajomych;
        em = M.DajEM();
        Query q = em.createQuery("SELECT z from Znajomi z");
        listaZnajomych = q.getResultList();
        return listaZnajomych;
    }
/**
 * Listuje znajomości użytkownika o podanym id.
 * To jedna z magicznych funkcji, których lepiej nie dotykać.
 * @param id    Id użytkownika.
 * @return  Lista znajomości.
 */
    public List<Znajomi> ListujZnajomychUzytkownika(int id) {
        List<Znajomi> listaZnajomych;
        em = M.DajEM();
        Query q = em.createQuery("SELECT z from Znajomi z where z.idUzytkownika=" + id + " or z.idZnajomego=" + id);
        listaZnajomych = q.getResultList();
        int tmp;
        boolean tmp2, tmp3;
        for (int i = 0; i < listaZnajomych.size(); i++) {
            if (listaZnajomych.get(i).getIdZnajomego().getIdUzytkownika() == id) {
                tmp = listaZnajomych.get(i).getIdUzytkownika().getIdUzytkownika();
                tmp2 = listaZnajomych.get(i).getCzyBliskiUzytkownika();
                tmp3 = listaZnajomych.get(i).getCzyBliskiZnajomego();

                listaZnajomych.get(i).setIdUzytkownika(new ManagerUzytkownika().PokazUzytkownika(id));
                listaZnajomych.get(i).setIdZnajomego(new ManagerUzytkownika().PokazUzytkownika(tmp));
                listaZnajomych.get(i).setCzyBliskiUzytkownika(tmp3);
                listaZnajomych.get(i).setCzyBliskiZnajomego(tmp2);

            }
        }

        return listaZnajomych;
    }
/**
 * Pokazuje informacje o znajomości o podanym id.
 * @param id    Id znajomości.
 * @return  Obiekt typu Znajomi.
 */
    public Znajomi PokazZnajomego(int id) {
        Znajomi znajomy;
        em = M.DajEM();
        Query q = em.createQuery("SELECT z from Znajomi z where z.idZnajomosci=" + id);
        znajomy = (Znajomi) q.getSingleResult();

        return znajomy;
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
           // em.flush();
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
         //   em.flush();
        }
    }
}
