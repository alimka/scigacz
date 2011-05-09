<%--
    Document   : index
    Created on : 2009-12-09, 10:06:29
    Author     : pedros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
            String authorized = (String) request.getAttribute("authorized");
            if (authorized != null) {
                session.setAttribute("authorized", authorized);
            }

            authorized = (String) session.getAttribute("authorized");
            if (authorized == "yes") {
                if (session.getAttribute("login") != null) {
%>
<jsp:forward page="backup.jsp"></jsp:forward>
<% } else {%>

<jsp:forward page="konto.jsp"/>

<%       }
            } else {
                if (authorized == "no") {
                    session.removeAttribute("authorized");
%>

<jsp:forward page="login.jsp"><jsp:param name="uwaga" value="logowanie" /></jsp:forward>

<%              } else {
%>

<jsp:forward page="login.jsp"></jsp:forward>

<%              }
            }
%>