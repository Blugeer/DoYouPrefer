/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.MurEntity;
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
    
    @RequestMapping(value="wall", method = RequestMethod.GET)
    public ModelAndView initConnect(@RequestParam(value = "user") String user, HttpServletRequest request){
        /* Quand on visite la page d'un ami */
        HttpSession session;
        session = request.getSession(false);
        List<QuestionEntity> questions;
        ModelAndView mv = new ModelAndView("wall");
        if (session.getAttribute("login") != null){
            String login = session.getAttribute("login").toString();
            List<PersonneEntity> amis = personneService.getAmisLogin(login);
            for (int i = 0; i < amis.size(); i++){
                if (amis.get(i).getLogin().equals(user)){
                    String result = "Bienvenue sur le mur de " + user;
                    questions = personneService.getQuestionsLogin(user);
                    mv.addObject("user", amis.get(i).getLogin());
                    mv.addObject("wallMessage", result);
                    mv.addObject("questions", questions);
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
        List<QuestionEntity> questions;
        
        
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
                
                if(request.getParameterMap().containsKey("answer1") || request.getParameterMap().containsKey("answer2")){
                    if (request.getParameterMap().containsKey("answer1") && request.getParameterMap().containsKey("question")){
                        int index = Integer.parseInt(request.getParameter("question"));
                        ReponseEntity r = new ReponseEntity(login, personneService.getUserByLogin(login), personneService.getQuestionsLogin(login).get(index));
                        r.setChoix(personneService.getQuestionsLogin(login).get(index).getChoix1());
                        questionService.addReponse(r);
                    }
                    if (request.getParameterMap().containsKey("answer2") && request.getParameterMap().containsKey("question")){
                        int index = Integer.parseInt(request.getParameter("question"));
                        ReponseEntity r = new ReponseEntity(login, personneService.getUserByLogin(login), personneService.getQuestionsLogin(login).get(index));
                        r.setChoix(personneService.getQuestionsLogin(login).get(index).getChoix2());
                        questionService.addReponse(r);
                    }
                }    
                
                questions = personneService.getQuestionsLogin(login);
                session.setAttribute("questions", questions);
                if(request.getParameterMap().containsKey("choix1") && request.getParameterMap().containsKey("choix2")){
                    String choix1 = request.getParameter("choix1");
                    String choix2 = request.getParameter("choix2");
                    ArrayList<MurEntity> murs = new ArrayList<>();
                    murs.add(personneService.getUserByLogin(login).getMur());
                    QuestionEntity q = new QuestionEntity(choix1, choix2, murs);
                    if(!questionService.addQuestion(q)){
                        mv = addErrorMessage("Erreur lors de l'ajout de question");
                        return mv;
                    }
                    questions.add(q);
                    session.setAttribute("questions", questions);
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
        mv.addObject("questions", questions);
        return mv;
    }
    
    private ModelAndView addErrorMessage(String errorMessage){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorMessage", errorMessage);
        return mv;
    }
}
