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
import javax.persistence.Lob;
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
@Table(name = "slide", catalog = "pmc_db", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Slide.findAll", query = "SELECT s FROM Slide s")
    , @NamedQuery(name = "Slide.findBySlideImgId", query = "SELECT s FROM Slide s WHERE s.slideImgId = :slideImgId")
    , @NamedQuery(name = "Slide.findBySlideImgName", query = "SELECT s FROM Slide s WHERE s.slideImgName = :slideImgName")})
public class Slide implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "slide_img_id")
    private Integer slideImgId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "slide_img_name")
    private String slideImgName;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "slide_img")
    private byte[] slideImg;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "slide_image_details")
    private String slideImageDetails;

    public Slide() {
    }

    public Slide(Integer slideImgId) {
        this.slideImgId = slideImgId;
    }

    public Slide(Integer slideImgId, String slideImgName, byte[] slideImg, String slideImageDetails) {
        this.slideImgId = slideImgId;
        this.slideImgName = slideImgName;
        this.slideImg = slideImg;
        this.slideImageDetails = slideImageDetails;
    }

    public Integer getSlideImgId() {
        return slideImgId;
    }

    public void setSlideImgId(Integer slideImgId) {
        this.slideImgId = slideImgId;
    }

    public String getSlideImgName() {
        return slideImgName;
    }

    public void setSlideImgName(String slideImgName) {
        this.slideImgName = slideImgName;
    }

    public byte[] getSlideImg() {
        return slideImg;
    }

    public void setSlideImg(byte[] slideImg) {
        this.slideImg = slideImg;
    }

    public String getSlideImageDetails() {
        return slideImageDetails;
    }

    public void setSlideImageDetails(String slideImageDetails) {
        this.slideImageDetails = slideImageDetails;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (slideImgId != null ? slideImgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Slide)) {
            return false;
        }
        Slide other = (Slide) object;
        if ((this.slideImgId == null && other.slideImgId != null) || (this.slideImgId != null && !this.slideImgId.equals(other.slideImgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Slide[ slideImgId=" + slideImgId + " ]";
    }
    
}
