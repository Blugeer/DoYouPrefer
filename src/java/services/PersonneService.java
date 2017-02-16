/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.PersonneEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author natha_000
 */
public interface PersonneService {
    // Fonction vérifiant la présence d'un utilisateur dans le BD
    public Boolean connectionUser(String login, String mdp);
    // Fonction permettant l'ajout d'un profil dans la BD
    public Boolean addPersonne(PersonneEntity p);
    // Fonction permettant l'ajout d'un ami dans la BD
    public Boolean addAmi(String login, String loginAmi);
    // Fonction récupérant les messages pour un login donné
    //public ArrayList<String> getMessages(String login);
    // Fonction récupérant les amis pour un login donné
    public ArrayList<String> getAmisLogin(String login);
    // Fonction récupérant les questiones pour un login donné
    public List<String> getQuestionsLogin(String login);
    // Fonction vérifiant si l'objet Personne existe pour un login donné
    public Boolean getUserByLogin(String login);
}
