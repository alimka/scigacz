/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package czak;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulina Łoś
 *
 * Klasa tworząca kopie bezpieczeństwa,
 * zapisująca je do plików o nazwie w formacie:  "yy-MM-dd_HH-mm-ss"
 * usuwająca pliki backupowe starsze niż 10 dniowe
 */
public class BackUp implements Runnable{
    private int BUFFER = 1048576;
    private String tekst;
    public static final String DATE_FORMAT_FILE = "yy-MM-dd_HH-mm-ss";

    /**
     * funkcja tworząca nazwę aktualnie powstającego backup -u
     * @return nazwa aktualnie powstającego backup -u
     */
    public static String nameOfFile() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FILE);
        return sdf.format(cal.getTime());
  }
    private String haslo;
    private String sciezkaPliku;
    private String nazwaKatalogu;
    private String nazwaPliku;
    private String nazwaDB;
    private String uzytkownik;
    private Process run;
    @Override
/**
     * nadpisana funkcja public void run() interfacu Runnable
     * wykonująca się przy tworzeniu każdego obiektu
     */
    public void run(){
            uzytkownik = "root";
            haslo = "root";
            nazwaKatalogu="back_up";
            nazwaPliku = nameOfFile();
            sciezkaPliku=nazwaKatalogu+File.separator+nazwaPliku+".sql";
            nazwaDB= "Scigacz";

            try {
                run = Runtime.getRuntime().exec("mysqldump --user="+uzytkownik+" --password="+haslo+" "+nazwaDB);
                InputStream in = run.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                StringBuffer temp = new StringBuffer();
                int count;
                char[] cbuf = new char[BUFFER];
                while ((count = br.read(cbuf, 0, BUFFER)) != -1) {
                    temp.append(cbuf, 0, count);
                }
                br.close();
                in.close();
                tekst = temp.toString();
                File dir= new File(nazwaKatalogu);
                dir.mkdir();
                String[] children = dir.list();
                FileWriter fw = new FileWriter(sciezkaPliku);
                fw.write(tekst);
                fw.close();

             System.out.println("***************************************************************************");
             System.out.println("Zrobiony back up o nazwie: "+nazwaPliku);
             System.out.println("***************************************************************************");
                if (children.length>=10){
                    //usuwam najstarsze pliki (najmniejsza nazwa pliku)
                    java.util.Arrays.sort(children);
                    File f =new File(nazwaKatalogu+File.separator+children[0]);
                    f.delete();
                }

        } catch (IOException ex) {
                Logger.getLogger(BackUp.class.getName()).log(Level.SEVERE, null, ex);
            }

}}