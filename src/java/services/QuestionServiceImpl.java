/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.MessageEntity;
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
public class QuestionServiceImpl implements QuestionService{
      
    @Autowired
    QuestionDAO questionDAO;
    
    @Override
    public Boolean addQuestion(QuestionEntity q){
        questionDAO.save(q);
        return true;
    }
    
    @Override
    public ArrayList<String> getMessages(String choix1, String choix2) {
        ArrayList<MessageEntity> messages = new ArrayList<>(questionDAO.findByChoix(choix1, choix2).get(0).getMessages());
        ArrayList<String> messagesString = new ArrayList<>();
        for (int i = 0; i < messages.size(); i++){
            messagesString.add(messages.get(i).toString());
        }
        return messagesString;
    }
}
