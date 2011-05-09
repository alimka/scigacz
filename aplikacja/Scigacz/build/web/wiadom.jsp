<%-- 
    Document   : wiadom
    Created on : 2010-01-06, 00:01:43
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
        alert("Wiadomość została wysłana");
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
                    login = "portalscigacz@gmail.com";
                } else {

                    ManagerUzytkownika managerUzytkownika = (ManagerUzytkownika) session.getAttribute("managerUzytkownika");
                    try {
                        Uzytkownicy odbiorca = (Uzytkownicy) managerUzytkownika.PokazUzytkownika(id);
                        login = odbiorca.getLogin();
                    } catch (Exception e) {}
                }
    %>
    <body>
        <div id="wiadom">
            <h1>Wiadomość</h1>
            <% if(baza!=null && param!=null) { %>
            <div id="uwaga">Wystąpił błąd</div>
            <% } %>
            <form action="WiadomoscServlet" method="POST">
                <input type="hidden" name="idNadawcy" value="<%= ((ScigaczDB.Uzytkownicy) session.getAttribute("uzytkownik")).getIdUzytkownika()%>" />
                <input type="hidden" name="idOdbiorcy" value="<%= id%>" />
                <input type="hidden" name="naduzycie" value=<%= (skarga!=null)?"tak":"nie" %> />
                <p><label>Odbiorca</label><input type="text" name="odbiorca" id="odbiorca" value="<%= (skarga==null)?login:"admin"%>" <%= (login != "") ? "disabled=\"disabled\"" : ""%> /></p>
                <p><label>Temat</label><input type="text" name="temat" id="temat" value="<%= (skarga!=null)?"Zgłoszenie nadużycia":"" %>" <%= (skarga!=null)?"disabled=\"disabled\"" : ""%>/></p>
                <p><label>Treść</label>
                    <textarea name="tresc" id="tresc" rows="" cols="" ></textarea></p>

                <input type="submit" value="Wyślij" name="wyslij" class="button" id="submit1" />
            </form>

            <script type="text/javascript">

                var f1 = new LiveValidation( 'odbiorca', {onlyOnSubmit: true } );
                f1.add( Validate.Presence );
                f1.add( Validate.Format, { pattern: /^([a-zA-z]+)([-._a-zA-Z0-9]+)$/i } );

                var f2 = new LiveValidation( 'temat', {onlyOnSubmit: true } );
                f2.add( Validate.Presence );

                var f3 = new LiveValidation( 'tresc', {onlyOnSubmit: true } );
                f3.add( Validate.Presence );

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
