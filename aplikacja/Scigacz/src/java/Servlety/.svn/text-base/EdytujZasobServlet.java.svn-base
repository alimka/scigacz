/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Managery.ManagerZasobow;
import ScigaczDB.Kategorie;
import ScigaczDB.Uzytkownicy;
import ScigaczDB.Zasoby;
import ScigaczDB.Zdjecia;
import com.oreilly.servlet.MultipartRequest;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
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
public class EdytujZasobServlet extends HttpServlet {

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
     * <p>Edycja danych wybranego zasobu.
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("/edytujZasob.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String nazwa = multiRequest.getParameter("nazwa");
        String opis = multiRequest.getParameter("opis");
        String napkat = multiRequest.getParameter("kategoria");
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
        }
        int dostepnosc = 0;
        try {
            dostepnosc = Integer.parseInt(multiRequest.getParameter("udostepnienie"));
        } catch (Exception e) {
        }

        int idZasobu = 0;
        try {
            idZasobu = Integer.parseInt(multiRequest.getParameter("idZasobu"));
        } catch (Exception e) {
        }

        Zasoby zasob;
        try {
            zasob = managerZasobow.PokazZasob(idZasobu);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/edytujZasob.jsp");
            dispatcher.forward(request, response);
            return;
        }
        Kategorie kategoria;
        try {
            kategoria = managerZasobow.PokazKat(idKategorii);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/edytujZasob.jsp");
            dispatcher.forward(request, response);
            return;
        }

        zasob.setIdKategorii(kategoria);
        zasob.setCzasPrzetrzymywania(czas);
        zasob.setDlakogo(dostepnosc);
        zasob.setNazwa(nazwa);
        zasob.setOpis(opis);
        try {
            managerZasobow.EdytujZasob(zasob);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/edytujZasob.jsp");
            dispatcher.forward(request, response);
            return;
        }

        List<Zdjecia> zdjeciaList;
        try {
            zdjeciaList = managerZasobow.PokazZdjeciaZasobu(idZasobu);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/edytujZasob.jsp");
            dispatcher.forward(request, response);
            return;
        }
        Zdjecia[] zdjecia = new Zdjecia[3];
        for (int i = 0; i < 3; i++) {
            zdjecia[i] = null;
        }
        for (int i = 0; i < zdjeciaList.size(); i++) {
            zdjecia[zdjeciaList.get(i).getNrZdjecia()] = zdjeciaList.get(i);
        }

        int idZdjecia = 3;
        Enumeration files = multiRequest.getFileNames();
        while (files.hasMoreElements()) {
            idZdjecia--;
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
                filename = nazwaZd;
                if (success) {
                    if (zdjecia[idZdjecia] == null) //zdjecia numerze idZdjecia jeszcze nie ma w bazie
                    {
                        Zdjecia zdjecie = new Zdjecia(null, filename, zdjeciaList.size());
                        zdjecie.setIdZasobu(zasob);
                        try {
                            managerZasobow.DodajZdjecieZasobu(zdjecie);
                        } catch (Exception e) {
                            request.setAttribute("bazkaOK", "no");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/edytujZasob.jsp");
                            dispatcher.forward(request, response);
                            return;
                        }
                    } else //zdjecie o tym numerze juz jest w bazie
                    {
                        zdjecia[idZdjecia].setPlik(filename);
                        try {
                            managerZasobow.EdytujZdjecie(zdjecia[idZdjecia]);
                        } catch (Exception e) {
                            request.setAttribute("bazkaOK", "no");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/edytujZasob.jsp");
                            dispatcher.forward(request, response);
                            return;
                        }
                    }
                }
            }
        } // while

        List<Zasoby> zasobyUzytkownika;
        try {
            zasobyUzytkownika = managerZasobow.ListujZasobyUzytkownika(uzytkownik.getIdUzytkownika());
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/edytujZasob.jsp");
            dispatcher.forward(request, response);
            return;
        }

        session.setAttribute("zasobyUzytkownika", zasobyUzytkownika);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/zasoby.jsp");
        dispatcher.forward(request, response);
    }
}
