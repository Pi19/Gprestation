/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entite.Travail;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HASIMANANJARA
 */
@Stateless
public class TravailFacade extends AbstractFacade<Travail> {
    @PersistenceContext(unitName = "Gprestation-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TravailFacade() {
        super(Travail.class);
    }
    public List<Travail> listFindByNumEntrEmp(String numEntr, String numEmp)
    {
        Query query = em.createNamedQuery("Travail.findByNumTravEntrep");
        query.setParameter("eNTREnum", numEntr);
        query.setParameter("numEmploye", numEmp);
        return query.getResultList();
    }
    public List<Travail>listNoPoste()
    {
      Query query = em.createNamedQuery("Travail.findNoPoste");
    
      return query.getResultList();
    }
    public List<Object[]> listeEmpEntreprise (String numEntreprise)
    {
         Query q = em.createNamedQuery("Travail.findListeEmpEntreprise");
         q.setParameter("eNTREnum", numEntreprise);
         List<Object[]> liste = q.getResultList();
         return liste;    
       
     }
    public List<Object[]> listeEmpEntrepriseAnnuel (String numEntreprise,int annee)
    {
         Query q = em.createNamedQuery("Travail.findListeEmpEntrepriseAnnuel");
         q.setParameter("eNTREnum", numEntreprise);
         q.setParameter("annee","%"+annee+"%");
         List<Object[]> liste = q.getResultList();
         return liste;    
       
     }
    public List<Object[]> listeEmpEntreprise2Date(String numEntreprise,Date debut,Date fin)
    {
         Query q = em.createNamedQuery("Travail.findListeEmpEntrepriseEntre2date");
         q.setParameter("eNTREnum", numEntreprise);
         q.setParameter("startDate",debut);
         q.setParameter("endDate",fin);
         
         List<Object[]> liste = q.getResultList();
         return liste;
        
    }
    public List<Travail> listeEntrepriseEmp(String numEmp,Date dContrat ,Date fContrat)
    {
       Query q = em.createNamedQuery("Travail.findListeEntrepriseEmp");
         q.setParameter("numEmp", numEmp);
         q.setParameter("startContrat",dContrat);
         q.setParameter("endContrat",fContrat);
        return  q.getResultList();
    }
    
    public List<Object []> test ()
    {
       Query q = em.createNamedQuery("Travail.findTest");
       return q.getResultList();
    }
    
}
