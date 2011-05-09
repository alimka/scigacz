/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerSkrzynki;
import Managery.ManagerUzytkownika;
import ScigaczDB.Systemowe;
import ScigaczDB.Uzytkownicy;
import ScigaczDB.Wiadomosci;
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
public class OdpowiedzNaProsbeServlet extends HttpServlet {

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
     * <P>Użytkownik odpowiada na otrzymaną prośbę o wypożyczenie zasobu.
     * <p>Jeśli zgadza się, to tworzone jest nowe wypożyczenie, a użytkownik wypożyczjący otrzymuje wiadomość z proponowanym terminem spotkania.
     * <p>Jeśli nie zgadza się, to użytkownik pragnący wypożyczyć zasób dostaje wiadomość odmowną.
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

        Systemowe wiadomosc = (Systemowe) session.getAttribute("wiadomosc");


        ManagerSkrzynki managerSkrzynki = (ManagerSkrzynki) session.getAttribute("managerSkrzynki");
        if (managerSkrzynki == null) {
            managerSkrzynki = new ManagerSkrzynki();
        }
        ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
        if (managerUzytkownika == null) {
            managerUzytkownika = new ManagerUzytkownika();
        }
        Uzytkownicy uzytkownik = (Uzytkownicy) session.getAttribute("uzytkownik");

        String zgoda = request.getParameter("opcja");
        Wiadomosci nowaWiadomosc = null;
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
        String przekierowanie = "/termin.jsp";

        if (zgoda == null) {
            request.setAttribute("opcjaNULL", "yes");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
            dispatcher.forward(request, response);
            return;
        } else if (zgoda.equals("Tak")) {

                Wiadomosci wiadomoscZgody = (Wiadomosci) session.getAttribute("WiadomoscZgody");

                String termin = request.getParameter("termin");
                String miejsce = request.getParameter("miejsce");
                String tresc = "Odpowiedź na wiadomość: " + wiadomosc.getTresc() +
                        " Zgadzam się. Proponuję spotkanie: Termin: " + termin + " Miejsce: " + miejsce;

                wiadomoscZgody.setTresc(tresc);

            try {
                managerSkrzynki.EdytujWiadomosc(wiadomoscZgody);
            } catch (Exception e) {
                request.setAttribute("bazaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp?s=3");
                dispatcher.forward(request, response);
                return;
            }
                request.setAttribute("wyslane", "ok");
            }

         else if (zgoda.equals("Nie")) {
            String tresc = "Odpowiedź na wiadomość: " + wiadomosc.getTresc() +
                    " Nie zgadzam się.";
            String temat = "Odmowa wypożyczenia zasobu";
            boolean czyPrzeczytana = false;
            Date data = new Date();

            nowaWiadomosc = new Wiadomosci(null, tresc, data, czyPrzeczytana, temat, true, true);
            try {
                managerSkrzynki.UsunWiadomoscSystemowa(wiadomosc.getIdSys(), uzytkownik.getIdUzytkownika());
            } catch (Exception e) {
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
                dispatcher.forward(request, response);
                return;
            }
            przekierowanie = "/skrzynka.jsp?s=3";
            try {
                Wiadomosci w = managerSkrzynki.DodajWiadomosc(nowaWiadomosc, odbiorca, uzytkownik);
            } catch (Exception e) {
                request.setAttribute("bazaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp?s=3");
                dispatcher.forward(request, response);
                return;
            }
        }

        request.setAttribute("wyslane", "ok");

        List<Wiadomosci> wiadomosciWyslane;
        try {
            wiadomosciWyslane = managerSkrzynki.ListujWiadomosciNadawcza(uzytkownik.getIdUzytkownika());
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        session.setAttribute("wiadomosciWyslane", wiadomosciWyslane);

        RequestDispatcher dispatcher = request.getRequestDispatcher(przekierowanie);
        dispatcher.forward(request, response);

    }
}
