/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.PersonneDAO;
import dao.PersonneEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author natha_000
 */

@Service
public class PersonneServiceImpl implements PersonneService {
    
    @Autowired
    PersonneDAO personneDAO;
    
    @Override
    public Boolean connectionUser(String login, String mdp) {
        if (!personneDAO.findByLogin(login).isEmpty()){
            return (personneDAO.findByLogin(login).get(0).getMdp().equals(mdp));      
        }
        return false;
    }
    
    @Override
    public Boolean addPersonne(PersonneEntity p){
        if (personneDAO.findByLogin(p.getLogin()).isEmpty() && personneDAO.findByMail(p.getMail()).isEmpty()){
            personneDAO.save(p);
            return true;
        }
        return false;
    }
}
