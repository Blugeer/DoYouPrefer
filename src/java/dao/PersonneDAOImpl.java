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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author natha_000
 */
public class PersonneDAOImpl implements PersonneDAO {
    @PersistenceContext(unitName="PersonnePU")
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
        Query q = em.createQuery("SELECT personne FROM PersonneEntity personne");
        return q.getResultList();
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<PersonneEntity> findByName(String nom) {
        Query q = em.createQuery("SELECT personne FROM PersonneEntity personne WHERE p.nom = ?").setParameter(1,nom);
        return q.getResultList();
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<PersonneEntity> findByLogin(String login) {
        Query q = em.createQuery("SELECT personne FROM PersonneEntity personne WHERE p.login = ?").setParameter(1,login);
        return q.getResultList();
    }
}
