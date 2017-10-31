/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.Link;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author user
 */
@Stateless
public class LinkFacade extends AbstractFacade<Link> {

    @PersistenceContext(unitName = "pmcWebsitePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LinkFacade() {
        super(Link.class);
    }

    public List<Link> getLinkListByMenuId(Integer menuId) {
        return em.createNamedQuery("Link.findByMenuId", Link.class)
                .setParameter("menuId", menuId)
                .getResultList();
    }
}
