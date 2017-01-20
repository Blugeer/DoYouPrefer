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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author natha_000
 */
@Entity
@Table(name="Question")
public class QuestionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String choix1;
    
    @Column
    private String choix2;
    
    @ManyToOne
    @JoinColumn(name="personneToQuestion_fk")
    private PersonneEntity personneToQuestion;
    
    @OneToMany(mappedBy="questionToMessage")
    private List<MessageEntity> messages;
    
    @OneToMany(mappedBy="questionToReponse")
    private List<ReponseEntity> reponses;
    
    @JoinTable(
            name="Mur_Question",
            joinColumns=@JoinColumn(name="id_question"),
            inverseJoinColumns=@JoinColumn(name="id_mur")
    )
    @ManyToMany
    private List<MurEntity> mursToQuestions;
    
    public QuestionEntity(){
        /*this.choix1 = "";
        this.choix2 = "";
        this.personneToQuestion = new PersonneEntity();
        this.messages = new ArrayList<>();
        this.reponses = new ArrayList<>();
        this.mursToQuestions = new ArrayList<>();*/
    }
    
    public QuestionEntity(String choix1, String choix2, PersonneEntity personneToQuestion, ArrayList<MessageEntity> messages, ArrayList<ReponseEntity> reponses, ArrayList<MurEntity> mursToQuestions){
        this.choix1 = choix1;
        this.choix2 = choix2;
        this.personneToQuestion = personneToQuestion;
        this.messages = messages;
        this.reponses = reponses;
        this.mursToQuestions = mursToQuestions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getChoix1() { return this.choix1; }
    public void setChoix1(String choix1) { this.choix1=choix1; }
    
    public String getChoix2() { return this.choix2; }
    public void setChoix2(String choix2) { this.choix2=choix2; }
    
    public PersonneEntity getPersonne() { return this.personneToQuestion; }
    public void setPersonne(PersonneEntity personneToQuestion) { this.personneToQuestion=personneToQuestion; }
    
    public List<MessageEntity> getMessages() { return messages; }
    public void setMessages(List<MessageEntity> messages) { this.messages=messages; }
    
    public List<ReponseEntity> getReponses() { return reponses; }
    public void setReponses(List<ReponseEntity> reponses) { this.reponses=reponses; }
    
    public List<MurEntity> getMurs() { return mursToQuestions; }
    public void setMurs(List<MurEntity> mursToQuestions) { this.mursToQuestions=mursToQuestions; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionEntity)) {
            return false;
        }
        QuestionEntity other = (QuestionEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.QuestionEntity[ id=" + id + " ]";
    }
    
}
