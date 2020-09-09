/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbean;

import entite.Employe;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import session.EmployeFacade;

/**
 *
 * @author HASIMANANJARA
 */
@ManagedBean
@SessionScoped
public class EmployeMBean {
    @EJB
    private EmployeFacade employeFacade;
    private Employe employe ;

    
    public EmployeMBean() {
        employe = new Employe();
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    
   public List <Employe> listeEmploye ()
   {
     return employeFacade.findAll();
   }
      public void afficheNotification(String summary) //pour afficher un message de notification
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
    }
    public void suppEmploye(Employe emp)
    {
       employeFacade.remove(emp);
        afficheNotification("Suppression Effectuee");
    }
    
    public void affecterEmp (Employe emp)
    {
     this.employe = emp ;
    }
    
    public void modifEmp ()
    {
      
        
        
        if( employe.getSituationMatrimonialeEmploye().isEmpty() ||employe.getNumEmploye().isEmpty() || employe.getNomEmploye().isEmpty())afficheNotification("Vérifier les champs obligatoires");
        
        else 
        {
        employeFacade.edit(employe);
        employe = new Employe();
        afficheNotification("Employe bien modifie");
        }
    
    }
   public String enrEmploye()
   {    
          List<Employe> listEmpByNum = new ArrayList<>();
        
        if(!employe.getNumEmploye().isEmpty())listEmpByNum = employeFacade.listFindByNum(employe.getNumEmploye());
        if(employe.getNumEmploye().isEmpty() || employe.getNomEmploye().isEmpty())afficheNotification("Vérifier les champs obligatoires");
        else if(!listEmpByNum.isEmpty())afficheNotification("Le numero existe dejà");
        else 
        {
        employeFacade.create(employe);
        employe = new Employe();
        afficheNotification("Employe bien enregistre");
        }
        return "createEmployeInterf.xhtml";
   }
   public String annulerEnr()
   {
       employe = new Employe();
       return "createEmployeInterf.xhtml";
   
   }
}
