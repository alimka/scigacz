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
 * @author Kamila Turek
 */
public class ZaprosZnajomegoServlet extends HttpServlet {

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
     * <P>Wysłanie do użytkownika wiadomości z prośbą o dodanie do grona znajomych.
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

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
        if (managerUzytkownika == null) {
            managerUzytkownika = new ManagerUzytkownika();
        }

        Uzytkownicy nadawca = (Uzytkownicy) session.getAttribute("uzytkownik");
        if (nadawca == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }

        int idNadawcy = nadawca.getIdUzytkownika();

        int idOdbiorcy = Integer.parseInt(request.getParameter("id"));
        Uzytkownicy odbiorca = null;
        try {
            odbiorca = managerUzytkownika.PokazUzytkownika(idOdbiorcy);
        } catch (Exception e) {
            request.setAttribute("bazaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/znajomi.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String temat = "Zaproszenie do znajomych";
        String tresc = "Uzytkownik " + nadawca.getLogin() + " zaprosił Cię do znajomych";

        ManagerSkrzynki managerSkrzynki = (ManagerSkrzynki) session.getAttribute("managerSkrzynki");
        if (managerSkrzynki == null) {
            managerSkrzynki = new ManagerSkrzynki();
        }

        Date data = new Date();
        int rodzaj = 1;
        Systemowe wiadomoscSystemowa = new Systemowe(null, tresc, data, false, temat, true, true, rodzaj, idNadawcy, -1);

        try {
            managerSkrzynki.DodajWiadomoscSystemowa(wiadomoscSystemowa, odbiorca);
        } catch (Exception e) {
            request.setAttribute("bazaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
            dispatcher.forward(request, response);
            return;
        }

        session.setAttribute("zaproszenieWyslane" + idOdbiorcy, "ok");

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

        RequestDispatcher dispatcher = request.getRequestDispatcher("/OsobaServlet?id=" + idOdbiorcy);
        dispatcher.forward(request, response);

    }

}
