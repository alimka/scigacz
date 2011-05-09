/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerZasobow;
import ScigaczDB.Zasoby;
import java.io.IOException;
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
public class RzeczServlet extends HttpServlet {

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
     * <p>Do sesji dodawany jest wybrany zasób, aby można było wyświetlić o nim pełne informacje.
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

        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            request.setAttribute("parametryOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
            dispatcher.forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        ManagerZasobow managerZasobow = (ManagerZasobow) session.getAttribute("managerZasobow");
        Zasoby zasob;
        try {
            zasob = managerZasobow.PokazZasob(id);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // pozwolilam sobie dodac pare linii zeby servlet obslugiwal takze wyswietlanie strony z edytowaniem zasobow - fircyk
        String edit = request.getParameter("edit");
        if (edit == null) {
            edit = "rzecz.jsp";
        } else {
            edit = "edytujZasob.jsp";
        }

        session.setAttribute("zasob", zasob);
        RequestDispatcher dispatcher = request.getRequestDispatcher(edit);
        dispatcher.forward(request, response);
    }
}
