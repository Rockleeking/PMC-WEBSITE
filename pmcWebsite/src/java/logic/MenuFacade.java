/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.Menu;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author user
 */
@Stateless
public class MenuFacade extends AbstractFacade<Menu> {

    @PersistenceContext(unitName = "pmcWebsitePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }
     public Menu findMenuByDisplayOrder(Integer displayOrder)
    {
        return em.createNamedQuery("Menu.findByDisplayOrder", Menu.class)
                .setParameter("displayOrder", displayOrder)
                .getSingleResult();
    }
    
    public void swapDisplayOrder(Menu sourceMenu,Menu destinationMenu)
    {
        Integer tempDisplayOrder=sourceMenu.getDisplayOrder();
        sourceMenu.setDisplayOrder(destinationMenu.getDisplayOrder());
        destinationMenu.setDisplayOrder(tempDisplayOrder);
        em.merge(sourceMenu);
        em.merge(destinationMenu);
    }
    public List<Menu> findAllByMenuOrder()
    {
        return em.createNamedQuery("Menu.findAll", Menu.class)
                .getResultList();
                
    }
  
    
}
