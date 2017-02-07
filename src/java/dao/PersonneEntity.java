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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author natha_000
 */
@Entity
@Table(name="Personne")
public class PersonneEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String login;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String mdp;
    @Column
    private String mail;
    
    @ManyToOne
    private PersonneEntity personneToAmis;
    
    @OneToMany(mappedBy="personneToAmis")
    private List<PersonneEntity> amis;
    
    @OneToMany(mappedBy="personneToQuestion")
    private List<QuestionEntity> questions;
    
    @OneToMany(mappedBy="personneToMessage")
    private List<MessageEntity> messages;
    
    @OneToMany(mappedBy="personneToReponse")
    private List<ReponseEntity> reponses;

    @OneToOne(mappedBy="personneToMur")
    MurEntity mur;
    
    
    public PersonneEntity(){
        /*this.login = "";
        this.nom = "";
        this.prenom = "";
        this.mdp = "";
        this.mail = "";
        this.amis = new ArrayList<>();
        this.questions = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.reponses = new ArrayList<>();
        this.mur = new MurEntity();*/
    } 
    
    public PersonneEntity(String login, String nom, String prenom, String mdp, String mail){
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.mail = mail;
        this.amis = new ArrayList<>();
        this.questions = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.reponses = new ArrayList<>();
        this.mur = new MurEntity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<PersonneEntity> getAmis() { return amis; }
    public void setAmis(List<PersonneEntity> amis) { this.amis=amis; }
    
    public List<QuestionEntity> getQuestions() { return questions; }
    public void setQuestions(List<QuestionEntity> questions) { this.questions=questions; }
    
    public List<MessageEntity> getMessages() { return messages; }
    public void setMessages(List<MessageEntity> messages) { this.messages=messages; }
    
    public List<ReponseEntity> getReponses() { return reponses; }
    public void setReponses(List<ReponseEntity> reponses) { this.reponses=reponses; }
    
    public MurEntity getMur() { return this.mur; }
    public void setMur(MurEntity mur) { this.mur=mur; }
    
    public String getNom() { return this.nom; }
    public void setNom(String nom) { this.nom=nom; }
   
    public String getLogin() { return this.login; }
    public void setLogin(String login) { this.login=login; }

    public String getPrenom() { return this.prenom; }
    public void setPrenom(String prenom) { this.prenom=prenom; }
    
    public String getMdp() { return this.mdp; }
    public void setMdp(String mdp) { this.mdp=mdp; }
    
    public String getMail() { return this.mail; }
    public void setMail(String mail) { this.mail=mail; }
    
    public PersonneEntity getPersonne() { return this.personneToAmis; }
    public void setPersonne(PersonneEntity personneToAmis) { this.personneToAmis=personneToAmis; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonneEntity)) {
            return false;
        }
        PersonneEntity other = (PersonneEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.PersonneEntity[ id=" + id + " ]";
    }
    
}
