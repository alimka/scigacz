<%-- 
    Document   : termin
    Created on : 2010-01-18, 22:35:21
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
        <script type="text/javascript" src="livevalidation2.js"></script>
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

                String idWiadomosci = request.getParameter("wiad");
                String idZasobu = request.getParameter("zas");

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
            <h1>Umów się</h1>
            <% if(baza!=null && param!=null) { %>
            <div id="uwaga">Wystąpił błąd</div>
            <% } %>
            <h3>Zgadzasz się na wypożyczenie. Podaj pasujący Ci termin i miejsce spotkania</h3>
            <form action="OdpowiedzNaProsbeServlet" method="POST">
                <input type="hidden" name="idNadawcy" value="<%= ((ScigaczDB.Uzytkownicy) session.getAttribute("uzytkownik")).getIdUzytkownika()%>" />
                <input type="hidden" name="idOdbiorcy" value="<%= id%>" />
                <input type="hidden" name="idWiadomosci" value="<%= idWiadomosci %>" />
                <input type="hidden" name="idZasobu" value="<%= idZasobu%>" />
                <input type="hidden" value="Tak" name="opcja" />
                <p><label>Użytkownik</label><input type="text" name="odbiorca" value="<%= login%>" disabled="disabled" /></p>
                <p><label>Miejsce</label><input type="text" name="miejsce" id="miejsce" /></p>
                <p><label>Termin</label><input type="text" name="termin" id="termin" /> <span>format DD-MM-RRRR, np. 18-01-2010</span></p>

                <input type="submit" value="Wyślij" name="wyslij" class="button" id="submit1"/>
            </form>
<%--

            <script type="text/javascript">

                var f1 = new LiveValidation( 'miejsce', {onlyOnSubmit: true } );
                f1.add( Validate.Presence );
                f1.add( Validate.Format, { pattern: /^([a-zA-z]+)([-._a-zA-Z0-9]+)$/i } );

                var f2 = new LiveValidation( 'termin', {onlyOnSubmit: true } );
                f2.add( Validate.Presence );
                f1.add( Validate.Format, { pattern: /^([0-9]{2}-[0-9]{2}-[0-9]{4})$/i } );

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
--%>

        </div>
    </body>
    <%                    } else {%>
    <jsp:forward page="login.jsp"><jsp:param name="uwaga" value="autoryzacja" /></jsp:forward>
    <%              }%>
</html>
