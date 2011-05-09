/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package czak.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author pedros
 */
@Entity
@Table(name = "Uzytkownicy")
@NamedQueries({@NamedQuery(name = "Uzytkownicy.findAll", query = "SELECT u FROM Uzytkownicy u"), @NamedQuery(name = "Uzytkownicy.findByIdUzytkownika", query = "SELECT u FROM Uzytkownicy u WHERE u.idUzytkownika = :idUzytkownika"), @NamedQuery(name = "Uzytkownicy.findByAvatar", query = "SELECT u FROM Uzytkownicy u WHERE u.avatar = :avatar"), @NamedQuery(name = "Uzytkownicy.findByDatablokada", query = "SELECT u FROM Uzytkownicy u WHERE u.datablokada = :datablokada"), @NamedQuery(name = "Uzytkownicy.findByEmail", query = "SELECT u FROM Uzytkownicy u WHERE u.email = :email"), @NamedQuery(name = "Uzytkownicy.findByHaslo", query = "SELECT u FROM Uzytkownicy u WHERE u.haslo = :haslo"), @NamedQuery(name = "Uzytkownicy.findByImie", query = "SELECT u FROM Uzytkownicy u WHERE u.imie = :imie"), @NamedQuery(name = "Uzytkownicy.findByKomentarzblokada", query = "SELECT u FROM Uzytkownicy u WHERE u.komentarzblokada = :komentarzblokada"), @NamedQuery(name = "Uzytkownicy.findByLogin", query = "SELECT u FROM Uzytkownicy u WHERE u.login = :login"), @NamedQuery(name = "Uzytkownicy.findByMiasto", query = "SELECT u FROM Uzytkownicy u WHERE u.miasto = :miasto"), @NamedQuery(name = "Uzytkownicy.findByNazwisko", query = "SELECT u FROM Uzytkownicy u WHERE u.nazwisko = :nazwisko"), @NamedQuery(name = "Uzytkownicy.findByTelefon", query = "SELECT u FROM Uzytkownicy u WHERE u.telefon = :telefon"), @NamedQuery(name = "Uzytkownicy.findByUprawnienia", query = "SELECT u FROM Uzytkownicy u WHERE u.uprawnienia = :uprawnienia"), @NamedQuery(name = "Uzytkownicy.findByWojewodztwo", query = "SELECT u FROM Uzytkownicy u WHERE u.wojewodztwo = :wojewodztwo")})
public class Uzytkownicy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUzytkownika")
    private Integer idUzytkownika;
    @Basic(optional = false)
    @Column(name = "avatar")
    private String avatar;
    @Basic(optional = false)
    @Column(name = "datablokada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datablokada;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "haslo")
    private String haslo;
    @Basic(optional = false)
    @Column(name = "imie")
    private String imie;
    @Basic(optional = false)
    @Column(name = "komentarzblokada")
    private String komentarzblokada;
    @Basic(optional = false)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @Column(name = "miasto")
    private String miasto;
    @Basic(optional = false)
    @Column(name = "nazwisko")
    private String nazwisko;
    @Basic(optional = false)
    @Column(name = "telefon")
    private int telefon;
    @Basic(optional = false)
    @Column(name = "uprawnienia")
    private int uprawnienia;
    @Basic(optional = false)
    @Column(name = "wojewodztwo")
    private String wojewodztwo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idWypozyczacza")
    private List<Wypozyczenia> wypozyczeniaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUdostepniacza")
    private List<Wypozyczenia> wypozyczeniaCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUzytkownika")
    private List<Zasoby> zasobyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNadawcy")
    private List<Wiadomosci> wiadomosciList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOdbiorcy")
    private List<Wiadomosci> wiadomosciCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idZnajomego")
    private List<Znajomi> znajomiList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUzytkownika")
    private List<Znajomi> znajomiCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOpiniujacego")
    private List<Opinie> opinieList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUzytkownika")
    private List<Opinie> opinieCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOdbiorcy")
    private List<Systemowe> systemoweList;

    public Uzytkownicy() {
    }

    public Uzytkownicy(Integer idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    public Uzytkownicy(Integer idUzytkownika, String avatar, Date datablokada, String email, String haslo, String imie, String komentarzblokada, String login, String miasto, String nazwisko, int telefon, int uprawnienia, String wojewodztwo) {
        this.idUzytkownika = idUzytkownika;
        this.avatar = avatar;
        this.datablokada = datablokada;
        this.email = email;
        this.haslo = haslo;
        this.imie = imie;
        this.komentarzblokada = komentarzblokada;
        this.login = login;
        this.miasto = miasto;
        this.nazwisko = nazwisko;
        this.telefon = telefon;
        this.uprawnienia = uprawnienia;
        this.wojewodztwo = wojewodztwo;
    }

    public Integer getIdUzytkownika() {
        return idUzytkownika;
    }

    public void setIdUzytkownika(Integer idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getDatablokada() {
        return datablokada;
    }

    public void setDatablokada(Date datablokada) {
        this.datablokada = datablokada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getKomentarzblokada() {
        return komentarzblokada;
    }

    public void setKomentarzblokada(String komentarzblokada) {
        this.komentarzblokada = komentarzblokada;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public int getUprawnienia() {
        return uprawnienia;
    }

    public void setUprawnienia(int uprawnienia) {
        this.uprawnienia = uprawnienia;
    }

    public String getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(String wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    public List<Wypozyczenia> getWypozyczeniaList() {
        return wypozyczeniaList;
    }

    public void setWypozyczeniaList(List<Wypozyczenia> wypozyczeniaList) {
        this.wypozyczeniaList = wypozyczeniaList;
    }

    public List<Wypozyczenia> getWypozyczeniaCollection1() {
        return wypozyczeniaCollection1;
    }

    public void setWypozyczeniaCollection1(List<Wypozyczenia> wypozyczeniaCollection1) {
        this.wypozyczeniaCollection1 = wypozyczeniaCollection1;
    }

    public List<Zasoby> getZasobyList() {
        return zasobyList;
    }

    public void setZasobyList(List<Zasoby> zasobyList) {
        this.zasobyList = zasobyList;
    }

    public List<Wiadomosci> getWiadomosciList() {
        return wiadomosciList;
    }

    public void setWiadomosciList(List<Wiadomosci> wiadomosciList) {
        this.wiadomosciList = wiadomosciList;
    }

    public List<Wiadomosci> getWiadomosciCollection1() {
        return wiadomosciCollection1;
    }

    public void setWiadomosciCollection1(List<Wiadomosci> wiadomosciCollection1) {
        this.wiadomosciCollection1 = wiadomosciCollection1;
    }

    public List<Znajomi> getZnajomiList() {
        return znajomiList;
    }

    public void setZnajomiList(List<Znajomi> znajomiList) {
        this.znajomiList = znajomiList;
    }

    public List<Znajomi> getZnajomiCollection1() {
        return znajomiCollection1;
    }

    public void setZnajomiCollection1(List<Znajomi> znajomiCollection1) {
        this.znajomiCollection1 = znajomiCollection1;
    }

    public List<Opinie> getOpinieList() {
        return opinieList;
    }

    public void setOpinieList(List<Opinie> opinieList) {
        this.opinieList = opinieList;
    }

    public List<Opinie> getOpinieCollection1() {
        return opinieCollection1;
    }

    public void setOpinieCollection1(List<Opinie> opinieCollection1) {
        this.opinieCollection1 = opinieCollection1;
    }

    public List<Systemowe> getSystemoweList() {
        return systemoweList;
    }

    public void setSystemoweList(List<Systemowe> systemoweList) {
        this.systemoweList = systemoweList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUzytkownika != null ? idUzytkownika.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uzytkownicy)) {
            return false;
        }
        Uzytkownicy other = (Uzytkownicy) object;
        if ((this.idUzytkownika == null && other.idUzytkownika != null) || (this.idUzytkownika != null && !this.idUzytkownika.equals(other.idUzytkownika))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "czakhiber.entity.Uzytkownicy[idUzytkownika=" + idUzytkownika + "]";
    }

}
