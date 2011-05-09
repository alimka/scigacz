/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerSkrzynki;
import Managery.ManagerUzytkownika;
import ScigaczDB.Uzytkownicy;
import ScigaczDB.Wiadomosci;
import java.io.IOException;
import java.io.PrintWriter;
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
public class WiadomoscServlet extends HttpServlet {

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
     * <p>Wysyłanie wiadomości do podanego użytkownika.
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
        PrintWriter out = response.getWriter();

        Uzytkownicy uzytkownik = (Uzytkownicy) session.getAttribute("uzytkownik");

        ManagerSkrzynki managerSkrzynki = (ManagerSkrzynki) session.getAttribute("managerSkrzynki");
        if (managerSkrzynki == null) {
            managerSkrzynki = new ManagerSkrzynki();
        }

        ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
        if (managerUzytkownika == null) {
            managerUzytkownika = new ManagerUzytkownika();
        }

        String nad = request.getParameter("idNadawcy");
        String odb = request.getParameter("idOdbiorcy");
        int idNadawcy = 0;
        try {
            idNadawcy = Integer.parseInt(nad);
        } catch (Exception e) {
            request.setAttribute("parametryOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
            dispatcher.forward(request, response);
            return;
        }

        int idOdbiorcy = 0;
        try {
            idOdbiorcy = Integer.parseInt(odb);
        } catch (Exception e) {
            request.setAttribute("parametryOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String temat = request.getParameter("temat");
        String naduzycie = request.getParameter("naduzycie");
        out.println(naduzycie);
        String tresc = request.getParameter("tresc");
        boolean czyPrzeczytana = false;
        Date data = new Date();

        Uzytkownicy nadawca;
        try {
            nadawca = managerUzytkownika.PokazUzytkownika(idNadawcy);
        } catch (Exception e) {
            request.setAttribute("bazaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Uzytkownicy odbiorca;
        if (idOdbiorcy == -1) {
            String loginOdbiorcy = request.getParameter("odbiorca");
            try {
                odbiorca = managerUzytkownika.UzytkownikOLoginie(loginOdbiorcy);
            } catch (Exception e) {
                request.setAttribute("bazaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
                dispatcher.forward(request, response);
                return;
            }
        } else {
            try {
                odbiorca = managerUzytkownika.PokazUzytkownika(idOdbiorcy);
            } catch (Exception e) {
                request.setAttribute("bazaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }

        Wiadomosci wiadomosc = new Wiadomosci(null, tresc, data, czyPrzeczytana, temat, true, true);

        if (naduzycie.equals("tak")) {
            try {
                wiadomosc.setTemat("Zgłoszenie nadużycia");
                managerSkrzynki.WyslijDoAdmina(wiadomosc, nadawca);
                out.println("Wyślij do admina");
            } catch (Exception e) {
                request.setAttribute("bazaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
                dispatcher.forward(request, response);
                return;
            }
        } else {
            try {
                Wiadomosci w = managerSkrzynki.DodajWiadomosc(wiadomosc, odbiorca, nadawca);
            } catch (Exception e) {
                request.setAttribute("bazaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }

        request.setAttribute("wyslane", "ok");

        List<Wiadomosci> wiadomosciWyslane;
        try {
            wiadomosciWyslane = managerSkrzynki.ListujWiadomosciNadawcza(idNadawcy);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
            dispatcher.forward(request, response);
            return;
        }

        session.setAttribute("wiadomosciWyslane", wiadomosciWyslane);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
        dispatcher.forward(request, response);
    }
}
