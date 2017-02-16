/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.QuestionEntity;
import dao.ReponseEntity;
import java.util.ArrayList;

/**
 *
 * @author natha_000
 */
public interface QuestionService {
    public Boolean addQuestion(QuestionEntity q);
    public ArrayList<String> getMessages(String choix1, String choix2);
    public Boolean addReponse(ReponseEntity r);
}
