/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerSkrzynki;
import Managery.ManagerUzytkownika;
import ScigaczDB.Systemowe;
import ScigaczDB.Uzytkownicy;
import ScigaczDB.Wiadomosci;
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
public class UsunWiadomoscServlet extends HttpServlet {

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

    /** 
     * Handles the HTTP <code>POST</code> method.
     * <p>Usuwanie zaznaczonych przez użytkownika wiadomości.
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
        Uzytkownicy uzytkownik = (Uzytkownicy) session.getAttribute("uzytkownik");
        ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
        if (managerUzytkownika == null) {
            managerUzytkownika = new ManagerUzytkownika();
        }

        int idUzytk = uzytkownik.getIdUzytkownika();

        ManagerSkrzynki managerSkrzynki = (ManagerSkrzynki) session.getAttribute("managerSkrzynki");
        if (managerSkrzynki == null) {
            managerSkrzynki = new ManagerSkrzynki();
        }
        String typ = request.getParameter("typ");
        if (typ.equals("systemowe")) {
            String[] wiadomosci = request.getParameterValues("idWiadomosci");
            if (wiadomosci != null) {
                for (int i = 0; i < wiadomosci.length; i++) {
                    int id = Integer.parseInt(wiadomosci[i]);
                    try {
                        managerSkrzynki.UsunWiadomoscSystemowa(id, idUzytk);
                    } catch (Exception e) {
                        request.setAttribute("bazkaOK", "no");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
                        dispatcher.forward(request, response);
                        return;
                    }
                }
                List<Systemowe> wiadomosciSystemowe;
                try {
                    wiadomosciSystemowe = managerSkrzynki.ListujWiadomosciSystemowe(idUzytk);
                } catch (Exception e) {
                    System.out.println(e);
                    request.setAttribute("bazkaOK", "no");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
                session.removeAttribute("wiadomosciSystemowe");
                session.setAttribute("wiadomosciSystemowe", wiadomosciSystemowe);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp?s=3");
                dispatcher.forward(request, response);
                return;

            }
        }
        String[] wiadomosci = request.getParameterValues("idWiadomosci");
        if (wiadomosci != null) {
            for (int i = 0; i < wiadomosci.length; i++) {
                int id = Integer.parseInt(wiadomosci[i]);
                try {
                    managerSkrzynki.UsunWiadomosc(id, idUzytk);
                } catch (Exception e) {
                    request.setAttribute("bazkaOK", "no");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            }
        }

        if (typ.equals("odebrane")) {
            List<Wiadomosci> wiadomosciOdebrane;
            try {
                wiadomosciOdebrane = managerSkrzynki.ListujWiadomosciOdbiorcza(idUzytk);
            } catch (Exception e) {
                System.out.println(e);
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
                dispatcher.forward(request, response);
                return;
            }
            session.removeAttribute("wiadomosciOdebrane");
            session.setAttribute("wiadomosciOdebrane", wiadomosciOdebrane);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp?s=1");
            dispatcher.forward(request, response);
            return;
        }
        if (typ.equals("wyslane")) {
            List<Wiadomosci> wiadomosciWyslane;
            try {
                wiadomosciWyslane = managerSkrzynki.ListujWiadomosciNadawcza(idUzytk);
            } catch (Exception e) {
                System.out.println(e);
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
                dispatcher.forward(request, response);
                return;
            }
            session.removeAttribute("wiadomosciWyslane");
            session.setAttribute("wiadomosciWyslane", wiadomosciWyslane);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp?s=2");
            dispatcher.forward(request, response);
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp");
        dispatcher.forward(request, response);
    }
}
