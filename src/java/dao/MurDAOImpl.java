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
public class MurDAOImpl implements MurDAO {

    @PersistenceContext(unitName="MurPU")
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    @Transactional
    @Override
    public void save(MurEntity mur) {
        mur = em.merge(mur);
        em.persist(mur);
    }
    
    @Transactional
    @Override
    public void update(MurEntity mur) {
        em.merge(mur);
    } 
    
    @Transactional
    @Override
    public void delete(MurEntity mur) {
        mur = em.merge(mur);
        em.remove(mur);
    }
    
    @Transactional(readOnly = true)
    @Override
    public MurEntity find(long id){
        return em.find(MurEntity.class, id);
    }
    
    @Transactional(readOnly =true)
    @Override
    public List<MurEntity> findAll() {
        Query q = em.createQuery("SELECT mur FROM MurEntity mur");
        return q.getResultList();
    }
    
}
