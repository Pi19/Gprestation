/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entite;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HASIMANANJARA
 */
@Entity
@Table(name = "travail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Travail.findAll", query = "SELECT t FROM Travail t"),
    @NamedQuery(name = "Travail.findTest", query = "SELECT t FROM Travail t"),
    @NamedQuery(name = "Travail.findListeEntrepriseEmp", query = "SELECT t FROM Travail t where t.travailPK.numEmploye = :numEmp AND  ( t.travailPK.dateT BETWEEN :startContrat AND :endContrat )"),
     @NamedQuery(name = "Travail.findListeEmpEntrepriseEntre2date", query = "SELECT t.travailPK.numEmploye,t.employe.nomEmploye,t.employe.prenomEmploye,SUM(t.tRAVAInbheures),(SUM(t.tRAVAInbheures)*(t.tRAVAItauxhoraire)),t.travailPK.dateT FROM Travail t where t.tRAVAIposte is NULL AND t.travailPK.eNTREnum = :eNTREnum AND ( t.travailPK.dateT BETWEEN :startDate AND :endDate ) GROUP BY t.travailPK.numEmploye"),
    @NamedQuery(name = "Travail.findListeEmpEntrepriseAnnuel", query = "SELECT t.travailPK.numEmploye,t.employe.nomEmploye,t.employe.prenomEmploye,SUM(t.tRAVAInbheures),(SUM(t.tRAVAInbheures)*(t.tRAVAItauxhoraire)),t.travailPK.dateT FROM Travail t where t.travailPK.eNTREnum = :eNTREnum AND t.travailPK.dateT LIKE :annee  GROUP BY t.travailPK.numEmploye"),
    @NamedQuery(name = "Travail.findListeEmpEntreprise", query = "SELECT t.travailPK.numEmploye,t.employe.nomEmploye,t.employe.prenomEmploye,SUM(t.tRAVAInbheures),(SUM(t.tRAVAInbheures)*(t.tRAVAItauxhoraire)),t.travailPK.dateT FROM Travail t where t.travailPK.eNTREnum = :eNTREnum  GROUP BY t.travailPK.numEmploye"),
    @NamedQuery(name = "Travail.findNoPoste", query = "SELECT t FROM Travail t  where t.tRAVAIposte IS NOT NULL"),
    @NamedQuery(name = "Travail.findByNumTravEntrep", query = "SELECT t FROM Travail t WHERE t.travailPK.numEmploye = :numEmploye AND t.travailPK.eNTREnum =:eNTREnum"),
    @NamedQuery(name = "Travail.findByNumEmploye", query = "SELECT t FROM Travail t WHERE t.travailPK.numEmploye = :numEmploye"),
    @NamedQuery(name = "Travail.findByENTREnum", query = "SELECT t FROM Travail t WHERE t.travailPK.eNTREnum = :eNTREnum"),
    @NamedQuery(name = "Travail.findByTRAVAIembauche", query = "SELECT t FROM Travail t WHERE t.tRAVAIembauche = :tRAVAIembauche"),
    @NamedQuery(name = "Travail.findByTRAVAInbheures", query = "SELECT t FROM Travail t WHERE t.tRAVAInbheures = :tRAVAInbheures"),
    @NamedQuery(name = "Travail.findByTRAVAItauxhoraire", query = "SELECT t FROM Travail t WHERE t.tRAVAItauxhoraire = :tRAVAItauxhoraire"),
    @NamedQuery(name = "Travail.findByTRAVAIposte", query = "SELECT t FROM Travail t WHERE t.tRAVAIposte = :tRAVAIposte"),
    @NamedQuery(name = "Travail.findByDateT", query = "SELECT t FROM Travail t WHERE t.travailPK.dateT = :dateT")})
public class Travail implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TravailPK travailPK;
    @Column(name = "TRAVAIembauche")
    @Temporal(TemporalType.DATE)
    private Date tRAVAIembauche;
    @Column(name = "TRAVAInbheures")
    private Integer tRAVAInbheures;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TRAVAItauxhoraire")
    private Float tRAVAItauxhoraire;
    @Size(max = 45)
    @Column(name = "TRAVAIposte")
    private String tRAVAIposte;
    @Transient
    private Integer annee;
    @Transient
    private Date debut;
    @Transient
    private Date fin;
    @Transient
    private Date Dcontrat;
    @Transient
    private Date Fcontrat;
    @JoinColumn(name = "numEmploye", referencedColumnName = "numEmploye", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Employe employe;
    @JoinColumn(name = "ENTREnum", referencedColumnName = "ENTREnum", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Entreprise entreprise;

    public Travail() {
    }

    public Date getDcontrat() {
        return Dcontrat;
    }

    public void setDcontrat(Date Dcontrat) {
        this.Dcontrat = Dcontrat;
    }

    public Date getFcontrat() {
        return Fcontrat;
    }

    public void setFcontrat(Date Fcontrat) {
        this.Fcontrat = Fcontrat;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    
    public Travail(TravailPK travailPK) {
        this.travailPK = travailPK;
    }

    public Travail(String numEmploye, String eNTREnum, Date dateT) {
        this.travailPK = new TravailPK(numEmploye, eNTREnum, dateT);
    }

    public TravailPK getTravailPK() {
        return travailPK;
    }

    public void setTravailPK(TravailPK travailPK) {
        this.travailPK = travailPK;
    }

    public Date getTRAVAIembauche() {
        return tRAVAIembauche;
    }

    public void setTRAVAIembauche(Date tRAVAIembauche) {
        this.tRAVAIembauche = tRAVAIembauche;
    }

    public Integer getTRAVAInbheures() {
        return tRAVAInbheures;
    }

    public void setTRAVAInbheures(Integer tRAVAInbheures) {
        this.tRAVAInbheures = tRAVAInbheures;
    }

    public Float getTRAVAItauxhoraire() {
        return tRAVAItauxhoraire;
    }

    public void setTRAVAItauxhoraire(Float tRAVAItauxhoraire) {
        this.tRAVAItauxhoraire = tRAVAItauxhoraire;
    }

    public String getTRAVAIposte() {
        return tRAVAIposte;
    }

    public void setTRAVAIposte(String tRAVAIposte) {
        this.tRAVAIposte = tRAVAIposte;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (travailPK != null ? travailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Travail)) {
            return false;
        }
        Travail other = (Travail) object;
        if ((this.travailPK == null && other.travailPK != null) || (this.travailPK != null && !this.travailPK.equals(other.travailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Travail[ travailPK=" + travailPK + " ]";
    }
    
}
