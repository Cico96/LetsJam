package it.univaq.disim.mwt.letsjam.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;


public class LoginSuccessHandler 
    extends SavedRequestAwareAuthenticationSuccessHandler
    implements AuthenticationSuccessHandler{

        public LoginSuccessHandler() {
            super();
            setUseReferer(false);
        }
    
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                Authentication authentication) throws IOException, ServletException {

                super.onAuthenticationSuccess(request, response, authentication);
        }
}
