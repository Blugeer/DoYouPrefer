/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.Personne;
import dao.PersonneDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author natha_000
 */

@Service("PersonneService")
public class PersonneServiceImpl implements PersonneService {
    
    @Autowired
    PersonneDAO personneDAO;
    
    @Override
    public Boolean addPersonne(Personne p){
        //TODO
        return true;
    }
}
