<%--
    Document   : osoba
    Created on : 2010-01-03, 22:55:11
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
                    String czyZnajomy = (String)session.getAttribute("czyZnajomy");
    %>
    <body>
        <div id="bg">
            <div id="main">
                <%  //sprawdzenie czy wywolane przez servlet
                                    try {
                                        if (session.getAttribute("ziomek") == null) {
                                            throw new Exception();
                                        }
                                    } catch (Exception e) {%>
                <div id="info"><h1>Podana strona nie istnieje.</h1></div>
                <div id="blank"></div>

                <a href="javascript: history.go(-1)">powrót</a>
                <% return;
                                    }
                %>
                <jsp:include page="top.jsp" />
                <jsp:useBean id="ziomek" type="ScigaczDB.Uzytkownicy" scope="session" />
                <jsp:useBean id="uzytkownik" type="ScigaczDB.Uzytkownicy" scope="session" />
                <jsp:useBean id="opinieZiomka" type="List<ScigaczDB.Opinie>" scope="session" />
                <jsp:useBean id="zasobyZiomka" type="List<ScigaczDB.Zasoby>" scope="session" />
                <jsp:useBean id="managerZnajomych" type="Managery.ManagerZnajomych" scope="session" />
                <div id="content">
                    <h1><%= ziomek.getLogin()%></h1>
                    <div id="av">
                        <img src="obrazki/<%= ziomek.getAvatar()%>" alt="" class="avatar" />
                    </div>
                    <div id="opis">
                        <h3><%= ziomek.getLogin()%></h3>
                        <h3><%= ziomek.getImie()%> <%= ziomek.getNazwisko()%></h3>
                        <h3><%= ziomek.getEmail()%></h3>
                        <h3><%= ziomek.getMiasto()%> (<%= ziomek.getWojewodztwo()%>)</h3>
                        <% if (ziomek.getTelefon()!=0) { %><h3>tel. <%= ziomek.getTelefon()%></h3><% } %>
                    </div>
                        <% if ((int)(ziomek.getIdUzytkownika())!=uzytkownik.getIdUzytkownika()) { %>
                    <div id="przyciski">
                        <a href="wiadom.jsp" onclick="window.open('wiadom.jsp?id=<%= ziomek.getIdUzytkownika()%>', '', 'menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=no,resizable=no,fullscreen=no,channelmode=no,width=400,height=320').focus(); return false">
                            <input type="submit" value="wyślij wiadomość" name="wiadom" class="button" id="butWiadom" /></a>
                        <% if (czyZnajomy=="nie") {
                        String zaproszenieWyslane = (String) session.getAttribute("zaproszenieWyslane"+ziomek.getIdUzytkownika());
                        if (zaproszenieWyslane != null) { %>
                            Prośba o znajomość wysłana.
                        <%
                            session.removeAttribute("zaproszenieWyslane");
                        }
                        else { %>
                        <a href="ZaprosZnajomegoServlet?id=<%= ziomek.getIdUzytkownika()%>"><input type="submit" value="zaproś do znajomych" name="zapros" class="button" id="butZapros" /></a>
                        <% }
                            } %>
                        <a href="wiadom.jsp" onclick="window.open('wiadom.jsp?id=<%= ziomek.getIdUzytkownika()%>&skarga=y', '', 'menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=no,resizable=no,fullscreen=no,channelmode=no,width=400,height=320').focus(); return false">
                            <input type="submit" value="zgłoś nadużycie" name="zglos" class="button" id="butZglos" /></a>

                        <!-- narazie jest w zlym miejscu, powinno byc w zakladce wypozyczone -->
                        <a href="opinia.jsp" onclick="window.open('opinia.jsp?id=<%= ziomek.getIdUzytkownika()%>', '', 'menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=no,resizable=no,fullscreen=no,channelmode=no,width=400,height=320').focus(); return false">
                            <input type="submit" value="wystaw opinię" name="opinia" class="button" id="butOpinia" /></a>
                    </div>
                    <% } %>
                    <div id="blank"></div>
                    <%
                                        int size = zasobyZiomka.size();
                                        if (size != 0) {
                    %>
                    <h2>Zasoby</h2>
                    <table id="zasobyUsera">
                        <thead>
                            <tr>
                                <th id="td50">Lp.</th>
                                <th id="td140"></th>
                                <th id="td500">Nazwa</th>
                                <th id="td300">Stan</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int j = 0;
                                for (int i = 0; i < size; i++) {
                                    ScigaczDB.Zasoby zasob = (ScigaczDB.Zasoby) (zasobyZiomka.get(i));

                                    int id1 = (int)zasob.getIdUzytkownika().getIdUzytkownika();
                                    int id2 = (int)uzytkownik.getIdUzytkownika();
                                    int jakBliscy = (int)managerZnajomych.JakBliscy(id1, id2);
                                    boolean mozna = false;
                                    if ((int)uzytkownik.getUprawnienia() == 1) {mozna = true;}
                                    if (id1 == id2) { mozna = true; }
                                    else  {
                                        if ((int)zasob.getDlakogo() == 0) { mozna = true; }
                                        if ((int)zasob.getDlakogo() == 1 && (jakBliscy >= 0)) { mozna = true; }
                                        if ((int)zasob.getDlakogo() == 2 && (jakBliscy == 1)) { mozna = true; }
                                    }
                                    if (! mozna) { continue; }

                                    j++;
                                    String img="";
                                    try {
                                        img = zasob.getZdjeciaList().get(0).getPlik();
                                    } catch (Exception e) {}
                            %>
                            <tr>
                                <td><%= j%>.</td>
                                <td class="thumb"><% if (img != "") {%><img alt="" src="obrazki/<%=img%>"/><%}%></td>
                                <td><a href="RzeczServlet?id=<%= zasob.getIdZasobu()%>"><%= zasob.getNazwa()%></a></td>
                                <%
                                int d = zasob.getDostepnosc();
                                String dost = "Wypożyczony";
                                if (d == 0)
                                    dost = "Dostępny";
                                if (d == 2)
                                    dost = "Oczekujący";
                                %>
                                <td><%= dost%></td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                    <% if (j==0) {%>
                    <h2>Brak zasobów</h2>
                    <% }
                        } else {%>
                    <h2>Brak zasobów</h2>
                    <% }
                                        size = opinieZiomka.size();
                                        if (size != 0) {
                    %>
                    <h2>Opinie</h2>
                    <table id="zasobyUsera">
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
                                                                        for (int i = 0; i < size; i++) {
                                                                            ScigaczDB.Opinie opinia = (ScigaczDB.Opinie) (opinieZiomka.get(i));
                            %>
                            <tr>
                                <td><%= i + 1%>.</td>
                                <td><a href="OsobaServlet?id=<%= opinia.getIdOpiniujacego().getIdUzytkownika()%>">
                                        <%= opinia.getIdOpiniujacego().getLogin()%></a></td>
                                <td><%= opinia.getTresc()%></td>
                                <td><%= opinia.getData()%></td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                    <% } else {%>
                    <h2>Brak opinii</h2>
                    <% }%>

                </div>
                <jsp:include page="foot.jsp" />
            </div>
        </div>
    </body>
    <%
                        session.removeAttribute("ziomek");
                    } else {%>
    <jsp:forward page="login.jsp"><jsp:param name="uwaga" value="autoryzacja" /></jsp:forward>
    <%              }%>
</html>
