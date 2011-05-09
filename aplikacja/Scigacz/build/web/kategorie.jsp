<%-- 
    Document   : backup
    Created on : 2010-01-16, 22:19:40
    Author     : Kasia Ko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="Managery.*" %>
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
                session = request.getSession();
                String niepusta = (String) request.getParameter("niepusta");
                if (authorized.equals("yes")) {
                    if(session.getAttribute("login")!=null) {
    %>
    <body>
        <div id="bg">
            <div id="main">
                <jsp:include page="top.jsp?m=1" />
                <div id="content">
                <%
                    ManagerZasobow managerZasobow = new ManagerZasobow();
                    List<ScigaczDB.Kategorie> kategorie = managerZasobow.ListujKategorie();
                    int size = kategorie.size();
                %>

                   <div id="formZmiany">
                       <h1>Kategorie</h1>
                       <% if (niepusta != null) { %>
                       <h2 id="uwaga">Kategoria nie pusta. Nie została usunięta.</h2>
                       <% } %>
                       <form id="edytujform" action="DodajKategorieServlet" method="POST">
                       <%
                            int i=0;
                            if (size!=0) {
                                for(; i<size; i++) {
                                    ScigaczDB.Kategorie kategoria = kategorie.get(i);
                       %>
                           <div>
                               <label>Kategoria <%= i + 1%></label>
                               <input type="text" id="kat<%= i%>" name="kat<%= i%>" value="<%= kategoria.getNazwa()%>" />
                               <a href="UsunKategorieServlet?id=<%= kategoria.getIdKategorii()%>">
                                   <img src="obrazki/usun.png" alt="">
                               </a>
                           </div>
                       <% }
                            } %>
                            <p><label>Stwórz nową</label><input type="text" id="kat<%= i%>" name="nazwa" value="" /></p>
                            <input type="submit" value="zatwierdź" name="ok" class="button" id="submit1" />
                        </form>
                </div>

                <div id="blank"></div>
                </div>
            </div>
        </div>
    </body>
    <% } else {%>
    <body>
        <div id="bg">
            <div id="main">
                <jsp:include page="top.jsp?m=6" />
                <jsp:useBean id="uzytkownik" type="ScigaczDB.Uzytkownicy" scope="session" />
                <jsp:useBean id="managerZasobow" type="Managery.ManagerZasobow" scope="session" />

                <div id="content">
                    <%
                        if (uzytkownik.getUprawnienia() == 1) {
                            List<ScigaczDB.Kategorie> kategorie = managerZasobow.ListujKategorie();
                            int size = kategorie.size();
                    %>
                   <div id="formZmiany">
                       <h1>Kategorie</h1>
                       <% if (niepusta != null) { %>
                       <h2 id="uwaga">Kategoria nie pusta. Nie została usunięta.</h2>
                       <% } %>
                       <form id="edytujform" action="DodajKategorieServlet" method="POST">
                       <%
                            int i=0;
                            if (size!=0) {
                                for(; i<size; i++) {
                                    ScigaczDB.Kategorie kategoria = kategorie.get(i);
                       %>
                           <div>
                               <label>Kategoria <%= i + 1%></label>
                               <input type="text" id="kat<%= i%>" name="kat<%= i%>" value="<%= kategoria.getNazwa()%>" />
                               <a href="UsunKategorieServlet?id=<%= kategoria.getIdKategorii()%>">
                                   <img src="obrazki/usun.png" alt="">
                               </a>
                           </div>
                       <% }
                            } %>
                            <p><label>Stwórz nową</label><input type="text" id="kat<%= i%>" name="nazwa" value="" /></p>
                            <input type="submit" value="zatwierdź" name="ok" class="button" id="submit1" />
                        </form>

                    <% } else {%>
                    <div id="uwaga">Nie masz odpowiednich uprawnień do przeglądania tej strony.</div>
                    <% }%>
                    <div id="blank"></div>



                   </div>
                </div>
                <jsp:include page="foot.jsp" />
            </div>
        </div>
    </body>
    <% }
                } else {%>
    <jsp:forward page="login.jsp"><jsp:param name="uwaga" value="autoryzacja" /></jsp:forward>
    <%              }%>
</html>
