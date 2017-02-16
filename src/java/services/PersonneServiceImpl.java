/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.PersonneDAO;
import dao.PersonneEntity;
import dao.QuestionDAO;
import dao.QuestionEntity;
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
    
    @Autowired
    QuestionDAO questionDAO;
    
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
            if (!personneDAO.findByLogin(login).get(0).getAmis().contains(pAmi) && !p.equals(pAmi)){
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

    @Override
    public ArrayList<PersonneEntity> getAmisLogin(String login) {
        ArrayList<PersonneEntity> amisLogin = new ArrayList<>(personneDAO.findByLogin(login).get(0).getAmis());
        return amisLogin;
    }
    
    @Override
    public List<QuestionEntity> getQuestionsLogin(String login) {
        return questionDAO.findByMur(personneDAO.findByLogin(login).get(0).getMur().getId());
    }
    
    /*@Override
    public ArrayList<String> getQuestionsLogin(String login) {
        ArrayList<QuestionEntity> questions = new ArrayList<>(personneDAO.findByLogin(login).get(0));
        return questions;
    }*/

    @Override
    public PersonneEntity getUserByLogin(String login) {
        if (personneDAO.findByLogin(login).size() > 0){
            return personneDAO.findByLogin(login).get(0);
        }
        else{
            return null;
        }
    }
}
