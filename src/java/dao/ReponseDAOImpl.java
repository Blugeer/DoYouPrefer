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
public class ReponseDAOImpl implements ReponseDAO {
    
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
    public void save(ReponseEntity reponse) {
        reponse = em.merge(reponse);
        em.persist(reponse);
    }
    
    @Transactional
    @Override
    public void update(ReponseEntity reponse) {
        em.merge(reponse);
    } 
    
    @Transactional
    @Override
    public void delete(ReponseEntity reponse) {
        reponse = em.merge(reponse);
        em.remove(reponse);
    }
    
    @Transactional(readOnly = true)
    @Override
    public ReponseEntity find(long id){
        return em.find(ReponseEntity.class, id);
    }
    
    @Transactional(readOnly =true)
    @Override
    public List<ReponseEntity> findAll() {
        Query q = em.createQuery("SELECT reponse FROM ReponseEntity reponse");
        return q.getResultList();
    }

    @Override
    public List<ReponseEntity> findByLogin(String login) {
        Query q = em.createQuery("SELECT reponse FROM ReponseEntity reponse WHERE reponse.personneToReponse.login = :login").setParameter("login", login);
        return q.getResultList();
    }
}
