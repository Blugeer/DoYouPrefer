/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.MurEntity;
import dao.NotificationDAO;
import dao.NotificationEntity;
import dao.PersonneDAO;
import dao.PersonneEntity;
import dao.MessageDAO;
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
public class MessageServiceImpl implements MessageService {
    
    @Autowired
    NotificationDAO notificationDAO;
    
    @Autowired
    PersonneDAO personneDAO;
    
    @Autowired
    QuestionDAO questionDAO;

    @Autowired
    MessageDAO messageDAO;
    
    @Override
    public Boolean addNotification(String login, String message, ArrayList<String> totalParticipants) {
        ArrayList<MurEntity> murs = new ArrayList<>();
        for (int i = 0; i < totalParticipants.size(); i++){
            if (!totalParticipants.get(i).equals(login)){
                murs.add(personneDAO.findByLogin(totalParticipants.get(i)).get(0).getMur());
            }
        }
        NotificationEntity n = new NotificationEntity(message, murs);
        notificationDAO.save(n);
        return true;
    }

    @Override
    public ArrayList<String> getNotifications(String login) {
        List<NotificationEntity> notifs = notificationDAO.findByLogin(login);
        ArrayList<String> notifsString = new ArrayList<>();
        for (int i = 0; i < notifs.size(); i++){
            notifsString.add(notifs.get(i).getMessage());
        }
        return notifsString;  
    }
    
    @Override
    public String getQuestionNotif (String login, int index){
        QuestionEntity q = questionDAO.findByMur(personneDAO.findByLogin(login).get(0).getMur().getId()).get(index);
        return q.toString();
    }

    @Override
    public Boolean deleteNotif(String login) {
        // Supprimer tous les enregistrements de la table
        notificationDAO.deleteAll(login);
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
