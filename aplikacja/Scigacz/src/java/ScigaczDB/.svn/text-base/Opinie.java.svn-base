/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ScigaczDB;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author pedros
 */
@Entity
@Table(name = "Opinie")
@NamedQueries({@NamedQuery(name = "Opinie.findAll", query = "SELECT o FROM Opinie o"), @NamedQuery(name = "Opinie.findByIdOpinii", query = "SELECT o FROM Opinie o WHERE o.idOpinii = :idOpinii"), @NamedQuery(name = "Opinie.findByTresc", query = "SELECT o FROM Opinie o WHERE o.tresc = :tresc"), @NamedQuery(name = "Opinie.findByData", query = "SELECT o FROM Opinie o WHERE o.data = :data"), @NamedQuery(name = "Opinie.findByKto", query = "SELECT o FROM Opinie o WHERE o.kto = :kto")})
public class Opinie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOpinii")
    private Integer idOpinii;
    @Basic(optional = false)
    @Column(name = "tresc")
    private String tresc;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @Column(name = "kto")
    private int kto;
    @JoinColumn(name = "idUzytkownika", referencedColumnName = "idUzytkownika")
    @ManyToOne(optional = false)
    private Uzytkownicy idUzytkownika;
    @JoinColumn(name = "idOpiniujacego", referencedColumnName = "idUzytkownika")
    @ManyToOne(optional = false)
    private Uzytkownicy idOpiniujacego;
    public Opinie() {
    }

    public Opinie(Integer idOpinii) {
        this.idOpinii = idOpinii;
    }


  public Opinie(Integer idOpinii,String tresc, Date data, int kto) {
        this.idOpinii = idOpinii;
        this.tresc = tresc;
        this.data = data;
        this.kto = kto;
    }
    public Integer getIdOpinii() {
        return idOpinii;
    }

    public void setIdOpinii(Integer idOpinii) {
        this.idOpinii = idOpinii;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Uzytkownicy getIdUzytkownika() {
        return idUzytkownika;
    }

    public void setIdUzytkownika(Uzytkownicy idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }


    public Uzytkownicy getIdOpiniujacego() {
        return idOpiniujacego;
    }

    public void setIdOpiniujacego(Uzytkownicy idOpiniujacego) {
        this.idOpiniujacego = idOpiniujacego;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpinii != null ? idOpinii.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opinie)) {
            return false;
        }
        Opinie other = (Opinie) object;
        if ((this.idOpinii == null && other.idOpinii != null) || (this.idOpinii != null && !this.idOpinii.equals(other.idOpinii))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ScigaczDB.Opinie[idOpinii=" + idOpinii + "]";
    }

}
