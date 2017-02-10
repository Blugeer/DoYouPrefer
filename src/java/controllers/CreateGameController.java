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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.PersonneService;

/**
 *
 * @author natha_000
 */
@Controller
public class CreateGameController {
    
    @Autowired
    private PersonneService personneService ;
        
    @RequestMapping(value="createGame", method = RequestMethod.GET)
    public String init(){
        return "index";
    }
    
    @RequestMapping(value="createGame", method = RequestMethod.POST)
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        HttpSession session;
        session = request.getSession(true);
        ModelAndView mv = new ModelAndView("createGame");;
        //if (request.getParameterMap().containsKey("amis")){ 
            String amis = "zouz";
            //ArrayList<PersonneEntity> amis = personneService.getAmisLogin(login);
            //ArrayList<String> amisString = new ArrayList<>();
            /*for (int i = 0; i < amis.size(); i++){
                amisString.add(amis.get(i).getLogin());
            }*/
            mv.addObject("amis", amis);
            //mv.addObject("amis", amisString);
        //}
        return mv;
    }
    
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
                    System.out.println("Size : " + questions.size());
                    mv.addObject("amis", amis.get(i).getLogin());
                    mv.addObject("wallMessage", result);
                    mv.addObject("questions", questions);
                }
            }
        }
	return mv;
    }
}