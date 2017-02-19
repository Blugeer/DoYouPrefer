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
    
    @Override
    public ArrayList<Float> getAnswerPercentage(String login){
        List<QuestionEntity> questions = questionDAO.findByMur(personneDAO.findByLogin(login).get(0).getMur().getId());
        List<ReponseEntity> reponses = reponseDAO.findAll();
        ArrayList<Float> questionsAnswerPercentage = new ArrayList<>();
        
        float choix1 = 0;
        float choix2 = 0; 

        for (int i = 0; i < questions.size(); i++){
            for (int j = 0; j < reponses.size(); j++){
               if (questions.get(i).equals(reponses.get(j).getQuestion())){
                   if (reponses.get(j).getChoix().equals(questions.get(i).getChoix1())){
                       choix1++;
                   }
                   else{
                       choix2++;
                   }
               }
            }
            if (choix1 == 0 && choix2 == 0){
                questionsAnswerPercentage.add(-1.0f);
            }
            else{
                questionsAnswerPercentage.add((choix1/(choix1+choix2))*100.0f);
                choix1 = 0;
                choix2 = 0;
            }
        }
        
        return questionsAnswerPercentage;
    }

    @Override
    public ArrayList<String> getParticipants(String login, int index) {
        ArrayList<String> participants = new ArrayList<>();
        QuestionEntity q = questionDAO.findByMur(personneDAO.findByLogin(login).get(0).getMur().getId()).get(index);
        for (int i = 0; i < q.getMurs().size(); i++){
            if (!q.getMurs().get(i).getPersonne().getLogin().equals(login)){
                participants.add(q.getMurs().get(i).getPersonne().getLogin());
            }
        }
        return participants;
    }
}
