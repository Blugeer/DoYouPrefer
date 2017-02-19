/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;

/**
 *
 * @author natha_000
 */
public interface MessageService {
    public Boolean addNotification(String login, String message, ArrayList<String> totalParticipants);
    public ArrayList<String> getNotifications(String login);
    public String getQuestionNotif (String login, int index);
    public Boolean deleteNotif (String login);
}
