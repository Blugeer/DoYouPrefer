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
    public Boolean addReponse(ReponseEntity r) {
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
}
