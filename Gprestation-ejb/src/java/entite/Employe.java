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
@Table(name = "employe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employe.findAll", query = "SELECT e FROM Employe e"),
    @NamedQuery(name = "Employe.findNum", query = "SELECT e.numEmploye FROM Employe e WHERE e.numEmploye LIKE :numEmploye"),
    @NamedQuery(name = "Employe.findByNumEmploye", query = "SELECT e FROM Employe e WHERE e.numEmploye = :numEmploye"),
    @NamedQuery(name = "Employe.findByNomEmploye", query = "SELECT e FROM Employe e WHERE e.nomEmploye = :nomEmploye"),
    @NamedQuery(name = "Employe.findByPrenomEmploye", query = "SELECT e FROM Employe e WHERE e.prenomEmploye = :prenomEmploye"),
    @NamedQuery(name = "Employe.findByAdresseEmploye", query = "SELECT e FROM Employe e WHERE e.adresseEmploye = :adresseEmploye"),
    @NamedQuery(name = "Employe.findBySituationMatrimonialeEmploye", query = "SELECT e FROM Employe e WHERE e.situationMatrimonialeEmploye = :situationMatrimonialeEmploye")})
public class Employe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(max = 50)
    @Column(name = "numEmploye")
    private String numEmploye;
    @Size(max = 255)
    @Column(name = "nomEmploye")
    private String nomEmploye;
    @Size(max = 105)
    @Column(name = "prenomEmploye")
    private String prenomEmploye;
    @Size(max = 100)
    @Column(name = "AdresseEmploye")
    private String adresseEmploye;
    @Size(max = 45)
    @Column(name = "SituationMatrimonialeEmploye")
    private String situationMatrimonialeEmploye;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employe")
    private Collection<Travail> travailCollection;

    public Employe() {
    }

    public Employe(String numEmploye) {
        this.numEmploye = numEmploye;
    }

    public String getNumEmploye() {
        return numEmploye;
    }

    public void setNumEmploye(String numEmploye) {
        this.numEmploye = numEmploye;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public String getPrenomEmploye() {
        return prenomEmploye;
    }

    public void setPrenomEmploye(String prenomEmploye) {
        this.prenomEmploye = prenomEmploye;
    }

    public String getAdresseEmploye() {
        return adresseEmploye;
    }

    public void setAdresseEmploye(String adresseEmploye) {
        this.adresseEmploye = adresseEmploye;
    }

    public String getSituationMatrimonialeEmploye() {
        return situationMatrimonialeEmploye;
    }

    public void setSituationMatrimonialeEmploye(String situationMatrimonialeEmploye) {
        this.situationMatrimonialeEmploye = situationMatrimonialeEmploye;
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
        hash += (numEmploye != null ? numEmploye.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employe)) {
            return false;
        }
        Employe other = (Employe) object;
        if ((this.numEmploye == null && other.numEmploye != null) || (this.numEmploye != null && !this.numEmploye.equals(other.numEmploye))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Employe[ numEmploye=" + numEmploye + " ]";
    }
    
}
