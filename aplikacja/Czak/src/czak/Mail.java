
package czak;

import czak.entity.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Paulina
 * Obsługa wysyłania maili. Parametry
 * 1 - za tydzień upływa termin
 * 2 - dziś upływa termin
 * 3 - upłynął termin
 *
 */
public class Mail {
        List<Wypozyczenia> lista;
        int typ;
        String nadawca;
        String smtpServ;
    Mail(){}
    Mail(int _typ, List<Wypozyczenia> _lista){
        typ = _typ;
        nadawca = "PortalScigacz@gmail.com";
        smtpServ = "smtp.gmail.com";
        lista = _lista;
    }
    /**
     * Funkcja wysyłająca maile od PortalScigacz o typie = parametr do wszystkich
     * Wypożyczaczy znajdujących się na liście
     * w przypadku typu maila: 1 lub 2 mail wysyłany jest też do Udostępniaczy
     */
    void wyslijMaileDoWszystkichZListy(){
        String imieUdostepniacza;
        String nazwiskoUdostepniacza;
        String emailUdostepniacza;
        String imieWypozyczacza;
        String nazwiskoWypozyczacza;
        String emailWypozyczacza;
        String nazwaZasobu;
        String komunikat;
        String temat;
        String komunikat2;
        Date dataZwrotu;
        int opoznienie;

              for(int i =0;i<lista.size();i++){
                   imieUdostepniacza=lista.get(i).getIdUdostepniacza().getImie();
                   nazwiskoUdostepniacza=lista.get(i).getIdUdostepniacza().getNazwisko();
                   emailUdostepniacza=lista.get(i).getIdUdostepniacza().getEmail();
                   imieWypozyczacza=lista.get(i).getIdWypozyczacza().getImie();
                   nazwiskoWypozyczacza=lista.get(i).getIdWypozyczacza().getNazwisko();
                   emailWypozyczacza=lista.get(i).getIdWypozyczacza().getEmail();
                   nazwaZasobu=lista.get(i).getIdZasobu().getNazwa();
                   dataZwrotu = lista.get(i).getDataZwrotu();
               switch(typ){
                   case(1):
                       temat="ZA TYDZIEŃ UPŁYWA TERMIN WYPOŻYCZENIA !!!";
                       komunikat = "UWAGA UWAGA UWAGA \n"+
                            imieWypozyczacza+"!!!\n"+
                            "Za tydzień upływa termin wypożyczenia przez Ciebie zasobu: " + nazwaZasobu+".\n"+
                            "Właściciel "+imieUdostepniacza+" "+nazwiskoUdostepniacza+ " prosi o zwrot na czas.";
                       komunikat2 = "UWAGA UWAGA UWAGA \n"+imieUdostepniacza+"!!!\n"+
                               "Za tydzień użytkownik "+imieWypozyczacza+" "+
                               nazwiskoWypozyczacza+" zwraca Ci: "+ nazwaZasobu+".\n";
                       wyslijMail(emailWypozyczacza,temat,komunikat);
                       wyslijMail(emailUdostepniacza,temat,komunikat2);
                       break;
                   case(2):
                       temat="DZIŚ UPŁYWA TERMIN WYPOŻYCZENIA !!!";
                       komunikat = "UWAGA UWAGA UWAGA \n"+
                               imieWypozyczacza+"!!!\n"+
                            "Dziś upływa termin wypożyczenia przez Ciebie zasobu: " + nazwaZasobu+".\n"+
                            "Własciciel "+imieUdostepniacza+" "+nazwiskoUdostepniacza+ " prosi o szybki kontakt.";
                       komunikat2 = "UWAGA UWAGA UWAGA \n"+imieUdostepniacza+"!!!\n"+
                               "Dziś mija termin do którego użytkownik "+imieWypozyczacza+" "+
                               nazwiskoWypozyczacza+" powinien zwrócić Ci: "+ nazwaZasobu+".\n";

                       wyslijMail(emailWypozyczacza,temat,komunikat);
                       wyslijMail(emailUdostepniacza,temat,komunikat2);
                       break;

                   case(3):
                        Date today = new Date();
                        opoznienie = (int) ((today.getTime() - dataZwrotu.getTime()) / (1000 * 60 * 60 * 24));
                        temat = "!!!  ILOŚć DNI OPÓŹNIENIA: "+opoznienie+"  !!!";
                        komunikat = "UWAGA UWAGA UWAGA \n"+
                            imieWypozyczacza+"\n"+
                            "Upłynął już termin wypożyczenia przez Ciebie zasobu: " + nazwaZasobu+".\n"+
                            "Własciciel "+imieUdostepniacza+" "+nazwiskoUdostepniacza+ " prosi o bardzo szybki kontakt! \n"+
                            "Ilość dni opóźnienia: "+opoznienie+".\n";
                        wyslijMail(emailWypozyczacza,temat,komunikat);
                       break;
               }

        }
      }//for
    /**
     * funkcja bezpośrednio wysyłająca maila
     * @param adresat adresat wiadomości
     * @param komunikat treść wiadomości
     * @param temat temat wiadomości
     */

    void wyslijMail(String adresat,String temat, String komunikat){

            Properties props = System.getProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", smtpServ);
            props.put("mail.smtp.auth", "true");
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
          try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(nadawca));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(adresat, false));
            msg.setSubject(temat);
            msg.setText(komunikat);
            msg.setSentDate(new Date());
            Transport.send(msg);
            System.out.println("**************************************************************************");
            System.out.println("Wiadomoc o temacie: " + temat + " wyslana do " + adresat + ": OK.");
            System.out.println("***************************************************************************");
          }catch (MessagingException ex) {
                System.out.println("***************************************************************************");
                System.out.println("Wysłanie maila niemożliwe - błąd autoryzacji lub brak dostępu do internetu");
                System.out.println("***************************************************************************");
        }
  }
/**
 * Klasa autoryzująca konto pocztowe PortalScigacz@gmail.com
 */
private class SMTPAuthenticator extends javax.mail.Authenticator {
        @Override
        /**
     *funkcja sprawdzająca poprawność loginu i hasła
     * @return prawda - gdy hasło i login się zgadzają, fałsz - gdy nie zgadzają się
     */
        public PasswordAuthentication getPasswordAuthentication() {
            String username = "PortalScigacz@gmail.com";
            String password = "scigacz5";
            return new PasswordAuthentication(username, password);
        }
    }
}

