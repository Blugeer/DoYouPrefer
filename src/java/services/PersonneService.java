/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.PersonneEntity;
import dao.QuestionEntity;
import java.util.ArrayList;

/**
 *
 * @author natha_000
 */
public interface PersonneService {
    public Boolean connectionUser(String login, String mdp);
    public Boolean addPersonne(PersonneEntity p);
    public Boolean addAmi(String login, String loginAmi);
    public ArrayList<String> getMessages(String login);
    public ArrayList<PersonneEntity> getAmisLogin(String login);
    public ArrayList<String> getQuestionsLogin(String login);
    public ArrayList<QuestionEntity> getQuestionsEntityLogin(String login);
    public PersonneEntity getUserByLogin(String login);
}
