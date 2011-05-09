/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerSkrzynki;
import Managery.ManagerUzytkownika;
import Managery.ManagerWypozyczen;
import Managery.ManagerZasobow;
import Managery.ManagerZnajomych;
import ScigaczDB.Systemowe;
import ScigaczDB.Uzytkownicy;
import ScigaczDB.Wiadomosci;
import ScigaczDB.Wypozyczenia;
import ScigaczDB.Zasoby;
import ScigaczDB.Znajomi;
import java.io.IOException;
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
public class WyswietlServlet extends HttpServlet {

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
     * <p>Obsługa poszczególnych zakładek:
     * <p>Zasoby: w sesji ustawiana jest lista zasobów użytkownika.
     * <p>Znajomi: Ustawiana jest lista znajomych użytkownika.
     * <p>Skrzynka: Ustawiane są listy wiadomości wysłanych, odebranych i systemowych.
     * <p>Pożyczone: Ustawiane są listy zasobów pożyczonych od innych oraz pożyczonym innym użytkownikom.
     *
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

        Uzytkownicy uzytkownik = (Uzytkownicy) session.getAttribute("uzytkownik");
        ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
        if (managerUzytkownika == null) {
            managerUzytkownika = new ManagerUzytkownika();
        }

        String zakladka = request.getParameter("z");
        int id = uzytkownik.getIdUzytkownika();

        if (zakladka.equals("zasoby")) {
            ManagerZasobow managerZasobow = (ManagerZasobow) session.getAttribute("managerZasobow");
            if (managerZasobow == null) {
                managerZasobow = new ManagerZasobow();
                session.setAttribute("managerZasobow", managerZasobow);
            }
            List<Zasoby> zasobyUzytkownika;
            try {
                zasobyUzytkownika = managerZasobow.ListujZasobyUzytkownika(id);
            } catch (Exception e) {
                System.out.println(e);
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }
            session.removeAttribute("zasobyUzytkownika");
            session.setAttribute("zasobyUzytkownika", zasobyUzytkownika);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
            dispatcher.forward(request, response);
            return;
        }
        if (zakladka.equals("znajomi")) {
            ManagerZnajomych managerZnajomych = (ManagerZnajomych) session.getAttribute("managerZnajomych");
            if (managerZnajomych == null) {
                managerZnajomych = new ManagerZnajomych();
                session.setAttribute("managerZnajomych", managerZnajomych);
            }
            List<Znajomi> znajomiUzytkownika;
            try {
                znajomiUzytkownika = managerZnajomych.ListujZnajomychUzytkownika(id);
            } catch (Exception e) {
                System.out.println(e);
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }
            session.removeAttribute("znajomiUzytkownika");
            session.setAttribute("znajomiUzytkownika", znajomiUzytkownika);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/znajomi.jsp");
            dispatcher.forward(request, response);
            return;
        }
        if (zakladka.equals("wiadomosci")) {
            ManagerSkrzynki managerSkrzynki = (ManagerSkrzynki) session.getAttribute("managerSkrzynki");
            if (managerSkrzynki == null) {
                managerSkrzynki = new ManagerSkrzynki();
                session.setAttribute("managerSkrzynki", managerSkrzynki);
            }
            List<Wiadomosci> wiadomosciWyslane;
            try {
                wiadomosciWyslane = managerSkrzynki.ListujWiadomosciNadawcza(id);
            } catch (Exception e) {
                System.out.println(e);
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            List<Wiadomosci> wiadomosciOdebrane;
            try {
                wiadomosciOdebrane = managerSkrzynki.ListujWiadomosciOdbiorcza(id);
            } catch (Exception e) {
                System.out.println(e);
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            List<Systemowe> wiadomosciSystemowe;
            try {
                wiadomosciSystemowe = managerSkrzynki.ListujWiadomosciSystemowe(id);
            } catch (Exception e) {
                System.out.println(e);
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }
            session.removeAttribute("wiadomosciWyslane");
            session.removeAttribute("wiadomosciOdebrane");
            session.removeAttribute("wiadomosciSystemowe");
            session.setAttribute("wiadomosciWyslane", wiadomosciWyslane);
            session.setAttribute("wiadomosciOdebrane", wiadomosciOdebrane);
            session.setAttribute("wiadomosciSystemowe", wiadomosciSystemowe);

            String s = request.getParameter("s");
            String strona = "skrzynka.jsp";
            if (s != null) {
                strona += "?s=" + s;
            }


            RequestDispatcher dispatcher = request.getRequestDispatcher(strona);
            dispatcher.forward(request, response);
            return;
        }
        if (zakladka.equals("nagany")) {
            ManagerSkrzynki managerSkrzynki = (ManagerSkrzynki) session.getAttribute("managerSkrzynki");
            if (managerSkrzynki == null) {
                managerSkrzynki = new ManagerSkrzynki();
                session.setAttribute("managerSkrzynki", managerSkrzynki);
            }
            List<Systemowe> wiadomosciSystemowe;
            try {
                wiadomosciSystemowe = managerSkrzynki.ListujNagany(-1);
            } catch (Exception e) {
                System.out.println(e);
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }
            session.removeAttribute("wiadomosciSystemowe");
            session.setAttribute("wiadomosciSystemowe", wiadomosciSystemowe);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/nagany.jsp");
            dispatcher.forward(request, response);
            return;
        }
        if (zakladka.equals("pozyczone")) {
            ManagerWypozyczen managerWypozyczen = (ManagerWypozyczen) session.getAttribute("manaegrWypozyczen");
            if (managerWypozyczen == null) {
                managerWypozyczen = new ManagerWypozyczen();
                session.setAttribute("managerWypozyczen", managerWypozyczen);
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pozyczone.jsp");
            dispatcher.forward(request, response);
            return;
        }


    }
}
