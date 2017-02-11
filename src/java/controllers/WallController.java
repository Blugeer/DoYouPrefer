/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.PersonneEntity;
import dao.QuestionEntity;
import dao.MessageEntity;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.PersonneService;
import services.QuestionService;
import services.MessageService;

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
    private MessageService messageService ;
    
    @RequestMapping(value="wall", method = RequestMethod.GET)
    public ModelAndView initConnect(@RequestParam(value = "user") String user, HttpServletRequest request){
        HttpSession session;
        session = request.getSession(false);
        ModelAndView mv = new ModelAndView("wall");
        if (session.getAttribute("login") != null){
            String login = session.getAttribute("login").toString();
            List<PersonneEntity> amis = personneService.getAmisLogin(login);
            for (int i = 0; i < amis.size(); i++){
                if (amis.get(i).getLogin().equals(user)){
                    String result = "Bienvenue sur le mur de " + user;
                    ArrayList<String> questions = personneService.getQuestionsLogin(user);
                    ArrayList<QuestionEntity> questionsEntity = personneService.getQuestionsEntityLogin(user);
                    ArrayList<ArrayList<String>> message = new ArrayList<>();
                    for (int j = 0; j < questionsEntity.size(); j++){
                        message.add(questionService.getMessages(questionsEntity.get(j).getChoix1(), questionsEntity.get(j).getChoix2()));
                    }
                    System.out.println("Size : " + questions.size());
                    mv.addObject("amis", amis.get(i).getLogin());
                    mv.addObject("wallMessage", result);
                    mv.addObject("questions", questions);
                    mv.addObject("message", message);
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
        List<PersonneEntity> amis;
        List<String> amisString;
        List<String> messages;
        List<String> questions;
        String message = "";
        
        
        // Si le paramètre login existe dans la requêtre POST
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
                    mv = addErrorMessage("Erreur lors de l'inscription ou de la connexion");
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
                amis = personneService.getAmisLogin(login);
                amisString = new ArrayList<>();
                for (int i = 0; i < amis.size(); i++){
                    amisString.add(amis.get(i).getLogin());
                }
                session.setAttribute("amis", amisString);
                questions = personneService.getQuestionsLogin(login);
                session.setAttribute("questions", questions);
            }
            // Cas où le login et/ou le mdp sont mal renseignés lors d'une inscription/connexion 
            else{
               mv = addErrorMessage("Erreur lors de l'inscription ou de la connexion");
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
                amis = personneService.getAmisLogin(login);
                amisString = new ArrayList<>();
                for (int i = 0; i < amis.size(); i++){
                    amisString.add(amis.get(i).getLogin());
                }
                session.setAttribute("amis", amisString);
                
                questions = personneService.getQuestionsLogin(login);
                session.setAttribute("questions", questions);
                if(request.getParameterMap().containsKey("choix1") && request.getParameterMap().containsKey("choix2")){
                    String messageFinal;
                    String choix1 = request.getParameter("choix1");
                    String choix2 = request.getParameter("choix2");
                    messageFinal = "Tu préfères : " + choix1 + " ou " + choix2 + " ?";
                    QuestionEntity q = new QuestionEntity(choix1, choix2, personneService.getUserByLogin(login));
                    if(!questionService.addQuestion(q)){
                        mv = addErrorMessage("Erreur lors de l'ajout d'ami");
                        return mv;
                    }
                    questions.add(messageFinal); 
                    session.setAttribute("questions", questions);
                }
                
                if(request.getParameterMap().containsKey("message")){
                    message = request.getParameter("message");
                    questions = personneService.getQuestionsLogin(login);
                    for (int i = 0; i < questions.size(); i++){
                        amisString.add(amis.get(i).getLogin());
                    }
                    MessageEntity m = new MessageEntity(message, personneService.getUserByLogin(login), personneService.getQuestionsEntityLogin(login).get(0));
                    if(!messageService.addMessage(m)){
                        mv = addErrorMessage("Erreur lors de l'ajout de commentaire");
                        return mv;
                    }
                    session.setAttribute("message", message);
                }
            }
            // Sinon message d'erreur
            else{
                mv = addErrorMessage("Erreur lors de l'inscription ou de la connexion");
                return mv;
            }
        }
        
        String result = "Bienvenue sur ton mur " + login;
        messages = (ArrayList<String>)session.getAttribute("messages");
        
        mv.addObject("wallMessage", result);
        mv.addObject("user", login);
        mv.addObject("amis", amisString);
        mv.addObject("questions", questions);
        mv.addObject("messages", messages);
        mv.addObject("message", message);
        mv.addObject("login", login);
        return mv;
    }
    
    private ModelAndView addErrorMessage(String errorMessage){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorMessage", errorMessage);
        return mv;
    }
}
