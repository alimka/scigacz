<%--
    Document   : konto
    Created on : 2009-12-14, 21:48:18
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
                <jsp:include page="top.jsp?m=1" />
                <jsp:useBean id="uzytkownik" type="ScigaczDB.Uzytkownicy" scope="session" />
                <jsp:useBean id="opinieUzytkownika" type="List<ScigaczDB.Opinie>" scope="session" />
                <div id="content">
                    <% if(request.getAttribute("bazkaOK")!=null) { %>
                    <div id="uwaga">Wystąpił błąd bazy danych. Przepraszamy.</div> <% } %>
                    <h1>Mój profil</h1>
                    <div id="av">
                        <img src="obrazki/<%= uzytkownik.getAvatar()%>" alt="" class="avatar" />
                    </div>
                    <div id="opis">
                        <h3><%= uzytkownik.getLogin() %></h3>
                        <h3><%= uzytkownik.getImie() %> <%= uzytkownik.getNazwisko() %></h3>
                        <h3><%= uzytkownik.getEmail() %></h3>
                        <h3><%= uzytkownik.getMiasto() %> (<%= uzytkownik.getWojewodztwo() %>)</h3>
                        <% if (uzytkownik.getTelefon()!=0) { %><h3>tel. <%= uzytkownik.getTelefon()%></h3><% } %>
                    </div>
                    <div id="przyciski">
                        <a href="edytujKonto.jsp"><input type="submit" value="edytuj dane" name="edytujKonto" class="button" id="butEdytuj" /></a>
                    </div>
                    <div id="blank"></div>
                    <%
                    int size = opinieUzytkownika.size();
                    if (size!=0) {
                        %>
                    <table>
                        <thead>
                            <tr>
                                <th id="td50">Lp.</th>
                                <th id="td140">Osoba</th>
                                <th id="td500">Opinia</th>
                                <th id="td100">Data</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                            for(int i=0; i < size; i++) {
                                ScigaczDB.Opinie opinia = opinieUzytkownika.get(i);
                            %>
                            <tr>
                                <td><%= i+1 %>.</td>
                                <td><a href="OsobaServlet?id=<%= opinia.getIdOpiniujacego().getIdUzytkownika() %>"><%= opinia.getIdOpiniujacego().getLogin() %></a></td>
                                <td><%= opinia.getTresc() %></td>
                                <td><%= opinia.getData() %></td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                    <!--
                    <div id="strony">
                        Poprzednia
                        1
                        <a href="#">2</a>
                        <a href="#">Następna</a>
                    </div>-->
                    <% } else { %>
                    <div id="info">Brak opini</div>
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
