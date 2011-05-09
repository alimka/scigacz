/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package czak.entity;

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
@NamedQueries({@NamedQuery(name = "Opinie.findAll", query = "SELECT o FROM Opinie o"), @NamedQuery(name = "Opinie.findByIdOpinii", query = "SELECT o FROM Opinie o WHERE o.idOpinii = :idOpinii"), @NamedQuery(name = "Opinie.findByData", query = "SELECT o FROM Opinie o WHERE o.data = :data"), @NamedQuery(name = "Opinie.findByKto", query = "SELECT o FROM Opinie o WHERE o.kto = :kto"), @NamedQuery(name = "Opinie.findByTresc", query = "SELECT o FROM Opinie o WHERE o.tresc = :tresc")})
public class Opinie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOpinii")
    private Integer idOpinii;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @Column(name = "kto")
    private int kto;
    @Basic(optional = false)
    @Column(name = "tresc")
    private String tresc;
    @JoinColumn(name = "idOpiniujacego", referencedColumnName = "idUzytkownika")
    @ManyToOne(optional = false)
    private Uzytkownicy idOpiniujacego;
    @JoinColumn(name = "idUzytkownika", referencedColumnName = "idUzytkownika")
    @ManyToOne(optional = false)
    private Uzytkownicy idUzytkownika;
    @JoinColumn(name = "idWypozyczenia", referencedColumnName = "idWypozyczenia")
    @ManyToOne(optional = false)
    private Wypozyczenia idWypozyczenia;

    public Opinie() {
    }

    public Opinie(Integer idOpinii) {
        this.idOpinii = idOpinii;
    }

    public Opinie(Integer idOpinii, Date data, int kto, String tresc) {
        this.idOpinii = idOpinii;
        this.data = data;
        this.kto = kto;
        this.tresc = tresc;
    }

    public Integer getIdOpinii() {
        return idOpinii;
    }

    public void setIdOpinii(Integer idOpinii) {
        this.idOpinii = idOpinii;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getKto() {
        return kto;
    }

    public void setKto(int kto) {
        this.kto = kto;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Uzytkownicy getIdOpiniujacego() {
        return idOpiniujacego;
    }

    public void setIdOpiniujacego(Uzytkownicy idOpiniujacego) {
        this.idOpiniujacego = idOpiniujacego;
    }

    public Uzytkownicy getIdUzytkownika() {
        return idUzytkownika;
    }

    public void setIdUzytkownika(Uzytkownicy idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    public Wypozyczenia getIdWypozyczenia() {
        return idWypozyczenia;
    }

    public void setIdWypozyczenia(Wypozyczenia idWypozyczenia) {
        this.idWypozyczenia = idWypozyczenia;
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
        return "czakhiber.entity.Opinie[idOpinii=" + idOpinii + "]";
    }

}
