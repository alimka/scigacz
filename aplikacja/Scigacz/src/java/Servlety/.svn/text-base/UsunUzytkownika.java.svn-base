/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerUzytkownika;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UsunUzytkownika extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /** 
     * Handles the HTTP <code>GET</code> method.
     * <P>Usuwa uzytkownika przez administratora systemu.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
        if (managerUzytkownika == null) {
            managerUzytkownika = new ManagerUzytkownika();
        }

        int idOdbiorcy = 0;
        try {
            idOdbiorcy = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            request.setAttribute("sizeOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/uzytkownicy.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            managerUzytkownika.UsunKonto(idOdbiorcy);
        } catch (Exception e) {
            request.setAttribute("bazaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/uzytkownicy.jsp");
            dispatcher.forward(request, response);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/uzytkownicy.jsp");
        dispatcher.forward(request, response);
    }

}
