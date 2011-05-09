/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package czak.entity;

import java.io.Serializable;
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

/**
 *
 * @author pedros
 */
@Entity
@Table(name = "Kategorie")
@NamedQueries({@NamedQuery(name = "Kategorie.findAll", query = "SELECT k FROM Kategorie k"), @NamedQuery(name = "Kategorie.findByIdKategorii", query = "SELECT k FROM Kategorie k WHERE k.idKategorii = :idKategorii"), @NamedQuery(name = "Kategorie.findByNazwa", query = "SELECT k FROM Kategorie k WHERE k.nazwa = :nazwa")})
public class Kategorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKategorii")
    private Integer idKategorii;
    @Basic(optional = false)
    @Column(name = "nazwa")
    private String nazwa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKategorii")
    private List<Zasoby> zasobyList;

    public Kategorie() {
    }

    public Kategorie(Integer idKategorii) {
        this.idKategorii = idKategorii;
    }

    public Kategorie(Integer idKategorii, String nazwa) {
        this.idKategorii = idKategorii;
        this.nazwa = nazwa;
    }

    public Integer getIdKategorii() {
        return idKategorii;
    }

    public void setIdKategorii(Integer idKategorii) {
        this.idKategorii = idKategorii;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<Zasoby> getZasobyList() {
        return zasobyList;
    }

    public void setZasobyList(List<Zasoby> zasobyList) {
        this.zasobyList = zasobyList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKategorii != null ? idKategorii.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kategorie)) {
            return false;
        }
        Kategorie other = (Kategorie) object;
        if ((this.idKategorii == null && other.idKategorii != null) || (this.idKategorii != null && !this.idKategorii.equals(other.idKategorii))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "czakhiber.entity.Kategorie[idKategorii=" + idKategorii + "]";
    }

}
