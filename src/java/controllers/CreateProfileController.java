/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.PersonneEntity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.PersonneService;

/**
 *
 * @author natha_000
 */
@Controller
public class CreateProfileController {
    
    @RequestMapping(value="createProfile", method = RequestMethod.GET)
    public String init(){
        return "index";
    }
    
    @RequestMapping(value="createProfile", method = RequestMethod.POST)
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session;
        ModelAndView mv;
        session = request.getSession(false);
        if (session.getAttribute("login") == null){ 
            mv = new ModelAndView("createProfile");
        }
        else{
            mv = new ModelAndView("error");
            String errorMessage = "Création de compte impossible. Vous êtes connecté";
            mv.addObject("errorMessage", errorMessage);
        }
        return mv; 
    }
}
