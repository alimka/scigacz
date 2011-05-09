<%-- 
    Document   : opinia
    Created on : 2010-01-16, 16:08:41
    Author     : Kasia Ko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ScigaczDB.*" %>
<%@page import="Managery.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ścigacz - serwis wyczesany w kosmos</title>
        <link rel="stylesheet" type="text/css" href="base.css">
        <script type="text/javascript" src="livevalidation.js"></script>
    </head>

    <%
            session = request.getSession();

            String authorized = (String) session.getAttribute("authorized");
            if (authorized == null) {
                authorized = "no";
            }

            if (authorized.equals("yes")) {
                String wyslane = (String)request.getAttribute("wyslane");
                if (wyslane!=null && wyslane.equals("ok")) {
    %>
    <script type="text/javascript">
        alert("Opinia została wystawiona.");
        self.close();
    </script>
    <%
                }
                String baza = (String)request.getAttribute("bazaOK");
                String param = (String)request.getAttribute("parametryOK");
                String s = request.getParameter("id");
                String skarga = request.getParameter("skarga");
                int id = -1;

                try {
                    id = Integer.parseInt(s);
                } catch (Exception e) {}
                String login = "";
                s = request.getParameter("adres");


                if (s == "admin") {
                    login = "admin";
                } else {

                    ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
                    try {
                        Uzytkownicy odbiorca = (Uzytkownicy) managerUzytkownika.PokazUzytkownika(id);
                        login = odbiorca.getLogin();
                    } catch (Exception e) {}
                }

                int idW = 0;
    %>
    <body>
        <div id="wiadom">
            <h1>Opinia</h1>
            <% if(baza!=null && param!=null) { %>
            <div id="uwaga">Wystąpił błąd</div>
            <% } %>
             
            <form action="OpinieServlet" method="POST">
                <input type="hidden" name="idNadawcy" value="<%= ((ScigaczDB.Uzytkownicy) session.getAttribute("uzytkownik")).getIdUzytkownika()%>" />
                <input type="hidden" name="idOdbiorcy" value="<%= id%>" />
                <p><label>Osoba</label><input type="text" name="odbiorca" id="odbiorca" value="<%= login %>" disabled="disabled" /></p>
                <p><label>Opinia</label>
                    <textarea name="tresc" id="tresc" rows="" cols="" ></textarea></p>

                <input type="submit" value="Wyślij" name="wyslij" class="button" id="submit1" />
            </form>

            <script type="text/javascript">

                var f1 = new LiveValidation( 'tresc', {onlyOnSubmit: true } );
                f1.add( Validate.Presence );

                var automaticOnSubmit = f1.form.onsubmit;
                f1.form.onsubmit = function(){
                    var valid = automaticOnSubmit();
                    if(valid) {
                        submit();
                    }
                    alert("Wypełnij wszystkie pola!");
                    return false;
                }
            </script>

        </div>
    </body>
    <%                    } else {%>
    <jsp:forward page="login.jsp"><jsp:param name="uwaga" value="autoryzacja" /></jsp:forward>
    <%              }%>
</html>

