/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package czak;

import czak.manager.ManagerNadManagery;
import czak.manager.ManagerWypozyczen;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.*;

/**
 * @author Paulina Łoś
 * Obsługa timera sterującego modułem CZAK
 * Czak co 24h:
 *      a) robi back-up
 *      b) wysyła wiadomości systemowe
 *      c) wysyła wiadomości mailowe
 *      d) do rozszerzenia : wysyła wiadomości sms-owe
 */
public class Main {

    public static ManagerWypozyczen mw;

    /** Uruchomienie timera i nadanie mu wartości początkowych
     *      - od kiedy zacząć odliczanie
     *      - jakie odstępy czasu odmierzać
     *      - jaką akcję uruchamiać
     */
    public static void main(String[] args) throws SQLException, ParseException {
        mw = new ManagerWypozyczen();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> sf1 = executorService.scheduleAtFixedRate(new InfoCzasJuzUplynal(), 0, 24, TimeUnit.HOURS);
        ScheduledFuture<?> sf2 = executorService.scheduleAtFixedRate(new InfoDzisKoniec(), 0, 24, TimeUnit.HOURS);
        ScheduledFuture<?> sf3 = executorService.scheduleAtFixedRate(new InfoZostalTydzien(), 0, 24, TimeUnit.HOURS);
        ScheduledFuture<?> sf4 = executorService.scheduleAtFixedRate(new BackUp(), 0, 24, TimeUnit.HOURS);

    }
}
