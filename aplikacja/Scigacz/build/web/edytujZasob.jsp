<%-- 
    Document   : edytujZasob
    Created on : 2010-01-04, 21:37:44
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
                <jsp:useBean id="zasob" type="ScigaczDB.Zasoby" scope="session" />
                <div id="content">
                    <div id="formZmiany">
                        <h1>Edytuj zasób nr <%= zasob.getIdZasobu()%></h1>

                        <form id="edytujform" action="EdytujZasobServlet" method="POST" enctype="multipart/form-data">
                            <input type="hidden" name="idZasobu" value="<%= zasob.getIdZasobu()%>" />
                            <p><label>Nazwa</label><input type="text" id="nazwa" name="nazwa" value="<%= zasob.getNazwa()%>" /></p>
                            <p><label>Opis</label><textarea id="texarea" name="opis" cols="" rows=""><%= zasob.getOpis()%></textarea></p>
                            <p><label>Kategoria</label><select name="kategoria">
                                    <% for (int i = 0; i < kat.size(); i++) {
                                        Kategorie k = kat.get(i);
                                    %>
                                    <option value="<%= k.getIdKategorii()%>"<%= (zasob.getIdKategorii().getIdKategorii().equals(i+1))?" selected":""%>><%= k.getNazwa()%>
                                    </option>
                                    <% }%>

                                </select></p>
                            <p><label>Czas wypożyczenia</label>
                                <input type="text" name="czas" value="<%= zasob.getCzasPrzetrzymywania()%>" id="smallinput"/>
                                <select name="miara">
                                    <option selected>dni</option>
                                    <option>tygodni</option>
                                    <option>miesięcy</option>
                                </select></p>
                            <p><label>Udostępnienie</label>
                                <select name="udostepnienie">
                                    <option value="0"<%= (zasob.getDlakogo() == 0) ? " selected" : ""%>>wszystkim</option>
                                    <option value="1"<%= (zasob.getDlakogo() == 1) ? " selected" : ""%>>znajomym</option>
                                    <option value="2"<%= (zasob.getDlakogo() == 2) ? " selected" : ""%>>bliskim znajomym</option>
                                    <option value="3"<%= (zasob.getDlakogo() == 3) ? " selected" : ""%>>nikomu</option>
                                </select></p>
                                <%
                                String img0="",img1="",img2="";
                                try {
                                    img0 = zasob.getZdjeciaList().get(0).getPlik();
                                } catch (Exception e) {}
                                try {
                                    img1 = zasob.getZdjeciaList().get(1).getPlik();
                                } catch (Exception e) {}
                                try {
                                    img2 = zasob.getZdjeciaList().get(2).getPlik();
                                } catch (Exception e) {}
                                %>
                            <p><label>Zdjęcie 1</label>
                                <% if(img0!="") { %><img alt="" src="obrazki/<%= img0%>" class="avatar" /><br><% } %>
                                <input type="file" name="zdjecie1" <%=(img0!="")?"id=\"obrazek\"":""%>/></p>
                            <p><label>Zdjęcie 2</label>
                                <% if(img1!="") { %><img alt="" src="obrazki/<%= img1%>" class="avatar" /><br><% } %>
                                <input type="file" name="zdjecie2" <%=(img1!="")?"id=\"obrazek\"":""%> /></p>
                            <p><label>Zdjęcie 3</label>
                                <% if(img2!="") { %><img alt="" src="obrazki/<%= img2%>" class="avatar" /><br><% } %>
                                <input type="file" name="zdjecie3" <%=(img2!="")?"id=\"obrazek\"":""%>/></p>


                            <input type="submit" value="edytuj zasób" name="edytuj" class="button" id="submit1" />
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
