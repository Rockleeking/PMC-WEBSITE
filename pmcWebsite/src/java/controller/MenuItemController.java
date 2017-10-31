
package controller;

import entity.MenuItem;
import entity.Page;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import logic.MenuItemFacade;
import logic.PageFacade;

@Named(value = "mic")
@SessionScoped
public class MenuItemController implements Serializable {
    
    private Boolean visible;
    private String menuItemName;
    private String pageId;
    private MenuItem currentMenuItem;
    private List<MenuItem> menuItemList;
    
    @EJB
    private MenuItemFacade menuItemFacade;
    
    @EJB
    private PageFacade pageFacade;
    
    public MenuItemController() {
    }
    
    @PostConstruct
    public void init(){
        visible = false;
        currentMenuItem = new MenuItem();
        menuItemList = menuItemFacade.findAll();
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
    
    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public MenuItem getCurrentMenuItem() {
        return currentMenuItem;
    }

    public void setCurrentMenuItem(MenuItem currentMenuItem) {
        this.currentMenuItem = currentMenuItem;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }
    
    public List<Page> getPageList(){
        return pageFacade.findAll();
    }

    public MenuItemFacade getMenuItemFacade() {
        return menuItemFacade;
    }

    public void setMenuItemFacade(MenuItemFacade menuItemFacade) {
        this.menuItemFacade = menuItemFacade;
    }

    public PageFacade getPageFacade() {
        return pageFacade;
    }

    public void setPageFacade(PageFacade pageFacade) {
        this.pageFacade = pageFacade;
    }
    
    public void save(){
        menuItemFacade.create(currentMenuItem);
        currentMenuItem = new MenuItem();
        menuItemList = menuItemFacade.findAll();
        visible =false;
    }
    
    public void currentMenuItemEdit(){
        menuItemFacade.edit(currentMenuItem);
        currentMenuItem = new MenuItem();
        menuItemList = menuItemFacade.findAll();
        visible =false;
    }
    
    public void reset(){
        this.menuItemName=null;
        this.pageId=null;
    }
    
    public void edit(Integer Id){
        currentMenuItem = menuItemFacade.find(Id);
        visible = true;
    }
    
    public void delete(Integer Id){
        menuItemFacade.remove(menuItemFacade.find(Id));
        menuItemList = menuItemFacade.findAll();
        currentMenuItem.setMenuItemName(null);
        currentMenuItem.setPageId(0);
    }
}
