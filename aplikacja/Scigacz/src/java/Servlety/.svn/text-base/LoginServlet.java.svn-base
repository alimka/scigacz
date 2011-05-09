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
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author k8
 */
public class LoginServlet extends HttpServlet {

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
     * <p>Logowanie użytkownika o podanym loginie i haśle. Tworzona jest sesja HttpSession oraz managery do interakcji z bazą danych.
     * W sesji zapisywany jest użytkownik oraz świeżo utworzone managery. 
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

        // do wypisywania czegokolwiek, czyli testowania :)

        // alimka - tworzy obiekt sesji:
        HttpSession session = request.getSession();
        String go = "/index.jsp";
        String login = request.getParameter("login");
        String password = request.getParameter("passwd");

        if (login != null && login.equals("administrator") && password != null && password.equals("dupa.8")) {
            session.setAttribute("authorized", "yes");
            session.setAttribute("login", "administrator");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
        }
        ManagerUzytkownika managerUzytkownika = new ManagerUzytkownika();
        int id;
        try {
            id = managerUzytkownika.idUzytkownika(login, password);
        } catch (Exception e) {
            request.setAttribute("bazaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        ManagerZnajomych managerZnajomych = new ManagerZnajomych();
        ManagerZasobow managerZasobow = new ManagerZasobow();
        ManagerOpinii managerOpinii = new ManagerOpinii();
        ManagerSkrzynki managerSkrzynki = new ManagerSkrzynki();

        if (id == -100) {
            session.setAttribute("authorized", "no");
        } else {
            Uzytkownicy uzytkownik;
            try {
                uzytkownik = managerUzytkownika.PokazUzytkownika(id);
            } catch (Exception e) {
                System.out.println(e);
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            boolean czyAdmin;
            try {
                czyAdmin = managerUzytkownika.CzyAdmin(uzytkownik);
            } catch (Exception e) {
                System.out.println(e);
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            List<Opinie> opinieUzytkownika;
            try {
                opinieUzytkownika = managerOpinii.ListujOpinieUzytkownika(id);
            } catch (Exception e) {
                System.out.println(e);
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }
            if (!uzytkownik.getDatablokada().equals(Timestamp.valueOf("1970-01-01 01:00:00"))) {
                go = "/blokada.jsp";
                session.setAttribute("authorized", "no");

            } else {

                session.setAttribute("authorized", "yes");
            }
            session.setAttribute("managerUzytkownika", managerUzytkownika);
            session.setAttribute("uzytkownik", uzytkownik);

            session.setAttribute("managerZnajomych", managerZnajomych);

            session.setAttribute("managerZasobow", managerZasobow);

            session.setAttribute("managerOpinii", managerOpinii);
            session.setAttribute("opinieUzytkownika", opinieUzytkownika);

            session.setAttribute("managerSkrzynki", managerSkrzynki);


        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(go);
        dispatcher.forward(request, response);
    }
}
