/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import java.io.IOException;
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
public class OsobaServlet extends HttpServlet {

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
     * <P>Podaje informacje niezbędne do wyświetlania profilu użytkownika, dostępnego dla osób przeglądających.
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

        // PrintWriter out = response.getWriter();
        int idZnajomego = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();

        ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
        if (managerUzytkownika == null) {
            managerUzytkownika = new ManagerUzytkownika();
        }

        ManagerZasobow managerZasobow = (ManagerZasobow) session.getAttribute("managerZasobow");
        if (managerZasobow == null) {
            managerZasobow = new ManagerZasobow();
        }

        ManagerOpinii managerOpinii = (ManagerOpinii) session.getAttribute("managerOpinii");
        if (managerOpinii == null) {
            managerOpinii = new ManagerOpinii();
        }

        ManagerZnajomych managerZnajomych = (ManagerZnajomych) session.getAttribute("managerZnajomych");
        if (managerZnajomych == null) {
            managerZnajomych = new ManagerZnajomych();
        }

        Uzytkownicy ziomek = managerUzytkownika.PokazUzytkownika(idZnajomego);
        session.setAttribute("ziomek", ziomek);

        List<Zasoby> zasobyZiomka = managerZasobow.ListujZasobyUzytkownika(idZnajomego);
        session.setAttribute("zasobyZiomka", zasobyZiomka);

        List<Opinie> opinieZiomka = managerOpinii.ListujOpinieUzytkownika(idZnajomego);
        session.setAttribute("opinieZiomka", opinieZiomka);

        Uzytkownicy uzytkownik = (Uzytkownicy) session.getAttribute("uzytkownik");
        int idUzytkownika = uzytkownik.getIdUzytkownika();

        int idZnajomosci = managerZnajomych.IdZnajomosci(idUzytkownika, idZnajomego);
        if (idZnajomosci == -10) {
            session.setAttribute("czyZnajomy", "nie");
        } else {
            session.setAttribute("czyZnajomy", "tak");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/osoba.jsp");
        dispatcher.forward(request, response);
    }
}
