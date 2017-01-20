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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author natha_000
 */
@Entity
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
    
    @OneToMany(mappedBy="amis")
    private List<Personne> amis = new ArrayList<Personne>();
    
    @OneToMany(mappedBy="personneToQuestion")
    private List<Question> questions = new ArrayList<Question>();
    
    @OneToMany(mappedBy="personneToMessage")
    private List<Message> messages = new ArrayList<Message>();
    
    @OneToMany(mappedBy="personneToReponse")
    private List<Reponse> reponses = new ArrayList<Reponse>();
    
    @ManyToOne
    @JoinColumn(name="amis_fk")
    private Personne personne;
    
    @OneToOne(mappedBy="personne")
    Mur mur;
    
    
    public PersonneEntity(){
        this.login = "";
        this.nom = "";
        this.prenom = "";
        this.mdp = "";
        this.mail = "";
        this.amis = new ArrayList<>();
        this.questions = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.reponses = new ArrayList<>();
        this.personne = new Personne();
        this.mur = new Mur();
    } 
    
    public PersonneEntity(String login, String nom, String prenom, String mdp, String mail, ArrayList<Personne> amis, ArrayList<Question> questions, ArrayList<Message> messages, ArrayList<Reponse> reponses, Personne personne, Mur mur){
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.mail = mail;
        this.amis = amis;
        this.questions = questions;
        this.messages = messages;
        this.reponses = reponses;
        this.personne = personne;
        this.mur = mur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<Personne> getAmis() { return amis; }
    public void setAmis(List<Personne> amis) { this.amis=amis; }
    
    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions=questions; }
    
    public List<Message> getMessages() { return messages; }
    public void setMessages(List<Message> messages) { this.messages=messages; }
    
    public List<Reponse> getReponses() { return reponses; }
    public void setReponses(List<Reponse> reponses) { this.reponses=reponses; }
    
    public Mur getMur() { return this.mur; }
    public void setMur(Mur mur) { this.mur=mur; }
    
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
    
    public Personne getPersonne() { return this.personne; }
    public void setPersonne(Personne personne) { this.personne=personne; }

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
