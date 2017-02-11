/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.MessageEntity;

/**
 *
 * @author natha_000
 */
public interface MessageService {
    public Boolean addMessage(MessageEntity m);
}
