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
@Table(name = "Wiadomosci")
@NamedQueries({@NamedQuery(name = "Wiadomosci.findAll", query = "SELECT w FROM Wiadomosci w"), @NamedQuery(name = "Wiadomosci.findByIdWiadomosci", query = "SELECT w FROM Wiadomosci w WHERE w.idWiadomosci = :idWiadomosci"), @NamedQuery(name = "Wiadomosci.findByCzyPrzeczytana", query = "SELECT w FROM Wiadomosci w WHERE w.czyPrzeczytana = :czyPrzeczytana"), @NamedQuery(name = "Wiadomosci.findByData", query = "SELECT w FROM Wiadomosci w WHERE w.data = :data"), @NamedQuery(name = "Wiadomosci.findByJestNadawca", query = "SELECT w FROM Wiadomosci w WHERE w.jestNadawca = :jestNadawca"), @NamedQuery(name = "Wiadomosci.findByJestOdbiorca", query = "SELECT w FROM Wiadomosci w WHERE w.jestOdbiorca = :jestOdbiorca"), @NamedQuery(name = "Wiadomosci.findByTemat", query = "SELECT w FROM Wiadomosci w WHERE w.temat = :temat"), @NamedQuery(name = "Wiadomosci.findByTresc", query = "SELECT w FROM Wiadomosci w WHERE w.tresc = :tresc")})
public class Wiadomosci implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWiadomosci")
    private Integer idWiadomosci;
    @Basic(optional = false)
    @Column(name = "czyPrzeczytana")
    private boolean czyPrzeczytana;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @Column(name = "jestNadawca")
    private boolean jestNadawca;
    @Basic(optional = false)
    @Column(name = "jestOdbiorca")
    private boolean jestOdbiorca;
    @Basic(optional = false)
    @Column(name = "temat")
    private String temat;
    @Basic(optional = false)
    @Column(name = "tresc")
    private String tresc;
    @JoinColumn(name = "idNadawcy", referencedColumnName = "idUzytkownika")
    @ManyToOne(optional = false)
    private Uzytkownicy idNadawcy;
    @JoinColumn(name = "idOdbiorcy", referencedColumnName = "idUzytkownika")
    @ManyToOne(optional = false)
    private Uzytkownicy idOdbiorcy;

    public Wiadomosci() {
    }

    public Wiadomosci(Integer idWiadomosci) {
        this.idWiadomosci = idWiadomosci;
    }

    public Wiadomosci(Integer idWiadomosci, boolean czyPrzeczytana, Date data, boolean jestNadawca, boolean jestOdbiorca, String temat, String tresc) {
        this.idWiadomosci = idWiadomosci;
        this.czyPrzeczytana = czyPrzeczytana;
        this.data = data;
        this.jestNadawca = jestNadawca;
        this.jestOdbiorca = jestOdbiorca;
        this.temat = temat;
        this.tresc = tresc;
    }

    public Integer getIdWiadomosci() {
        return idWiadomosci;
    }

    public void setIdWiadomosci(Integer idWiadomosci) {
        this.idWiadomosci = idWiadomosci;
    }

    public boolean getCzyPrzeczytana() {
        return czyPrzeczytana;
    }

    public void setCzyPrzeczytana(boolean czyPrzeczytana) {
        this.czyPrzeczytana = czyPrzeczytana;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean getJestNadawca() {
        return jestNadawca;
    }

    public void setJestNadawca(boolean jestNadawca) {
        this.jestNadawca = jestNadawca;
    }

    public boolean getJestOdbiorca() {
        return jestOdbiorca;
    }

    public void setJestOdbiorca(boolean jestOdbiorca) {
        this.jestOdbiorca = jestOdbiorca;
    }

    public String getTemat() {
        return temat;
    }

    public void setTemat(String temat) {
        this.temat = temat;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Uzytkownicy getIdNadawcy() {
        return idNadawcy;
    }

    public void setIdNadawcy(Uzytkownicy idNadawcy) {
        this.idNadawcy = idNadawcy;
    }

    public Uzytkownicy getIdOdbiorcy() {
        return idOdbiorcy;
    }

    public void setIdOdbiorcy(Uzytkownicy idOdbiorcy) {
        this.idOdbiorcy = idOdbiorcy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWiadomosci != null ? idWiadomosci.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wiadomosci)) {
            return false;
        }
        Wiadomosci other = (Wiadomosci) object;
        if ((this.idWiadomosci == null && other.idWiadomosci != null) || (this.idWiadomosci != null && !this.idWiadomosci.equals(other.idWiadomosci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "czakhiber.entity.Wiadomosci[idWiadomosci=" + idWiadomosci + "]";
    }

}
