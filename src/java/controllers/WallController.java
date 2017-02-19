/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.MurEntity;
import dao.NotificationEntity;
import dao.PersonneEntity;
import dao.QuestionEntity;
import dao.ReponseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.MessageService;
import services.PersonneService;
import services.QuestionService;

/**
 * @author natha_000
 */

@Controller
public class WallController {
    
    @Autowired
    private PersonneService personneService ;
    
    @Autowired
    private QuestionService questionService ;
    
    @Autowired
    private MessageService messageService;
    
    @RequestMapping(value="wall", method = RequestMethod.GET)
    public ModelAndView initConnect(@RequestParam(value = "user") String user, HttpServletRequest request){
        /* Quand on visite la page d'un ami */
        HttpSession session;
        session = request.getSession(false);
        List<String> questionsString;
        ModelAndView mv = new ModelAndView("wall");
        if (session.getAttribute("login") != null){
            String login = session.getAttribute("login").toString();
            List<String> amisLogin = personneService.getAmisLogin(login);
            for (int i = 0; i < amisLogin.size(); i++){
                if (amisLogin.get(i).equals(user)){
                    String result = "Bienvenue sur le mur de " + user;
                    questionsString = personneService.getQuestionsLogin(user);
                    mv.addObject("user", user);
                    mv.addObject("wallMessage", result);
                    mv.addObject("questions", questionsString);
                    mv.addObject("questionsAnswered", questionService.getQuestionsAnswered(user));
                    mv.addObject("questionsPercentages", questionService.getAnswerPercentage(user));
                }
            }
        }
	return mv;
    }
     
