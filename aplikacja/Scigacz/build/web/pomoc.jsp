<%-- 
    Document   : pomoc
    Created on : 2010-01-27, 12:08:50
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

    </head>
    <body>
        <div id="bg">
            <div id="main">
                <jsp:include page="top.jsp" />
                <div id="content" class="doc">
                    <h1>Dokumentacja użytkownika</h1>
                    <div>
                        <h3>Czym jest Ścigacz?</h3>
                        Ścigacz jest to serwis dostępny przez przeglądarkę internetową służący do komunikowania się z osobami,
                        pragnącymi wypożyczać przedmioty użytku codziennego, oraz badający czas i prawidłowość zwracania przedmiotów.
                    </div>
                    <div>
                        <h3>Rejestracja</h3>
                         Niezbędne jest, aby użytkownik korzystający z systemu był zarejestrowany. Rejestracja polega na uzupełnieniu
                         wymaganych pól (oznaczonych znakiem gwiazdki) w podanym formularzu oraz zaakceptowaniu regulaminu, a następnie
                         potwierdzeniu przyciskiem "Zarejestruj".
                    </div>
                    <div>
                        <h3>Logowanie</h3>
                        Aby się zalogować, należy w przeglądarce wpisać adres serwisu, po uruchomieniu strony w odpowiednich polach
                        należy wpisać login oraz hasło, a następnie kliknąć przycisk "Zaloguj". Wymagane jest posiadanie konta.
                        Jeżeli nie masz konta, patrz Rejestracja.
                    </div>

                    <div>
                        <h3>Wyszukiwanie</h3>
                        Do wyszukiwania należy użyć formularza dostępnego na górze każdej strony serwisu. Można wyszukiwać dostępne
                        zasoby oraz użytkowników systemu. Wynikiem wyszukiwania jest lista znalezionych zapytań wyświetlonych w tabeli.
                    </div>

                    <h2>Konto</h2>
                    <div>
                        <div>
                            <h3>Zmiana danych</h3>
                            Dane użytkownika mogą być przez niego zmienione w każdej chwili. Aby tego dokonać, należy w zakładce konto
                            wybrać przycisk "edytuj dane". Na nowo otwartej stronie należy podać nowe dane, które mają być zmienione.
                            Zatwierdzenie odbywa się poprzez wybranie przycisku "edytuj dane".
                        </div>
                        <div>
                            <h3>Zmiana zdjęcia/avatara</h3>
                            Avatar można zmienić na stronie edycji danych, ale nie może być on większy niż 500KB. Patrz zmiana danych.
                        </div>
                        <div>
                            <h3>Zmiana hasła</h3>
                            Hasło można zmienić na stronie edycji danych w dolnym formularzu. Należy podać stare hasło oraz dla
                            potwierdzenia dwa razy nowe hasło. Zatwierdzić należy wybierając "zmień hasło".
                        </div>
                    </div>

                    <h2>Zasoby</h2>
                    <div>
                        <div>
                            <h3>Dodawanie zasobu</h3>
                            W zakładce Zasoby należy wybrać Dodaj nowy. W formularzu należy podać dane o przedmiocie, który jest do
                            wypożyczenia. Niezbędne jest wypełnienie pól: nazwa, opis, czas wypożyczenia (mierzony w dniach, tygodniach
                            lub miesiącach) oraz wybór kategorii z rozwijalnej listy.
                            Dodanie zdjęć wypożyczanego przedmiotu jest opcjonalne.
                            Udostępnienie:<br>
                            - wszystkim - przedmiot będą mogli wypożyczyć wszyscy użytkownicy serwisu<br>
                            - znajomym - przedmiot będzie dostępny dla użytkowników dodanych do znajomych (patrz znajomi)<br>
                            - bliskim znajomym - przedmiot będą mogli zobaczyć i wypożyczyć tylko użytkownicy oznaczeni jako bliscy
                            znajomi (patrz znajomi)
                        </div>
                        <div>
                            <h3>Edytowanie zasobu</h3>
                            Informacje o dodanym zasobie można modyfikować używając podanego formularza. Aby uruchomić stronę z tym
                            formularzem należy w tabeli z listą zasobów kliknąć w ikonkę ołówka lub na stronie podglądu zasobu wybrać
                            przycisk "edytuj". Reszta odbywa się podobnie, jak przy dodawaniu nowego zasobu.
                        </div>
                        <div>
                            <h3>Usuwanie zasobu</h3>
                            W tabeli z listą zasobów należy zaznaczyć kwadracik w rzędzie przedmiotu, który ma zostać usunięty.
                            Następnie należy wybrać przycisk "usuń zaznaczone". Do zaznaczenia/odznaczenia wszystkich wierszy można
                            używać pierwszego kwadracika.
                        </div>
                    </div>

                    <h2>Znajomi</h2>
                    <div>
                        <div>
                            <h3>Dodawanie znajomych</h3>
                            Aby dodać użytkownika do list znajomych należy na stronie podglądu danego użytkownika wybrać przycisk
                            "zaproś do znajomych". Wtedy zostanie przesłana wiadomość do tego użytkownika z zaproszeniem
                            do wspólnej znajomości. Jeżeli odpowie twierdząco, obie osoby zostaną na przemian dodane do list znajomych
                            drugiego użytkownika. Najłatwiejszym sposobem znajdowania nowych znajomych jest użycie wyszukiwarki.
                        </div>
                        <div>
                            <h3>Edycja stopnia znajomości</h3>
                            Od stopnia znajomości zależy ilość dostępnych przedmiotów do wypożyczenia, im bliższy stopień tym więcej
                            zasobów dany użytkownik zobaczy. Aby zmienić stopień znajomości należy kliknąć ikonkę zielonego znaku
                            dostępną w tabeli z listą znajomych użytkowników.<br>
                            Znak plus - zmiana na bliższą znajomość,<br>
                            znak minus -  obniżenie stopnia znajomości.<br>
                            Uwaga! Stopień znajomości nie jest obustronny.
                        </div>
                        <div>
                            <h3>Usuwanie znajomości</h3>
                            W tabeli z listą znajomych należy zaznaczyć kwadracik w wierszu znajomości, która ma zostać usunięta.
                            Następnie należy wybrać przycisk "usuń zaznaczone". Do zaznaczenia/odznaczenia wszystkich wierszy można
                            używać pierwszego kwadracika.
                        </div>
                    </div>

                    <h2>Skrzynka</h2>
                    <div>
                        <div>
                            <h3>Wiadomości systemowe</h3>
                            Wiadomości wysyłane automatycznie przez system nazywane są wiadomościami systemowymi. Do takich wiadomości
                            należy:<br>
                            - zapytanie z zaproszeniem do znajomych<br>
                            - zapytanie o wypożyczenie zasobu<br>
                            - informacja o zgodzie na wypożyczenie<br>
                            - informacja o zbliżającym się terminie oddania przedmiotu
                        </div>
                        <div>
                            <h3>Czytanie wiadomości</h3>
                            Nieodczytane wiadomości w tabeli z listą wiadomości danego typu zapisane są pogrubioną czcionką. Aby je
                            odczytać należy kliknąć temat wiadomości. Pojawi się nowa strona z treścią wiadomości.
                        </div>
                        <div>
                            <h3>Wysyłanie wiadomości</h3>
                            Można wysyłać wiadomości innym użytkownikom serwisu. Aby tego dokonać na stronie z podglądem informacji
                            o użytkowniku należy wybrać przycisk "wyślij wiadomość". Można również odpisywać na odebrane wiadomości,
                            na stronie podglądu wiadomości należy użyć przycisku "Odpisz". W obu przypadkach pojawi się małe okienko
                            w którym należy wpisać temat wiadomości oraz treść.<br>
                            Poprzez wybranie "Napisz" w zakładce skrzynki otworzy się okienko w którym oprócz tematu i treści należy
                            wpisać nadawcę - login użytkownika serwisu.
                        </div>
                        <div>
                            <h3>Usuwanie wiadomości</h3>
                            W tabeli z listą wiadomości danego typu należy zaznaczyć kwadracik w wierszu wiadomości, która ma zostać
                            usunięta. Następnie należy wybrać przycisk "usuń zaznaczone". Do zaznaczenia/odznaczenia wszystkich wierszy
                            można używać pierwszego kwadracika.
                        </div>
                    </div>

                    <h2>Pożyczanie</h2>
                    <div>
                        <div>
                            <h3>Wypożyczanie przedmiotu</h3>
                            Na stronie podglądu przedmiotu, jeżeli użytkownik może dokonać wypożyczenia, będzie widoczny przycisk
                            "wypożycz". Po jego kliknięciu zostanie wysłana wiadomość do właściciela z prośbą o wypożyczenie. Jeżeli
                            właściciel odpowie twierdząco, zostanie wysłana wiadomość informacyjna do użytkownika zainteresowanego
                            danym przedmiotem. Następnie użytkownicy wymieniają między sobą wiadomości, aby umówić się co do spotkania
                            i przekazania przedmiotu. Najłatwiejszym sposobem znajdowania zasobów jest użycie wyszukiwarki.
                        </div>
                        <div>
                            <h3>Zwrot przedmiotu</h3>
                            Jeżeli przedmiot został zwrócony właścicielowi, do zapisania tego w systemie użytkownik wypożyczający
                            powinien użyć przycisku "oddaj zasób" w tabeli z listą pożyczeń. Wtedy do właściciela przedmiotu zostanie
                            wysłana wiadomość z informacją o oddaniu zasobu, zostanie on poproszony o potwierdzenie tej informacji.
                            Dopiero wtedy przedmiot będzie mógł być ponownie wypożyczony w serwisie.
                        </div>
                    </div>

                    <h2>Opinie</h2>
                    <div>
                        <div>
                            <h3>Wystawianie opinii</h3>
                            Opinia jest informacją o poczynaniach danego użytkownika. Można wystawić opinię użytkownikowi poprzez
                            wybranie przycisku "wystaw opinię" na stronie podglądu użytkownika.
                        </div>
                        <div>
                            <h3>Czytanie opinii</h3>
                            Lista opinii o użytkowniku dostępna jest na jego stronie podglądu.
                        </div>
                    </div>

                    <h2>Nadużycia</h2>
                    <div>
                        <div>
                            <h3>Zgłaszanie nadużyć</h3>
                            Jeżeli zostanie zauważone nadużycie, użytkownik może zgłosić je administratorowi serwisu poprzez wybranie
                            przycisku na stronie podglądu użytkownika, "zgłoś nadużycie". W nowo otwartym oknie należy dopisać krótką
                            informację uzasadniającą dane zgłoszenie.
                        </div>
                    </div>

                    <h2>Blokada</h2>
                    <div>
                        <div>
                            <h3>Blokada użytkownika</h3>
                            Użytkownik może zostać zablokowany przez administratora w przypadku rażących nadużyć, łamania regulaminu.
                            Odblokowanie także odbywa się z woli administratora.
                        </div>
                    </div>
                    <div id="blank"></div>
                </div>
            </div>
        </div>
    </body>
</html>

