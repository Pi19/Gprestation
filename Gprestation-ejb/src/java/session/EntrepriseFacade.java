/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entite.Entreprise;
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
public class EntrepriseFacade extends AbstractFacade<Entreprise> {
    @PersistenceContext(unitName = "Gprestation-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntrepriseFacade() {
        super(Entreprise.class);
    }
    
    public List<Entreprise> listeNumEntreprise(String num)
    {
     Query query = em.createNamedQuery("Entreprise.findNum");
     query.setParameter("eNTREnum","%" +num+ "%");
     return query.getResultList();
    }
}
