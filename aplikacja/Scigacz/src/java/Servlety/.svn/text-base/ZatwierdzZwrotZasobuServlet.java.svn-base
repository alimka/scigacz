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
public class ZatwierdzZwrotZasobuServlet extends HttpServlet {

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
     * Handles the HTTP <code>POST</code> method.
     * <p>Użytkownik udstępniający odpowiada na wiadomość o oddaniu zasobu.
     * <p>Odpowiedź twierdząca: Wypożyczenie jest usuwane. Użytkownik wypożyczający wiadomość dostaje potwierdzenie zwrotu zasobu.
     * <p>Odpowiedź przecząca: Użytkownik wypożyczający otrzymuje wiadomość informującą, że nie oddał jeszcze zasobu.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        ManagerSkrzynki managerSkrzynki = (ManagerSkrzynki) session.getAttribute("managerSkrzynki");
        if (managerSkrzynki == null) {
            managerSkrzynki = new ManagerSkrzynki();
        }
        ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
        if (managerUzytkownika == null) {
            managerUzytkownika = new ManagerUzytkownika();
        }
        Uzytkownicy uzytkownik = (Uzytkownicy) session.getAttribute("uzytkownik");

        ManagerZasobow managerZasobow = (ManagerZasobow) session.getAttribute("managerZasobow");
        if (managerZasobow == null) {
            managerZasobow = new ManagerZasobow();
        }

        ManagerWypozyczen managerWypozyczen = (ManagerWypozyczen) session.getAttribute("managerWypozyczen");
        if (managerWypozyczen == null) {
            managerWypozyczen = new ManagerWypozyczen();
        }

        int idZasobu;
        try {
            idZasobu = Integer.parseInt(request.getParameter("idZasobu"));
        } catch (Exception e) {
            request.setAttribute("parametryOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Zasoby zasob;
        try {
            zasob = managerZasobow.PokazZasob(idZasobu);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String tresc;
        String temat;
        String oddany = request.getParameter("opcja");
        if (oddany.equals("Tak")) {
            Wypozyczenia wypozyczenie;
            try {
                wypozyczenie = managerWypozyczen.PokazWypozyczeniePoZasobie(idZasobu);
            } catch (Exception e) {
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pozyczone.jsp");
                dispatcher.forward(request, response);
                return;
            }
            try {
                managerWypozyczen.UsunWypozyczenie(wypozyczenie.getIdWypozyczenia());
            } catch (Exception e) {
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pozyczone.jsp");
                dispatcher.forward(request, response);
                return;
            }
            zasob.setDostepnosc(0);
            try {
                managerZasobow.EdytujZasob(zasob);
            } catch (Exception e) {
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pozyczone.jsp");
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
            tresc = "Oddałeś mi zasób <a href=\"/Scigacz/RzeczServlet?id=" + zasob.getIdZasobu() + "\">" + zasob.getNazwa() + "</a>. Dziękuję za współpracę.";
            temat = "Oddałeś zasób";
        } else {
            tresc = "Nie oddałeś mi jescze zasobu <a href=\"/Scigacz/RzeczServlet?id=" + zasob.getIdZasobu() + "\">" + zasob.getNazwa() + "</a>, więc nie próbuj oszukać systemu :>";
            temat = "Nie oddałeś zasobu";
            zasob.setDostepnosc(1);
            try {
                managerZasobow.EdytujZasob(zasob);
            } catch (Exception e) {
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pozyczone.jsp");
                dispatcher.forward(request, response);
                return;
            }

        }
        int idWiadomosci;
        try {
            idWiadomosci = Integer.parseInt(request.getParameter("idWiadomosci"));
        } catch (Exception e) {
            request.setAttribute("parametryOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
            dispatcher.forward(request, response);
            return;
        }
        try {
            managerSkrzynki.UsunWiadomoscSystemowa(idWiadomosci, uzytkownik.getIdUzytkownika());
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
            dispatcher.forward(request, response);
            return;
        }
        boolean czyPrzeczytana = false;
        Date data = new Date();
        int idOdbiorcy;
        try {
            idOdbiorcy = Integer.parseInt(request.getParameter("idOdbiorcy"));
        } catch (Exception e) {
            request.setAttribute("parametryOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
            dispatcher.forward(request, response);
            return;
        }
        Uzytkownicy odbiorca;
        try {
            odbiorca = managerUzytkownika.PokazUzytkownika(idOdbiorcy);
        } catch (Exception e) {
            request.setAttribute("bazaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Wiadomosci nowaWiadomosc = new Wiadomosci(null, tresc, data, czyPrzeczytana, temat, true, true);
        try {
            Wiadomosci w = managerSkrzynki.DodajWiadomosc(nowaWiadomosc, odbiorca, uzytkownik);
        } catch (Exception e) {
            request.setAttribute("bazaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp?s=3");
            dispatcher.forward(request, response);
            return;
        }
        List<Wiadomosci> wiadomosciWyslane;
        try {
            wiadomosciWyslane = managerSkrzynki.ListujWiadomosciNadawcza(uzytkownik.getIdUzytkownika());
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
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
        session.removeAttribute("wiadomosciWyslane");
        session.setAttribute("wiadomosciWyslane", wiadomosciWyslane);

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

        RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp?s=2");
        dispatcher.forward(request, response);
    }
}
