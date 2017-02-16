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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    
    @Id
    private String login;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String mdp;
    @Column
    private String mail;
 
    @JoinTable(
        name="personneToAmis_fk",
        joinColumns=@JoinColumn(name="id_user"),
        inverseJoinColumns=@JoinColumn(name="id_ami")
    )
    @ManyToMany
    private List<PersonneEntity> amis;
    
    @OneToMany(mappedBy="personneToMessage")
    private List<MessageEntity> messages;
    
    @OneToMany(mappedBy="personneToReponse")
    private List<ReponseEntity> reponses;

    @OneToOne(mappedBy="personneToMur")
    MurEntity mur;
    
    
    public PersonneEntity(){
    } 
    
    public PersonneEntity(String login, String nom, String prenom, String mdp, String mail){
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.mail = mail;
        this.amis = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.reponses = new ArrayList<>();
        this.mur = new MurEntity(this, new ArrayList<>(), new ArrayList<>());
    }
    
    public List<PersonneEntity> getAmis() { return amis; }
    public void setAmis(List<PersonneEntity> amis) { this.amis=amis; }
    
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonneEntity)) {
            return false;
        }
        PersonneEntity other = (PersonneEntity) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.PersonneEntity[ login=" + login + " ]";
    }
    
}
