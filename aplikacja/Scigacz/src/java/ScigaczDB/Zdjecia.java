/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ScigaczDB;

import java.io.Serializable;
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

/**
 *
 * @author pedros
 */
@Entity
@Table(name = "Zdjecia")
@NamedQueries({@NamedQuery(name = "Zdjecia.findAll", query = "SELECT z FROM Zdjecia z"),@NamedQuery(name = "Zdjecia.findByNrZdjecia", query = "SELECT z FROM Zdjecia z where z.nrZdjecia= :nrZdjecia"), @NamedQuery(name = "Zdjecia.findByIdZdjecia", query = "SELECT z FROM Zdjecia z WHERE z.idZdjecia = :idZdjecia"), @NamedQuery(name = "Zdjecia.findByPlik", query = "SELECT z FROM Zdjecia z WHERE z.plik = :plik")})
public class Zdjecia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idZdjecia")
    private Integer idZdjecia;
    @Basic(optional = false)
    @Column(name = "plik")
    private String plik;
    @Basic(optional = false)
    @Column(name = "nrZdjecia")
    private int nrZdjecia;
    @JoinColumn(name = "idZasobu", referencedColumnName = "idZasobu")
    @ManyToOne(optional = false)
    private Zasoby idZasobu;

    public Zdjecia() {
    }

    public Zdjecia(Integer idZdjecia) {
        this.idZdjecia = idZdjecia;
    }

    public Zdjecia(Integer idZdjecia, String plik, int nrZdjecia) {
        this.idZdjecia = idZdjecia;
        this.plik = plik;
        this.nrZdjecia=nrZdjecia;
    }

    public Integer getIdZdjecia() {
        return idZdjecia;
    }

    public void setIdZdjecia(Integer idZdjecia) {
        this.idZdjecia = idZdjecia;
    }


    public int getNrZdjecia() {
        return nrZdjecia;
    }

    public void setNrZdjecia(int nrZdjecia) {
        this.nrZdjecia = nrZdjecia;
    }

    public String getPlik() {
        return plik;
    }

    public void setPlik(String plik) {
        this.plik = plik;
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
        hash += (idZdjecia != null ? idZdjecia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zdjecia)) {
            return false;
        }
        Zdjecia other = (Zdjecia) object;
        if ((this.idZdjecia == null && other.idZdjecia != null) || (this.idZdjecia != null && !this.idZdjecia.equals(other.idZdjecia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ScigaczDB.Zdjecia[idZdjecia=" + idZdjecia + "]";
    }

}
