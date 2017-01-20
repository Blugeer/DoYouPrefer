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
public class MessageDAOImpl implements MessageDAO {
    
    @PersistenceContext(unitName="MessagePU")
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    @Transactional
    @Override
    public void save(MessageEntity message) {
        message = em.merge(message);
        em.persist(message);
    }
    
    @Transactional
    @Override
    public void update(MessageEntity message) {
        em.merge(message);
    } 
    
    @Transactional
    @Override
    public void delete(MessageEntity message) {
        message = em.merge(message);
        em.remove(message);
    }
    
    @Transactional(readOnly = true)
    @Override
    public MessageEntity find(long id){
        return em.find(MessageEntity.class, id);
    }
    
    @Transactional(readOnly =true)
    @Override
    public List<MessageEntity> findAll() {
        Query q = em.createQuery("SELECT message FROM MessageEntity message");
        return q.getResultList();
    }
    
}
