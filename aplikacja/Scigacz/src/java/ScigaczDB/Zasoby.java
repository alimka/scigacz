/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ScigaczDB;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author pedros
 */
@Entity
@Table(name = "Zasoby")
@NamedQueries({@NamedQuery(name = "Zasoby.findAll", query = "SELECT z FROM Zasoby z"), @NamedQuery(name = "Zasoby.findByIdZasobu", query = "SELECT z FROM Zasoby z WHERE z.idZasobu = :idZasobu"), @NamedQuery(name = "Zasoby.findByNazwa", query = "SELECT z FROM Zasoby z WHERE z.nazwa = :nazwa"), @NamedQuery(name = "Zasoby.findByCzasPrzetrzymywania", query = "SELECT z FROM Zasoby z WHERE z.czasPrzetrzymywania = :czasPrzetrzymywania"), @NamedQuery(name = "Zasoby.findByDostepnosc", query = "SELECT z FROM Zasoby z WHERE z.dostepnosc = :dostepnosc"), @NamedQuery(name = "Zasoby.findByOpis", query = "SELECT z FROM Zasoby z WHERE z.opis = :opis"), @NamedQuery(name = "Zasoby.findByDlakogo", query = "SELECT z FROM Zasoby z WHERE z.dlakogo = :dlakogo")})
public class Zasoby implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idZasobu")
    private Integer idZasobu;
    @Basic(optional = false)
    @Column(name = "nazwa")
    private String nazwa;
    @Basic(optional = false)
    @Column(name = "czasPrzetrzymywania")
    private int czasPrzetrzymywania;
    @Basic(optional = false)
    @Column(name = "dostepnosc")
    private int dostepnosc;
    @Basic(optional = false)
    @Column(name = "opis")
    private String opis;
    @Basic(optional = false)
    @Column(name = "dlakogo")
    private int dlakogo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idZasobu")
    private List<Wypozyczenia> wypozyczeniaList;
    @JoinColumn(name = "idUzytkownika", referencedColumnName = "idUzytkownika")
    @ManyToOne(optional = false)
    private Uzytkownicy idUzytkownika;
    @JoinColumn(name = "idKategorii", referencedColumnName = "idKategorii")
    @ManyToOne(optional = false)
    private Kategorie idKategorii;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idZasobu")
    private List<Zdjecia> zdjeciaList;
  

    public Zasoby() {
    }

    public Zasoby(Integer idZasobu) {
        this.idZasobu = idZasobu;
    }
 public Zasoby(Integer idZasobu, String nazwa, int czasPrzetrzymywania, int dostepnosc, String opis, int dlakogo) {
        this.idZasobu = idZasobu;
        this.nazwa = nazwa;
        this.czasPrzetrzymywania = czasPrzetrzymywania;
        this.dostepnosc = dostepnosc;
        this.opis = opis;
        this.dlakogo = dlakogo;
    }


    public Integer getIdZasobu() {
        return idZasobu;
    }

    public void setIdZasobu(Integer idZasobu) {
        this.idZasobu = idZasobu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getCzasPrzetrzymywania() {
        return czasPrzetrzymywania;
    }

    public void setCzasPrzetrzymywania(int czasPrzetrzymywania) {
        this.czasPrzetrzymywania = czasPrzetrzymywania;
    }

    public int getDostepnosc() {
        return dostepnosc;
    }

    public void setDostepnosc(int dostepnosc) {
        this.dostepnosc = dostepnosc;
    }

      public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getDlakogo() {
        return dlakogo;
    }

    public void setDlakogo(int dlakogo) {
        this.dlakogo = dlakogo;
    }

    public List<Wypozyczenia> getWypozyczeniaList() {
        return wypozyczeniaList;
    }

    public void setWypozyczeniaList(List<Wypozyczenia> wypozyczeniaList) {
        this.wypozyczeniaList = wypozyczeniaList;
    }

    public Uzytkownicy getIdUzytkownika() {
        return idUzytkownika;
    }

    public void setIdUzytkownika(Uzytkownicy idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    public Kategorie getIdKategorii() {
        return idKategorii;
    }

    public void setIdKategorii(Kategorie idKategorii) {
        this.idKategorii = idKategorii;
    }

    public List<Zdjecia> getZdjeciaList() {
        return zdjeciaList;
    }

    public void setZdjeciaList(List<Zdjecia> zdjeciaList) {
        this.zdjeciaList = zdjeciaList;
    }

   


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idZasobu != null ? idZasobu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zasoby)) {
            return false;
        }
        Zasoby other = (Zasoby) object;
        if ((this.idZasobu == null && other.idZasobu != null) || (this.idZasobu != null && !this.idZasobu.equals(other.idZasobu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ScigaczDB.Zasoby[idZasobu=" + idZasobu + "]";
    }

}
