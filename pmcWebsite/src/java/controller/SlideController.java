/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Page;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import logic.PageFacade;

/**
 *
 * @author user
 */
@Named(value = "slideController")
@SessionScoped
public class SlideController implements Serializable {

    
    private List<String> images;
    private Boolean visible;
    
    @EJB
    private PageFacade pageFacade;
    
    public SlideController() {
    }
    
    private Page page;
     
    @PostConstruct
    public void init() {
        visible = true;
        images = new ArrayList<String>();
        for (int i = 1; i <= 2; i++) {
            images.add("slideshow" + i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
