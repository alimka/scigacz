<%-- 
    Document   : edytujKonto
    Created on : 2010-01-05, 17:31:16
    Author     : kot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    %>
    <body>
        <div id="bg">
            <div id="main">
                <jsp:include page="top.jsp" />
                <jsp:useBean id="uzytkownik" type="ScigaczDB.Uzytkownicy" scope="session" />
                <div id="content">
                    <div id="formZmiany">
                        <h1>Edytuj dane konta</h1>

                        <form id="zmienDaneForm" action="EdytujKontoServlet" method="POST" enctype="multipart/form-data">
                            <label>Imię</label><input type="text" id="imie" name="imie" value="<%= uzytkownik.getImie()%>" /><br>
                            <label>Nazwisko</label><input type="text" id="nazwisko" name="nazwisko" value="<%= uzytkownik.getNazwisko()%>" /><br>
                            <label>E-mail</label><input type="text" id="email" name="email" value="<%= uzytkownik.getEmail()%>" /><br>
                            <label>Miasto</label><input type="text" id="miasto" name="miasto" value="<%= uzytkownik.getMiasto()%>" /><br>
                            <label>Telefon</label><input type="text" id="telefon" name="telefon" value="<%= uzytkownik.getTelefon()%>" /><br>
                            <label>Województwo</label><select id="wojew" name="wojew">
                                <option<%= (uzytkownik.getWojewodztwo().equals("dolnośląskie"))?" selected":"" %>>dolnośląskie</option>
                                <option<%= (uzytkownik.getWojewodztwo().equals("kujawsko-pomorskie"))?" selected":"" %>>kujawsko-pomorskie</option>
                                <option<%= (uzytkownik.getWojewodztwo().equals("lubelskie"))?" selected":"" %>>lubelskie</option>
                                <option<%= (uzytkownik.getWojewodztwo().equals("lubuskie"))?" selected":"" %>>lubuskie</option>
                                <option<%= (uzytkownik.getWojewodztwo().equals("łódzkie"))?" selected":"" %>>łódzkie</option>
                                <option<%= (uzytkownik.getWojewodztwo().equals("małopolskie"))?" selected":"" %>>małopolskie</option>
                                <option<%= (uzytkownik.getWojewodztwo().equals("mazowieckie"))?" selected":"" %>>mazowieckie</option>
                                <option<%= (uzytkownik.getWojewodztwo().equals("opolskie"))?" selected":"" %>>opolskie</option>
                                <option<%= (uzytkownik.getWojewodztwo().equals("podkarpackie"))?" selected":"" %>>podkarpackie</option>
                                <option<%= (uzytkownik.getWojewodztwo().equals("podlaskie"))?" selected":"" %>>podlaskie</option>
                                <option<%= (uzytkownik.getWojewodztwo().equals("pomorskie"))?" selected":"" %>>pomorskie</option>
                                <option<%= (uzytkownik.getWojewodztwo().equals("śląskie"))?" selected":"" %>>śląskie</option>
                                <option<%= (uzytkownik.getWojewodztwo().equals("świętokrzyskie"))?" selected":"" %>>świętokrzyskie</option>
                                <option<%= (uzytkownik.getWojewodztwo().equals("warmińsko-mazurskie"))?" selected":"" %>>warmińsko-mazurskie</option>
                                <option<%= (uzytkownik.getWojewodztwo().equals("wielkopolskie"))?" selected":"" %>>wielkopolskie</option>
                                <option<%= (uzytkownik.getWojewodztwo().equals("zachodniopomorskie"))?" selected":"" %>>zachodniopomorskie</option>
                            </select><br>
                            <label>Avatar</label><img alt="" src="obrazki/<%= uzytkownik.getAvatar()%>" class="avatar" />
                            <input type="file" name="avatar" value="" /><br>

                            <input type="submit" value="edytuj dane" name="edytuj" class="button" id="submit1" /><br>
                        </form>
                <script type="text/javascript">

                    var f1 = new LiveValidation( 'imie', {onlyOnSubmit: true } );
                    f1.add( Validate.Presence );
                    f1.add( Validate.Format, { pattern: /^([^'"\*\?\:#$%&`~\(\)]+)$/i } );

                    var f2 = new LiveValidation( 'nazwisko', {onlyOnSubmit: true } );
                    f2.add( Validate.Presence );
                    f2.add( Validate.Format, { pattern: /^([^'"\*\?\:#$%&`~\(\)]+)$/i } );

                    var email = new LiveValidation( 'email', {onlyOnSubmit: true } );
                    email.add( Validate.Presence );
                    email.add( Validate.Email );
                    email.add( Validate.Format, { pattern: /^([a-zA-z]+)([-.@_a-zA-Z0-9]+)$/i } );

                    var f3 = new LiveValidation( 'miasto', {onlyOnSubmit: true } );
                    f3.add( Validate.Presence );
                    f3.add( Validate.Format, { pattern: /^([^'"\*\?\:#$%&`~\(\)]+)$/i } );

                    var f4 = new LiveValidation( 'wojew', {onlyOnSubmit: true } );
                    f4.add( Validate.Presence );

                    var f5 = new LiveValidation( 'telefon', {onlyOnSubmit: true} );
                    f5.add( Validate.Numericality, { onlyInteger: true } );

                    var automaticOnSubmit = f1.form.onsubmit;
                    f1.form.onsubmit = function(){
                        var valid = automaticOnSubmit();
                        if(valid) {
                            submit();
                        }
                        return false;
                    }
                </script>

                        <form id="zmienHasloForm" action="EdytujKontoServlet" method="POST">
                            <label>Obecne hasło</label><input type="password" id="psswd" name="psswd" value="" /><br>
                            <label>Nowe hasło</label><input type="password" id="newpsswd" name="newpsswd" value="" /><br>
                            <label>Powtórz hasło</label><input type="password" id="newpsswd2" name="newpsswd2" value="" /><br>

                            <input type="submit" value="zmień hasło" name="edytuj" class="button" id="submit1" /><br>
                        </form>
                <script type="text/javascript">

                    var h1 = new LiveValidation('newpsswd2');
                    h1.add( Validate.Confirmation, { match: 'newpsswd' } );
                    var h2 = new LiveValidation( 'psswd', {onlyOnSubmit: true } );
                    h2.add( Validate.Presence );
                    var h3 = new LiveValidation( 'newpsswd', {onlyOnSubmit: true } );
                    h3.add( Validate.Presence );
                    var h4 = new LiveValidation( 'newpsswd2', {onlyOnSubmit: true } );
                    h4.add( Validate.Presence );

                    var automaticOnSubmit2 = h1.form.onsubmit;
                    h1.form.onsubmit = function(){
                        var valid = automaticOnSubmit2();
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
