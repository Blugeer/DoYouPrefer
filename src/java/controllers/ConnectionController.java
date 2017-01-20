/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author natha_000
 */
@Controller
public class ConnectionController {
    
    @RequestMapping(value="index", method = RequestMethod.GET)
    public String initIndex(){
        return "index";
    }
    
    @RequestMapping(value="index", method = RequestMethod.POST)
    protected ModelAndView handle(HttpServletRequest request,HttpServletResponse response) 
    throws Exception 
    {
        HttpSession session;
        session = request.getSession(false);
        if (session.getAttribute("login") != null){
            session.invalidate();
        }
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}
