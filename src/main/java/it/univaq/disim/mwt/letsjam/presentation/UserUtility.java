package it.univaq.disim.mwt.letsjam.presentation;

import it.univaq.disim.mwt.letsjam.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import it.univaq.disim.mwt.letsjam.security.CustomUserDetails;

public class UserUtility {

	
	public static User getUtente() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
			return ((CustomUserDetails) usernamePasswordAuthenticationToken.getPrincipal()).getUser();
		} else {
			throw new RuntimeException();
		}
	}
	
}
