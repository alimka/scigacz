/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package czak.entity;

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
@Table(name = "Znajomi")
@NamedQueries({@NamedQuery(name = "Znajomi.findAll", query = "SELECT z FROM Znajomi z"), @NamedQuery(name = "Znajomi.findByIdZnajomosci", query = "SELECT z FROM Znajomi z WHERE z.idZnajomosci = :idZnajomosci"), @NamedQuery(name = "Znajomi.findByCzyBliskiUzytkownika", query = "SELECT z FROM Znajomi z WHERE z.czyBliskiUzytkownika = :czyBliskiUzytkownika"), @NamedQuery(name = "Znajomi.findByCzyBliskiZnajomego", query = "SELECT z FROM Znajomi z WHERE z.czyBliskiZnajomego = :czyBliskiZnajomego")})
public class Znajomi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idZnajomosci")
    private Integer idZnajomosci;
    @Basic(optional = false)
    @Column(name = "czyBliskiUzytkownika")
    private boolean czyBliskiUzytkownika;
    @Basic(optional = false)
    @Column(name = "czyBliskiZnajomego")
    private boolean czyBliskiZnajomego;
    @JoinColumn(name = "idZnajomego", referencedColumnName = "idUzytkownika")
    @ManyToOne(optional = false)
    private Uzytkownicy idZnajomego;
    @JoinColumn(name = "idUzytkownika", referencedColumnName = "idUzytkownika")
    @ManyToOne(optional = false)
    private Uzytkownicy idUzytkownika;

    public Znajomi() {
    }

    public Znajomi(Integer idZnajomosci) {
        this.idZnajomosci = idZnajomosci;
    }

    public Znajomi(Integer idZnajomosci, boolean czyBliskiUzytkownika, boolean czyBliskiZnajomego) {
        this.idZnajomosci = idZnajomosci;
        this.czyBliskiUzytkownika = czyBliskiUzytkownika;
        this.czyBliskiZnajomego = czyBliskiZnajomego;
    }

    public Integer getIdZnajomosci() {
        return idZnajomosci;
    }

    public void setIdZnajomosci(Integer idZnajomosci) {
        this.idZnajomosci = idZnajomosci;
    }

    public boolean getCzyBliskiUzytkownika() {
        return czyBliskiUzytkownika;
    }

    public void setCzyBliskiUzytkownika(boolean czyBliskiUzytkownika) {
        this.czyBliskiUzytkownika = czyBliskiUzytkownika;
    }

    public boolean getCzyBliskiZnajomego() {
        return czyBliskiZnajomego;
    }

    public void setCzyBliskiZnajomego(boolean czyBliskiZnajomego) {
        this.czyBliskiZnajomego = czyBliskiZnajomego;
    }

    public Uzytkownicy getIdZnajomego() {
        return idZnajomego;
    }

    public void setIdZnajomego(Uzytkownicy idZnajomego) {
        this.idZnajomego = idZnajomego;
    }

    public Uzytkownicy getIdUzytkownika() {
        return idUzytkownika;
    }

    public void setIdUzytkownika(Uzytkownicy idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idZnajomosci != null ? idZnajomosci.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Znajomi)) {
            return false;
        }
        Znajomi other = (Znajomi) object;
        if ((this.idZnajomosci == null && other.idZnajomosci != null) || (this.idZnajomosci != null && !this.idZnajomosci.equals(other.idZnajomosci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "czakhiber.entity.Znajomi[idZnajomosci=" + idZnajomosci + "]";
    }

}
