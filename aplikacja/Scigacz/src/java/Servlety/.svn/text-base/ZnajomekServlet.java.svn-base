/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Managery.*;
import ScigaczDB.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kamila Turek
 */
public class ZnajomekServlet extends HttpServlet {

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
     * <P> Usuwanie wybranych znajomych z listy znajomych przez użytkownika.
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
        String[] znajomki = request.getParameterValues("znajomek");

        ManagerZnajomych managerZnajomych = (ManagerZnajomych) session.getAttribute("managerZnajomych");
        if (managerZnajomych == null) {
            managerZnajomych = new ManagerZnajomych();
        }

        Uzytkownicy uzytkownik = (Uzytkownicy) session.getAttribute("uzytkownik");
        int idUzytkownika = uzytkownik.getIdUzytkownika();
        if (znajomki != null) {
            for (int i = 0; i < znajomki.length; i++) {
                int idZnajomego = Integer.parseInt(znajomki[i]);
                int idZnajomosci = managerZnajomych.IdZnajomosci(idUzytkownika, idZnajomego);
                managerZnajomych.UsunZnajomego(idZnajomosci);
            }
        }

        List<Znajomi> znajomiUzytkownika = managerZnajomych.ListujZnajomychUzytkownika(idUzytkownika);
        session.setAttribute("znajomiUzytkownika", znajomiUzytkownika);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/znajomi.jsp");
        dispatcher.forward(request, response);
    }

}
