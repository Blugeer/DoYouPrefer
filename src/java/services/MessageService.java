/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.NotificationEntity;

/**
 *
 * @author natha_000
 */
public interface MessageService {
    public Boolean addNotification(NotificationEntity n);
}
