/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entite.Employe;
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
public class EmployeFacade extends AbstractFacade<Employe> {
    @PersistenceContext(unitName = "Gprestation-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeFacade() {
        super(Employe.class);
    }
    public List<Employe> listFindByNum(String numero)
    {
        Query query = em.createNamedQuery("Employe.findByNumEmploye");
        query.setParameter("numEmploye", numero);
        return query.getResultList();
    }
    public List<Employe> listNum(String num)
    {  
        Query query = em.createNamedQuery("Employe.findNum");
        query.setParameter("numEmploye","%" +num+ "%");
        return query.getResultList();
    
    }
}
