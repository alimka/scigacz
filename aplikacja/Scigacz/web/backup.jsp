<%-- 
    Document   : backup
    Created on : 2010-01-16, 22:19:40
    Author     : Kasia Ko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    if(session.getAttribute("login")!=null) {
    %>
    <body>
        <div id="bg">
            <div id="main">
                <jsp:include page="top.jsp" />
                <div id="content">

                    <h1>Tworzenie kopii bezpieczeństwa</h1>
                    <div>
                        <h2>Informacje ogólne</h2>
                        <p class="tekst">
                            Poral Scigacz wspiera tworzenie kopii bezpieczeństwa. Nowe kopie twrzone są co 24 godziny
                            i przechowywane w folderze: "Aplikacja/Scigacz/back_up". Pliki zapisywane są pod nazwami o formacie:
                            "yy-MM-dd_HH-mm-ss" (dwucyfrowyRok-miesiąc-dzien_godzina-minuta-sekunda). W folderze back_up znajduje się co
                            najwyżej 10 plików. Przestarzałe kopie są automatycznie usuwane.</p>
                    </div>
                    <div>
                        <h2>Wymagania</h2>
                        <p class="tekst">
                            Portal Ścigacz do tworzenia kopii bezpieczeństwa używa programu mysqldump.exe. W razie problemów proszę
                            sprawdzić czy istnieje program i odpowiednia zmienna systemowa (PATH = "katalog bin w którym znaduje się
                            program mysqldump.exe")
                        </p>
                    </div>
                    <div>
                        <h2>Przywracanie bazy danych</h2>
                        <p class="tekst">
                            Aby przywrócić bazę danych administrator może skorzystać z programu mysql.exe. W tym celu należy z wiersza
                            poleceń wywołać komendę:<br>
                            <code>mysql --password=haslo --user=login nazwaBazy < wybranaKopia.sql</code>
                        </p>
                    </div>
                    <div>
                        <h2>Kontakt</h2>
                        <p class="tekst">
                            PortalScigacz@gmail.com
                        </p>
                    </div>
                    <h2>Dziękujemy!</h2>

                    <div id="blank"></div>
                </div>
            </div>
        </div>
    </body>
    <% } else { %>
    <body>
        <div id="bg">
            <div id="main">
                <jsp:include page="top.jsp?m=5" />
                <jsp:useBean id="uzytkownik" type="ScigaczDB.Uzytkownicy" scope="session" />
                <div id="content">
                    <%
                    if (uzytkownik.getUprawnienia() == 1) {
                    %>
                    <h1>Tworzenie kopii bezpieczeństwa</h1>
                    <div>
                        <h2>Informacje ogólne</h2>
                        <p class="tekst">
                            Poral Scigacz wspiera tworzenie kopii bezpieczeństwa. Nowe kopie twrzone są co 24 godziny
                            i przechowywane w folderze: "Aplikacja/Scigacz/back_up". Pliki zapisywane są pod nazwami o formacie:
                            "yy-MM-dd_HH-mm-ss" (dwucyfrowyRok-miesiąc-dzien_godzina-minuta-sekunda). W folderze back_up znajduje się co
                            najwyżej 10 plików. Przestarzałe kopie są automatycznie usuwane.</p>
                    </div>
                    <div>
                        <h2>Wymagania</h2>
                        <p class="tekst">
                            Portal Ścigacz do tworzenia kopii bezpieczeństwa używa programu mysqldump.exe. W razie problemów proszę
                            sprawdzić czy istnieje program i odpowiednia zmienna systemowa (PATH = "katalog bin w którym znaduje się
                            program mysqldump.exe")
                        </p>
                    </div>
                    <div>
                        <h2>Przywracanie bazy danych</h2>
                        <p class="tekst">
                            Aby przywrócić bazę danych administrator może skorzystać z programu mysql.exe. W tym celu należy z wiersza
                            poleceń wywołać komendę:<br>
                            <code>mysql --password=haslo --user=login nazwaBazy < wybranaKopia.sql</code>
                        </p>
                    </div>
                    <div>
                        <h2>Kontakt</h2>
                        <p class="tekst">
                            PortalScigacz@gmail.com
                        </p>
                    </div>
                    <h2>Dziękujemy!</h2>
                                   
                    <% } else {%>
                    <div id="uwaga">Nie masz odpowiednich uprawnień do przeglądania tej strony.</div>
                    <% }%>
                    <div id="blank"></div>
                </div>
            </div>
        </div>
    </body>
    <% }
                    } else {%>
    <jsp:forward page="login.jsp"><jsp:param name="uwaga" value="autoryzacja" /></jsp:forward>
    <%              }%>
</html>
