/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.PersonneDAO;
import dao.PersonneEntity;
import java.util.ArrayList;
import java.util.List;
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
    
    @Override
    public Boolean addAmi(String login, String loginAmi){
        if (!personneDAO.findByLogin(login).isEmpty() && !personneDAO.findByLogin(loginAmi).isEmpty()){
            PersonneEntity p = personneDAO.findByLogin(login).get(0);
            PersonneEntity pAmi = personneDAO.findByLogin(loginAmi).get(0);
            List<PersonneEntity> listeAmis = p.getAmis();
            if (!listeAmis.contains(pAmi)){
                listeAmis.add(pAmi);
                p.setAmis(listeAmis);
                personneDAO.update(p);
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> getMessages(String login) {
        //return personneDAO.
        return null;
    }
}
