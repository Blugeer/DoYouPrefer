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
public class NotificationDAOImpl implements NotificationDAO {
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
    public void save(NotificationEntity notif) {
        notif = em.merge(notif);
        em.persist(notif);
    }
    
    @Transactional
    @Override
    public void update(NotificationEntity notif) {
        em.merge(notif);
    } 
    
    @Transactional
    @Override
    public void delete(NotificationEntity notif) {
        notif = em.merge(notif);
        em.remove(notif);
    }
    
    @Transactional(readOnly = true)
    @Override
    public NotificationEntity find(long id){
        return em.find(NotificationEntity.class, id);
    }
    
    @Transactional(readOnly =true)
    @Override
    public List<NotificationEntity> findAll() {
        Query q = em.createQuery("SELECT notif FROM NotificationEntity notif");
        return q.getResultList();
    }
}
