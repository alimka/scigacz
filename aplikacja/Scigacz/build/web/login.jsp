<%--
    Document   : login.jsp
    Created on : 2009-12-13, 14:39:45
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
        <script type="text/javascript">
        <!-- Hide
            function setfocus() {
                document.form.login.focus();
            }
        // -->
        </script>
    </head>
    <%
                session = request.getSession();
                String uwaga = (String) request.getParameter("uwaga");
                String bazaOK = (String) request.getAttribute("bazaOK");
                String bazkaOK = (String) request.getAttribute("bazkaOK");
                String authorized = (String) session.getAttribute("authorized");
                if (authorized == null) authorized = "no";
                if (authorized.equals("no")) {
    %>

    <body onLoad="setfocus()">
        <div id="bg">
            <div id="log">
                <h1>Logowanie</h1>
                <% if (uwaga != null) {
                    if (uwaga.equals("logowanie")) { %>
                <h2 id="uwaga">Błędne dane logowania. Spróbuj ponownie.</h2>
                <% }
                    else if (uwaga.equals("wylogowano")) { %>
                <h2 id="uwaga">Zostałeś wylogowany.</h2>
                <%  }
                    else if (uwaga.equals("autoryzacja")) { %>
                <h2 id="uwaga">Strona wymaga zalogowania.</h2>
                <%  }
                   }
                if (bazaOK != null && bazaOK.equals("no")) { %>
                <h2 id="uwaga">Błędne dane logowania. Spróbuj ponownie.</h2>
                <% }
                if (bazkaOK != null && bazkaOK.equals("no")) { %>
                <h2 id="uwaga">Błąd bazy danych. Przepraszamy.</h2>
                <% } %>
                <form id="loginform" action="LoginServlet" method="POST" name="form">
                    <label>Login</label><input type="text" name="login" class="loginput" /><br>
                    <label>Hasło</label><input type="password" name="passwd" class="loginput" /><br>

                    <input type="submit" value="Zaloguj" name="log" id="submit1" /><br>
                </form>
                <a href="przypom.jsp">Nie pamiętam hasła</a><br>
            </div>
            <a href="rejestracja.jsp" id="linkreg">Rejestracja</a>
            <a href="pomoc.jsp" id="linkpom">Pomoc</a>
        </div>
    </body>
    <%
                    } else {
    %>

    <jsp:forward page="index.jsp"></jsp:forward>
    <%                }
    %>
</html>

