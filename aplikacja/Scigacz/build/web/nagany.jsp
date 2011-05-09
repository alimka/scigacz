<%-- 
    Document   : nagany
    Created on : 2010-01-16, 22:22:09
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
        <script type="text/javascript" src="checkbox.js"></script>
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
                <jsp:include page="top.jsp?m=4" />
                <jsp:useBean id="uzytkownik" type="ScigaczDB.Uzytkownicy" scope="session" />
                <jsp:useBean id="wiadomosciSystemowe" type="List<ScigaczDB.Systemowe>" scope="session" />
                <div id="content">
                    <%
                        if (uzytkownik.getUprawnienia() == 1) {
                    %>
                    <h1>Nagany</h1>
                    <%
                        int size = wiadomosciSystemowe.size();
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
                                    ScigaczDB.Systemowe wiadomosc = (ScigaczDB.Systemowe) (wiadomosciSystemowe.get(i));
                            %>
                            <tr>
                                <td><input type="checkbox" id="cb<%= i + 1%>" /></td>
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
                    <a href="#" class="button" id="butUsun">usuń zaznaczone</a>
                    <% } else {%>
                    <div id="info">Brak wiadomości</div>
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
