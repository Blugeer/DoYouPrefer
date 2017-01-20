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
@Table(name="Message")
public class MessageEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String contenu;
    
    @ManyToOne
    @JoinColumn(name="personneToMessage_fk")
    private PersonneEntity personneToMessage;
    
    @ManyToOne
    @JoinColumn(name="questionToMessage_fk")
    private QuestionEntity questionToMessage;
    
    public MessageEntity(){
        /*this.contenu= "" ;
        this.personneToMessage= new PersonneEntity();
        this.questionToMessage = new QuestionEntity();*/
    } 
    
    public MessageEntity(String contenu, PersonneEntity personne, QuestionEntity question){
        this.contenu = contenu;
        this.personneToMessage = personne;
        this.questionToMessage = question;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getContenu() { return this.contenu; }
    public void setContenu(String contenu) { this.contenu=contenu; }
    
    public PersonneEntity getPersonne() { return this.personneToMessage; }
    public void setPersonne(PersonneEntity personneToMessage) { this.personneToMessage=personneToMessage; }
    
    public QuestionEntity getQuestion() { return this.questionToMessage; }
    public void setQuestion(QuestionEntity questionToMessage) { this.questionToMessage=questionToMessage; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageEntity)) {
            return false;
        }
        MessageEntity other = (MessageEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.MessageEntity[ id=" + id + " ]";
    }
    
}
