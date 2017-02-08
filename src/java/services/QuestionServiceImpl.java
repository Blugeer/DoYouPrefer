/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.QuestionDAO;
import dao.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author natha_000
 */

@Service
public class QuestionServiceImpl implements QuestionService{
      
    @Autowired
    QuestionDAO questionDAO;
    
    @Override
    public Boolean addQuestion(QuestionEntity q){
        questionDAO.save(q);
        return true;
    }
}
