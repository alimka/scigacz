<%--
    Document   : zasoby
    Created on : 2010-01-03, 19:41:43
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
                <jsp:include page="top.jsp?m=2" />
                <jsp:useBean id="zasobyUzytkownika" type="List<ScigaczDB.Zasoby>" scope="session" />
                <div id="content">
                    <h1>Moje zasoby</h1>
                    <a href="dodajZasob.jsp"><input type="submit" value="Dodaj nowy" name="dodajZasob" class="button" id="butDodaj" /></a>
                        <%
                                            int size = zasobyUzytkownika.size();
                                            if (size != 0) {
                        %>
                    <form action="UsunZasobServlet" method="POST">
                    <table id="tableZasoby">
                        <thead>
                            <tr>
                                <th id="td30"><input type="checkbox" id="CheckAll" onClick="checkAll(document)" /></th>
                                <th id="td50">Lp.</th>
                                <th id="td50"></th>
                                <th id="td240">Nazwa</th>
                                <th id="td120">Kategoria</th>
                                <th id="td120">Widoczność</th>
                                <th id="td30"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                                    for (int i = 0; i < size; i++) {
                                                        ScigaczDB.Zasoby zasob = (ScigaczDB.Zasoby) (zasobyUzytkownika.get(i));
                                                        String img;
                                                        try { img = ((ScigaczDB.Zdjecia)zasob.getZdjeciaList().get(0)).getPlik(); }
                                                        catch (Exception e) { img = ""; }
                            %>
                            <tr>
                                <td><input type="checkbox" id="cb<%= i + 1%>" name="idZasobu" value="<%= zasob.getIdZasobu() %>"/></td>
                                <td><%= i + 1%>.</td>
                                <td class="thumb"><% if(img!="") { %><img alt="" src="obrazki/<%=img%>"/><%}%></td>
                                <td><a href="RzeczServlet?id=<%= zasob.getIdZasobu()%>"><%= zasob.getNazwa()%></a></td>
                                <td><%= ((ScigaczDB.Kategorie)zasob.getIdKategorii()).getNazwa() %></td>
                                <!-- zasob.getWypozyczeniaList().size() == 0 ? "" : ((ScigaczDB.Wypozyczenia)zasob.getWypozyczeniaList().get(0)).getIdWypozyczacza().getLogin() -->
                                <td><%= (zasob.getDlakogo()==0)?"wszyscy":(zasob.getDlakogo()==1)?"znajomi":"bliscy znajomi" %></td>
                                <td class="edit">
                                    <a href="RzeczServlet?id=<%= zasob.getIdZasobu()%>&edit=y">
                                        <img src="obrazki/edit.png" alt="edytuj"/></a>
                                </td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                    <input type="submit" value="usuń zaznaczone" class="button" id="butUsun" />
                    </form>

                    <div id="blank"></div>
                    <div id="legenda">
                        <h3>Legenda</h3>
                        <img src="obrazki/edit.png" alt=""><span>edytuj informacje o zasobie</span><br>
                    </div>

                    <!--<div id="strony">
                    <a href="UsunZasobServlet" class="button" id="butUsun">usuń zaznaczone</a>
                        Poprzednia
                        1
                        <a href="#">2</a>
                        <a href="#">Następna</a>
                    </div>-->
                    <% } else {%>
                    <div id="info">Brak zasobów</div>
                    <% } %>
                    <div id="blank"></div>
                </div>
                <jsp:include page="foot.jsp" />
            </div>
        </div>
    </body>
    <%
                    } else { %>
    <jsp:forward page="login.jsp"><jsp:param name="uwaga" value="autoryzacja" /></jsp:forward>
    <%              }%>
</html>
