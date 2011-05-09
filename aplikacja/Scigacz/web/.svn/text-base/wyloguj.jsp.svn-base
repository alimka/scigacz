<%-- 
    Document   : wyloguj
    Created on : 2010-01-05, 13:04:06
    Author     : Kasia Ko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

            session = request.getSession();
            session.removeAttribute("authorized");
            session.removeAttribute("login");
            session.removeAttribute("passwd");

            session.removeAttribute("wiadomosciWyslane");
            session.removeAttribute("wiadomosciOdebrane");
            session.removeAttribute("wiadomosciSystemowe");

            session.removeAttribute("managerUzytkownika");
            session.removeAttribute("managerZasobow");
            session.removeAttribute("managerZnajomych");
            session.removeAttribute("managerOpinii");
            session.removeAttribute("managerSkrzynki");
            session.removeAttribute("managerRezerwacji");
            session.removeAttribute("managerWypozyczen");

            session.removeAttribute("uzytkownik");
            session.removeAttribute("znajomiUzytkownika");
            session.removeAttribute("zasobyUzytkownika");
            session.removeAttribute("opinieUzytkownika");

            session.removeAttribute("ziomek");
            session.removeAttribute("zasobyZiomka");
            session.removeAttribute("opinieZiomka");
            session.removeAttribute("czyZnajomy");

            session.removeAttribute("wypozyczone");
            session.removeAttribute("udostepnione");
            session.removeAttribute("zasob");
            session.removeAttribute("uzytkownicy");
            session.removeAttribute("akcja");
            session.removeAttribute("prosbaWyslana");
            %>
<jsp:include page="login.jsp"><jsp:param name="uwaga" value="wylogowano" /></jsp:include>
<%
            //response.sendRedirect(response.encodeRedirectURL(""));
%>
