/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerUzytkownika;
import Managery.ManagerZnajomych;
import ScigaczDB.Uzytkownicy;
import ScigaczDB.Znajomi;
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
 * @author alimka
 */
public class EdytujZnajomoscServlet extends HttpServlet {

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
     * <p> Edycja znajomości pomiędzy użytkonikami. W zalezności od stopnia znajomości zachodzi zmiana z 'bliskiej znajomości' na 'znajomość' oraz odwrotnie z 'znajomości' na 'bliską znajomość'.
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
        int idZnajomego = Integer.parseInt(request.getParameter("id"));
        out.println("idZnajomego: " + idZnajomego);

        HttpSession session = request.getSession();

        ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
        if (managerUzytkownika == null) {
            managerUzytkownika = new ManagerUzytkownika();
        }

        ManagerZnajomych managerZnajomych = (ManagerZnajomych) session.getAttribute("managerZnajomych");
        if (managerZnajomych == null) {
            managerZnajomych = new ManagerZnajomych();
        }

        Uzytkownicy uzytkownik = (Uzytkownicy) session.getAttribute("uzytkownik");
        if (uzytkownik == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }

        int idUzytkownika = uzytkownik.getIdUzytkownika();
        out.println("idUzytkownika: " + idUzytkownika);
        int idZnajomosci;
        try {
            idZnajomosci = managerZnajomych.IdZnajomosci(idZnajomego, idUzytkownika);
            out.println("idZnajomosci: " + idZnajomosci);
        } catch (Exception e) {
            request.setAttribute("parametryOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
            dispatcher.forward(request, response);
            return;
        }

        boolean czyBliski;
        try {
            czyBliski = managerZnajomych.CzyBliski(idZnajomosci, idUzytkownika);
            out.println("czyBliski: " + czyBliski);
        } catch (Exception e) {
            request.setAttribute("parametryOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            managerZnajomych.ZmienRodzajZnajomosci(idZnajomosci, idUzytkownika);
            czyBliski = managerZnajomych.CzyBliski(idZnajomosci, idUzytkownika);
            out.println("zmiana czyBliski: " + czyBliski);
        } catch (Exception e) {
            request.setAttribute("parametryOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
            dispatcher.forward(request, response);
            return;
        }

        List<Znajomi> znajomiUzytkownika;
        try {
            znajomiUzytkownika = managerZnajomych.ListujZnajomychUzytkownika(idUzytkownika);
        } catch (Exception e) {
            request.setAttribute("parametryOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/wiadom.jsp");
            dispatcher.forward(request, response);
            return;
        }

        session.setAttribute("znajomiUzytkownika", znajomiUzytkownika);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/znajomi.jsp");
        dispatcher.forward(request, response);
    }
}
