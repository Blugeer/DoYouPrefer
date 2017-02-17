/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.MurEntity;
import dao.PersonneDAO;
import dao.QuestionDAO;
import dao.QuestionEntity;
import dao.ReponseDAO;
import dao.ReponseEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author natha_000
 */

@Service
public class QuestionServiceImpl implements QuestionService{
    
    @Autowired
    PersonneDAO personneDAO;
    
    @Autowired
    QuestionDAO questionDAO;
    
    @Autowired
    ReponseDAO reponseDAO;
    
    PersonneService personneService;
    
    @Override
    public Boolean addQuestion(String choix1, String choix2, ArrayList<String> totalParticipants){
        ArrayList<MurEntity> murs = new ArrayList<>();
        for (int i = 0; i < totalParticipants.size(); i++){
            if (personneDAO.findByLogin(totalParticipants.get(i)).size() > 0){
                murs.add(personneDAO.findByLogin(totalParticipants.get(i)).get(0).getMur());
            }
        }
                     
        QuestionEntity q = new QuestionEntity(choix1, choix2, murs);
        questionDAO.save(q);
        return true;
    }

    @Override
    public Boolean addReponse(String login, int index, int choix) {
        QuestionEntity q = questionDAO.findByMur(personneDAO.findByLogin(login).get(0).getMur().getId()).get(index);
        ReponseEntity r = new ReponseEntity(login, personneDAO.findByLogin(login).get(0), q);
        switch (choix) {
            case 1:
                r.setChoix(q.getChoix1());
                break;
            case 2:
                r.setChoix(q.getChoix2());
                break;
            default:
                return false;
        }
        reponseDAO.save(r);
        return true;
    }

    @Override
    public ArrayList<String> getQuestions(String login) {
        List<QuestionEntity> questions = questionDAO.findByMur(personneDAO.findByLogin(login).get(0).getMur().getId());
        ArrayList<String> questionsString = new ArrayList<>();
        
        for (int i = 0; i < questions.size(); i++){
            questionsString.add(questions.get(i).toString());
        }
        return questionsString;
    }
    
    
    @Override
    public ArrayList<Boolean> getQuestionsAnswered(String login) {
        
        List<QuestionEntity> questions = questionDAO.findByMur(personneDAO.findByLogin(login).get(0).getMur().getId());
        List<ReponseEntity> reponses = reponseDAO.findByLogin(login);
        ArrayList<Boolean> questionsAnsweredByLogin = new ArrayList<>();
        boolean found;
        
        for (int i = 0; i < questions.size(); i++){
            found = false;
            for (int j = 0; j < reponses.size(); j++){
               if (questions.get(i).equals(reponses.get(j).getQuestion()) && !found){
                   found = true;
               }
            }
            questionsAnsweredByLogin.add(found);
        }
        
        return questionsAnsweredByLogin;
    }
}
