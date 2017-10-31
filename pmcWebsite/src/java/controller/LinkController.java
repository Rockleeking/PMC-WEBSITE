
package controller;

import entity.Link;
import entity.Menu;
import entity.MenuItem;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import logic.LinkFacade;
import logic.MenuFacade;
import logic.MenuItemFacade;

@Named(value = "lc")
@SessionScoped
public class LinkController implements Serializable {

    private Boolean visible;
    private String menuId;
    private String menuItemId;
    private Link currentLink;
    private List<Link> linkList;
    
    @EJB
    private LinkFacade linkFacade;
    @EJB
    private MenuFacade menuFacade;
    @EJB
    private MenuItemFacade menuItemFacade;
    
    public LinkController() {
    }
    
    @PostConstruct
    public void init(){
        visible = false;
        currentLink = new Link();
        linkList = linkFacade.findAll();
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    public void toggleVisibility(){
        if(visible){
            this.visible = false;
        }
        else{
            visible = true;
        }
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Link getCurrentLink() {
        return currentLink;
    }

    public void setCurrentLink(Link currentLink) {
        this.currentLink = currentLink;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }
    
    public List<Menu> getMenuList(){
        return menuFacade.findAll();
    }
    
    public List<MenuItem> getMenuItemList(){
        return menuItemFacade.findAll();
    }

    public LinkFacade getLinkFacade() {
        return linkFacade;
    }

    public void setLinkFacade(LinkFacade linkFacade) {
        this.linkFacade = linkFacade;
    }

    public MenuFacade getMenuFacade() {
        return menuFacade;
    }

    public void setMenuFacade(MenuFacade menuFacade) {
        this.menuFacade = menuFacade;
    }

    public MenuItemFacade getMenuItemFacade() {
        return menuItemFacade;
    }

    public void setMenuItemFacade(MenuItemFacade menuItemFacade) {
        this.menuItemFacade = menuItemFacade;
    }
    
    public void save(){
        linkFacade.create(currentLink);
        currentLink = new Link();
        linkList = linkFacade.findAll();
        visible =false;
    }
    
    public void currentEditLink(){
        linkFacade.edit(currentLink);
        currentLink = new Link();
        linkList = linkFacade.findAll();
        visible =false;
    }
    
    public void reset(){
        this.menuId = null;
        this.menuItemId = null;
    }
    
    public void edit(Integer id){
        currentLink = linkFacade.find(id);
        visible = true;
    }
    
    public void delete(Integer id){
        linkFacade.remove(linkFacade.find(id));
        linkList = linkFacade.findAll();
        currentLink.setMenuId(0);
        currentLink.setMenuItemId(0);
    }
}
