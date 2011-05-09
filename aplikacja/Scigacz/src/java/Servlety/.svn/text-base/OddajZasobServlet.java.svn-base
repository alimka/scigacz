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
public class OddajZasobServlet extends HttpServlet {

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
     * <p>Do użytkownika udostępniającego zasób wysyłana jest wiadomość o oddaniu zasobu.
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


        int idWypozyczenia;
        try {
            idWypozyczenia = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            request.setAttribute("ParametryOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pozyczone.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Wypozyczenia wypozyczenie;
        try {
            wypozyczenie = managerWypozyczen.PokazWypozyczenie(idWypozyczenia);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pozyczone.jsp");
            dispatcher.forward(request, response);
            return;
        }
/*        try {
            managerWypozyczen.OznaczDateZwrotu(wypozyczenie, new Date());
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pozyczone.jsp");
            dispatcher.forward(request, response);
            return;
        }
*/
        Zasoby zasob = wypozyczenie.getIdZasobu();
        zasob.setDostepnosc(3);
        try {
            managerZasobow.EdytujZasob(zasob);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pozyczone.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Uzytkownicy udostepniacz = zasob.getIdUzytkownika();
        String tresc = "Użytkownik <a href=\"/Scigacz/OsobaServlet?id=" + uzytkownik.getIdUzytkownika() + "\">" +
                uzytkownik.getLogin() + "</a> oddał zasób <a href=\"/Scigacz/RzeczServlet?id=" + zasob.getIdZasobu() + "\">" + zasob.getNazwa() + "</a>.";
        Date data = new Date();
        boolean czyPrzeczytana = false;
        String temat = "Zwrot zasobu";
        boolean jestOdbiorca = true;
        boolean jestNadawca = true;
        int rodzaj = 5;
        int idDoZwrotu = uzytkownik.getIdUzytkownika();
        Systemowe prosba = new Systemowe(null, tresc, data, czyPrzeczytana, temat, jestOdbiorca, jestNadawca, rodzaj, idDoZwrotu, zasob.getIdZasobu());
        try {
            managerSkrzynki.DodajWiadomoscSystemowa(prosba, udostepniacz);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
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

        request.setAttribute("zwrot", "tak");
        session.setAttribute("zwrot" + wypozyczenie.getIdWypozyczenia(), "ok");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pozyczone.jsp");
        dispatcher.forward(request, response);

    }
}
