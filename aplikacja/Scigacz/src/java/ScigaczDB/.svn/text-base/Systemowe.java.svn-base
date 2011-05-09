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
@Table(name = "Systemowe")
@NamedQueries({@NamedQuery(name = "Systemowe.findAll", query = "SELECT s FROM Systemowe s"),
@NamedQuery(name = "Systemowe.findByIdSys", query = "SELECT s FROM Systemowe s WHERE s.idSys = :idSys"),
@NamedQuery(name = "Systemowe.findByTresc", query = "SELECT s FROM Systemowe s WHERE s.tresc = :tresc"),
@NamedQuery(name = "Systemowe.findByData", query = "SELECT s FROM Systemowe s WHERE s.data = :data"),
@NamedQuery(name = "Systemowe.findByCzyPrzeczytana", query = "SELECT s FROM Systemowe s WHERE s.czyPrzeczytana = :czyPrzeczytana"),
@NamedQuery(name = "Systemowe.findByRodzaj", query = "SELECT s FROM Systemowe s WHERE s.rodzaj = :rodzaj"),
@NamedQuery(name = "Systemowe.findByIdDoZwrotu", query = "SELECT s FROM Systemowe s WHERE s.idDoZwrotu = :idDoZwrotu"),
@NamedQuery(name = "Systemowe.findByIdZasobu", query = "SELECT s FROM Systemowe s WHERE s.idZasobu = :idZasobu"),
@NamedQuery(name = "Systemowe.findByTemat", query = "SELECT s FROM Systemowe s WHERE s.temat = :temat")})

public class Systemowe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSys")
    private Integer idSys;
    @Basic(optional = false)
    @Column(name = "tresc")
    private String tresc;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @Column(name = "czyPrzeczytana")
    private boolean czyPrzeczytana;
    @Basic(optional = false)
    @Column(name = "rodzaj")
    private int rodzaj;
    @Basic(optional = true)
    @Column(name = "idDoZwrotu")
    private int idDoZwrotu;
    @Basic(optional = true)
    @Column(name = "idZasobu")
    private int idZasobu;
    @Basic(optional = false)
    @Column(name = "jestOdbiorca")
    private boolean jestOdbiorca;
    @Basic(optional = false)
    @Column(name = "jestNadawca")
    private boolean jestNadawca;
    @Basic(optional = false)
    @Column(name = "temat")
    private String temat;
    @JoinColumn(name = "idOdbiorcy", referencedColumnName = "idUzytkownika")
    @ManyToOne(optional = false)
    private Uzytkownicy idOdbiorcy;
    
    public Systemowe() {
    }

    public Systemowe(Integer idSys) {
        this.idSys = idSys;
    }

    public Systemowe(Integer idSys, String tresc, Date data, boolean czyPrzeczytana,String temat,boolean jestOdbiorca,boolean jestNadawca,int rodzaj,int idDoZwrotu,int idZasobu) {
        this.idSys = idSys;
        this.tresc = tresc;
        this.data = data;
        this.czyPrzeczytana = czyPrzeczytana;
        this.temat=temat;
        this.jestNadawca=jestNadawca;
        this.jestOdbiorca=jestOdbiorca;
        this.rodzaj=rodzaj;
        this.idDoZwrotu=idDoZwrotu;
        this.idZasobu=idZasobu;
    }

    public Integer getIdSys() {
        return idSys;
    }

    public void setIdSys(Integer idSys) {
        this.idSys = idSys;
    }

    public String getTresc() {
        return tresc;
    }


    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    
    public int getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(int rodzaj) {
        this.rodzaj=rodzaj;
    }

        public int getIdDoZwrotu() {
        return idDoZwrotu;
    }

    public void setIdDoZwrotu(int idDoZwrotu) {
        this.idDoZwrotu=idDoZwrotu;
    }


        public int getIdZasobu() {
        return idZasobu;
    }

    public void setIdZasobu(int idZasobu) {
        this.idDoZwrotu=idZasobu;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean getCzyPrzeczytana() {
        return czyPrzeczytana;
    }

    public void setCzyPrzeczytana(boolean czyPrzeczytana) {
        this.czyPrzeczytana = czyPrzeczytana;
    }

    public boolean getJestOdbiorca() {
        return jestOdbiorca;
    }

    public void setJestOdbiorca(boolean jestOdbiorca) {
        this.jestOdbiorca = jestOdbiorca;
    }

       public boolean getJestNadawca() {
        return jestNadawca;
    }

    public void setJestNadawca(boolean jestNadawca) {
        this.jestNadawca = jestNadawca;
    }

    public String getTemat() {
        return temat;
    }

    public void setTemat(String temat) {
        this.temat = temat;
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
        hash += (idSys != null ? idSys.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Systemowe)) {
            return false;
        }
        Systemowe other = (Systemowe) object;
        if ((this.idSys == null && other.idSys != null) || (this.idSys != null && !this.idSys.equals(other.idSys))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ScigaczDB.Systemowe[idSys=" + idSys + "]";
    }

}
