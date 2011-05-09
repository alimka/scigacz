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

/**
 *
 * @author k8
 */
public class RejestracjaServlet extends HttpServlet {

    /**
     * 
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
     * <p>Dodawany jest nowy użytkownik do bazy danych, jeśli nie istnieje użytkownik o podanym loginie lub e-maliu.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    @SuppressWarnings("empty-statement")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");

        ManagerUzytkownika manager = new ManagerUzytkownika();

        String dir = getServletContext().getRealPath("build");
        dir = dir.split("build")[0];
        dir = dir + "/web/obrazki";
        MultipartRequest multiRequest;
        try {
            multiRequest = new MultipartRequest(request, dir, 500000, "UTF-8");
        } catch (Exception e) {
            request.setAttribute("sizeOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/rejestracja.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String login = multiRequest.getParameter("login");
        String haslo = multiRequest.getParameter("haslo");

        String imie = multiRequest.getParameter("imie");
        String nazwisko = multiRequest.getParameter("nazwisko");
        String email = multiRequest.getParameter("email");
        int telefon = 0;
        try {
            telefon = Integer.parseInt(multiRequest.getParameter("telefon"));
        } catch (Exception e) {
        }

        String miasto = multiRequest.getParameter("miasto");
        String wojew = multiRequest.getParameter("wojew");

        String domyslnyAvatar = "av.jpg";
        String avatar = domyslnyAvatar;
        Enumeration files = multiRequest.getFileNames();
        File file = null;
        String name;
        while (files.hasMoreElements()) {
            name = (String) files.nextElement();
            String filename = multiRequest.getFilesystemName(name);
            String type = multiRequest.getContentType(name);
            file = multiRequest.getFile(name);
            if (file == null) {
                continue;
            }
            if (type.contains("image")) // wyslany plik jest obrazkiem
            {
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                long id = date.getTime();
                avatar = "av_" + id;
                File file2 = new File(dir + "/" + avatar);
                boolean success = file.renameTo(file2);
                if (!success) {
                    avatar = domyslnyAvatar;
                }
            }
        }

        Date d = new Date(0);

        int wolne;
        try {
            wolne = manager.SprawdzLoginMail(login, email);
        } catch (Exception e) {
            request.setAttribute("bazkaOK", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/rejestracja.jsp");
            dispatcher.forward(request, response);
            return;
        }        //// 0-wszystko ok; 1-login już jest; 2-email już jest; 3-oba są.
        if (wolne != 0) {
            if (wolne == 1) {
                request.setAttribute("loginZajety", "tak");
            }
            if (wolne == 2) {
                request.setAttribute("emailZajety", "tak");
            }
            if (wolne == 3) {
                request.setAttribute("obaZajete", "tak");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/rejestracja.jsp");
            dispatcher.forward(request, response);
            return;
        }
        Uzytkownicy u = new Uzytkownicy(null, imie, nazwisko, login, haslo, email, telefon, miasto, avatar, 0, d, wojew, "");
             try {
            manager.DodajKonto(u);
               } catch (Exception e) {
                  request.setAttribute("bazkaOK", "no");
                  RequestDispatcher dispatcher = request.getRequestDispatcher("/rejestracja.jsp");
                  dispatcher.forward(request, response);
                   return;
               }
    
     //   } catch (Exception e) {
      //      request.setAttribute("bazkaOK", "no");
      //      RequestDispatcher dispatcher = request.getRequestDispatcher("/rejestracja.jsp");
      //      dispatcher.forward(request, response);
     //       return;
     //   }



        request.setAttribute("rej", "ok");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/rejestracja.jsp");
        dispatcher.forward(request, response);

    }
}
