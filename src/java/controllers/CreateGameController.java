
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
 * Création de question
 * @author natha_000
 */
@Controller
public class CreateGameController {
    
    @Autowired    
    private PersonneService personneService;
        
    @RequestMapping(value="createGame", method = RequestMethod.GET)
    public String init(){
        return "createGame";
    }
    
    @RequestMapping(value="createGame", method = RequestMethod.POST)
    protected String handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session;
        
        session = request.getSession(false);
        List<String> invites;
        if ((ArrayList<PersonneEntity>)session.getAttribute("participants") == null){
            invites = new ArrayList<>();
        }
        else{
            invites = (ArrayList<String>)session.getAttribute("participants");
        }
        
        /**
         * On gère ici l'ajout de participant à la question
         * On vérifie que celui-ci existe et est dans la liste d'amis de l'utilisateur
         */
        if (request.getParameterMap().containsKey("loginParticipant")){
            if (personneService.getUserByLogin(request.getParameter("loginParticipant")) != null){
                ArrayList<String> amisLogin;
                amisLogin = personneService.getAmisLogin((String)session.getAttribute("login"));
                for (int i = 0; i < amisLogin.size(); i++){
                    if (amisLogin.get(i).equals(request.getParameter("loginParticipant")) && !invites.contains(request.getParameter("loginParticipant"))){
                        invites.add(amisLogin.get(i));
                    }
                }
                session.setAttribute("participants", invites);
            }
        }
              
        return "createGame";
    }
}
