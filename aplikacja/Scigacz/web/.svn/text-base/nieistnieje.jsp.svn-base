<%-- 
    Document   : nieistnieje
    Created on : 2010-01-17, 00:43:27
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
                <jsp:include page="top.jsp" />
                <div id="content">
                    <div id="uwaga">Podana strona nie istnieje.</div>
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
