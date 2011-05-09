/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerSkrzynki;
import Managery.ManagerZasobow;
import Managery.ManagerZnajomych;
import ScigaczDB.Systemowe;
import ScigaczDB.Uzytkownicy;
import ScigaczDB.Zasoby;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class WypozyczServlet extends HttpServlet {

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
     * <p>Wysłanie wiadomości do użytkownika udostępniającego zasób o chęci jego wypożyczenia.
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
        int idUzyt = uzytkownik.getIdUzytkownika();

        ManagerZasobow managerZasobow = (ManagerZasobow) session.getAttribute("managerZasobow");
        if (managerZasobow == null) {
            managerZasobow = new ManagerZasobow();
        }

        ManagerZnajomych managerZnajomych = (ManagerZnajomych) session.getAttribute("mangerZnajomych");
        if (managerZnajomych == null) {
            managerZnajomych = new ManagerZnajomych();
        }

        ManagerSkrzynki managerSkrzynki = (ManagerSkrzynki) session.getAttribute("managerSkrzynki");
        if (managerSkrzynki == null) {
            managerSkrzynki = new ManagerSkrzynki();
        }


        String zasobID = request.getParameter("z");
        if (zasobID != null) {
            int idZasobu = Integer.parseInt(zasobID);
            Zasoby zasob = null;
            try {
                zasob = managerZasobow.PokazZasob(idZasobu);
            } catch (Exception e) {
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
                dispatcher.forward(request, response);
                return;
            }
            zasob.setDostepnosc(2);
            try {
                managerZasobow.EdytujZasob(zasob);
            } catch (Exception e) {
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
                dispatcher.forward(request, response);
                return;
            }

            Uzytkownicy udostepniacz = zasob.getIdUzytkownika();
            String tresc = "Użytkownik <a href=\"/Scigacz/OsobaServlet?id=" + uzytkownik.getIdUzytkownika() + "\">" +
                    uzytkownik.getLogin() + "</a> prosi o wypożyczenie zasobu <a href=\"/Scigacz/RzeczServlet?id=" + zasob.getIdZasobu() + "\">" + zasob.getNazwa() + "</a>.";
            Date data = new Date();
            boolean czyPrzeczytana = false;
            String temat = "Prosba o wypozyczenie zasobu";
            boolean jestOdbiorca = true;
            boolean jestNadawca = true;
            int rodzaj = 4;
            int idDoZwrotu = idUzyt;
            Systemowe prosba = new Systemowe(null, tresc, data, czyPrzeczytana, temat, jestOdbiorca, jestNadawca, rodzaj, idDoZwrotu, idZasobu);
            try {
                managerSkrzynki.DodajWiadomoscSystemowa(prosba, udostepniacz);
            } catch (Exception e) {
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
                dispatcher.forward(request, response);
                return;
            }
            session.setAttribute("prosbaWyslana", "tak");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/RzeczServlet?id=" + zasob.getIdZasobu());
            dispatcher.forward(request, response);
            return;
        }



    }
}
