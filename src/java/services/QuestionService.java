/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.QuestionEntity;
import dao.ReponseEntity;

/**
 *
 * @author natha_000
 */
public interface QuestionService {
    // Fonction permettant l'ajout d'une question dans la BD
    public Boolean addQuestion(QuestionEntity q);
    // Fonction permettant l'ajout d'une réponse dans la BD
    public Boolean addReponse(ReponseEntity r);
}
