/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author natha_000
 */
@Repository
public class PersonneDAOImpl implements PersonneDAO {
    @PersistenceContext(unitName="DoYouPreferPU")
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    @Transactional
    @Override
    public void save(PersonneEntity personne) {
        personne = em.merge(personne);
        em.persist(personne);
    }
    
    @Transactional
    @Override
    public void update(PersonneEntity personne) {
        em.merge(personne);
    } 
    
    @Transactional
    @Override
    public void delete(PersonneEntity personne) {
        personne = em.merge(personne);
        em.remove(personne);
    }
    
    @Transactional(readOnly = true)
    @Override
    public PersonneEntity find(long id){
        return em.find(PersonneEntity.class, id);
    }
    
    @Transactional(readOnly =true)
    @Override
    public List<PersonneEntity> findAll() {
        Query q = em.createQuery("SELECT p FROM PersonneEntity p");
        return q.getResultList();
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<PersonneEntity> findByName(String name) {
        Query q = em.createQuery("SELECT p FROM PersonneEntity p WHERE p.nom = :name").setParameter("name", name);
        return q.getResultList();
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<PersonneEntity> findByLogin(String login) {
        Query q = em.createQuery("SELECT p FROM PersonneEntity p WHERE p.login = :login").setParameter("login", login);
        return q.getResultList();
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<PersonneEntity> findByMail(String mail) {
        Query q = em.createQuery("SELECT p FROM PersonneEntity p WHERE p.mail = :mail").setParameter("mail", mail);
        return q.getResultList();
    }
    
    /*@Transactional(readOnly = true)
    @Override
    public List<MessageEntity> getMessages(String login) {
        Query q = em.createQuery("SELECT p FROM PersonneEntity p WHERE p.login = :login").setParameter("login", login);
        return q.getResultList();
    }*/
}
