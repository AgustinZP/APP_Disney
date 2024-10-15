package com.AccesoDatos.component;

import org.springframework.stereotype.Component;

@Component("validations")
public class Validations {
	
	public boolean validadorTelefono(String telefono) {
		if(telefono.length() == 9 && telefono.matches("\\d+")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean validadorPassword(String password) {
		
		if(password.length() >= 8){
			boolean hasUppercase = false;
	        boolean hasLowercase = false;
	        boolean hasSpecialChar = false;
	        
	        for (int i = 0; i < password.length(); i++) {
	            char c = password.charAt(i);
	            if (Character.isUpperCase(c)) {
	                hasUppercase = true;
	            } else if (Character.isLowerCase(c)) {
	                hasLowercase = true;
	            } else if (c >= 33 && c <= 126) {
	                hasSpecialChar = true;
	            }
	        }

	        return hasUppercase && hasLowercase && hasSpecialChar;
	    }
	    return false;
	}

}
