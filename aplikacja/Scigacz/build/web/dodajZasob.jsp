<%-- 
    Document   : dodajZasob
    Created on : 2010-01-03, 22:34:40
    Author     : Kasia Ko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ScigaczDB.*" %>
<%@page import="Managery.*" %>
<%@page import="java.util.*" %>
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
                    ManagerZasobow managerZasobow = (ManagerZasobow) session.getAttribute("managerZasobow");
                    List<Kategorie> kat = managerZasobow.PokazKategorie();
    %>
    <body>
        <div id="bg">
            <div id="main">
                <jsp:include page="top.jsp" />
                <div id="content">
                    <div id="formZmiany">
                        <h1>Dodaj zasób</h1>

                        <form id="dodajform" action="DodajZasobServlet" method="POST" enctype="multipart/form-data">
                            <p><label>Nazwa</label><input type="text" id="nazwa" name="nazwa" /></p>
                            <p><label>Opis</label><textarea id="textarea" name="opis" cols="" rows=""></textarea></p>
                            <p><label>Kategoria</label><select id="kategoria" name="kategoria">
                                    <option value="">wybierz</option>
                                    <% for (int i = 0; i < kat.size(); i++) {
                                        Kategorie k = kat.get(i);
                                    %>
                                    <option value="<%= k.getIdKategorii()%>"><%= k.getNazwa()%></option>
                                    <% }%>
                                </select></p>
                            <p><label>Czas wypożyczenia</label><input type="text" name="czas" id="smallinput"/>
                                <select name="miara">
                                    <option>dni</option>
                                    <option>tygodni</option>
                                    <option>miesięcy</option>
                                </select></p>
                            <p><label>Udostępnienie</label>
                                <select name="udostepnienie">
                                    <option value="0">wszystkim</option>
                                    <option value="1">znajomym</option>
                                    <option value="2">bliskim znajomym</option>
                                </select></p>
                            <p><label>Zdjęcie 1</label><input type="file" name="zdjecie1" /></p>
                            <p><label>Zdjęcie 2</label><input type="file" name="zdjecie2" /></p>
                            <p><label>Zdjęcie 3</label><input type="file" name="zdjecie3" /></p>

                            <input type="submit" value="Dodaj zasób" name="dodaj" class="button" id="submit1" />
                        </form>
                <script type="text/javascript">

                    var f1 = new LiveValidation( 'nazwa', {onlyOnSubmit: true } );
                    f1.add( Validate.Presence );
                    f1.add( Validate.Format, { pattern: /^([^'"\*#$%&`~]+)$/i } );

                    var f2 = new LiveValidation( 'textarea', {onlyOnSubmit: true } );
                    f2.add( Validate.Presence );
                    f2.add( Validate.Format, { pattern: /^([^'"\*#$%&`~]+)$/i } );

                    var f3 = new LiveValidation( 'smaillinput', {onlyOnSubmit: true } );
                    f3.add( Validate.Presence );
                    f3.add( Validate.Numericality, { onlyInteger: true } );

                    var automaticOnSubmit = f1.form.onsubmit;
                    f1.form.onsubmit = function(){
                        var valid = automaticOnSubmit();
                        if(valid) {
                            submit();
                        }
                        return false;
                    }
                </script>
                    </div>
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
