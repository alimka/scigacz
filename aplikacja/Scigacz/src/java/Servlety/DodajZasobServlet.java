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
import com.oreilly.servlet.MultipartRequest;
import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author k8
 */
public class DodajZasobServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * <P> Dodawanie nowego zasobu przez uzytkownika.
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
     * <p>Dodawany jest nowy zasób użytkownika.
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

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Uzytkownicy uzytkownik = (Uzytkownicy) session.getAttribute("uzytkownik");
        ManagerZasobow managerZasobow = (ManagerZasobow) session.getAttribute("managerZasobow");
        if (managerZasobow == null) {
            managerZasobow = new ManagerZasobow();
        }
        String dir = getServletContext().getRealPath("build");
        dir = dir.split("build")[0];
        dir = dir + "/web/obrazki";

        MultipartRequest multiRequest;
        try {
            multiRequest = new MultipartRequest(request, dir, 500000, "UTF-8");
        } catch (Exception e) {
            request.setAttribute("sizeOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dodajZasob.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String nazwa = multiRequest.getParameter("nazwa");
        String opis = multiRequest.getParameter("opis");
        int idKategorii = 0;
        try {
            idKategorii = Integer.parseInt(multiRequest.getParameter("kategoria"));
        } catch (Exception e) {
        }
        int czas = 0;
        try {
            czas = Integer.parseInt(multiRequest.getParameter("czas"));
        } catch (Exception e) {
        }
        String miara = null;
        if (czas != 0) {
            miara = multiRequest.getParameter("miara");
            if (miara.equals("tygodni")) {
                czas *= 7;
            }
            if (miara.equals("miesiecy")) {
                czas *= 30;
            }
        } else {
            czas = 30;
        }
        int dostepnosc = 0;
        try {
            dostepnosc = Integer.parseInt(multiRequest.getParameter("udostepnienie"));
        } catch (Exception e) {
        }
        Zasoby zasob = new Zasoby(null, nazwa, czas, 0, opis, dostepnosc);
        zasob.setIdUzytkownika(uzytkownik);
        List<Kategorie> kategorie = null;
        try {
            kategorie = managerZasobow.PokazKategorie();
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dodajZasob.jsp");
            dispatcher.forward(request, response);
            return;
        }
        if (kategorie.size() == 0) {
            out.println("Dodaj cokolwiek do tabeli kategorii!");
            return;
        }
        Kategorie kategoria;
        try {
            kategoria = managerZasobow.PokazKat(idKategorii);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dodajZasob.jsp");
            dispatcher.forward(request, response);
            return;
        }

        zasob.setIdKategorii(kategoria);

        try {
            managerZasobow.DodajZasob(zasob);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dodajZasob.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Enumeration files = multiRequest.getFileNames();
        int nrZdjecia = 3;
        while (files.hasMoreElements()) {
            nrZdjecia--;
            String name = (String) files.nextElement();
            String filename = multiRequest.getFilesystemName(name);
            String type = multiRequest.getContentType(name);
            File file = multiRequest.getFile(name);
            if (file == null) {
                continue;
            }
            if (type.contains("image")) // wyslany plik jest obrazkiem
            {
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                long id = date.getTime();
                String nazwaZd = "zd_" + id;
                File file2 = new File(dir + "/" + nazwaZd);
                boolean success = file.renameTo(file2);
                if (success) {
                    Zdjecia zdjecie = new Zdjecia(null, nazwaZd, nrZdjecia);
                    zdjecie.setIdZasobu(zasob);
                    try {
                        managerZasobow.DodajZdjecieZasobu(zdjecie);
                    } catch (Exception e) {
                        request.setAttribute("bazkaOK", "no");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/dodajZasob.jsp");
                        dispatcher.forward(request, response);
                        return;
                    }
                }
            }
        } // while

        List<Zasoby> zasobyUzytkownika;
        try {
            zasobyUzytkownika = managerZasobow.ListujZasobyUzytkownika(uzytkownik.getIdUzytkownika());
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dodajZasob.jsp");
            dispatcher.forward(request, response);
            return;
        }

        session.setAttribute("zasobyUzytkownika", zasobyUzytkownika);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
        dispatcher.forward(request, response);

    }
}
