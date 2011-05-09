/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerUzytkownika;
import Managery.ManagerZasobow;
import Managery.ManagerZnajomych;
import ScigaczDB.Uzytkownicy;
import ScigaczDB.Zasoby;
import java.io.IOException;
import java.io.PrintWriter;
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
public class SzukajServlet extends HttpServlet {

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
     * <p>Wyszukiwanie użytkowników lub zasobów na podstawie podanego wzorca.
     * W sesji umieszczana jest lista znalezionych pozycji.
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
        Uzytkownicy uzytkownik = (Uzytkownicy) session.getAttribute("uzytkownik");
        ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
        if (managerUzytkownika == null) {
            managerUzytkownika = new ManagerUzytkownika();
        }
        ManagerZasobow managerZasobow = (ManagerZasobow) session.getAttribute("managerZasobow");
        if (managerZasobow == null) {
            managerZasobow = new ManagerZasobow();
        }


        String klucz = request.getParameter("key");
        String akcja = request.getParameter("akcja");
        List<Zasoby> zasoby = null;
        List<Uzytkownicy> uzytkownicy = null;
        if (akcja.equals("zasoby")) {

            try {
                zasoby = managerZasobow.SzukajPoZasobach(klucz);
                uzytkownicy = managerUzytkownika.SzukajPoUzytkownikach(klucz);

            } catch (Exception e) {
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                return;
            }
        } else //szukaj uzytkownikow
        {

            try {
                zasoby = managerZasobow.SzukajPoZasobach(klucz);
                uzytkownicy = managerUzytkownika.SzukajPoUzytkownikach(klucz);
            } catch (Exception e) {
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }
        session.setAttribute("uzytkownicy", uzytkownicy);
        session.setAttribute("zasoby", zasoby);
        session.setAttribute("akcja", akcja);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/wyniki.jsp");
        dispatcher.forward(request, response);
    }
}
