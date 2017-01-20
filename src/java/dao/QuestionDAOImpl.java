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
public class QuestionDAOImpl implements QuestionDAO {
    @PersistenceContext(unitName="QuestionPU")
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    @Transactional
    @Override
    public void save(QuestionEntity question) {
        question = em.merge(question);
        em.persist(question);
    }
    
    @Transactional
    @Override
    public void update(QuestionEntity question) {
        em.merge(question);
    } 
    
    @Transactional
    @Override
    public void delete(QuestionEntity question) {
        question = em.merge(question);
        em.remove(question);
    }
    
    @Transactional(readOnly = true)
    @Override
    public QuestionEntity find(long id){
        return em.find(QuestionEntity.class, id);
    }
    
    @Transactional(readOnly =true)
    @Override
    public List<QuestionEntity> findAll() {
        Query q = em.createQuery("SELECT question FROM QuestionEntity question");
        return q.getResultList();
    }
}
