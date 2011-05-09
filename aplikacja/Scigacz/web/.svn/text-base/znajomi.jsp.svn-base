<%--
    Document   : znajomi
    Created on : 2010-01-03, 20:15:08
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
                <jsp:include page="top.jsp?m=4" />
                <jsp:useBean id="znajomiUzytkownika" type="List<ScigaczDB.Znajomi>" scope="session" />
                <div id="content">
                    <h1>Moi znajomi</h1>
                    <%
                    int size = znajomiUzytkownika.size();
                    if (size != 0) {
                    %>
                    <form action="ZnajomekServlet" method="POST" name="znajomekServlet">
                        <table>
                            <thead>
                                <tr>
                                    <th id="td30"><input type="checkbox" id="CheckAll" onClick="checkAll(document)" /></th>
                                    <th id="td50">Lp.</th>
                                    <th id="td240">Login</th>
                                    <th id="td300">Imię i nazwisko</th>
                                    <th id="td240">Grupa</th>
                                    <th id="td50"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                for (int i = 0; i < size; i++) {
                                    ScigaczDB.Uzytkownicy znajomy = (ScigaczDB.Uzytkownicy) (znajomiUzytkownika.get(i).getIdZnajomego());
                                %>
                                <tr>
                                    <td><input type="checkbox" id="cb<%= i + 1%>" name="znajomek" value="<%= znajomy.getIdUzytkownika() %>" /></td>
                                    <td><%= i + 1%>.</td>
                                    <td><a href="OsobaServlet?id=<%= znajomy.getIdUzytkownika()%>"><%= znajomy.getLogin()%></a></td>
                                    
                                    <td><a href="OsobaServlet?id=<%= znajomy.getIdUzytkownika()%>"><%= znajomy.getImie()%>

                                            <%= znajomy.getNazwisko()%></a></td>
                                    <td><%= (!znajomiUzytkownika.get(i).getCzyBliskiUzytkownika()) ? "Znajomy" : "Bliski znajomy"%></td>
                                <td class="edit">
                                    
                                    <a href="EdytujZnajomoscServlet?id=<%= znajomy.getIdUzytkownika()%>"><% if (znajomiUzytkownika.get(i).getCzyBliskiUzytkownika()) { %>
                                        <img src="obrazki/minus.png" alt=""><% } else { %>
                                        <img src="obrazki/plus.png" alt=""><% } %>
                                    </a>
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
                        <img src="obrazki/plus.png" alt=""><span>zmień na bliskiego znajomego</span><br>
                        <img src="obrazki/minus.png" alt=""><span>zmień na znajomego</span><br>
                    </div>

                    <!--<div id="strony">
                        Poprzednia
                        1
                        <a href="#">2</a>
                        <a href="#">Następna</a>
                    </div>
                    <a href="#" class="button" id="butZnajomy">edytuj znajomość</a>-->
                    <% } else {%>
                    <div id="info">Nie masz znajomych :(</div>
                    <% }%>
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
