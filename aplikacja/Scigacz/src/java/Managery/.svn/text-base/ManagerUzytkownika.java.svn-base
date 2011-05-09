/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Managery;

import ScigaczDB.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

;

/**
 *
 * @author pedros
 * Manager odpowiedzialny za tabelę Uzytkownicy.
 */
public class ManagerUzytkownika {

    ManagerNadManagery M;
    EntityManager em;

    /**
     * Tworzy ManagaeraNadManagery obsługującego operacje na tabeli Uzytkownicy.
     */
    public ManagerUzytkownika() {
        M = new ManagerNadManagery();
    }

    /**
     * Zwraca id użtkownika o podanym loginie i haśle.
     * @param login Login użytkownika.
     * @param haslo Hasło użytkownika.
     * @return  Zwraca id jeśli taki użytkownik istnieje i hasło jest poprawne lub -100 w przeciwnym wypadku.
     */
    public int idUzytkownika(String login, String haslo) {
        Uzytkownicy uzytkownik = null;
        em = M.DajEM();
        haslo = Szyfruj(haslo);

        Query q = em.createQuery("SELECT u from Uzytkownicy u where u.login='" + login + "' and u.haslo='" + haslo + "'");
        if (q.getResultList().size() == 0) {
            return -100;
        } else {
            uzytkownik = (Uzytkownicy) q.getSingleResult();

            return uzytkownik.getIdUzytkownika();
        }
    }

    /**
     * Funkcja szyfrująca hasło użytkownika metodą MD5.
     * @param pass  Hasło użytkownika.
     * @return      Nowe zaszyfrowane hasło.
     */
    public String Szyfruj(String pass) {
        byte[] defaultBytes = (pass).getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }

