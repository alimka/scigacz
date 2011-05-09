/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerUzytkownika;
import ScigaczDB.Uzytkownicy;
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
 * @author Kamila Turek
 */
public class ZablokujServlet extends HttpServlet {

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
     * <P>Blokowanie lub odblokowywanie użytkownika, w zależności od ustawień początkowych użytkownik zablokowany zostaje odblokowany a uzytkownik odblokowany zostaje zablokowany.
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

        int idOdbiorcy = 0;
        Uzytkownicy blokowany = null;
        try {
            idOdbiorcy = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            request.setAttribute("sizeOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/uzytkownicy.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            blokowany = managerUzytkownika.PokazUzytkownika(idOdbiorcy);
        } catch (Exception e) {
        }

        Date dataBlokowania = blokowany.getDatablokada();
        Date podstawowaData = new Date(0);
        
        if (dataBlokowania.compareTo(podstawowaData) == 0)
            dataBlokowania = new Date();
        else
            dataBlokowania = podstawowaData;

        String komentarz = "";

        try {
            managerUzytkownika.Blokada(blokowany, dataBlokowania, komentarz);
        } catch (Exception e) {
            request.setAttribute("bazaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/uzytkownicy.jsp");
            dispatcher.forward(request, response);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/uzytkownicy.jsp");
        dispatcher.forward(request, response);
    }

}
