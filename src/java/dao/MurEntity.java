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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author natha_000
 */
@Entity
public class MurEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name="personne_fk")
    private Personne personne;
    
    @ManyToMany(mappedBy="murs")
    private List<Question> questions;
    
    public MurEntity(){
        this.personne = new Personne() ;
        this.questions = new ArrayList<>();
    } 
    
    public MurEntity(Personne personne, List<Question> question){
        this.personne = personne;
        this.questions = questions;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Personne getPersonne() { return this.personne; }
    public void setPersonne(Personne personne) { this.personne=personne; }
    
    public List<Question> getQuestions() { return this.questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }

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
