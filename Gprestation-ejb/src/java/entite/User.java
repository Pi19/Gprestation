/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entite;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HASIMANANJARA
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUSERid", query = "SELECT u FROM User u WHERE u.uSERid = :uSERid"),
    @NamedQuery(name = "User.findByUSERlogin", query = "SELECT u FROM User u WHERE u.uSERlogin = :uSERlogin"),
    @NamedQuery(name = "User.findByUSERpass", query = "SELECT u FROM User u WHERE u.uSERpass = :uSERpass")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USERid")
    private Integer uSERid;
    @Size(max = 45)
    @Column(name = "USERlogin")
    private String uSERlogin;
    @Size(max = 255)
    @Column(name = "USERpass")
    private String uSERpass;

    public User() {
    }

    public User(Integer uSERid) {
        this.uSERid = uSERid;
    }

    public Integer getUSERid() {
        return uSERid;
    }

    public void setUSERid(Integer uSERid) {
        this.uSERid = uSERid;
    }

    public String getUSERlogin() {
        return uSERlogin;
    }

    public void setUSERlogin(String uSERlogin) {
        this.uSERlogin = uSERlogin;
    }

    public String getUSERpass() {
        return uSERpass;
    }

    public void setUSERpass(String uSERpass) {
        this.uSERpass = uSERpass;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uSERid != null ? uSERid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.uSERid == null && other.uSERid != null) || (this.uSERid != null && !this.uSERid.equals(other.uSERid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.User[ uSERid=" + uSERid + " ]";
    }
    
}
