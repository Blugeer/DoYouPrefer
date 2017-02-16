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

    /*@Override
    public ArrayList<String> getMessages(String login) {
        //return personneDAO.
        return null;
    }*/

    @Override
    public ArrayList<String> getAmisLogin(String login) {
        List<PersonneEntity> amis = personneDAO.findByLogin(login).get(0).getAmis();
        ArrayList<String> amisLogin = new ArrayList<>();
        if (amis.size() > 0){
            for (int i = 0; i < amis.size(); i++){
                amisLogin.add(amis.get(i).getLogin());
            }
        }
        return amisLogin;
    }
    
    @Override
    public List<String> getQuestionsLogin(String login) {
        List<QuestionEntity> questions = questionDAO.findByMur(personneDAO.findByLogin(login).get(0).getMur().getId());
        ArrayList<String> questionsString = new ArrayList<>();
        if (questions.size() > 0){
            for (int i = 0; i < questions.size(); i++){
                questionsString.add(questions.get(i).toString());
            }
            
        }
        return questionsString;
    }

    @Override
    public Boolean getUserByLogin(String login) {
        return (personneDAO.findByLogin(login).size() > 0);
    }
}
