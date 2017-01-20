/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author natha_000
 */
@Entity
@Table(name="Reponse")
public class ReponseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String choix;
    
    @ManyToOne
    @JoinColumn(name="personneToReponse_fk")
    private PersonneEntity personneToReponse;
    
    @ManyToOne
    @JoinColumn(name="questionToReponse_fk")
    private QuestionEntity questionToReponse;
    
    public ReponseEntity(){
        /*this.choix = "";
        this.personneToReponse = new PersonneEntity();
        this.questionToReponse = new QuestionEntity();*/
    }
    
    public ReponseEntity(String choix, PersonneEntity personneToReponse, QuestionEntity questionToReponse){
        this.choix = choix;
        this.personneToReponse = personneToReponse;
        this.questionToReponse = questionToReponse;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getChoix() { return this.choix; }
    public void setChoix(String choix) { this.choix=choix; }
    
    public PersonneEntity getPersonne() { return this.personneToReponse; }
    public void setPersonne(PersonneEntity personne) { this.personneToReponse=personne; }
    
    public QuestionEntity getQuestion() { return this.questionToReponse; }
    public void setQuestion(QuestionEntity questionToReponse) { this.questionToReponse=questionToReponse; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReponseEntity)) {
            return false;
        }
        ReponseEntity other = (ReponseEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.ReponseEntity[ id=" + id + " ]";
    }
    
}
