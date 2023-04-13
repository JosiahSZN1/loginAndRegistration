package com.codingdojo.authentication.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.authentication.models.LoginUser;
import com.codingdojo.authentication.models.User;
import com.codingdojo.authentication.services.UserService;

@Controller
public class HomeController {
    
    // Add once service is implemented:
    @Autowired
     private UserService userServ; 
    
    @GetMapping("/")
    public String rIndex(@ModelAttribute("newUser") User user, @ModelAttribute("newLogin") LoginUser loginUser) {
    
        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        return "index.jsp";
    }
    
    @GetMapping("/home")
    public String rSuccess(Model model, HttpSession session) {
    	Long userId = (Long) session.getAttribute("id");
    	if(userId==null) {
    		return "redirect:/";
    	}
    	User user = userServ.getOne(userId);
    	model.addAttribute("user", user);
    	return "success.jsp";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
   	 session.setAttribute("id", null);
   	 return "redirect:/";
    }
    
    @PostMapping("/register")
    public String pRegister(
    		@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, HttpSession session, Model model
            ) {
        userServ.register(newUser, result);
        // TO-DO Later -- call a register method in the service 
        // to do some extra validations and create a new user!
        
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
        	model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        
        session.setAttribute("id", newUser.getId());
        
        return "redirect:/home";
    }
    
    @PostMapping("/login")
    public String pLogin(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
        // Add once service is implemented:
         User user = userServ.login(newLogin, result);
    
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
    
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session,
        session.setAttribute("id", user.getId());
        // in other words, log them in.
       
        return "redirect:/home";
    }
    
}
