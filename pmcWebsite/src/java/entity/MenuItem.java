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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sony
 */
@Entity
@Table(name = "menu_item", catalog = "pmc_db", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuItem.findAll", query = "SELECT m FROM MenuItem m")
    , @NamedQuery(name = "MenuItem.findByMenuItemId", query = "SELECT m FROM MenuItem m WHERE m.menuItemId = :menuItemId")
    , @NamedQuery(name = "MenuItem.findByMenuItemName", query = "SELECT m FROM MenuItem m WHERE m.menuItemName = :menuItemName")
    , @NamedQuery(name = "MenuItem.findByPageId", query = "SELECT m FROM MenuItem m WHERE m.pageId = :pageId")})
public class MenuItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "menu_item_id")
    private Integer menuItemId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "menu_item_name")
    private String menuItemName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "page_id")
    private int pageId;

    public MenuItem() {
    }

    public MenuItem(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }

    public MenuItem(Integer menuItemId, String menuItemName, int pageId) {
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.pageId = pageId;
    }

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuItemId != null ? menuItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuItem)) {
            return false;
        }
        MenuItem other = (MenuItem) object;
        if ((this.menuItemId == null && other.menuItemId != null) || (this.menuItemId != null && !this.menuItemId.equals(other.menuItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MenuItem[ menuItemId=" + menuItemId + " ]";
    }
    
}