            String foo = messageDigest.toString();
            return hexString.toString();
        } catch (NoSuchAlgorithmException nsae) {
            return pass;
        }
    }

    /**
     * Tworzy nowe konto dla użytkownika.
     * @param u     Obiekt typu Uzytkownicy.
     * @throws NoSuchAlgorithmException
     */
    public void DodajKonto(Uzytkownicy u) throws NoSuchAlgorithmException {
        String haslo = u.getHaslo();
        u.setHaslo(Szyfruj(haslo));

        persist(u);
    }

    /**
     * Usuwa konto o podanym id z bazy.
     * @param id    Id użytkownika.
     */
    public void UsunKonto(int id) {
        Uzytkownicy u = PokazUzytkownika(id);
        em = M.DajEM();


        Query q = em.createQuery("SELECT z from Zasoby z where z.idUzytkownika=" + id);
        List<Zasoby> listaZasobow = q.getResultList();
        if (listaZasobow != null) {
            for (Zasoby X : listaZasobow) {
                q = em.createQuery("DELETE from Zdjecia z where z.idZasobu=" + X.getIdZasobu());
                try {
                    em.getTransaction().begin();
                    q.executeUpdate();
                    em.getTransaction().commit();
                } catch (Exception e) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
                    em.getTransaction().rollback();
                } finally {
                }

            }
        }
                em = M.DajEM();

        q = em.createQuery("DELETE from Zasoby z where z.idUzytkownika=" + id);
        try {
            em.getTransaction().begin();
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
        }


        q = em.createQuery("DELETE from Wypozyczenia w where w.idUdostepniacza=" + id + " or w.idWypozyczacza=" + id);
        try {
            em.getTransaction().begin();
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
        }


        q = em.createQuery("DELETE from Znajomi z where z.idUzytkownika=" + id + " or z.idZnajomego=" + id);

        try {
            em.getTransaction().begin();
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
        }


        q = em.createQuery("DELETE from Opinie o where o.idOpiniujacego=" + id + " or o.idUzytkownika=" + id);
        try {
            em.getTransaction().begin();
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
        }

        q = em.createQuery("DELETE from Systemowe s where s.idOdbiorcy=" + id+ " or s.idDoZwrotu="+id);
        try {
            em.getTransaction().begin();
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
        }

        q = em.createQuery("DELETE from Wiadomosci w where w.idOdbiorcy=" + id + " or w.idNadawcy=" + id);
        try {
            em.getTransaction().begin();
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
        }



        q = em.createQuery("DELETE from Uzytkownicy u where u.idUzytkownika=" + id);
        try {
            em.getTransaction().begin();
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
        }
    }

    /**
     * Zwraca użytkownika o podanym loginie.
     * @param login Login użytkownika.
     * @return  Obiekt typu Uzytkownicy lub null.
     */
    public Uzytkownicy UzytkownikOLoginie(String login) {
        Uzytkownicy u = null;

        em = M.DajEM();
        Query q = em.createQuery("SELECT u from Uzytkownicy u where u.login like '" + login + "'");
        if (q.getResultList().size() > 0) {
            u = (Uzytkownicy) q.getSingleResult();
        }

        return u;
    }

    /**
     * Zwraca informację czy istnieje już dany login lub email w bazie.
     * @param login Podany login.
     * @param mail  Podany adres mailowy.
     * @return  0-nie ma ani loginu ani adresu; 1-istnieje już login; 2-istnieje już adres; 3-istnieje taki login i adres.
     */
    public int SprawdzLoginMail(String login, String mail) {
        em = M.DajEM();
        int ile = 0;
        boolean raz = false, dwa = false;
        Query q = em.createQuery("SELECT u from Uzytkownicy u where u.login like '" + login + "'");
        ile = q.getResultList().size();

        if (ile > 0) {
            ile = 0;
            raz = true;
        }
        q = em.createQuery("SELECT u from Uzytkownicy u where u.email like '" + mail + "'");
        ile = q.getResultList().size();
        if (ile > 0) {
            ile = 0;
            dwa = true;
        }

        if (raz && dwa) {
            return 3;
        } else if (raz && !dwa) {
            return 1;
        } else if (!raz && dwa) {
            return 2;
        } else {
            return 0;
        }
        //// 0-wszystko ok; 1-login już jest; 2-email już jest; 3-oba są.

    }

    /**
     * Pokazuje wszystkich uzytkowników.
     * @return     Lista użytkowników.
     */
    public List<Uzytkownicy> PokazUzytkownikow() {
        List<Uzytkownicy> listaUzytkownikow;
        em = M.DajEM();
        Query q = em.createQuery("SELECT u from Uzytkownicy u where u.uprawnienia=0");
        listaUzytkownikow = q.getResultList();
        return listaUzytkownikow;
    }

    /**
     * Pokazuje użytkownika o podanym id.
     * @param id    Id użytkownika.
     * @return  Obiekt typu Uzytkownicy.
     */
    public Uzytkownicy PokazUzytkownika(int id) {
        ///Pokaz info o osobie
        Uzytkownicy uzytkownik;
        em = M.DajEM();
        Query q = em.createQuery("SELECT u from Uzytkownicy u where u.idUzytkownika=" + id);
        uzytkownik = (Uzytkownicy) q.getSingleResult();
        return uzytkownik;
    }

    /**
     * Sprawdza czy dany użytkownik jest administratorem.
     * @param u     Użytkownik.
     * @return  true - jest administratorem; false - nie jest.
     */
    public boolean CzyAdmin(Uzytkownicy u) {
        if (u.getUprawnienia() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Blokuje lub odblokowuje użytkowika.
     * @param id    Id użytkownika.
     * @param dokiedy   Data do kiedy użytkownik ma być zablokowany; jeśli 0 - użytkownik zostaje odblokowany.
     * @param komentarz Komentarz do zablokowania.
     */
    public void Blokada(Uzytkownicy id, Date dokiedy, String komentarz) {
// jesli dokiedy==0 to odblokuje
        id.setDatablokada(dokiedy);
        id.setKomentarzblokada(komentarz);
        merge(id);

    }

    /**
     * Wyszukiwanie w bazie wśród użytkowników po zadanym kluczu.
     * @param klucz Klucz wyszukiwania.
     * @return  Lista użytkowników.
     */
    public List<Uzytkownicy> SzukajPoUzytkownikach(String klucz) {
        List<Uzytkownicy> listaUzytkownikow;
        em = M.DajEM();
        Query q = em.createQuery("SELECT u from Uzytkownicy u where u.login like '%" + klucz + "%' or u.imie like '%" + klucz + "%' or u.nazwisko like '%" + klucz + "%' or u.wojewodztwo like '%" + klucz + "%' or u.miasto like '%" + klucz + "%'");
        listaUzytkownikow = q.getResultList();
        return listaUzytkownikow;

    }

    /**
     * Edycja użytkownika.
     * @param u Obiekt typu Uzytkownicy.
     */
    public void EdytujUzytkownika(Uzytkownicy u) {
        merge(u);
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
