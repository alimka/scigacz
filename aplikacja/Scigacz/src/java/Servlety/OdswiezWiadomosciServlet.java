/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerSkrzynki;
import Managery.ManagerUzytkownika;
import Managery.ManagerWypozyczen;
import Managery.ManagerZasobow;
import ScigaczDB.Systemowe;
import ScigaczDB.Uzytkownicy;
import ScigaczDB.Wiadomosci;
import ScigaczDB.Wypozyczenia;
import ScigaczDB.Zasoby;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author k8
 */
public class OdswiezWiadomosciServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Systemowe wiad = (Systemowe) session.getAttribute("wiadomosc");
        ManagerSkrzynki managerSkrzynki = (ManagerSkrzynki) session.getAttribute("managerSkrzynki");
        if (managerSkrzynki == null) {
            managerSkrzynki = new ManagerSkrzynki();
        }
        ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
        if (managerUzytkownika == null) {
            managerUzytkownika = new ManagerUzytkownika();
        }
        ManagerZasobow managerZasobow = (ManagerZasobow) session.getAttribute("managerZasobow");
        if (managerZasobow == null) {
            managerZasobow = new ManagerZasobow();
        }
        ManagerWypozyczen managerWypozyczen = (ManagerWypozyczen) session.getAttribute("managerWypozyczen");
        if (managerWypozyczen == null) {
            managerWypozyczen = new ManagerWypozyczen();
        }

        Uzytkownicy uzytkownik = (Uzytkownicy) session.getAttribute("uzytkownik");
        int idZasobu = wiad.getIdZasobu();
        Zasoby zasob = null;

        try {
            zasob = managerZasobow.PokazZasob(idZasobu);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
            dispatcher.forward(request, response);
            return;
        }
        int idOdbiorcy = wiad.getIdDoZwrotu();
        Uzytkownicy odbiorca;
        try {
            odbiorca = managerUzytkownika.PokazUzytkownika(idOdbiorcy);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
            dispatcher.forward(request, response);
            return;
        }

        int dni = zasob.getCzasPrzetrzymywania();
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, dni);
        Date dataZwrotu = calendar.getTime();
        Wypozyczenia wypozyczenie = new Wypozyczenia(null, dataZwrotu, today);
        wypozyczenie.setIdUdostepniacza(uzytkownik);
        wypozyczenie.setIdWypozyczacza(odbiorca);
        wypozyczenie.setIdZasobu(zasob);
        boolean udalosie = false;
        try {
            udalosie = managerWypozyczen.DodajWypozyczenie(wypozyczenie);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
            dispatcher.forward(request, response);
            return;
        }
        if (udalosie) {
            zasob.setDostepnosc(1);
            try {
                managerZasobow.EdytujZasob(zasob);
            } catch (Exception e) {
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
                dispatcher.forward(request, response);
                return;
            }

            List<Wypozyczenia> wypozyczone;
            try {
                wypozyczone = managerWypozyczen.ListujWypozyczeniaWypozyczacz(uzytkownik);
            } catch (Exception e) {
                System.out.println(e);
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }
            List<Wypozyczenia> udostepnione;
            try {
                udostepnione = managerWypozyczen.ListujWypozyczeniaUdostepniacz(uzytkownik);
            } catch (Exception e) {
                System.out.println(e);
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            session.setAttribute("wypozyczone", wypozyczone);
            session.setAttribute("udostepnione", udostepnione);

            try {
                managerSkrzynki.UsunWiadomoscSystemowa(wiad.getIdSys(), uzytkownik.getIdUzytkownika());
            } catch (Exception e) {
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
                dispatcher.forward(request, response);
                return;
            }
            List<Systemowe> wiadomosciSystemowe;
            try {
                wiadomosciSystemowe = managerSkrzynki.ListujWiadomosciSystemowe(uzytkownik.getIdUzytkownika());
            } catch (Exception e) {
                System.out.println(e);
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }
            session.removeAttribute("wiadomosciSystemowe");
            session.setAttribute("wiadomosciSystemowe", wiadomosciSystemowe);


            String tresc = "Odpowiedź na wiadomość: " + wiad.getTresc() +
                    " Zgadzam się. Podaj pasujące Ci termian oraz miejsce spotkania. ";
            String temat = "Zgoda na wypożyczenie zasobu";
            boolean czyPrzeczytana = false;
            Date data = new Date();

            Wiadomosci nowaWiadomosc = new Wiadomosci(null, tresc, data, czyPrzeczytana, temat, true, true);
            Wiadomosci wiadomosc;
            try {
                wiadomosc = managerSkrzynki.DodajWiadomosc(nowaWiadomosc, odbiorca, uzytkownik);
            } catch (Exception e) {
                request.setAttribute("bazaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp?s=3");
                dispatcher.forward(request, response);
                return;
            }

            session.setAttribute("WiadomoscZgody", wiadomosc);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp?s=3");
            dispatcher.forward(request, response);

        }
    }
    }
