<%-- 
    Document   : rzecz
    Created on : 2010-01-03, 23:10:19
    Author     : Kasia Ko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ścigacz - serwis wyczesany w kosmos</title>
        <link rel="stylesheet" type="text/css" href="base.css">
    </head>
    <%
                session = request.getSession();
                String authorized = (String) session.getAttribute("authorized");
                if (authorized == null) {
                    authorized = "no";
                }
                if (authorized.equals("yes")) {
    %>
    <body>
        <div id="bg">
            <div id="main">
                <jsp:include page="top.jsp" />
                <jsp:useBean id="zasob" type="ScigaczDB.Zasoby" scope="session" />
                <jsp:useBean id="uzytkownik" type="ScigaczDB.Uzytkownicy" scope="session" />
                <jsp:useBean id="managerZnajomych" type="Managery.ManagerZnajomych" scope="session" />
                <div id="content">
                    <%
                        int id1 = (int)zasob.getIdUzytkownika().getIdUzytkownika();
                        int id2 = (int)uzytkownik.getIdUzytkownika();
                        int jakBliscy = (int)managerZnajomych.JakBliscy(id1, id2);
                        boolean mozna = false;
                        if ((int)uzytkownik.getUprawnienia() == 1) {mozna = true;}
                        if (id1 == id2) { mozna = true; }
                        if ((int)zasob.getDlakogo() == 0) { mozna = true; }
                        if ((int)zasob.getDlakogo() == 1 && (jakBliscy >= 0)) { mozna = true; }
                        if ((int)zasob.getDlakogo() == 2 && (jakBliscy == 1)) { mozna = true; }
                        if (! mozna) {
                    %>
                    <div id="uwaga">Nie masz dostępu do tego zasobu</div>
                    <% } else { %>
                    <h1>Zasób nr <%= zasob.getIdZasobu()%></h1>
                    <div id="zdjecia">
                        <%
                                            String img0,img1,img2;
                                            try {
                                                img0 = zasob.getZdjeciaList().get(0).getPlik();
                                            } catch (Exception e) {
                                                img0 = "brak.png";
                                            }
                                            try {
                                                img1 = zasob.getZdjeciaList().get(1).getPlik();
                                            } catch (Exception e) {
                                                img1 = "brak.png";
                                            }
                                            try {
                                                img2 = zasob.getZdjeciaList().get(2).getPlik();
                                            } catch (Exception e) {
                                                img2 = "brak.png";
                                            }
                        %>
                        <img src="obrazki/<%= img0%>" alt="" class="avatar" />

                        <img src="obrazki/<%= img0%>" alt="" class="mini" />
                        <img src="obrazki/<%= img1%>" alt="" class="mini" />
                        <img src="obrazki/<%= img2%>" alt="" class="mini" />
                    </div>
                    <div id="opisZasobu">
                        <label>Nazwa</label><%= zasob.getNazwa()%><br>
                        <label>Kategoria</label><%= zasob.getIdKategorii().getNazwa()%><br>
                        <label>Osoba udostępniająca</label><a href="OsobaServlet?id=<%= zasob.getIdUzytkownika().getIdUzytkownika() %>">
                            <%= zasob.getIdUzytkownika().getLogin()%></a><br>
                        <label>Czas na wypożyczenie</label><%= zasob.getCzasPrzetrzymywania()%> dni<br>
                        <label>Opis</label><%= zasob.getOpis()%>
                    </div>
                    <div id="przyciski">
                        <% if (zasob.getIdUzytkownika().getIdUzytkownika().equals(uzytkownik.getIdUzytkownika())) { %>
                        <a href="RzeczServlet?id=<%= zasob.getIdZasobu()%>&edit=y">
                            <input type="submit" value="edytuj" name="edytuj" class="button" id="butWypozycz" /></a>
                        <% } else {
                        String prosbaWyslana = (String) session.getAttribute("prosbaWyslana");
                        if (prosbaWyslana != null) { %>
                            Prośba o wypożyczenie wysłana.
                        <%
                            session.removeAttribute("prosbaWyslana");
                        }
                        else {
                            int dost = zasob.getDostepnosc();
                            if (dost == 1)
                            { %>
                            Zasób jest w tym momencie wypożyczony.
                            <%
                            }
                            else if (dost == 2)
                            { %>
                            Zasób oczekuje na akceptację wypożyczenia.
                            <%
                            }
                            else if (dost == 3)
                            { %>
                            Zasób oczekuje na potwierdzenie zwrotu.
                            <%
                            }
                            else
                            { %>

                        <a href="WypozyczServlet?z=<%= zasob.getIdZasobu()%>"><input type="submit" value="wypożycz" name="wypozycz" class="button" id="butWypozycz" /></a>
                        
                        <% } } }%>
                    </div>
                    <% } %>
                    <div id="blank"></div>
                </div>
                <jsp:include page="foot.jsp" />
            </div>
        </div>
    </body>
    <%                    } else {%>
    <jsp:forward page="login.jsp"><jsp:param name="uwaga" value="autoryzacja" /></jsp:forward>
    <%              }%>
</html>
