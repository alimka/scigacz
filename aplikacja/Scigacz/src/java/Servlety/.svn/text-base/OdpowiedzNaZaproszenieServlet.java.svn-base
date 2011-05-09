/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerSkrzynki;
import Managery.ManagerUzytkownika;
import Managery.ManagerZnajomych;
import ScigaczDB.Systemowe;
import ScigaczDB.Uzytkownicy;
import ScigaczDB.Wiadomosci;
import ScigaczDB.Znajomi;
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
 * @author alimka
 */
public class OdpowiedzNaZaproszenieServlet extends HttpServlet {

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
     * <P>Użytkownik odpowiada na wysłaną do niego prośbę o dodanie do grona znajomych.
     * <p>Jeśli użytkownik się zgadza i wysyła odpowiedź o treści 'tak' następuje dodanie użytkowników do list znajomych oraz wysłanie użytkownikowi, który wysłał wiadomość, potwierdzenia, że znajduje się na liście znajomych
     * <p>Jeśli użytkownik się nie zgadza i wysyła odpowiedź o treści 'nie następuje wysłanie użytkownikowi, który wysłał prośbę, wiadomość o odmowie
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

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        // Wysłanie wiadomości o akceptacji:
        String akceptacja = request.getParameter("opcja");

        if (akceptacja == null) {
            request.setAttribute("opcjaNULL", "yes");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
            dispatcher.forward(request, response);
            return;
        } else if (akceptacja.equals("Tak")) {

            int idOdbiorcy;
            try {
                idOdbiorcy = Integer.parseInt(request.getParameter("idOdbiorcy"));
            } catch (Exception e) {
                request.setAttribute("parametryOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
                dispatcher.forward(request, response);
                return;
            }

            Uzytkownicy nadawca = (Uzytkownicy) session.getAttribute("uzytkownik");
            int idNadawcy = nadawca.getIdUzytkownika();
            String loginNadawcy = nadawca.getLogin();

            ManagerSkrzynki managerSkrzynki = (ManagerSkrzynki) session.getAttribute("managerSkrzynki");
            if (managerSkrzynki == null) {
                managerSkrzynki = new ManagerSkrzynki();
            }

            ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
            if (managerUzytkownika == null) {
                managerUzytkownika = new ManagerUzytkownika();
            }

            Uzytkownicy odbiorca;
            try {
                odbiorca = managerUzytkownika.PokazUzytkownika(idOdbiorcy);
            } catch (Exception e) {
                request.setAttribute("bazaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
                dispatcher.forward(request, response);
                return;
            }

            String temat = "Dodanie do znajomych";
            String tresc = loginNadawcy + " zgodził/a dodać się Ciebie do listy znajomych.";
            boolean czyPrzeczytana = false;
            Date data = new Date();

            Wiadomosci wiadomosc = new Wiadomosci(null, tresc, data, czyPrzeczytana, temat, true, true);

            try {
                Wiadomosci w = managerSkrzynki.DodajWiadomosc(wiadomosc, odbiorca, nadawca);
            } catch (Exception e) {
                request.setAttribute("bazaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
                dispatcher.forward(request, response);
                return;
            }

            List<Wiadomosci> wiadomosciWyslane;
            try {
                wiadomosciWyslane = managerSkrzynki.ListujWiadomosciNadawcza(idNadawcy);
            } catch (Exception e) {
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            session.setAttribute("wiadomosciWyslane", wiadomosciWyslane);

            // Dodanie nowej znajomości do tabeli:
            ManagerZnajomych managerZnajomych = (ManagerZnajomych) session.getAttribute("managerZnajomych");
            if (managerZnajomych == null) {
                managerZnajomych = new ManagerZnajomych();
            }

            Znajomi znajomosc = new Znajomi(null, false, false);
            znajomosc.setIdUzytkownika(nadawca);
            znajomosc.setIdZnajomego(odbiorca);

            managerZnajomych.DodajZnajomego(znajomosc);

            List<Znajomi> znajomiUzytkownika;
            try {
                znajomiUzytkownika = managerZnajomych.ListujZnajomychUzytkownika(idNadawcy);
            } catch (Exception e) {
                request.setAttribute("parametryOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
                dispatcher.forward(request, response);
                return;
            }

            session.setAttribute("znajomiUzytkownika", znajomiUzytkownika);

            int idWiadomosci = Integer.parseInt(request.getParameter("idWiadomosci"));
            managerSkrzynki.UsunWiadomoscSystemowa(idWiadomosci, idOdbiorcy);

            List<Systemowe> wiadomosciSystemowe;
            try
            {
                wiadomosciSystemowe = managerSkrzynki.ListujWiadomosciSystemowe(idNadawcy);
            }
            catch (Exception e)
            {
                System.out.println(e);
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }




            session.setAttribute("wiadomosciSystemowe", wiadomosciSystemowe);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/znajomi.jsp");
            dispatcher.forward(request, response);

        } else if (akceptacja.equals("Nie")) {

            int idOdbiorcy;
            try {
                idOdbiorcy = Integer.parseInt(request.getParameter("idOdbiorcy"));
            } catch (Exception e) {
                request.setAttribute("parametryOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
                dispatcher.forward(request, response);
                return;
            }

            Uzytkownicy nadawca = (Uzytkownicy) session.getAttribute("uzytkownik");
            int idNadawcy = nadawca.getIdUzytkownika();
            String loginNadawcy = nadawca.getLogin();

            ManagerSkrzynki managerSkrzynki = (ManagerSkrzynki) session.getAttribute("managerSkrzynki");
            if (managerSkrzynki == null) {
                managerSkrzynki = new ManagerSkrzynki();
            }

            ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
            if (managerUzytkownika == null) {
                managerUzytkownika = new ManagerUzytkownika();
            }

            Uzytkownicy odbiorca;
            try {
                odbiorca = managerUzytkownika.PokazUzytkownika(idOdbiorcy);
            } catch (Exception e) {
                request.setAttribute("bazaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
                dispatcher.forward(request, response);
                return;
            }

            String temat = "Dodanie do znajomych nie powiodło się";
            String tresc = loginNadawcy + " nie zgodził/a dodać się Ciebie do listy znajomych.";
            boolean czyPrzeczytana = false;
            Date data = new Date();

            Wiadomosci wiadomosc = new Wiadomosci(null, tresc, data, czyPrzeczytana, temat, true, true);

            try {
                Wiadomosci w =  managerSkrzynki.DodajWiadomosc(wiadomosc, odbiorca, nadawca);
            } catch (Exception e) {
                request.setAttribute("bazaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
                dispatcher.forward(request, response);
                return;
            }

            List<Wiadomosci> wiadomosciWyslane;
            try {
                wiadomosciWyslane = managerSkrzynki.ListujWiadomosciNadawcza(idNadawcy);
            } catch (Exception e) {
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            session.setAttribute("wiadomosciWyslane", wiadomosciWyslane);

            int idWiadomosci = Integer.parseInt(request.getParameter("idWiadomosci"));
            managerSkrzynki.UsunWiadomoscSystemowa(idWiadomosci, idOdbiorcy);
            
            List<Systemowe> wiadomosciSystemowe;
            try
            {
                wiadomosciSystemowe = managerSkrzynki.ListujWiadomosciSystemowe(idNadawcy);
            }
            catch (Exception e)
            {
                System.out.println(e);
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }
            
            session.setAttribute("wiadomosciSystemowe", wiadomosciSystemowe);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
            dispatcher.forward(request, response);
        }
    }

}
