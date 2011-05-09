package czak;

import czak.entity.*;
import czak.manager.*;
import java.util.*;

/**
 *
 * @author Paulina
 *
 * Obsługa wysyłania wiadomości wewntętrznych. Parametry
 * 1 - za tydzień upływa termin
 * 2 - dziś upływa termin
 * 3 - upłynął termin
 *
 */
public class WiadomoscWew {
      int typ;
      List<Wypozyczenia> lista;
      int opoznienie;
      String temat;
      String komunikat;
      int rodzaj;

      WiadomoscWew(){}
      WiadomoscWew(int _typ,List<Wypozyczenia> _lista){
        typ=_typ;
        lista = _lista;
        rodzaj = 2;
        }
 /**
     * Funkcja sterująca wysyłaniem wiadomości wewnętrznych od Czaka do wszystkich
     * Wypożyczaczy znajdujących się na liście
     * w przypadku typu wiadomości: 1 lub 2 wiadomość wysyłana jest też do Udostępniaczy
     */

      public void wyslijWiadomoscDoWszystkichZListy(){

        System.out.println("***************************************************************************");
        System.out.println("Wysylanie wiadomosci wew typu: "+typ);
        System.out.println("***************************************************************************");
        Uzytkownicy  wypozyczacz;
        Uzytkownicy udostepniacz;
        Zasoby zasob;
        Wypozyczenia wypozyczenie;

        Date today = new Date();
        String komunikat2;
        for(int i =0;i<lista.size();i++){
                   udostepniacz=lista.get(i).getIdUdostepniacza();
                   wypozyczacz=lista.get(i).getIdWypozyczacza();
                   zasob=lista.get(i).getIdZasobu();
                   wypozyczenie = lista.get(i);
         switch(typ){
                   case(1):
                       temat="ZA TYDZIEŃ UPŁYWA TERMIN WYPOŻYCZENIA !!!";
                       komunikat = "UWAGA UWAGA UWAGA \n"+
                           wypozyczacz.getImie()+"!!!\n"+
                           "Za tydzień upływa termin wypożyczenia przez Ciebie zasobu: " + zasob.getNazwa()+".\n"+
                           "Właściciel "+udostepniacz.getImie()+" "+udostepniacz.getNazwisko()+ " prosi o zwrot na czas.";
                        komunikat2 = "UWAGA UWAGA UWAGA \n"+udostepniacz.getImie()+"!!!\n"+
                               "Za tydzień użytkownik "+wypozyczacz.getImie()+" "+
                               wypozyczacz.getNazwisko()+" zwraca Ci: "+ zasob.getNazwa()+".\n";
                       wyslij(udostepniacz,temat,komunikat2,rodzaj);
                       wyslij(wypozyczacz,temat,komunikat,rodzaj);
                       break;
                   case(2):
                       temat="DZIŚ UPŁYWA TERMIN WYPOŻYCZENIA !!!";
                       temat = "DZIS UPLYWA TERMIN WYPOZYCZENIA!!!";
                       komunikat = "UWAGA UWAGA UWAGA \n"+
                               wypozyczacz.getImie()+"!!!\n"+
                               "Dziś upływa termin wypożyczenia przez Ciebie zasobu: " + zasob.getNazwa()+".\n"+
                                "Własciciel "+udostepniacz.getImie()+" "+udostepniacz.getNazwisko()+ " prosi o szybki kontakt.";
                       komunikat2 = "UWAGA UWAGA UWAGA \n"+
                           wypozyczacz.getImie()+"!!!\n"+
                           "Dziś upływa termin do ktorego użytkownik: " +
                           wypozyczacz.getImie()+" "+wypozyczacz.getNazwisko()+
                           " powinien zwrocić Ci "+ zasob.getNazwa()+".\n";
                       wyslij(udostepniacz,temat,komunikat2,rodzaj);
                       wyslij(wypozyczacz,temat,komunikat,rodzaj);
                       break;
                   case(3):
                        opoznienie = (int) ((today.getTime() - wypozyczenie.getDataZwrotu().getTime()) / (1000 * 60 * 60 * 24));
                        if(opoznienie==14){
                            temat = "!!!  NAGANA !!!";
                            komunikat = "UWAGA UWAGA UWAGA \n"+
                            wypozyczacz.getImie()+"\n"+
                            "Zostajesz upomniany naganą od administratora\n Jak najszybciej zwróć zasób : "+
                                zasob.getNazwa()+" użytkownikowi: "+udostepniacz.getImie() + " "+udostepniacz.getNazwisko();
                       rodzaj=3;
                        }else{
                        temat = "!!!  ILOŚć DNI OPÓŹNIENIA: "+opoznienie+"  !!!";
                        komunikat = "UWAGA UWAGA UWAGA \n"+
                            wypozyczacz.getImie()+"\n"+
                            "Upłynął już termin wypożyczenia przez Ciebie zasobu: " +zasob.getNazwa()+".\n"+
                            "Własciciel "+udostepniacz.getImie()+" "+udostepniacz.getNazwisko()+ " prosi o bardzo szybki kontakt! \n"+
                            "Ilość dni opóźnienia: "+opoznienie+".\n";
                        }
                        wyslij(wypozyczacz,temat,komunikat,rodzaj);
                        break;
               }
        }
      }
       /**
     * funkcja bezpośrednio wysyłająca wiadomość wewnętrzną
     * @param u użytkownik(adresat) wiadomości
     * @param komunikat treść wiadomości
     * @param rodzaj rodzaj wiadomości
     */
        void wyslij(Uzytkownicy u,String temat, String komunikat,int rodzaj){
               ManagerSkrzynki managerSkrzynki = new ManagerSkrzynki();
               Systemowe w=new Systemowe(null, komunikat, new Date(),false,temat,true,true,rodzaj,-1,-1);

               managerSkrzynki.DodajWiadomoscSystemowa(w,u);
      }
}