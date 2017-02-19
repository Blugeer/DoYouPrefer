/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.MessageDAO;
import dao.MessageEntity;
import dao.NotificationDAO;
import dao.NotificationEntity;
import dao.PersonneDAO;
import dao.PersonneEntity;
import dao.QuestionDAO;
import dao.QuestionEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author natha_000
 */

@Service
public class MessageServiceImpl implements MessageService {
    
    @Autowired
    NotificationDAO notificationDAO;
    
    @Autowired
    MessageDAO messageDAO;
    
    @Autowired
    PersonneDAO personneDAO;

    @Autowired
    QuestionDAO questionDAO;
    
    @Override
    public Boolean addNotification(NotificationEntity n) {
        notificationDAO.save(n);
        return true;
    }
    
    @Override
    public Boolean addMessage(String contenu, String login, Integer idQuestion) {
        if (!personneDAO.findByLogin(login).isEmpty()){
            PersonneEntity p = personneDAO.findByLogin(login).get(0);
            List<QuestionEntity> questions = questionDAO.findByMur(personneDAO.findByLogin(login).get(0).getMur().getId());
            MessageEntity commentaire = new MessageEntity(contenu, p, questions.get(idQuestion));
            messageDAO.save(commentaire);
            return true;
        }
        return false;
    }
    
}