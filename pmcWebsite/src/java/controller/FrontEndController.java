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
import javax.enterprise.context.RequestScoped;
import logic.LinkFacade;
import logic.MenuFacade;
import logic.MenuItemFacade;
import logic.PageFacade;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@Named(value = "fec")
@RequestScoped
public class FrontEndController implements Serializable {
    
    @EJB
    private PageFacade pageFacade;
    @EJB
    private MenuFacade menuFacade;
    @EJB
    private MenuItemFacade menuItemFacade;
    @EJB
    private LinkFacade linkFacade;
    private String currentContent;
    private MenuModel menuModel;
    private String logo;
    
    private Boolean slidePanelVisibility;
    private Boolean mapPanelVisibility;
    private Boolean feedbackPanelVisibility;
    private Boolean commentPanelVisibility;
    
    public FrontEndController() {
    }
    
    @PostConstruct
    public void init() {
        currentContent = pageFacade.findByPageName("home").getPageContent();
        logo = "resources/images/assets/LOGO.png";
        slidePanelVisibility = true;
        mapPanelVisibility = false;
        feedbackPanelVisibility = false;
        commentPanelVisibility = false;
        menuModel = new DefaultMenuModel();
        List<Menu> menuList = menuFacade.findAllByMenuOrder();
        for (Menu menu : menuList) {
            if (menu.getPageId() == 0) {
                DefaultSubMenu subMenu = new DefaultSubMenu(menu.getMenuName());
                List<Link> linkList = linkFacade.getLinkListByMenuId(menu.getMenuId());
                for (Link link : linkList) {
                    MenuItem menuItem = menuItemFacade.find(link.getMenuItemId());
                    DefaultMenuItem defaultMenuItem = new DefaultMenuItem(menuItem.getMenuItemName());
                    if (pageFacade.find(menuItem.getPageId()) != null) {
                        defaultMenuItem.setCommand("#{fec.setCurrentContent('" + pageFacade.find(menuItem.getPageId())
                                .getPageContent() + "',false, false, false, true)}");
                        defaultMenuItem.setUpdate("contentPanel,slidePanel");
                        //defaultMenuItem.setUpdate("contentPanel");
                        
                    } else {
                        System.out.println("Could not find page with page id " + menuItem.getPageId());
                    }
                    subMenu.addElement(defaultMenuItem);
                }
                menuModel.addElement(subMenu);
            } else {
                DefaultMenuItem menuItem = new DefaultMenuItem(menu.getMenuName());
                if (menu.getMenuName().equalsIgnoreCase("Home")) {
                     menuItem.setCommand("#{fec.setCurrentContent('" + pageFacade.find(menu.getPageId())
                            .getPageContent() + "',true, false, false, false)}");
                } 
                else if (menu.getMenuName().equalsIgnoreCase("About")) {
                    menuItem.setCommand("#{fec.setCurrentContent('" + pageFacade.find(menu.getPageId())
                            .getPageContent() + "', false, false, false, true)}");
                } 
                else if (menu.getMenuName().equalsIgnoreCase("Contact")) {
                    menuItem.setCommand("#{fec.setCurrentContent('" + pageFacade.find(menu.getPageId())
                            .getPageContent() + "', false, true, true, false)}");
                } 
                else {
                    menuItem.setCommand("#{fec.setCurrentContent('" + pageFacade.find(menu.getPageId())
                            .getPageContent() + "',false, false, false, false)}");
                }
                menuItem.setUpdate("contentPanel");
                menuModel.addElement(menuItem);
            }
        }
        
    }
    
    public String getCurrentContent() {
        return currentContent;
    }
    
    public void setCurrentContent(String currentContent, Boolean slidePanelVisibility, Boolean mapPanelVisibility, Boolean feedbackPanelVisibility, Boolean commentPanelVisibility) {
        this.currentContent = currentContent;
        this.slidePanelVisibility = slidePanelVisibility;
        this.mapPanelVisibility = mapPanelVisibility;
        this.feedbackPanelVisibility = feedbackPanelVisibility;
        this.commentPanelVisibility = commentPanelVisibility;
    }
    
    public MenuModel getMenuModel() {
        return menuModel;
    }
    
    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }
    
    public String getLogo() {
        return logo;
    }
    
    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    public Boolean getSlidePanelVisibility() {
        return slidePanelVisibility;
    }
    
    public void setSlidePanelVisibility(Boolean slidePanelVisibility) {
        this.slidePanelVisibility = slidePanelVisibility;
    }
    
    public Boolean getMapPanelVisibility() {
        return mapPanelVisibility;
    }
    
    public void setMapPanelVisibility(Boolean mapPanelVisibility) {
        this.mapPanelVisibility = mapPanelVisibility;
    }

    public Boolean getFeedbackPanelVisibility() {
        return feedbackPanelVisibility;
    }

    public void setFeedbackPanelVisibility(Boolean feedbackPanelVisibility) {
        this.feedbackPanelVisibility = feedbackPanelVisibility;
    }

    public Boolean getCommentPanelVisibility() {
        return commentPanelVisibility;
    }

    public void setCommentPanelVisibility(Boolean commentPanelVisibility) {
        this.commentPanelVisibility = commentPanelVisibility;
    }
    
}
