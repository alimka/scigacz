<%-- 
    Document   : uzytkownicy
    Created on : 2010-01-16, 16:37:36
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
                    Managery.ManagerUzytkownika mu = (Managery.ManagerUzytkownika) session.getAttribute("managerUzytkownika");
                    List<ScigaczDB.Uzytkownicy> uzytkownicy = mu.PokazUzytkownikow();

    %>
    <body>
        <div id="bg">
            <div id="main">
                <jsp:include page="top.jsp?m=2" />
                <jsp:useBean id="uzytkownik" type="ScigaczDB.Uzytkownicy" scope="session" />
                <div id="content">
                    <%
                                        if (uzytkownik.getUprawnienia() == 1) {
                    %>
                    <h2>Lista użytkowników</h2>
                    <%
                                                int size = uzytkownicy.size();
                                                if (size != 0) {
                    %>
                    <table id="tableZasoby">
                        <thead>
                            <tr>
                                <th id="td50">Lp.</th>
                                <th id="td50"></th>
                                <th id="td240">Login</th>
                                <th id="td240">Imię i nazwisko</th>
                                <th id="td240">Miejscowość</th>
                                <th id="td120"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                                    for (int i = 0; i < size; i++) {
                                                        String img = "";
                                                        ScigaczDB.Uzytkownicy osoba = null;
                                                        ScigaczDB.Zasoby zasob = null;
                                                        osoba = uzytkownicy.get(i);
                                                        try {
                                                            img = osoba.getAvatar();
                                                        } catch (Exception e) {
                                                        }
                            %>
                            <tr>
                                <td><%= i + 1%>.</td>
                                <td class="thumb"><img src="obrazki/<%=img%>" alt=""/></td>
                                <td><a href="OsobaServlet?id=<%= osoba.getIdUzytkownika()%>"><%= osoba.getLogin()%></a></td>
                                <td><%= osoba.getImie()%> <%= osoba.getNazwisko()%></td>
                                <td><%= osoba.getMiasto()%> (<%= osoba.getWojewodztwo()%>)</td>
                                <td class="editUser">                                    
                                    <%
                                    Date dataBlokady = new Date(0);
                                    Date blokadaU = osoba.getDatablokada();
                                    if (blokadaU.compareTo(dataBlokady) == 0) {%> <!-- trzeba dodac warunek -->
                                        <a href="ZablokujServlet?id=<%= osoba.getIdUzytkownika()%>">
                                            <img src="obrazki/zablokuj.png" alt="">
                                        </a>
                                    <% } else {%>
                                    <a href="ZablokujServlet?id=<%= osoba.getIdUzytkownika()%>">
                                        <img src="obrazki/odblokuj.png" alt="">
                                    </a>
                                    <% }%>
                                    <a href="UsunUzytkownika?id=<%= osoba.getIdUzytkownika()%>">
                                        <img src="obrazki/usun.png" alt="">
                                    </a>
                                </td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                    <div id="legenda">
                        <h3>Legenda</h3>
                        <img src="obrazki/zablokuj.png" alt=""><span>zablokuj użytkownika</span><br>
                        <img src="obrazki/odblokuj.png" alt=""><span>odblokuj użytkownika</span><br>
                        <img src="obrazki/usun.png" alt=""><span>usuń użytkownika</span><br>
                    </div>

                    <!--<div id="strony">
                        Poprzednia
                        1
                        <a href="#">2</a>
                        <a href="#">Następna</a>
                    </div>-->
                    <% } else {%>
                    <div id="uwaga">Brak użytkowników</div>
                    <% }
                                            } else {%>
                    <div id="uwaga">Nie masz odpowiednich uprawnień do przeglądania tej strony.</div>
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

