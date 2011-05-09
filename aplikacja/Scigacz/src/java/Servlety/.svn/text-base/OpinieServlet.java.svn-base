/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerOpinii;
import Managery.ManagerSkrzynki;
import Managery.ManagerUzytkownika;
import Managery.ManagerWypozyczen;
import ScigaczDB.Opinie;
import ScigaczDB.Uzytkownicy;
import ScigaczDB.Wiadomosci;
import ScigaczDB.Wypozyczenia;
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
 * @author k8
 */
public class OpinieServlet extends HttpServlet {

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
        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        Uzytkownicy uzytkownik = (Uzytkownicy) session.getAttribute("uzytkownik");

        ManagerOpinii managerOpinii = (ManagerOpinii) session.getAttribute("managerOpinii");
        if (managerOpinii == null) {
            managerOpinii = new ManagerOpinii();
        }


        ManagerWypozyczen managerWypozyczen = (ManagerWypozyczen) session.getAttribute("managerWypozyczen");
        if (managerWypozyczen == null) {
            managerWypozyczen = new ManagerWypozyczen();
        }

        ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
        if (managerUzytkownika == null) {
            managerUzytkownika = new ManagerUzytkownika();
        }

        String nad = request.getParameter("idNadawcy");
        String odb = request.getParameter("idOdbiorcy");
       // String wyp = request.getParameter("idWypozyczenia");

        int idNadawcy = 0;
        try {
            idNadawcy = Integer.parseInt(nad);
        } catch (Exception e) {
            request.setAttribute("parametryOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/osoba.jsp");
            dispatcher.forward(request, response);
            return;
        }

        int idOdbiorcy = 0;
        try {
            idOdbiorcy = Integer.parseInt(odb);
        } catch (Exception e) {
            request.setAttribute("parametryOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/osoba.jsp");
            dispatcher.forward(request, response);
            return;
        }


       /* int idWypozyczenia = 0;
        try {
            idWypozyczenia = Integer.parseInt(wyp);
        } catch (Exception e) {
            request.setAttribute("parametryOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/osoba.jsp");
            dispatcher.forward(request, response);
            return;
        }
*/
        String tresc = request.getParameter("tresc");
        Date data = new Date();
        //Date data = new Date(0, 0, 0);


  /*      Wypozyczenia wypozyczenie;
        try {
            wypozyczenie = managerWypozyczen.PokazWypozyczenie(idWypozyczenia);
        } catch (Exception e) {
            request.setAttribute("bazaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/osoba.jsp");
            dispatcher.forward(request, response);
            return;
        }
*/
        Uzytkownicy nadawca;
        try {
            nadawca = managerUzytkownika.PokazUzytkownika(idNadawcy);
        } catch (Exception e) {
            request.setAttribute("bazaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/osoba.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Uzytkownicy odbiorca;
        if (idOdbiorcy == -1)
        {
            String loginOdbiorcy = request.getParameter("odbiorca");
            try 
            {
                odbiorca = managerUzytkownika.UzytkownikOLoginie(loginOdbiorcy);
            } 
            catch (Exception e) 
            {
                request.setAttribute("bazaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/osoba.jsp");
                dispatcher.forward(request, response);
                return;
            }            
        }    
        else
        {
            try
            {
                odbiorca = managerUzytkownika.PokazUzytkownika(idOdbiorcy);
            }
            catch (Exception e)
            {
                request.setAttribute("bazaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/osoba.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }

        Opinie opinia = new Opinie(null,tresc,data,1);
        opinia.setIdOpiniujacego(nadawca);
        opinia.setIdUzytkownika(odbiorca);
        try {
            managerOpinii.DodajOpinie(opinia);
        } catch (Exception e) {
            request.setAttribute("bazaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/osoba.jsp");
            dispatcher.forward(request, response);
            return;
        }

        request.setAttribute("wyslane", "ok");

        List<Opinie> opinieUzytkownika;
        try {
           opinieUzytkownika = managerOpinii.ListujOpinieUzytkownika(idOdbiorcy);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        session.setAttribute("opinieZiomka", opinieUzytkownika);

        //RequestDispatcher dispatcher = request.getRequestDispatcher("/skrzynka.jsp?s=2");
           RequestDispatcher dispatcher = request.getRequestDispatcher("/opinia.jsp");
        dispatcher.forward(request, response);
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
