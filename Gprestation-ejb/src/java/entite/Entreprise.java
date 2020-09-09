/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entite;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HASIMANANJARA
 */
@Entity
@Table(name = "entreprise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entreprise.findAll", query = "SELECT e FROM Entreprise e"),
    @NamedQuery(name = "Entreprise.findNum", query = "SELECT e.eNTREnum FROM Entreprise e WHERE e.eNTREnum LIKE :eNTREnum"),
    @NamedQuery(name = "Entreprise.findByENTREnum", query = "SELECT e FROM Entreprise e WHERE e.eNTREnum = :eNTREnum"),
    @NamedQuery(name = "Entreprise.findByENTREdesign", query = "SELECT e FROM Entreprise e WHERE e.eNTREdesign = :eNTREdesign"),
    @NamedQuery(name = "Entreprise.findByENTRElieu", query = "SELECT e FROM Entreprise e WHERE e.eNTRElieu = :eNTRElieu")})
public class Entreprise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ENTREnum")
    private String eNTREnum;
    @Size(max = 45)
    @Column(name = "ENTREdesign")
    private String eNTREdesign;
    @Size(max = 45)
    @Column(name = "ENTRElieu")
    private String eNTRElieu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entreprise")
    private Collection<Travail> travailCollection;

    public Entreprise() {
    }

    public Entreprise(String eNTREnum) {
        this.eNTREnum = eNTREnum;
    }

    public String getENTREnum() {
        return eNTREnum;
    }

    public void setENTREnum(String eNTREnum) {
        this.eNTREnum = eNTREnum;
    }

    public String getENTREdesign() {
        return eNTREdesign;
    }

    public void setENTREdesign(String eNTREdesign) {
        this.eNTREdesign = eNTREdesign;
    }

    public String getENTRElieu() {
        return eNTRElieu;
    }

    public void setENTRElieu(String eNTRElieu) {
        this.eNTRElieu = eNTRElieu;
    }

    @XmlTransient
    public Collection<Travail> getTravailCollection() {
        return travailCollection;
    }

    public void setTravailCollection(Collection<Travail> travailCollection) {
        this.travailCollection = travailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eNTREnum != null ? eNTREnum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entreprise)) {
            return false;
        }
        Entreprise other = (Entreprise) object;
        if ((this.eNTREnum == null && other.eNTREnum != null) || (this.eNTREnum != null && !this.eNTREnum.equals(other.eNTREnum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Entreprise[ eNTREnum=" + eNTREnum + " ]";
    }
    
}
