/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ScigaczDB;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Wypozyczenia")
@NamedQueries({@NamedQuery(name = "Wypozyczenia.findAll", query = "SELECT w FROM Wypozyczenia w"), @NamedQuery(name = "Wypozyczenia.findByIdWypozyczenia", query = "SELECT w FROM Wypozyczenia w WHERE w.idWypozyczenia = :idWypozyczenia"), @NamedQuery(name = "Wypozyczenia.findByDataZwrotu", query = "SELECT w FROM Wypozyczenia w WHERE w.dataZwrotu = :dataZwrotu"), @NamedQuery(name = "Wypozyczenia.findByDataWypozyczenia", query = "SELECT w FROM Wypozyczenia w WHERE w.dataWypozyczenia = :dataWypozyczenia")})
public class Wypozyczenia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWypozyczenia")
    private Integer idWypozyczenia;
    @Basic(optional = false)
    @Column(name = "dataZwrotu")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataZwrotu;
    @Basic(optional = false)
    @Column(name = "dataWypozyczenia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataWypozyczenia;
    @JoinColumn(name = "idWypozyczacza", referencedColumnName = "idUzytkownika")
    @ManyToOne(optional = false)
    private Uzytkownicy idWypozyczacza;

    @JoinColumn(name = "idUdostepniacza", referencedColumnName = "idUzytkownika")
    @ManyToOne(optional = false)
    private Uzytkownicy idUdostepniacza;


    @JoinColumn(name = "idZasobu", referencedColumnName = "idZasobu")
    @ManyToOne(optional = false)
    private Zasoby idZasobu;



    public Wypozyczenia() {
    }

    public Wypozyczenia(Integer idWypozyczenia) {
        this.idWypozyczenia = idWypozyczenia;
    }

    public Wypozyczenia(Integer idWypozyczenia, Date dataZwrotu, Date dataWypozyczenia) {
        this.idWypozyczenia = idWypozyczenia;
        this.dataZwrotu = dataZwrotu;
        this.dataWypozyczenia = dataWypozyczenia;
    }

    public Integer getIdWypozyczenia() {
        return idWypozyczenia;
    }

    public void setIdWypozyczenia(Integer idWypozyczenia) {
        this.idWypozyczenia = idWypozyczenia;
    }

    public Date getDataZwrotu() {
        return dataZwrotu;
    }

    public void setDataZwrotu(Date dataZwrotu) {
        this.dataZwrotu = dataZwrotu;
    }

    public Date getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    public void setDataWypozyczenia(Date dataWypozyczenia) {
        this.dataWypozyczenia = dataWypozyczenia;
    }

    public Uzytkownicy getIdWypozyczacza() {
        return idWypozyczacza;
    }

    public void setIdWypozyczacza(Uzytkownicy idWypozyczacza) {
        this.idWypozyczacza = idWypozyczacza;
    }


      public Uzytkownicy getIdUdostepniacza() {
        return idUdostepniacza;
    }

    public void setIdUdostepniacza(Uzytkownicy idUdostepniacza) {
        this.idUdostepniacza = idUdostepniacza;
    }
    public Zasoby getIdZasobu() {
        return idZasobu;
    }

    public void setIdZasobu(Zasoby idZasobu) {
        this.idZasobu = idZasobu;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWypozyczenia != null ? idWypozyczenia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wypozyczenia)) {
            return false;
        }
        Wypozyczenia other = (Wypozyczenia) object;
        if ((this.idWypozyczenia == null && other.idWypozyczenia != null) || (this.idWypozyczenia != null && !this.idWypozyczenia.equals(other.idWypozyczenia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ScigaczDB.Wypozyczenia[idWypozyczenia=" + idWypozyczenia + "]";
    }

}
