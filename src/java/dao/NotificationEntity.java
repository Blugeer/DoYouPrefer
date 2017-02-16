/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author natha_000
 */

@Entity
@Table(name="Notification")
public class NotificationEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String message;
    
    @JoinTable(
            name="notifsToMurs_fk",
            joinColumns=@JoinColumn(name="id_notif"),
            inverseJoinColumns=@JoinColumn(name="id_mur")
    )
    @ManyToMany
    private List<MurEntity> murs;
    
    public NotificationEntity(){
    } 
    
    public NotificationEntity(String message, ArrayList<MurEntity> murs){
        this.message = message;
        this.murs = murs;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message=message; }
    
    public List<MurEntity> getMessages() { return murs; }
    public void setMessages(List<MurEntity> murs) { this.murs=murs; }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificationEntity)) {
            return false;
        }
        NotificationEntity other = (NotificationEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.NotificationEntity[ id=" + id + " ]";
    }
}
