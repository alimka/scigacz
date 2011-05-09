<%-- 
    Document   : skrzynka1
    Created on : 2010-01-16, 21:56:00
    Author     : Kasia Ko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String w = request.getParameter("w");
    int wiadom;
    if (w != null) {
        String sys = request.getParameter("sys");
        wiadom = Integer.parseInt(w);
%>
<div id="menu2">
    <ul>
        <li id="m_pre"><a href="WyswietlServlet?z=wiadomosci&s=1" id="m">Odebrane</a></li>
        <li id="m_pre"><a href="WyswietlServlet?z=wiadomosci&s=2" id="m">Wysłane</a></li>
        <li id="m_pre"><a href="wiadom.jsp" id="m" onclick="window.open('wiadom.jsp', 'nazwa', 'menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=no,resizable=no,fullscreen=no,channelmode=no,width=400,height=320').focus(); return false">Napisz</a></li>
    </ul>
</div>
<jsp:useBean id="managerSkrzynki" type="Managery.ManagerSkrzynki" scope="session" />
<jsp:useBean id="uzytkownik" type="ScigaczDB.Uzytkownicy" scope="session" />
<div id="skrzynka">
    <div id ="przyciski">
        <a href="javascript: history.go(-1)" id="butPowrot" class="button">powrót</a><br>
        <% 
            ScigaczDB.Wiadomosci wiadomosc = managerSkrzynki.PokazWiadomosc(wiadom);
            int uid = -1;
            if (wiadomosc.getIdNadawcy().getIdUzytkownika()==uzytkownik.getIdUzytkownika())
                uid = wiadomosc.getIdOdbiorcy().getIdUzytkownika();
            else
                uid = wiadomosc.getIdNadawcy().getIdUzytkownika();

        %>
        <a href="wiadom.jsp" id="butPowrot" class="button" onclick="window.open('wiadom.jsp?id=<%= uid%>', 'nazwa', 'menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=no,resizable=no,fullscreen=no,channelmode=no,width=400,height=320').focus(); return false">
            ODPISZ</a>
        <%  %></div>
    <div id="wiadomosc">
        <h2>Wiadomość</h2>
        <%
                                            if (sys == null) {
                                                ScigaczDB.Wiadomosci wiadomo = managerSkrzynki.PokazWiadomosc(wiadom);
                                                
                                                managerSkrzynki.OznaczJakoPrzeczytana(wiadomo.getIdWiadomosci());
        %>
        <p><label>Od:</label><%= wiadomo.getIdNadawcy().getLogin()%></p>
        <p><label>Do:</label><%= wiadomo.getIdOdbiorcy().getLogin()%></p>
        <p><label>Temat:</label><%= wiadomo.getTemat()%></p>
        <p><label>Data:</label><%= wiadomo.getData()%></p>
        <p id="tresc"><%= wiadomo.getTresc()%></p>
        <%
                                            } else if (sys.equals("y")) {
                                                ScigaczDB.Systemowe wiadomo = managerSkrzynki.PokazWiadomoscSystemowa(wiadom);
                                                managerSkrzynki.OznaczJakoPrzeczytanaSys(wiadomosc.getIdWiadomosci());
        %>
        <p><label>Od:</label>Czak</p>
        <p><label>Do:</label><%= wiadomo.getIdOdbiorcy().getLogin()%></p>
        <p><label>Temat:</label><%= wiadomo.getTemat()%></p>
        <p><label>Data:</label><%= wiadomo.getData()%></p>
        <div id="tresc">
            <%= wiadomo.getTresc()%>
        </div>
        <%
                                                        }%>
    </div>
</div>
<div id="blank"></div>

<%
    } else {
        String s = request.getParameter("s");
        int skrz;
        if (s != null) {
            skrz = Integer.parseInt(s);
        } else {
            skrz = 1;
        }
%>
<div id="menu2">
    <ul>
        <% if (skrz < 2) {%>
        <li id="m_ac"><a href="WyswietlServlet?z=wiadomosci&s=1" id="m">Odebrane</a></li>
        <li id="m_pre"><a href="WyswietlServlet?z=wiadomosci&s=2" id="m">Wysłane</a></li>
        <% } else if (skrz == 2) {%>
        <li id="m_pre"><a href="WyswietlServlet?z=wiadomosci&s=1" id="m">Odebrane</a></li>
        <li id="m_ac"><a href="WyswietlServlet?z=wiadomosci&s=2" id="m">Wysłane</a></li>
        <% }%>
        <li id="m_pre"><a href="wiadom.jsp" id="m" onclick="window.open('wiadom.jsp', 'nazwa', 'menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=no,resizable=no,fullscreen=no,channelmode=no,width=400,height=320').focus(); return false">Napisz</a></li>
    </ul>
</div>
<jsp:useBean id="wiadomosciWyslane" type="List<ScigaczDB.Wiadomosci>" scope="session" />
<jsp:useBean id="wiadomosciOdebrane" type="List<ScigaczDB.Wiadomosci>" scope="session" />
<div id="skrzynka">
    <% if (skrz < 2) {%>
    <h1>Skrzynka odbiorcza</h1>
    <%
         int size = wiadomosciOdebrane.size();
         if (size != 0) {
    %>
    <table>
        <thead>
            <tr>
                <th id="td30"><input type="checkbox" id="CheckAll" onClick="checkAll(document)" /></th>
                <th id="td140">Nadawca</th>
                <th id="td500">Temat</th>
                <th id="td120">Data</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (int i = 0; i < size; i++) {
                    ScigaczDB.Wiadomosci wiadomo = (ScigaczDB.Wiadomosci) (wiadomosciOdebrane.get(i));
            %>
            <tr>
                <td><input type="checkbox" id="cb<%= i + 1%>" /></td>
                <td>
                    <a href="OsobaServlet?id=<%=wiadomo.getIdNadawcy().getIdUzytkownika()%>">
                        <%= (!wiadomo.getCzyPrzeczytana()) ? "<b>" : ""%>
                        <%=wiadomo.getIdNadawcy().getLogin()%>
                        <%= (!wiadomo.getCzyPrzeczytana()) ? "</b>" : ""%>
                    </a>
                </td>
                <td>
                    <a href="skrzynka.jsp?w=<%= wiadomo.getIdWiadomosci()%>">
                        <%= (!wiadomo.getCzyPrzeczytana()) ? "<b>" : ""%>
                        <%= wiadomo.getTemat()%>
                        <%= (!wiadomo.getCzyPrzeczytana()) ? "</b>" : ""%>
                    </a>
                </td>
                <td><%= wiadomo.getData()%></td>
            </tr>
            <% }
            %>
        </tbody>
    </table>
    <a href="#" class="button" id="butUsun">usuń zaznaczone</a>
    <% } else {%>
    <div id="info">Brak wiadomości</div>
    <% }
        } else if (skrz == 2) {%>
    <h1>Skrzynka nadawcza</h1>
    <%
         int size = wiadomosciWyslane.size();
         if (size != 0) {
    %>
    <table>
        <thead>
            <tr>
                <th id="td30"><input type="checkbox" id="CheckAll" onClick="checkAll(document)" /></th>
                <th id="td140">Odbiorca</th>
                <th id="td500">Temat</th>
                <th id="td120">Data</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (int i = 0; i < size; i++) {
                    ScigaczDB.Wiadomosci wiadomo = (ScigaczDB.Wiadomosci) (wiadomosciWyslane.get(i));
            %>
            <tr>
                <td><input type="checkbox" id="cb<%= i + 1%>" /></td>
                <td>
                    <a href="OsobaServlet?id=<%=wiadomo.getIdOdbiorcy().getIdUzytkownika()%>">
                        <%= (!wiadomo.getCzyPrzeczytana()) ? "<b>" : ""%>
                        <%=wiadomo.getIdOdbiorcy().getLogin()%>
                        <%= (!wiadomo.getCzyPrzeczytana()) ? "</b>" : ""%>
                    </a>
                </td>
                <td>
                    <a href="skrzynka.jsp?w=<%= wiadomo.getIdWiadomosci()%>">
                        <%= (!wiadomo.getCzyPrzeczytana()) ? "<b>" : ""%>
                        <%= wiadomo.getTemat()%>
                        <%= (!wiadomo.getCzyPrzeczytana()) ? "</b>" : ""%>
                    </a>
                </td>
                <td><%= wiadomo.getData()%></td>
            </tr>
            <% }
            %>
        </tbody>
    </table>
    <a href="#" class="button" id="butUsun">usuń zaznaczone</a>
    <% } else {%>
    <div id="info">Brak wiadomości</div>
    <% }
        } %>
    <!--
    <div id="strony">
        Poprzednia
        1
        <a href="#">2</a>
        <a href="#">Następna</a>
    </div>
    -->
    <div id="blank"></div>
</div>

<% }%>
