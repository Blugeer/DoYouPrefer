/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author natha_000
 */
@Entity
@Table(name="Mur")
public class MurEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private PersonneEntity personneToMur;
    
    @ManyToMany(mappedBy="murs")
    private List<QuestionEntity> questions;
    
    
    @ManyToMany(mappedBy="murs")
    private List<NotificationEntity> notifs;
    
    public MurEntity(){
    } 
    
    public MurEntity(PersonneEntity personneToMur, ArrayList<QuestionEntity> questions, ArrayList<NotificationEntity> notifs){
        this.personneToMur = personneToMur;
        this.questions = questions;
        this.notifs = notifs;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public PersonneEntity getPersonne() { return this.personneToMur; }
    public void setPersonne(PersonneEntity personneToMur) { this.personneToMur=personneToMur; }
    
    public List<QuestionEntity> getQuestions() { return this.questions; }
    public void setQuestions(List<QuestionEntity> questions) { this.questions = questions; }
    
    public List<NotificationEntity> getNotifs() { return this.notifs; }
    public void setNotifs(List<NotificationEntity> notifs) { this.notifs = notifs; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MurEntity)) {
            return false;
        }
        MurEntity other = (MurEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.MurEntity[ id=" + id + " ]";
    }    
}
