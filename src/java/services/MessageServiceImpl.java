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
    public Boolean addMessage(String contenu, String login, Long idQuestion) {
        if (!personneDAO.findByLogin(login).isEmpty()){
            PersonneEntity p = personneDAO.findByLogin(login).get(0);
            MessageEntity commentaire = new MessageEntity(contenu, p, questionDAO.find(idQuestion));
            messageDAO.save(commentaire);
            return true;
        }
        return false;
    }
    
}