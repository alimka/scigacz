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
@NamedQueries({@NamedQuery(name = "Wypozyczenia.findAll", query = "SELECT w FROM Wypozyczenia w"), @NamedQuery(name = "Wypozyczenia.findByIdWypozyczenia", query = "SELECT w FROM Wypozyczenia w WHERE w.idWypozyczenia = :idWypozyczenia"), @NamedQuery(name = "Wypozyczenia.findByDataWypozyczenia", query = "SELECT w FROM Wypozyczenia w WHERE w.dataWypozyczenia = :dataWypozyczenia"), @NamedQuery(name = "Wypozyczenia.findByDataZwrotu", query = "SELECT w FROM Wypozyczenia w WHERE w.dataZwrotu = :dataZwrotu")})
public class Wypozyczenia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWypozyczenia")
    private Integer idWypozyczenia;
    @Basic(optional = false)
    @Column(name = "dataWypozyczenia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataWypozyczenia;
    @Basic(optional = false)
    @Column(name = "dataZwrotu")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataZwrotu;
    @JoinColumn(name = "idWypozyczacza", referencedColumnName = "idUzytkownika")
    @ManyToOne(optional = false)
    private Uzytkownicy idWypozyczacza;
    @JoinColumn(name = "idZasobu", referencedColumnName = "idZasobu")
    @ManyToOne(optional = false)
    private Zasoby idZasobu;
    @JoinColumn(name = "idUdostepniacza", referencedColumnName = "idUzytkownika")
    @ManyToOne(optional = false)
    private Uzytkownicy idUdostepniacza;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idWypozyczenia")
    private List<Opinie> opinieList;

    public Wypozyczenia() {
    }

    public Wypozyczenia(Integer idWypozyczenia) {
        this.idWypozyczenia = idWypozyczenia;
    }

    public Wypozyczenia(Integer idWypozyczenia, Date dataWypozyczenia, Date dataZwrotu) {
        this.idWypozyczenia = idWypozyczenia;
        this.dataWypozyczenia = dataWypozyczenia;
        this.dataZwrotu = dataZwrotu;
    }

    public Integer getIdWypozyczenia() {
        return idWypozyczenia;
    }

    public void setIdWypozyczenia(Integer idWypozyczenia) {
        this.idWypozyczenia = idWypozyczenia;
    }

    public Date getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    public void setDataWypozyczenia(Date dataWypozyczenia) {
        this.dataWypozyczenia = dataWypozyczenia;
    }

    public Date getDataZwrotu() {
        return dataZwrotu;
    }

    public void setDataZwrotu(Date dataZwrotu) {
        this.dataZwrotu = dataZwrotu;
    }

    public Uzytkownicy getIdWypozyczacza() {
        return idWypozyczacza;
    }

    public void setIdWypozyczacza(Uzytkownicy idWypozyczacza) {
        this.idWypozyczacza = idWypozyczacza;
    }

    public Zasoby getIdZasobu() {
        return idZasobu;
    }

    public void setIdZasobu(Zasoby idZasobu) {
        this.idZasobu = idZasobu;
    }

    public Uzytkownicy getIdUdostepniacza() {
        return idUdostepniacza;
    }

    public void setIdUdostepniacza(Uzytkownicy idUdostepniacza) {
        this.idUdostepniacza = idUdostepniacza;
    }

    public List<Opinie> getOpinieList() {
        return opinieList;
    }

    public void setOpinieList(List<Opinie> opinieList) {
        this.opinieList = opinieList;
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
        return "czakhiber.entity.Wypozyczenia[idWypozyczenia=" + idWypozyczenia + "]";
    }

}
