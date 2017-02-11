/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.MessageDAO;
import dao.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author natha_000
 */
public class MessageServiceImpl implements MessageService {
          
    @Autowired
    MessageDAO messageDAO;
    
    @Override
    public Boolean addMessage(MessageEntity m){
        messageDAO.save(m);
        return true;
    }
}
