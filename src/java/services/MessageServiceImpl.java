/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.NotificationDAO;
import dao.NotificationEntity;
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

    @Override
    public Boolean addNotification(NotificationEntity n) {
        notificationDAO.save(n);
        return true;
    }
    
}
