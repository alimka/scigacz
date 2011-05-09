<%-- 
    Document   : wyniki
    Created on : 2010-01-13, 10:14:37
    Author     : Kasia Ko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
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
                    String akcja = (String) session.getAttribute("akcja");
                    boolean a = (akcja.equals((String)"uzytkownicy"))?true:false;

    %>
    <body>
        <div id="bg">
            <div id="main">

                <jsp:include page="top.jsp" />
                <jsp:useBean id="uzytkownicy" type="List<ScigaczDB.Uzytkownicy>" scope="session" />
                <jsp:useBean id="zasoby" type="List<ScigaczDB.Zasoby>" scope="session" />
                <jsp:useBean id="uzytkownik" type="ScigaczDB.Uzytkownicy" scope="session" />
                <jsp:useBean id="managerZnajomych" type="Managery.ManagerZnajomych" scope="session" />
              
                <div id="content">
                    <h2>Wyszukan<%= (!a) ? "e zasoby" : "i użytkownicy"%></h2>
                    <%
                int size;
                if (a) { 
                    size = uzytkownicy.size();
                } else {
                   
                    size = zasoby.size();
                }
                if (size != 0) {
                    %>
                    <table id="tableZasoby">
                        <thead>
                            <tr>
                                <% if (a) {%>
                                <th id="td50">Lp.</th>
                                <th id="td50"></th>
                                <th id="td240">Login</th>
                                <th id="td240">Imię i nazwisko</th>
                                <th id="td240">Miejscowość</th>
                                <th id="td120">Województwo</th>
                                <% } else {%>
                                <th id="td50">Lp.</th>
                                <th id="td50"></th>
                                <th id="td240">Nazwa</th>
                                <th id="td120">Dostępność</th>
                                <th id="td240">Właściciel</th>
                                <th id="td240">Kategoria</th>
                                <% }
                                if (uzytkownik.getUprawnienia()==1) {%>
                                <th id="td100"></th>
                                <% } %>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int j = 0;
                                for (int i = 0; i < size; i++) {
                                    String img="";
                                    ScigaczDB.Uzytkownicy osoba = null;
                                    ScigaczDB.Zasoby zasob = null;
                                    if (a) {
                                        osoba = uzytkownicy.get(i);
                                        j++;
                                        try {
                                            img = osoba.getAvatar();
                                        } catch (Exception e) {}
                                    } else {
                                        zasob = zasoby.get(i);
                                        
                                        int id1 = (int)zasob.getIdUzytkownika().getIdUzytkownika();
                                        int id2 = (int)uzytkownik.getIdUzytkownika();
                                        int jakBliscy = (int)managerZnajomych.JakBliscy(id1, id2);
                                        boolean mozna = false;
                                        if ((int)uzytkownik.getUprawnienia() == 1) {mozna = true;}
                                        if (id1 == id2) { mozna = true; }
                                        else if((int)zasob.getDostepnosc() <= 1) {
                                            if ((int)zasob.getDlakogo() == 0) { mozna = true; }
                                            if ((int)zasob.getDlakogo() == 1 && (jakBliscy >= 0)) { mozna = true; }
                                            if ((int)zasob.getDlakogo() == 2 && (jakBliscy == 1)) { mozna = true; }
                                        }
                                        if (! mozna) { continue; }

                                        j++;
                                        try {
                                            img = ((ScigaczDB.Zdjecia) zasob.getZdjeciaList().get(0)).getPlik();
                                        } catch (Exception e) {}
                                    }
                            %>
                            <tr>
                                <td><%= j%>.</td>
                                <td class="thumb"><img src="obrazki/<%=img%>" alt=""/></td>
                                <td><% if (a) { %>
                                    <a href="OsobaServlet?id=<%= osoba.getIdUzytkownika() %>"><%= osoba.getLogin() %></a>
                                    <% } else { %>
                                    <a href="RzeczServlet?id=<%= zasob.getIdZasobu() %>"><%= zasob.getNazwa() %></a>
                                    <% } %></td>
                                <td><% if (a) { %>
                                    <%= osoba.getImie() %> <%= osoba.getNazwisko() %>
                                    <% } else { %>
                                    <%= (zasob.getDostepnosc()==0)?"dostępny":"wypożyczony" %>
                                    <% } %><!-- ((ScigaczDB.Kategorie)zasob.getIdKategorii()).getNazwa() --></td>
                                <td><% if (a) { %>
                                    <%= osoba.getMiasto() %>
                                    <% } else { %>
                                    <a href="OsobaServlet?id=<%= zasob.getIdUzytkownika().getIdUzytkownika() %>"><%= zasob.getIdUzytkownika().getLogin() %></a>
                                    <% } %><!-- zasob.getWypozyczeniaList().size() == 0 ? "" : ((ScigaczDB.Wypozyczenia)zasob.getWypozyczeniaList().get(0)).getIdWypozyczacza().getLogin() --></td>
                                <td><% if (a) { %>
                                    <%= osoba.getWojewodztwo() %>
                                    <% } else { %>
                                    <%= ((ScigaczDB.Kategorie)zasob.getIdKategorii()).getNazwa() %>
                                    <% } %></td>
                               <% 
                                if (uzytkownik.getUprawnienia()==1) {
                                    if (a) { %>                                
                                <td class="editUser">
                                    <a href="#?id=<%= osoba.getIdUzytkownika()%>">
                                        <% if (true) { %> <!-- trzeba dodac warunek -->
                                        <img src="obrazki/zablokuj.png" alt="">
                                        <% } else { %>
                                        <img src="obrazki/odblokuj.png" alt="">
                                        <% } %>
                                    </a>
                                    <a href="#?id=<%= osoba.getIdUzytkownika()%>">
                                        <img src="obrazki/usun.png" alt="">
                                    </a>
                                </td>
                                    <% } else { %>
                                <td class="editUser">
                                    <a href="#?id=<%= zasob.getIdZasobu()%>">
                                        <img src="obrazki/usun.png" alt="">
                                    </a>
                                </td>
                                <%     }
                                   }%>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                    <% if (j==0) {%>
                    <div id="info">Nie znaleziono pasujących <%= (a)?"użytkowników":"zasobów"%></div>
                    <% } else if (uzytkownik.getUprawnienia()==1) { %>
                    <div id="legenda">
                        <h3>Legenda</h3>
                        <img src="obrazki/zablokuj.png" alt=""><span>zablokuj</span><br>
                        <img src="obrazki/odblokuj.png" alt=""><span>odblokuj</span><br>
                        <img src="obrazki/usun.png" alt=""><span>usuń</span><br>
                    </div>
                    <% } %>

                    <!--<div id="strony">
                        Poprzednia
                        1
                        <a href="#">2</a>
                        <a href="#">Następna</a>
                    </div>-->
                    <% } else {%>
                    <div id="info">Nie znaleziono pasujących <%= (a)?"użytkowników":"zasobów"%></div>
                    <% }%>
                    <div id="blank"></div>
                </div>
                <jsp:include page="foot.jsp" />
            </div>
        </div>
    </body>
    <%
                } else {%>
    <jsp:forward page="login.jsp"><jsp:param name="uwaga" value="autoryzacja" /></jsp:forward>
    <%              }%>
</html>
