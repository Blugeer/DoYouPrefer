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
public interface QuestionService {
    // Fonction permettant l'ajout d'une question dans la BD
    public Boolean addQuestion(String choix1, String choix2, ArrayList<String> totalParticipants);
    // Fonction permettant l'ajout d'une réponse dans la BD
    public Boolean addReponse(String login, int index, int choix);
    // Permet de renvoyer la liste des questions sous forme de chaîne de caractères
    public ArrayList<String> getQuestions(String login);
    // Renvoie une liste de booléens permettant de savoir à quelles questions un utilisateur a déjà répondu
    public ArrayList<Boolean> getQuestionsAnswered(String login);
}
