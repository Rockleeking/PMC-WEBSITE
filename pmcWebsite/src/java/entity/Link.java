/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sony
 */
@Entity
@Table(name = "link", catalog = "pmc_db", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Link.findAll", query = "SELECT l FROM Link l")
    , @NamedQuery(name = "Link.findByLinkId", query = "SELECT l FROM Link l WHERE l.linkId = :linkId")
    , @NamedQuery(name = "Link.findByMenuId", query = "SELECT l FROM Link l WHERE l.menuId = :menuId")
    , @NamedQuery(name = "Link.findByMenuItemId", query = "SELECT l FROM Link l WHERE l.menuItemId = :menuItemId")})
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "link_id")
    private Integer linkId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_id")
    private int menuId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_item_id")
    private int menuItemId;

    public Link() {
    }

    public Link(Integer linkId) {
        this.linkId = linkId;
    }

    public Link(Integer linkId, int menuId, int menuItemId) {
        this.linkId = linkId;
        this.menuId = menuId;
        this.menuItemId = menuItemId;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (linkId != null ? linkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Link)) {
            return false;
        }
        Link other = (Link) object;
        if ((this.linkId == null && other.linkId != null) || (this.linkId != null && !this.linkId.equals(other.linkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Link[ linkId=" + linkId + " ]";
    }
    
}
