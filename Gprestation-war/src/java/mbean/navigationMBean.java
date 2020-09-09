/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author HASIMANANJARA
 */
@ManagedBean
@ViewScoped
public class navigationMBean {

   public  String accueil()
   {
    return "accueilInterf.xhtml";
   
   }
   public String listeEmp ()
   {
     return "listeEmployeInterf.xhtml";
   }
   public String enrEntreprise ()
   {
      return "createEntrepriseInterf.xhtml";
   }
   public String enrEmploye()
   {
     return "createEmployeInterf.xhtml";
   }
   public String listeEntrep ()
   {
       return "listeEntrepriseInterf.xhtml";
   
   }
   public  String enrPersonnel ()
   {
    return "createTravailInterf.xhtml";
   }
   public String listePersonnel ()
   {
        return "listeTravailInterf.xhtml"; 
   
   }
   public String bilanEntreprise()
   {
     return  " bilanEntreprise2DateInterf.xhtml";
   }
   public String bilanEmploye()
   {
      return "listeEntrepriseEmpInterf.xhtml";
   }
   public String chart()
   {
      return "chartInterf.xhtml";
   }
}
