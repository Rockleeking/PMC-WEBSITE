/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.Page;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author user
 */
@Stateless
public class PageFacade extends AbstractFacade<Page> {

    @PersistenceContext(unitName = "pmcWebsitePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PageFacade() {
        super(Page.class);
    }
    
   public Page findByPageName(String pageName)
   {
     return  em.createNamedQuery("Page.findByPageName", Page.class).setParameter("pageName", pageName).getSingleResult();
  }
}
