/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerZasobow;
import ScigaczDB.Kategorie;
import java.io.IOException;
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
public class UsunKategorieServlet extends HttpServlet {

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
     * <p>Usuwanie zaznaczonych przez użytkownika zasobów.
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
        ManagerZasobow managerZasobow = (ManagerZasobow) session.getAttribute("managerZasobow");
        if (managerZasobow == null) {
            managerZasobow = new ManagerZasobow();
        }
        String kat = request.getParameter("id");
        if (kat != null) {
            int id = Integer.parseInt(kat);
            try {
                managerZasobow.UsunKategorie(id);
            } catch (Exception e) {
                request.setAttribute("niepusta", "yes");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }



        List<Kategorie> kategorie;
        try {
            kategorie = managerZasobow.ListujKategorie();
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/kategorie.jsp");
            dispatcher.forward(request, response);
            return;
        }

        session.setAttribute("kategorie", kategorie);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/kategorie.jsp");
        dispatcher.forward(request, response);
    }
}
