<%-- 
    Document   : skrzynka0
    Created on : 2010-01-16, 21:50:50
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
<jsp:useBean id="uzytkownik" type="ScigaczDB.Uzytkownicy" scope="session" />
<jsp:useBean id="managerSkrzynki" type="Managery.ManagerSkrzynki" scope="session" />
<div id="menu2">
    <ul>
        <li id="m_pre"><a href="WyswietlServlet?z=wiadomosci&s=1" id="m">Odebrane</a></li>
        <li id="m_pre"><a href="WyswietlServlet?z=wiadomosci&s=2" id="m">Wysłane</a></li>
        <li id="m_pre"><a href="WyswietlServlet?z=wiadomosci&s=3" id="m">Systemowe</a></li>
        <li id="m_pre"><a href="wiadom.jsp" id="m" onclick="window.open('wiadom.jsp', 'nazwa', 'menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=no,resizable=no,fullscreen=no,channelmode=no,width=400,height=320').focus(); return false">Napisz</a></li>
    </ul>
</div>
<div id="skrzynka">
    <div id ="przyciski">
        <a href="javascript: history.go(-1)" id="butPowrot" class="button">powrót</a><br>
        <% if (sys == null) {
            ScigaczDB.Wiadomosci wiadomosc = managerSkrzynki.PokazWiadomosc(wiadom);
            int uid = -1;
            if (wiadomosc.getIdNadawcy().getIdUzytkownika()==uzytkownik.getIdUzytkownika())
                uid = wiadomosc.getIdOdbiorcy().getIdUzytkownika();
            else
                uid = wiadomosc.getIdNadawcy().getIdUzytkownika();

        %>
        <a href="wiadom.jsp" id="butPowrot" class="button" onclick="window.open('wiadom.jsp?id=<%= uid%>', 'nazwa', 'menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=no,resizable=no,fullscreen=no,channelmode=no,width=400,height=320').focus(); return false">
            ODPISZ</a>
        <% } %>
    </div>
    <div id="wiadomosc">
        <h2>Wiadomość</h2>
        <%
            if (sys == null) {
                ScigaczDB.Wiadomosci wiadomosc = managerSkrzynki.PokazWiadomosc(wiadom);
                managerSkrzynki.OznaczJakoPrzeczytana(wiadomosc.getIdWiadomosci());
        %>
        <p><label>Od:</label><%= wiadomosc.getIdNadawcy().getLogin()%></p>
        <p><label>Do:</label><%= wiadomosc.getIdOdbiorcy().getLogin()%></p>
        <p><label>Temat:</label><%= wiadomosc.getTemat()%></p>
        <p><label>Data:</label><%= wiadomosc.getData()%></p>
        <p id="tresc"><%= wiadomosc.getTresc()%></p>
        <%
            } else if (sys.equals("y")) {
                ScigaczDB.Systemowe wiadomosc = managerSkrzynki.PokazWiadomoscSystemowa(wiadom);
                managerSkrzynki.OznaczJakoPrzeczytanaSys(wiadomosc.getIdSys());
        %>
        <p><label>Od:</label>Czak</p>
        <p><label>Do:</label><%= wiadomosc.getIdOdbiorcy().getLogin()%></p>
        <p><label>Temat:</label><%= wiadomosc.getTemat()%></p>
        <p><label>Data:</label><%= wiadomosc.getData()%></p>
        <div id="tresc">
            <%= wiadomosc.getTresc()%>
            <% if (wiadomosc.getRodzaj() == 1) {%>
            <form id="wiadomoscform" action="OdpowiedzNaZaproszenieServlet" method="POST">
                <input type="hidden" name="idOdbiorcy" value="<%= wiadomosc.getIdDoZwrotu()%>" />
                <input type="hidden" name="idWiadomosci" value="<%= wiadomosc.getIdSys()%>" />
                <input type="submit" value="Tak" name="opcja" class="button" />
                <input type="submit" value="Nie" name="opcja" class="button" />
            </form>
            <% } else if (wiadomosc.getRodzaj() == 4) {%>
            <form id="wiadomoscform" action="OdpowiedzNaProsbeServlet" method="POST">
                <a href="termin" id="m" onclick="<% session.setAttribute("wiadomosc", wiadomosc); %>window.location = 'OdswiezWiadomosciServlet'; window.open('termin.jsp?id=<%= wiadomosc.getIdDoZwrotu()%>&wiad=<%= wiadomosc.getIdSys()%>&zas=<%= wiadomosc.getIdZasobu()%>', 'nazwa', 'menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=no,resizable=no,fullscreen=no,channelmode=no,width=400,height=320').focus(); return false"><input type="submit" value="Tak" name="tak" class="button" /></a>
                <input type="hidden" name="idOdbiorcy" value="<%= wiadomosc.getIdDoZwrotu()%>" />
                <input type="hidden" name="idWiadomosci" value="<%= wiadomosc.getIdSys()%>" />
                <input type="hidden" name="idZasobu" value="<%= wiadomosc.getIdZasobu()%>" />
                <input type="submit" value="Nie" name="opcja" class="button" />
            </form>
            <% } else if (wiadomosc.getRodzaj() == 5) {%>
            <form id="wiadomoscform" action="ZatwierdzZwrotZasobuServlet" method="POST">
                <input type="hidden" name="idOdbiorcy" value="<%= wiadomosc.getIdDoZwrotu()%>" />
                <input type="hidden" name="idWiadomosci" value="<%= wiadomosc.getIdSys()%>" />
                <input type="hidden" name="idZasobu" value="<%= wiadomosc.getIdZasobu()%>" />
                <input type="submit" value="Tak" name="opcja" class="button" />
                <input type="submit" value="Nie" name="opcja" class="button" />
            </form>
            <% }%>
        </div>
        <% }%>
    </div>
