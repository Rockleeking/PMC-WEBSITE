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
@Table(name = "gallery", catalog = "pmc_db", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gallery.findAll", query = "SELECT g FROM Gallery g")
    , @NamedQuery(name = "Gallery.findByGalleryImgId", query = "SELECT g FROM Gallery g WHERE g.galleryImgId = :galleryImgId")
    , @NamedQuery(name = "Gallery.findByGalleryImgName", query = "SELECT g FROM Gallery g WHERE g.galleryImgName = :galleryImgName")})
public class Gallery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gallery_img_id")
    private Integer galleryImgId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "gallery_img_name")
    private String galleryImgName;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "gallery_img")
    private byte[] galleryImg;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "gallery_img_caption")
    private String galleryImgCaption;

    public Gallery() {
    }

    public Gallery(Integer galleryImgId) {
        this.galleryImgId = galleryImgId;
    }

    public Gallery(Integer galleryImgId, String galleryImgName, byte[] galleryImg, String galleryImgCaption) {
        this.galleryImgId = galleryImgId;
        this.galleryImgName = galleryImgName;
        this.galleryImg = galleryImg;
        this.galleryImgCaption = galleryImgCaption;
    }

    public Integer getGalleryImgId() {
        return galleryImgId;
    }

    public void setGalleryImgId(Integer galleryImgId) {
        this.galleryImgId = galleryImgId;
    }

    public String getGalleryImgName() {
        return galleryImgName;
    }

    public void setGalleryImgName(String galleryImgName) {
        this.galleryImgName = galleryImgName;
    }

    public byte[] getGalleryImg() {
        return galleryImg;
    }

    public void setGalleryImg(byte[] galleryImg) {
        this.galleryImg = galleryImg;
    }

    public String getGalleryImgCaption() {
        return galleryImgCaption;
    }

    public void setGalleryImgCaption(String galleryImgCaption) {
        this.galleryImgCaption = galleryImgCaption;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (galleryImgId != null ? galleryImgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gallery)) {
            return false;
        }
        Gallery other = (Gallery) object;
        if ((this.galleryImgId == null && other.galleryImgId != null) || (this.galleryImgId != null && !this.galleryImgId.equals(other.galleryImgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Gallery[ galleryImgId=" + galleryImgId + " ]";
    }
    
}
