/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.PersonneEntity;
import dao.QuestionEntity;
import java.util.ArrayList;
import java.util.List;

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
    public List<QuestionEntity> getQuestionsLogin(String login);
    public PersonneEntity getUserByLogin(String login);
}
