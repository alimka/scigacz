/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerZasobow;
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
public class UsunZasobServlet extends HttpServlet {

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
     * <p>Usuwanie zaznaczonych przez użytkownika zasobów.
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
        ManagerZasobow managerZasobow = (ManagerZasobow) session.getAttribute("managerZasobow");
        if (managerZasobow == null) {
            managerZasobow = new ManagerZasobow();
        }
        String[] zasoby = request.getParameterValues("idZasobu");
        if (zasoby != null) {
            for (int i = 0; i < zasoby.length; i++) {
                int id = Integer.parseInt(zasoby[i]);
                try {
                    managerZasobow.UsunZasob(id);
                } catch (Exception e) {
                    request.setAttribute("bazkaOK", "no");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            }

        }


        Uzytkownicy uzytkownik = (Uzytkownicy) session.getAttribute("uzytkownik");
        int idUzyt = 0;
        try {
            idUzyt = uzytkownik.getIdUzytkownika();
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
            dispatcher.forward(request, response);
            return;
        }

        List<Zasoby> zasobyUzytkownika;
        try {
            zasobyUzytkownika = managerZasobow.ListujZasobyUzytkownika(idUzyt);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
            dispatcher.forward(request, response);
            return;
        }

        session.setAttribute("zasobyUzytkownika", zasobyUzytkownika);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
        dispatcher.forward(request, response);
    }
}
