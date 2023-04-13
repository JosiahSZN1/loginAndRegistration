package com.codingdojo.authentication.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.authentication.models.LoginUser;
import com.codingdojo.authentication.models.User;
import com.codingdojo.authentication.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepo;
	
	public List<User> getAll() {
		return userRepo.findAll();
	}
    
	public User getOne(Long id) {
		return userRepo.findById(id).orElse(null);
	}
	
	public User create(User u, BindingResult result) { 
//		do the passwords match?
		if(!u.getPassword().equals(u.getConfirm())) {
//			do something
			result.rejectValue("confirm", null);
		}
//		does the user already exist?
		return userRepo.save(u);
	}
    // TO-DO: Write register and login methods!
    public User register(User newUser, BindingResult result) {
        // TO-DO: Additional validations!
// TO-DO - Reject values or register if no errors:
        
        // Reject if email is taken (present in database)
    	Optional<User>potentialUser = userRepo.findByEmail(newUser.getEmail());
    	if(potentialUser.isPresent()) {
        	result.rejectValue("email", "Matches", "Email already taken.");
        }
        // Reject if password doesn't match confirmation
    	String passwordEntered = newUser.getPassword();
    	if(!passwordEntered.equals(newUser.getConfirm())) {
    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    	}

        
        // Return null if result has errors
    	if(result.hasErrors()) {
    	    // Exit the method and go back to the controller 
    	    // to handle the response
    	    return null;
    	}

        // Hash and set password, save user to database
    	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashed);
    	return userRepo.save(newUser);
       
    }
    public User login(LoginUser newLoginObject, BindingResult result) {
        // TO-DO: Additional validations!
    	// TO-DO - Reject values:
        
    	// Find user in the DB by email
    	Optional<User>potentialUser = userRepo.findByEmail(newLoginObject.getEmail());

        // Reject if NOT present
        if(!potentialUser.isPresent()) {
        	result.rejectValue("email", "Matches", "Email does not match any reocrds");
        	return null;
        }
        // Reject if BCrypt password match fails
        
        User foundUser = potentialUser.get();
        
 
        if(!BCrypt.checkpw(newLoginObject.getPassword(), foundUser.getPassword())) {
        	result.rejectValue("password", "Matches", "Incorrect password");
        }
        // Return null if result has errors
        if(result.hasErrors()) {
        	return null;
        }
        // Otherwise, return the user object
        return foundUser;
    }
    
}
