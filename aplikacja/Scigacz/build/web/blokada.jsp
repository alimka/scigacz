<%-- 
    Document   : blokada
    Created on : 2010-01-05, 21:37:38
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
                if (authorized.equals("no")) {
    %>
    <jsp:useBean id="uzytkownik" type="ScigaczDB.Uzytkownicy" scope="session" />
    <body>
        <div id="blokada_outer">
            <div id="blokada">
                <h1>Zostałeś zablokowany</h1>
                <h2><%= uzytkownik.getKomentarzblokada() %></h2>
                <a href="wiadom.jsp" onclick="window.open('wiadom.jsp?adres=admin', '', 'menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=no,resizable=no,fullscreen=no,channelmode=no,width=400,height=320').focus(); return false">kontakt z Administatorem</a>
            </div>
        </div>
    </body>
    <%                    } else {%>
    <jsp:forward page="login.jsp"><jsp:param name="uwaga" value="autoryzacja" /></jsp:forward>
    <%              }%>
</html>