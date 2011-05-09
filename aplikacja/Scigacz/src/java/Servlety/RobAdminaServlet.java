/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerOpinii;
import Managery.ManagerUzytkownika;
import Managery.ManagerWypozyczen;
import ScigaczDB.Opinie;
import ScigaczDB.Uzytkownicy;
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
 * @author pedros
 */
public class RobAdminaServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
  processRequest(request, response);
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
        if (managerUzytkownika == null) {
            managerUzytkownika = new ManagerUzytkownika();
        }

Uzytkownicy a=managerUzytkownika.UzytkownikOLoginie("admin");
if (a==null){
        Uzytkownicy admin = new Uzytkownicy(null,"Admin","Admin","admin","admin","",0,"","av.jpg",1,new Date(0),"","");
        try  {

            managerUzytkownika.DodajKonto(admin);
        } catch (Exception e) {
            request.setAttribute("bazaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/backup.jsp");
            dispatcher.forward(request, response);
            return;
        }
}
        request.setAttribute("wyslane", "ok");

        //RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp?s=2");
           RequestDispatcher dispatcher = request.getRequestDispatcher("/backup.jsp");
        dispatcher.forward(request, response);
    

    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
