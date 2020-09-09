/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbean;


import entite.Entreprise;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import session.EntrepriseFacade;

/**
 *
 * @author HASIMANANJARA
 */
@ManagedBean
@SessionScoped
public class EntrepriseMBean {
    @EJB
    private EntrepriseFacade entrepriseFacade;
    private Entreprise entreprise ;
   
    public EntrepriseMBean ()
    {
         entreprise = new Entreprise();
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
    
    public List<Entreprise> listeEntreprise ()
    {
        return entrepriseFacade.findAll();
    }
    public void annulerrEntreprise()
    {
        entreprise = new Entreprise();
    }
    public void modifEntreprise ()
    {
       entrepriseFacade.edit(entreprise);
       afficheNotification("Modification effectuée");
    }
    public void suppEntreprise (Entreprise entreprise)
    {  
       entrepriseFacade.remove(entreprise);
       afficheNotification("Suppression effectuée");
    
    }
    public String enrEntreprise ()
    {
       if(entreprise.getENTREnum().isEmpty() || entreprise.getENTREdesign().isEmpty() || entreprise.getENTRElieu().isEmpty())afficheNotification("Vérifier les champs obligatoires");
        else 
        {
            entrepriseFacade.create(entreprise);
            entreprise = new Entreprise();
            afficheNotification("Enregistrement effectuée");
        }
        return "createEntrepriseInterf.xhtml";
    }
   public void afficheNotification(String summary) //pour afficher un message de notification
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
    }
   public void affecter (Entreprise entrep)
   {
     this.entreprise = entrep ;
   }
  
   
   
    
}
