<%--
    Document   : register
    Created on : 2009-12-13, 14:43:17
    Author     : Kasia Ko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            String rejClicked = (String) request.getAttribute("rej");
            if (rejClicked != null) {
                session.setAttribute("registered", "yes");
            } else {
                session.setAttribute("registered", "no");
            }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ścigacz - serwis wyczesany w kosmos</title>
        <link rel="stylesheet" type="text/css" href="base.css">
        <script type="text/javascript" src="livevalidation.js"></script>
    </head>
    <body>
        <div id="bg">
            <div id="reg">
                <%
            String registered = (String) session.getAttribute("registered");
            if (registered.equals("no")) { %>

                <h1>Rejestracja</h1>
                <%                String sizeOK = (String) request.getAttribute("sizeOK");
                                if (sizeOK != null) {%>
                <div id="uwaga">Za duzy obrazek</div>
                <%                                }
                %>
                <form id="regform" action="RegisterServlet" method="POST" enctype="multipart/form-data">
                    <p><label>Login*</label><input type="text" id="login" name="login" class="reginput" /></p>
                    <p><label>Hasło*</label><input type="password" id="haslo" name="haslo" class="reginput" /></p>
                    <p><label>Powtórz hasło*</label><input type="password" id="haslo1" class="reginput" /></p>
                    <br>
                    <p><label>Imię*</label><input type="text" id="imie" name="imie" class="reginput" /></p>
                    <p><label>Nazwisko*</label><input type="text" id="nazwisko" name="nazwisko" class="reginput" /></p>
                    <p><label>E-mail*</label><input type="text" id="email" name="email" class="reginput" /></p>
                    <p><label>Telefon</label><input type="text" id="telefon" name="telefon" class="reginput" /></p>
                    <p><label>Miasto*</label><input type="text"  id="miasto" name="miasto" class="reginput" /></p>
                    <p><label>Województwo*</label><select id="wojew" name="wojew">
                            <option></option>
                            <option>dolnośląskie</option>
                            <option>kujawsko-pomorskie</option>
                            <option>lubelskie</option>
                            <option>lubuskie</option>
                            <option>łódzkie</option>
                            <option>małopolskie</option>
                            <option>mazowieckie</option>
                            <option>opolskie</option>
                            <option>podkarpackie</option>
                            <option>podlaskie</option>
                            <option>pomorskie</option>
                            <option>śląskie</option>
                            <option>świętokrzyskie</option>
                            <option>warmińsko-mazurskie</option>
                            <option>wielkopolskie</option>
                            <option>zachodniopomorskie</option>
                        </select></p>
                    <p><label>Avatar</label><input type="file" name="avatar" value="" accept="image/jpeg,image/gif,image/png,image/bmp" /></p>
                    <p id="checkbox">*  - pola wymagane</p>

                    <p id="checkbox"><input type="checkbox" id="checkReg1" checked /><span id="text">Akceptuję <a href="regul.jsp">regulamin</a></span></p>
                    <p id="checkbox"><input type="checkbox" id="checkReg2" checked /><span id="text">Zgadzam się na przetwarzanie moich danych osobowych</span></p>

                    <input type="submit" value="Zarejestruj" name="rej" id="submit1" />
                </form>
                <script type="text/javascript">

                    var f1 = new LiveValidation( 'login', {onlyOnSubmit: true } );
                    f1.add( Validate.Presence );
                    f1.add( Validate.Format, { pattern: /^([a-zA-z]+)([-._a-zA-Z0-9]+)$/i } );

                    var f2 = new LiveValidation('haslo1');
                    f2.add( Validate.Confirmation, { match: 'haslo' } );
                    var f21 = new LiveValidation( 'haslo', {onlyOnSubmit: true } );
                    f21.add( Validate.Presence );
                    var f22 = new LiveValidation( 'haslo1', {onlyOnSubmit: true } );
                    f22.add( Validate.Presence );

                    var f3 = new LiveValidation( 'imie', {onlyOnSubmit: true } );
                    f3.add( Validate.Presence );
                    f3.add( Validate.Format, { pattern: /^([^'"\*\?\:#$%&`~\(\)]+)$/i } );

                    var f4 = new LiveValidation( 'nazwisko', {onlyOnSubmit: true } );
                    f4.add( Validate.Presence );
                    f4.add( Validate.Format, { pattern: /^([^'"\*\?\:#$%&`~\(\)]+)$/i } );

                    var email = new LiveValidation( 'email', {onlyOnSubmit: true } );
                    email.add( Validate.Presence );
                    email.add( Validate.Email );
                    email.add( Validate.Format, { pattern: /^([a-zA-z]+)([-.@_a-zA-Z0-9]+)$/i } );

                    var f5 = new LiveValidation( 'miasto', {onlyOnSubmit: true } );
                    f5.add( Validate.Presence );
                    f5.add( Validate.Format, { pattern: /^([^'"\*\?\:#$%&`~\(\)]+)$/i } );

                    var f6 = new LiveValidation( 'wojew', {onlyOnSubmit: true } );
                    f6.add( Validate.Presence );

                    var f7 = new LiveValidation( 'checkReg1', {onlyOnSubmit: true} );
                    f7.add( Validate.Acceptance );

                    var f8 = new LiveValidation( 'checkReg2', {onlyOnSubmit: true} );
                    f8.add( Validate.Acceptance );

                    var f9 = new LiveValidation( 'telefon', {onlyOnSubmit: true} );
                    f9.add( Validate.Numericality, { onlyInteger: true } );

                    var automaticOnSubmit = f1.form.onsubmit;
                    f1.form.onsubmit = function(){
                        var valid = automaticOnSubmit();
                        if(valid) {
                            submit();
                        }
                        return false;
                    }
                </script>

                <%
                String loginZajety = (String) request.getAttribute("loginZajety");
                String emailZajety = (String) request.getAttribute("emailZajety");
                String obaZajete = (String) request.getAttribute("obaZajete");
                if (obaZajete!= null && obaZajete.equals("tak")) {
                %>
                <script type="text/javascript">
                    alert("Taka osoba już się zarejestrowała.");
                </script>
                <% } else {
                    if (loginZajety!= null && loginZajety.equals("tak")) {
                %>
                <script type="text/javascript">
                    alert("Taki login już istnieje. Wybierz inny.");
                </script>
                <% }
                if (emailZajety!= null && emailZajety.equals("tak")) {
                %>
                <script type="text/javascript">
                    alert("Ktoś już zarejestrował się na ten email.");
                </script>
                <% }
                    }
                    } else {
                %>

                <p class="komunikat">Zostałeś poprawnie zarejestrowany. Możesz się teraz zalogować.</p>
                <a href="./">Powrót do strony logowania</a>

                <%  }%>
            </div>
        </div>
    </body>

</html>
