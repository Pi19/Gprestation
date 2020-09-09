/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entite;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author HASIMANANJARA
 */
@Embeddable
public class TravailPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "numEmploye")
    private String numEmploye;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ENTREnum")
    private String eNTREnum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DateT")
    @Temporal(TemporalType.DATE)
    private Date dateT;

    public TravailPK() {
    }

    public TravailPK(String numEmploye, String eNTREnum, Date dateT) {
        this.numEmploye = numEmploye;
        this.eNTREnum = eNTREnum;
        this.dateT = dateT;
    }

    public String getNumEmploye() {
        return numEmploye;
    }

    public void setNumEmploye(String numEmploye) {
        this.numEmploye = numEmploye;
    }

    public String getENTREnum() {
        return eNTREnum;
    }

    public void setENTREnum(String eNTREnum) {
        this.eNTREnum = eNTREnum;
    }

    public Date getDateT() {
        return dateT;
    }

    public void setDateT(Date dateT) {
        this.dateT = dateT;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numEmploye != null ? numEmploye.hashCode() : 0);
        hash += (eNTREnum != null ? eNTREnum.hashCode() : 0);
        hash += (dateT != null ? dateT.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TravailPK)) {
            return false;
        }
        TravailPK other = (TravailPK) object;
        if ((this.numEmploye == null && other.numEmploye != null) || (this.numEmploye != null && !this.numEmploye.equals(other.numEmploye))) {
            return false;
        }
        if ((this.eNTREnum == null && other.eNTREnum != null) || (this.eNTREnum != null && !this.eNTREnum.equals(other.eNTREnum))) {
            return false;
        }
        if ((this.dateT == null && other.dateT != null) || (this.dateT != null && !this.dateT.equals(other.dateT))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.TravailPK[ numEmploye=" + numEmploye + ", eNTREnum=" + eNTREnum + ", dateT=" + dateT + " ]";
    }
    
}