    @RequestMapping(value="wall", method = RequestMethod.POST)
    protected ModelAndView handle(HttpServletRequest request,HttpServletResponse response) 
    throws Exception 
    {
        HttpSession session;
        ModelAndView mv;
        String login, nom, prenom, mdp, mail;
        List<String> amisString;
        List<String> questionsString;
        String messageNotif;
        
        
        // Si le paramètre login existe dans la requêtre POST (connexion ou création de compte)
        if (request.getParameterMap().containsKey("login")){ 
            // On récupère les différents paramètres de requête
            login = request.getParameter("login");
            nom = request.getParameter("nom");
            prenom = request.getParameter("prenom");
            mdp = request.getParameter("mdp");
            mail = request.getParameter("mail");
            
            // Cas où le login et le mdp sont renseignés
            if (login != null && login.length() > 0 && mdp != null && mdp.length() > 0){
                // Cas où l'on est dans la création de compte avec des paramètres valides
                if (nom != null && prenom != null && mail != null && nom.length() > 0 && prenom.length() > 0 && mail.length() > 0){        
                    PersonneEntity p = new PersonneEntity(login, nom, prenom, mdp, mail);
                    // Si l'ajout de personne ne réussit pas, c'est que l'utilisateur existe déjà
                    if (!(personneService.addPersonne(p))){
                       mv = addErrorMessage("Erreur : cet utilisateur existe déjà"); 
                       return mv;
                    }
                }
                // Cas où les champs sont mal renseignés lors de l'inscription
                else if (nom != null && prenom != null && mail != null && (nom.length() == 0 || prenom.length() == 0 || mail.length() == 0)){
                    mv = addErrorMessage("Erreur lors de l'inscription");
                    return mv;
                }
                // Cas où les identifiants sont invalides lors d'une connexion
                else if(!personneService.connectionUser(login, mdp)){
                    mv = addErrorMessage("Identifiants non valides");
                    return mv;
                }
                // Mise en place de la vue du mur et création de la session si tout se passe bien
                mv = new ModelAndView("wall");
                session = request.getSession(true);
                session.setAttribute("login", request.getParameter("login"));
                amisString = personneService.getAmisLogin(login);
                session.setAttribute("amis", amisString);
                questionsString = personneService.getQuestionsLogin(login);
                session.setAttribute("questions", questionsString);
            }
            // Cas où le login et/ou le mdp sont mal renseignés lors d'une inscription/connexion 
            else{
               mv = addErrorMessage("Erreur lors du renseignement de paramètres");
               return mv;
            }
        }
        // Si le paramètre login n'existe pas dans la requêtre POST
        else{
            // On récupère la session
            session = request.getSession(false);
            // Si la requête contient le paramètre loginAmi, alors on tente l'ajout d'un ami
            if (request.getParameterMap().containsKey("loginAmi")){
                // Si l'ajout d'un ami ne se passe pas bien, on affiche un message d'erreur
                if(!personneService.addAmi(session.getAttribute("login").toString(), request.getParameter("loginAmi"))){
                    mv = addErrorMessage("Erreur lors de l'ajout d'ami");
                    return mv;
                }
            }
            // Cas où le login de la session n'est pas nul
            if (session.getAttribute("login") != null){
                mv = new ModelAndView("wall");
                login = (String)session.getAttribute("login");
                amisString = personneService.getAmisLogin(login);
                session.setAttribute("amis", amisString);
                
                /**
                 * Si l'on observe qu'une réponse a été sélectionnée
                 */
                if(request.getParameterMap().containsKey("answer1") || request.getParameterMap().containsKey("answer2")){
                    if (request.getParameterMap().containsKey("question")){
                        int index = Integer.parseInt(request.getParameter("question"));
                        if (request.getParameterMap().containsKey("answer1")){
                            questionService.addReponse(login, index, 1); 
                        }
                        else if (request.getParameterMap().containsKey("answer2")){
                            questionService.addReponse(login, index, 2);
                        }
                        String question = messageService.getQuestionNotif(login, index);
                        messageNotif = login + " a répondu à la question : " + question; 
                        ArrayList<String> totalParticipants = questionService.getParticipants(login, index);
                        if (!messageService.addNotification(login, messageNotif, totalParticipants)){
                            mv = addErrorMessage("Problème d'envoi de notification");
                            return mv;
                        }
                    }
                }  
                
                /**
                 * Si une question a été créé, ainsi que la liste des participants qui va avec
                 */
                if(request.getParameterMap().containsKey("choix1") && request.getParameterMap().containsKey("choix2")){
                    String choix1 = request.getParameter("choix1");
                    String choix2 = request.getParameter("choix2");     
                    
                    ArrayList<String> totalParticipants = new ArrayList<>();
                    totalParticipants.add(login);
                    
                    if (session.getAttribute("participants") != null){
                        ArrayList<String> participants = (ArrayList<String>)session.getAttribute("participants");
                        for (int i = 0; i < participants.size(); i++){
                            totalParticipants.add(participants.get(i));
                        }
                        messageNotif = "Vous avez reçu une nouvelle question de la part de " + login;
                        if (!messageService.addNotification(login, messageNotif, totalParticipants)){
                            mv = addErrorMessage("Problème d'envoi de notification");
                            return mv;
                        }
                        session.setAttribute("participants", new ArrayList<>());
                    }
                    
                    if(!questionService.addQuestion(choix1, choix2, totalParticipants)){
                        mv = addErrorMessage("Erreur lors de l'ajout de question");
                        return mv;
                    }
                    
                    session.setAttribute("questions", questionService.getQuestions(login));
                }
                
                if(request.getParameterMap().containsKey("deleteNotif")){
                    messageService.deleteNotif(login);
                }
            }
            // Sinon message d'erreur
            else{
                mv = addErrorMessage("Erreur lors de l'inscription ou de la connexion");
                return mv;
            }
        }
        
        String result = "Bienvenue sur ton mur " + login;
        //messages = (ArrayList<String>)session.getAttribute("messages");
        
        mv.addObject("wallMessage", result);
        mv.addObject("user", login);
        mv.addObject("amis", amisString);
        mv.addObject("questions", questionService.getQuestions(login));
        mv.addObject("questionsAnswered", questionService.getQuestionsAnswered(login));
        mv.addObject("questionsPercentages", questionService.getAnswerPercentage(login));
        mv.addObject("notifs", messageService.getNotifications(login));
        return mv;
    }
    
    private ModelAndView addErrorMessage(String errorMessage){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorMessage", errorMessage);
        return mv;
    }
}