</div>
<div id="blank"></div>

<%         } else {
        String s = request.getParameter("s");
        int skrz;
        if (s != null) {
            skrz = Integer.parseInt(s);
        } else {
            skrz = 3;
        }
%>
<div id="menu2">
    <ul>
        <% if (skrz < 2) {%>
        <li id="m_ac"><a href="WyswietlServlet?z=wiadomosci&s=1" id="m">Odebrane</a></li>
        <li id="m_pre"><a href="WyswietlServlet?z=wiadomosci&s=2" id="m">Wysłane</a></li>
        <li id="m_pre"><a href="WyswietlServlet?z=wiadomosci&s=3" id="m">Systemowe</a></li>
        <% }
            else if (skrz == 2) {%>
        <li id="m_pre"><a href="WyswietlServlet?z=wiadomosci&s=1" id="m">Odebrane</a></li>
        <li id="m_ac"><a href="WyswietlServlet?z=wiadomosci&s=2" id="m">Wysłane</a></li>
        <li id="m_pre"><a href="WyswietlServlet?z=wiadomosci&s=3" id="m">Systemowe</a></li>
        <% } else if (skrz == 3) {%>
        <li id="m_pre"><a href="WyswietlServlet?z=wiadomosci&s=1" id="m">Odebrane</a></li>
        <li id="m_pre"><a href="WyswietlServlet?z=wiadomosci&s=2" id="m">Wysłane</a></li>
        <li id="m_ac"><a href="WyswietlServlet?z=wiadomosci&s=3" id="m">Systemowe</a></li>
        <% }%>
            <li id="m_pre"><a href="wiadom.jsp" id="m" onclick="window.open('wiadom.jsp', 'nazwa', 'menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=no,resizable=no,fullscreen=no,channelmode=no,width=400,height=320').focus(); return false">Napisz</a></li>
    </ul>
</div>
<jsp:useBean id="wiadomosciWyslane" type="List<ScigaczDB.Wiadomosci>" scope="session" />
<jsp:useBean id="wiadomosciOdebrane" type="List<ScigaczDB.Wiadomosci>" scope="session" />
<jsp:useBean id="wiadomosciSystemowe" type="List<ScigaczDB.Systemowe>" scope="session" />
<div id="skrzynka">
    <% if (skrz < 2) {%>
    <h1>Skrzynka odbiorcza</h1>
    <%
         int size = wiadomosciOdebrane.size();
         if (size != 0) {
    %>
    <form action="UsunWiadomoscServlet" method="POST">
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
                    ScigaczDB.Wiadomosci wiadomosc = (ScigaczDB.Wiadomosci) (wiadomosciOdebrane.get(i));
            %>
            <tr>
                <td><input type="checkbox" id="cb<%= i + 1%>" name="idWiadomosci" value="<%= wiadomosc.getIdWiadomosci()%>"/></td>
                <td>
                    <a href="OsobaServlet?id=<%=wiadomosc.getIdNadawcy().getIdUzytkownika()%>">
                        <%= (!wiadomosc.getCzyPrzeczytana()) ? "<b>" : ""%>
                        <%=wiadomosc.getIdNadawcy().getLogin()%>
                        <%= (!wiadomosc.getCzyPrzeczytana()) ? "</b>" : ""%>
                    </a>
                </td>
                <td>
                    <a href="skrzynka.jsp?w=<%= wiadomosc.getIdWiadomosci()%>">
                        <%= (!wiadomosc.getCzyPrzeczytana()) ? "<b>" : ""%>
                        <%= wiadomosc.getTemat()%>
                        <%= (!wiadomosc.getCzyPrzeczytana()) ? "</b>" : ""%>
                    </a>
                </td>
                <td><%= wiadomosc.getData()%></td>
            </tr>
            <% }
            %>
        </tbody>
    </table>
    <input type="hidden" value="odebrane" name="typ"/>
    <input type="submit" value="usuń zaznaczone" class="button" id="butUsun" />
   </form>
    <% } else {%>
    <div id="info">Brak wiadomości</div>
    <% }
                         } else if (skrz == 2) {%>
    <h1>Skrzynka nadawcza</h1>
    <%
         int size = wiadomosciWyslane.size();
         if (size != 0) {
    %>
    <form action="UsunWiadomoscServlet" method="POST">
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
                    ScigaczDB.Wiadomosci wiadomosc = (ScigaczDB.Wiadomosci) (wiadomosciWyslane.get(i));
            %>
            <tr>
                <td><input type="checkbox" id="cb<%= i + 1%>" name="idWiadomosci" value="<%= wiadomosc.getIdWiadomosci()%>"/></td>
                <td>
                    <a href="OsobaServlet?id=<%=wiadomosc.getIdOdbiorcy().getIdUzytkownika()%>">
                        <%= (!wiadomosc.getCzyPrzeczytana()) ? "<b>" : ""%>
                        <%=wiadomosc.getIdOdbiorcy().getLogin()%>
                        <%= (!wiadomosc.getCzyPrzeczytana()) ? "</b>" : ""%>
                    </a>
                </td>
                <td>
                    <a href="skrzynka.jsp?w=<%= wiadomosc.getIdWiadomosci()%>">
                        <%= (!wiadomosc.getCzyPrzeczytana()) ? "<b>" : ""%>
                        <%= wiadomosc.getTemat()%>
                        <%= (!wiadomosc.getCzyPrzeczytana()) ? "</b>" : ""%>
                    </a>
                </td>
                <td><%= wiadomosc.getData()%></td>
            </tr>
            <% }
            %>
        </tbody>
    </table>
    <input type="hidden" value="wyslane" name="typ"/>
    <input type="submit" value="usuń zaznaczone" class="button" id="butUsun" />
    </form>
    <% } else {%>
    <div id="info">Brak wiadomości</div>
    <% }
                         } else if (skrz == 3) {%>
    <h1>Wiadomości systemowe</h1>
    <%
         int size = wiadomosciSystemowe.size();
         if (size != 0) {
    %>
    <form action="UsunWiadomoscServlet" method="POST">
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
                    ScigaczDB.Systemowe wiadomosc = (ScigaczDB.Systemowe) (wiadomosciSystemowe.get(i));
            %>
            <tr>
                <td><input type="checkbox" id="cb<%= i + 1%>" name="idWiadomosci" value="<%= wiadomosc.getIdSys()%>"/></td>
                <td>
                    <a href="obrazki/chuck.jpg">
                        <%= (!wiadomosc.getCzyPrzeczytana()) ? "<b>" : ""%>
                        Czak
                        <%= (!wiadomosc.getCzyPrzeczytana()) ? "</b>" : ""%>
                    </a>
                </td>
                <td>
                    <a href="skrzynka.jsp?w=<%= wiadomosc.getIdSys()%>&sys=y">
                        <%= (!wiadomosc.getCzyPrzeczytana()) ? "<b>" : ""%>
                        <%= wiadomosc.getTemat()%>
                        <%= (!wiadomosc.getCzyPrzeczytana()) ? "</b>" : ""%>
                    </a>
                </td>
                <td><%= wiadomosc.getData()%></td>
            </tr>
            <% }
            %>
        </tbody>
    </table>
    <input type="hidden" value="systemowe" name="typ"/>
    <input type="submit" value="usuń zaznaczone" class="button" id="butUsun" />
    </form>
    <% } else {%>
    <div id="info">Brak wiadomości</div>
    <% }
                         }%>
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
