/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.PersonneEntity;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.PersonneService;

/**
 * @author natha_000
 */

@Controller
public class WallController {
    
    @Autowired
    private PersonneService personneService ;
    
    @RequestMapping(value="wall", method = RequestMethod.GET)
    public String initConnect(){
	return "index";
    }
     
    @RequestMapping(value="wall", method = RequestMethod.POST)
    protected ModelAndView handle(HttpServletRequest request,HttpServletResponse response) 
    throws Exception 
    {
        HttpSession session;
        ModelAndView mv;
        String login, nom, prenom, mdp, mail;
        List<String> messages;
        
        if (request.getParameterMap().containsKey("login")){ 
            login = request.getParameter("login");
            nom = request.getParameter("nom");
            prenom = request.getParameter("prenom");
            mdp = request.getParameter("mdp");
            mail = request.getParameter("mail");
            
            if (login != null && login.length() > 0 && mdp != null && mdp.length() > 0){
                if (nom != null && prenom != null && mail != null && nom.length() > 0 && prenom.length() > 0 && mail.length() > 0){        
                    PersonneEntity p = new PersonneEntity(login, nom, prenom, mdp, mail);
                    if (!(personneService.addPersonne(p))){
                       mv = addErrorMessage("Erreur : cet utilisateur existe déjà"); 
                       return mv;
                    }
                }
                else if (nom != null && prenom != null && mail != null && (nom.length() == 0 || prenom.length() == 0 || mail.length() == 0)){
                    mv = addErrorMessage("Erreur lors de l'inscription ou de la connexion");
                    return mv;
                }
                else if(!personneService.connectionUser(login, mdp)){
                    mv = addErrorMessage("Identifiants non valides");
                    return mv;
                }
                mv = new ModelAndView("wall");
                session = request.getSession(true);
                session.setAttribute("login", request.getParameter("login"));
            }
            else{
               mv = addErrorMessage("Erreur lors de l'inscription ou de la connexion");
               return mv;
            }
        }
        else{
            session = request.getSession(false);
            if (request.getParameterMap().containsKey("loginAmi")){
                if(!personneService.addAmi(session.getAttribute("login").toString(), request.getParameter("loginAmi"))){
                    mv = addErrorMessage("Erreur lors de l'ajout d'ami");
                    return mv;
                }
            }
            if (session.getAttribute("login") != null){
                mv = new ModelAndView("wall");
                login = (String)session.getAttribute("login");
                mdp = (String)session.getAttribute("mdp");
                // Verify if the session attribute exists
                if (session.getAttribute("messages") == null){
                    session.setAttribute("messages", new ArrayList<>());
                }
                messages = (ArrayList<String>)session.getAttribute("messages");
                if(request.getParameterMap().containsKey("choix1") && request.getParameterMap().containsKey("choix2")){
                    String messageFinal;
                    String choix1 = request.getParameter("choix1");
                    String choix2 = request.getParameter("choix2");
                    messageFinal = "Tu préfères : " + choix1 + " ou " + choix2 + " ?";
                    messages.add(messageFinal);
                }
                session.setAttribute("messages", messages); 
            }
            else{
                mv = addErrorMessage("Erreur lors de l'inscription ou de la connexion");
                return mv;
            }
        }
        
        String result = "Bienvenue sur ton mur " + login;
        messages = (ArrayList<String>)session.getAttribute("messages");
        
        mv.addObject("wallMessage", result);
        mv.addObject("messages", messages);
        return mv;
    }
    
    private ModelAndView addErrorMessage(String errorMessage){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorMessage", errorMessage);
        return mv;
    }
}
