<%-- 
    Document   : pozyczone
    Created on : 2010-01-13, 23:31:11
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
    %>
    <body>
        <div id="bg">
            <div id="main">
                <jsp:include page="top.jsp?m=5" />
                <jsp:useBean id="wypozyczone" type="List<ScigaczDB.Wypozyczenia>" scope="session" />
                <jsp:useBean id="udostepnione" type="List<ScigaczDB.Wypozyczenia>" scope="session" />
                <div id="content">
                    <h1>Pożyczone</h1>
                    <%
                        int size = wypozyczone.size();
                        if (size != 0) {
                    %>
                    <h3>Pożyczone przeze mnie</h3>
                    <% String wyslane = (String) request.getAttribute("zwrot");
                        if (wyslane != null) {
                    %>
                    <div id="info">Wiadomość o oddaniu została wysłana do użytkownika udostępniającego zasób.</div>
                    <% } %>
                    <form action="#" method="POST">
                    <table id="tableZasoby">
                        <thead>
                            <tr>
                                <th id="td50">Lp.</th>
                                <th id="td50"></th>
                                <th id="td240">Nazwa</th>
                                <th id="td120">Udostępniacz</th>
                                <th id="td120">Od</th>
                                <th id="td120">Do</th>
                                <th id="td30"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (int i = 0; i < size; i++) {
                                    ScigaczDB.Wypozyczenia pozyczka = wypozyczone.get(i);
                                    ScigaczDB.Zasoby zasob = pozyczka.getIdZasobu();
                                    String img = "";
                                    try { img = ((ScigaczDB.Zdjecia)zasob.getZdjeciaList().get(0)).getPlik(); }
                                    catch (Exception e) {}
                            %>
                            <tr>
                                <td><%= i + 1%>.</td>
                                <td class="thumb"><% if(img!="") { %><img alt="" src="obrazki/<%=img%>"/><%}%></td>
                                <td><a href="RzeczServlet?id=<%= zasob.getIdZasobu()%>"><%= zasob.getNazwa()%></a></td>
                                <td><%= zasob.getIdUzytkownika().getLogin() %></td>
                                <td><%= pozyczka.getDataWypozyczenia() %></td>
                                <td><%= pozyczka.getDataZwrotu() %></td>
                                <td class="edit">
                                        <% if (zasob.getDostepnosc() != 3) {%>
                                         <a href="OddajZasobServlet?id=<%= pozyczka.getIdWypozyczenia() %>">
                                        <img src="obrazki/oddaj.png" alt="oddaj"/></a>
                                        <% } %>
                                </td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                    </form>
                        
                    <div id="blank"></div>
                    <div id="legenda">
                        <h3>Legenda</h3>
                        <img src="obrazki/oddaj.png" alt=""><span>oddaj zasób</span><br>
                    </div>

                    <!--<div id="strony">
                    <a href="UsunZasobServlet" class="button" id="butUsun">usuń zaznaczone</a>
                        Poprzednia
                        1
                        <a href="#">2</a>
                        <a href="#">Następna</a>
                    </div>-->
                    <% } else {%>
                    <div id="info">Brak pożyczeń</div>
                    <% } %>

                    <div id="blank"></div>
                    <%
                        size = udostepnione.size();
                        if (size != 0) {
                    %>
                    <h3>Pożyczone ode mnie</h3>
                    <form action="#" method="POST">
                    <table id="tableZasoby">
                        <thead>
                            <tr>
                                <th id="td50">Lp.</th>
                                <th id="td50"></th>
                                <th id="td240">Nazwa</th>
                                <th id="td120">Wypożyczający</th>
                                <th id="td120">Od</th>
                                <th id="td120">Do</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (int i = 0; i < size; i++) {
                                    ScigaczDB.Wypozyczenia pozyczka = udostepnione.get(i);
                                    ScigaczDB.Zasoby zasob = pozyczka.getIdZasobu();
                                    String img = "";
                                    try { img = ((ScigaczDB.Zdjecia)zasob.getZdjeciaList().get(0)).getPlik(); }
                                    catch (Exception e) {}
                            %>
                            <tr>
                                <td><%= i + 1%>.</td>
                                <td class="thumb"><% if(img!="") { %><img alt="" src="obrazki/<%=img%>"/><%}%></td>
                                <td><a href="RzeczServlet?id=<%= zasob.getIdZasobu()%>"><%= zasob.getNazwa()%></a></td>
                                <td><a href="OsobaServlet?id=<%=  pozyczka.getIdWypozyczacza().getIdUzytkownika() %>"><%= pozyczka.getIdWypozyczacza().getLogin() %></a></td>
                                <td><%= pozyczka.getDataWypozyczenia() %></td>
                                <td><%= pozyczka.getDataZwrotu() %></td>
                                <td class="edit">
                                    <!--<a href="#?id=<%-- pozyczka.getIdWypozyczenia() --%>">
                                        <img src="obrazki/oddaj.png" alt="oddaj"/></a>-->
                                </td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                    <input type="submit" value="usuń zaznaczone" class="button" id="butUsun" />
                    </form>

                    <!-- <div id="blank"></div>
                    <div id="legenda">
                        <h3>Legenda</h3>
                        <img src="obrazki/oddaj.png" alt=""><span>oddaj zasób</span><br>
                    </div> -->

                    <!--<div id="strony">
                    <a href="UsunZasobServlet" class="button" id="butUsun">usuń zaznaczone</a>
                        Poprzednia
                        1
                        <a href="#">2</a>
                        <a href="#">Następna</a>
                    </div>-->
                    <% } else {%>
                    <div id="info">Brak wypożyczonych</div>
                    <% } %>
                    <div id="blank"></div>
                </div>
                <jsp:include page="foot.jsp" />
            </div>
        </div>
    </body>
    <%
                    } else { %>
    <jsp:forward page="login.jsp"><jsp:param name="uwaga" value="autoryzacja" /></jsp:forward>
    <%              }%>
</html>
