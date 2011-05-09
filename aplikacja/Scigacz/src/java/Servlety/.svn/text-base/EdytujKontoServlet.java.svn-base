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
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author k8
 */
public class EdytujKontoServlet extends HttpServlet {

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
     * <p>Edycja danych użytkownika.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String encoding = request.getCharacterEncoding();
        request.setCharacterEncoding("UTF-8");
        encoding = request.getCharacterEncoding();
        processRequest(request, response);

        HttpSession session = request.getSession();
        Uzytkownicy uzytkownik = (Uzytkownicy) session.getAttribute("uzytkownik");
        ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
        if (managerUzytkownika == null) {
            managerUzytkownika = new ManagerUzytkownika();
        }

        String dir = getServletContext().getRealPath("build");
        dir = dir.split("build")[0];
        dir = dir + "/web/obrazki";
        MultipartRequest multiRequest;
        try {
            multiRequest = new MultipartRequest(request, dir, 500000, "UTF-8");

        } catch (Exception e) {
            request.setAttribute("sizeOK", "no");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/edytujKonto.jsp");

            dispatcher.forward(request, response);
            return;
        }

        String haslo = multiRequest.getParameter("psswd");
        String noweHaslo = multiRequest.getParameter("newpsswd");

//        multiRequest.
        String imie = multiRequest.getParameter("imie");
        String nazwisko = multiRequest.getParameter("nazwisko");
        String miasto = multiRequest.getParameter("miasto");
        String email = multiRequest.getParameter("email");
        String wojewodztwo = multiRequest.getParameter("wojew");

        int telefon = 0;
        try {
            telefon = Integer.parseInt(multiRequest.getParameter("telefon"));
        } catch (Exception e) {
        }



        String akcja = multiRequest.getParameter("edytuj");
        String przekierowanie = "/index.jsp";

        if (akcja.equals("edytuj dane")) {
            uzytkownik.setImie(imie);
            uzytkownik.setNazwisko(nazwisko);
            uzytkownik.setMiasto(miasto);
            uzytkownik.setWojewodztwo(wojewodztwo);
            uzytkownik.setEmail(email);
            uzytkownik.setTelefon(telefon);
            Enumeration files = multiRequest.getFileNames();
            while (files.hasMoreElements()) {
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
                    String avatar = "av_" + id;
                    File file2 = new File(dir + "/" + avatar);
                    boolean success = file.renameTo(file2);
                    if (success) {
                        File staryAvatar = new File(dir + "/" + uzytkownik.getAvatar());
                        staryAvatar.delete();
                        uzytkownik.setAvatar(avatar);
                    }
                }
            }
            try {
                managerUzytkownika.EdytujUzytkownika(uzytkownik);
            } catch (Exception e) {
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/rejestracja.jsp");
                dispatcher.forward(request, response);
                return;
            }
        } else //edytuj haslo
        {
            String hasloMD5 = null;
            try {
                hasloMD5 = managerUzytkownika.Szyfruj(haslo);
            } catch (Exception e) {
                request.setAttribute("bazkaOK", "no");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/rejestracja.jsp");
                dispatcher.forward(request, response);
                return;
            }
            if (hasloMD5.equals(uzytkownik.getHaslo())) {
                hasloMD5 = null;
                try {
                    hasloMD5 = managerUzytkownika.Szyfruj(noweHaslo);
                } catch (Exception e) {
                    request.setAttribute("bazkaOK", "no");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/rejestracja.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
                uzytkownik.setHaslo(hasloMD5);
                try {
                    managerUzytkownika.EdytujUzytkownika(uzytkownik);
                } catch (Exception e) {
                    request.setAttribute("bazkaOK", "no");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/rejestracja.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            } else {
                przekierowanie = "/edytujKonto.jsp";
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(przekierowanie);
        dispatcher.forward(request, response);

    }
}
