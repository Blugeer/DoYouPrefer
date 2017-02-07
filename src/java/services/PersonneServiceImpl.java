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
    public Boolean addPersonne(PersonneEntity p){
        System.out.println(p.getLogin());
        if(personneDAO.findByLogin(p.getLogin()).isEmpty()){
            System.out.println(p.getLogin());
            personneDAO.save(p);
            return true;
        }
        return false;
    }
}
