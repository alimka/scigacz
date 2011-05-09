<%-- 
    Document   : top
    Created on : 2010-01-07, 21:46:21
    Author     : Kasia Ko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            session = request.getSession();
            String authorized = (String) session.getAttribute("authorized");
            if (authorized == null) {
                authorized = "no";
            }
            if (authorized.equals("yes")) {

                String login = (String) session.getAttribute("login");
                if (login != null && login.equals("administrator")) {

                    String param = request.getParameter("m");
                    int m = 0;
                    try {
                        m = Integer.parseInt(param);
                    } catch (Exception e) {
                    }
%>
<div id="top">
    <a href="http://pozycz.fora.pl"target="_blank" class="button" id="butForum">FORUM</a>
    <a href="RobAdminaServlet" class="button" id="butStworz">Stwórz admina</a>
    <a href="wyloguj.jsp" class="button" id="butLogout">Wyloguj się</a>
</div>
<div id="menu">
    <ul>
        <li id="m1_<%=(m == 0) ? "ac" : "pre"%>"><a href="backup.jsp" title="Przywracanie bazy" id="m1">Backup</a></li>
        <li id="m1_<%=(m == 1) ? "ac" : "pre"%>"><a href="kategorie.jsp" title="Kategorie" id="m1">Kategorie</a></li>
    </ul>
</div>
<small>Administrator</small>
<% } else {%>
<jsp:useBean id="uzytkownik" type="ScigaczDB.Uzytkownicy" scope="session" />
<%
        String param = request.getParameter("m");
        int m = 0;
        try {
            m = Integer.parseInt(param);
        } catch (Exception e) {}
%>

<div id="top">
    <a href="http://pozycz.fora.pl"target="_blank" class="button" id="butForum">FORUM</a>
    <form name="search" action="SzukajServlet" method="POST" id="search">
        <input type="text" name="key" value="Szukaj..." onclick="this.value=''" />
        <select name="akcja">
            <option value="zasoby">zasobów</option>
            <option value="uzytkownicy">użytkowników</option>
        </select>
        <input type="submit" name="searchPress" value="OK" class="button"/>
    </form>
    <a href="wyloguj.jsp" class="button" id="butLogout">Wyloguj się</a>
</div>
<div id="menu">
    <ul>
        <% if (uzytkownik.getUprawnienia() == 0) {%>
        <li id="m1_<%=(m == 1) ? "ac" : "pre"%>"><a href="konto.jsp" title="Twoje konto" id="m1">Konto</a></li>
        <li id="m1_<%=(m == 2) ? "ac" : "pre"%>"><a href="WyswietlServlet?z=zasoby" title="Lista zasobów"id="m1">Zasoby</a></li>
        <li id="m1_<%=(m == 4) ? "ac" : "pre"%>"><a href="WyswietlServlet?z=znajomi" title="Znajomi" id="m1">Znajomi</a></li>
        <li id="m1_<%=(m == 3) ? "ac" : "pre"%>"><a href="WyswietlServlet?z=wiadomosci" title="Wiadomości" id="m1">Skrzynka</a></li>
        <li id="m1_<%=(m == 5) ? "ac" : "pre"%>"><a href="WyswietlServlet?z=pozyczone" title="Pożyczone" id="m1">Pożyczone</a></li>
        <% } else if (uzytkownik.getUprawnienia() == 1) {%>
        <li id="m1_<%=(m == 1) ? "ac" : "pre"%>"><a href="konto.jsp" title="Twoje konto" id="m1">Konto</a></li>
        <li id="m1_<%=(m == 2) ? "ac" : "pre"%>"><a href="uzytkownicy.jsp" title="Lista użytkowników" id="m1">Osoby</a></li>
        <li id="m1_<%=(m == 3) ? "ac" : "pre"%>"><a href="WyswietlServlet?z=wiadomosci" title="Wiadomości" id="m1">Skrzynka</a></li>
        <li id="m1_<%=(m == 4) ? "ac" : "pre"%>"><a href="WyswietlServlet?z=nagany" title="Nagany" id="m1">Nagany</a></li>
        <li id="m1_<%=(m == 6) ? "ac" : "pre"%>"><a href="kategorie.jsp" title="Kategorie" id="m1">Kategorie</a></li>
        <li id="m1_<%=(m == 5) ? "ac" : "pre"%>"><a href="backup.jsp" title="Przywracanie bazy" id="m1">Backup</a></li>

        <% }%>
    </ul>
</div>

<small><%=uzytkownik.getImie()%> <%=uzytkownik.getNazwisko()%></small>
<% }
            } // no authorized
            else { %>
<div id="top">
    <a href="http://pozycz.fora.pl"target="_blank" class="button" id="butForum">FORUM</a>
    <a href="login.jsp" class="button" id="butLogout">Zaloguj się</a>
</div>
<div id="menu"></div>
<% } %>